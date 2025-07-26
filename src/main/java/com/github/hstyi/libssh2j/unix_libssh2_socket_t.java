package com.github.hstyi.libssh2j;

class unix_libssh2_socket_t implements libssh2_socket_t {
    final int fd;

    unix_libssh2_socket_t(int fd) {
        this.fd = fd;
    }
}
