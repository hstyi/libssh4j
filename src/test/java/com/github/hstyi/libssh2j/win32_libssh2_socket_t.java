package com.github.hstyi.libssh2j;

import com.sun.jna.Pointer;

class win32_libssh2_socket_t implements libssh2_socket_t {
    final int fd;

    win32_libssh2_socket_t(int fd) {
        this.fd = fd;
    }

    @Override
    public Pointer getPointer() {
        return Pointer.createConstant(fd);
    }
}
