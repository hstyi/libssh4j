package com.github.hstyi.libssh2j;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

interface Ws2_32 extends Library {
    Ws2_32 INSTANCE = Native.load("ws2_32", Ws2_32.class);

    int AF_INET = 2;
    int SOCK_STREAM = 1;
    int IPPROTO_TCP = 6;
    int INVALID_SOCKET = -1;
    int SOCKET_ERROR = -1;

    int WSAStartup(short wVersionRequested, WSAData lpWSAData);

    int WSACleanup();

    int WSAGetLastError();

    int socket(int af, int type, int protocol);

    int connect(int s, SockaddrIn name, int namelen);

    int closesocket(int s);

    int inet_addr(String cp);

    short htons(short hostshort);


    class WSAData extends Structure {
        public short wVersion;
        public short wHighVersion;
        public byte[] szDescription = new byte[257];
        public byte[] szSystemStatus = new byte[129];
        public short iMaxSockets;
        public short iMaxUdpDg;
        public Pointer lpVendorInfo;

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("wVersion", "wHighVersion", "szDescription",
                    "szSystemStatus", "iMaxSockets", "iMaxUdpDg", "lpVendorInfo");
        }
    }

    class SockaddrIn extends Structure {
        public short sin_family;
        public short sin_port;
        public int sin_addr;
        public byte[] sin_zero = new byte[8];

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("sin_family", "sin_port", "sin_addr", "sin_zero");
        }
    }
}