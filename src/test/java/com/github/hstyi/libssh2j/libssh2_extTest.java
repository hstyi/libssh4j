package com.github.hstyi.libssh2j;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class libssh2_extTest {
    @Test
    void test_create_libssh2_socket_t() throws Exception {
        final ServerSocket serverSocket = new ServerSocket(0);

        final Thread thread = new Thread(() -> {
            try {
                serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();

        final libssh2_socket_t sock = libssh2_ext.create_libssh2_socket_t("127.0.0.1", serverSocket.getLocalPort());
        assertNotNull(sock);
        assertEquals(libssh2.LIBSSH2_ERROR_NONE, libssh2.LIBSSH2_SOCKET_CLOSE(sock));

        thread.join();
    }
}