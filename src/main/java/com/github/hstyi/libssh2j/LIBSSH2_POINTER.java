package com.github.hstyi.libssh2j;

import com.sun.jna.Pointer;

class LIBSSH2_POINTER implements LIBSSH2_AGENT, LIBSSH2_SFTP, LIBSSH2_CHANNEL, LIBSSH2_SFTP_HANDLE, LIBSSH2_SESSION {
    final Pointer pointer;

    LIBSSH2_POINTER(Pointer pointer) {
        this.pointer = pointer;
    }


    @Override
    public int hashCode() {
        return pointer.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof LIBSSH2_POINTER))
            return false;
        LIBSSH2_POINTER other = (LIBSSH2_POINTER) obj;
        return other.pointer.equals(this.pointer);
    }
}
