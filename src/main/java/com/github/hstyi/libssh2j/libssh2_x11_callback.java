package com.github.hstyi.libssh2j;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public interface libssh2_x11_callback extends libssh2_cb_generic {
    void invoke(Pointer session, Pointer channel, Pointer shost, int sport, PointerByReference _abstract);
}
