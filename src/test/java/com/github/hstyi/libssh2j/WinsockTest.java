package com.github.hstyi.libssh2j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@EnabledOnOs({OS.WINDOWS})
class WinsockTest {
    @Test
    void test() throws IOException {

        final ServerSocket serverSocket = new ServerSocket(0);

        final int socket = Ws2_32.INSTANCE.socket(Ws2_32.AF_INET, Ws2_32.SOCK_STREAM, Ws2_32.IPPROTO_TCP);
        assertNotEquals(Ws2_32.INVALID_SOCKET, socket);

        Ws2_32.SockaddrIn serverAddr = new Ws2_32.SockaddrIn();
        serverAddr.sin_family = Ws2_32.AF_INET;
        serverAddr.sin_port = Ws2_32.INSTANCE.htons((short) serverSocket.getLocalPort());
        serverAddr.sin_addr = Ws2_32.INSTANCE.inet_addr("127.0.0.1");

        int result = Ws2_32.INSTANCE.connect(socket, serverAddr, serverAddr.size());
        assertNotEquals(Ws2_32.SOCKET_ERROR, result);

        assertEquals(0, Ws2_32.INSTANCE.closesocket(socket));

    }
}