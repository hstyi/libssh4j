package com.github.hstyi.libssh2j;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

interface CLib extends Library {


    int AF_INET = 2;
    int SOCK_STREAM = 1;
    int AF_INET6 = 10;

    CLib INSTANCE = Native.load(Platform.C_LIBRARY_NAME, CLib.class);

    int socket(int domain, int type, int protocol);

    int connect(int sockfd, Structure addr, int addrlen);

    int close(int fd);

    class sockaddr_in extends Structure {
        public short sin_family;
        public short sin_port;
        public byte[] sin_addr = new byte[4];
        public byte[] sin_zero = new byte[8]; // Padding

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("sin_family", "sin_port", "sin_addr", "sin_zero");
        }
    }

    class sockaddr_in6 extends Structure {
        public short sin6_family;     // AF_INET6
        public short sin6_port;       // htons(port)
        public int sin6_flowinfo;     // usually 0
        public byte[] sin6_addr = new byte[16]; // 128-bit IPv6 address
        public int sin6_scope_id;     // usually 0 for global addresses

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("sin6_family", "sin6_port", "sin6_flowinfo", "sin6_addr", "sin6_scope_id");
        }
    }


}
