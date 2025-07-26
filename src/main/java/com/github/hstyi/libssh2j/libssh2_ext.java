package com.github.hstyi.libssh2j;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import org.jetbrains.annotations.Nullable;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import static com.github.hstyi.libssh2j.CLib.AF_INET;
import static com.github.hstyi.libssh2j.CLib.AF_INET6;

public class libssh2_ext {

    public static int libssh2_session_supported_algs(LIBSSH2_SESSION session, int method_type, ArrayList<String> algs) {
        final PointerByReference algsRef = new PointerByReference();
        final int rc = libssh2.libssh2_session_supported_algs(session, method_type, algsRef);

        if (rc > 0) {
            final Pointer algsPointer = algsRef.getValue();
            for (int i = 0; i < rc; i++) {
                final Pointer algPtr = algsPointer.getPointer((long) i * Native.POINTER_SIZE);
                algs.add(algPtr.getString(0));
            }
        }

        return rc;
    }

    @Nullable
    public static libssh2_socket_t create_libssh2_socket_t(String host, int port) {
        final InetAddress addr;

        try {
            addr = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            return null;
        }

        if (libssh2_loader.isWin) {

            final int socket = Ws2_32.INSTANCE.socket(Ws2_32.AF_INET, Ws2_32.SOCK_STREAM, Ws2_32.IPPROTO_TCP);
            if (Ws2_32.INVALID_SOCKET == socket) {
                return null;
            }

            Ws2_32.SockaddrIn serverAddr = new Ws2_32.SockaddrIn();
            serverAddr.sin_family = Ws2_32.AF_INET;
            serverAddr.sin_port = Ws2_32.INSTANCE.htons((short) port);
            serverAddr.sin_addr = Ws2_32.INSTANCE.inet_addr("127.0.0.1");

            int result = Ws2_32.INSTANCE.connect(socket, serverAddr, serverAddr.size());
            if (result == Ws2_32.SOCKET_ERROR) {
                Ws2_32.INSTANCE.closesocket(socket);
            }

            return new win32_libssh2_socket_t(socket);
        } else {
            final int fd = CLib.INSTANCE.socket(AF_INET, CLib.SOCK_STREAM, 0);
            if (fd < 0) return null;

            if (addr instanceof Inet4Address) {
                CLib.sockaddr_in sockaddr = new CLib.sockaddr_in();
                sockaddr.sin_family = AF_INET;
                sockaddr.sin_port = Short.reverseBytes((short) port);
                sockaddr.sin_addr = addr.getAddress();
                sockaddr.sin_zero = new byte[8];
                final int connect = CLib.INSTANCE.connect(fd, sockaddr, sockaddr.size());
                if (connect != 0) {
                    CLib.INSTANCE.close(fd);
                    return null;
                }
            } else if (addr instanceof Inet6Address) {
                final CLib.sockaddr_in6 addr6 = new CLib.sockaddr_in6();
                addr6.sin6_family = AF_INET6;
                addr6.sin6_port = Short.reverseBytes((short) port);
                addr6.sin6_flowinfo = 0;
                addr6.sin6_addr = addr.getAddress();
                addr6.sin6_scope_id = 0;

                final int connect = CLib.INSTANCE.connect(fd, addr6, addr6.size());
                if (connect != 0) {
                    CLib.INSTANCE.close(fd);
                    return null;
                }
            }

            return new unix_libssh2_socket_t(fd);
        }
    }
}
