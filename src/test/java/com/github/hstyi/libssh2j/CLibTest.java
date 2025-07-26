package com.github.hstyi.libssh2j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import static com.github.hstyi.libssh2j.CLib.AF_INET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisabledOnOs({OS.WINDOWS})
class CLibTest {
    @Test
    void test() throws IOException {
        final ServerSocket serverSocket = new ServerSocket(0);
        final int fd = CLib.INSTANCE.socket(AF_INET, CLib.SOCK_STREAM, 0);
        assertNotEquals(0, fd);

        CLib.sockaddr_in addr = new CLib.sockaddr_in();
        addr.sin_family = AF_INET;
        addr.sin_port = Short.reverseBytes((short) serverSocket.getLocalPort());
        addr.sin_addr = InetAddress.getByName("127.0.0.1").getAddress();
        addr.sin_zero = new byte[8];

        final int connect = CLib.INSTANCE.connect(fd, addr, addr.size());
        assertEquals(0, connect);

        assertEquals(0, CLib.INSTANCE.close(fd));
    }
}