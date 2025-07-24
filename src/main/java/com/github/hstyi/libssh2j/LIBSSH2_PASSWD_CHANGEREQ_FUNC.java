package com.github.hstyi.libssh2j;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public interface LIBSSH2_PASSWD_CHANGEREQ_FUNC extends Callback {

    void invoke(
            Pointer session, PointerByReference newpw,
            IntByReference newpw_len, PointerByReference _abstract
    );
}
