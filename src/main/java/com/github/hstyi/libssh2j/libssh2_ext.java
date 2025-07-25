package com.github.hstyi.libssh2j;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import java.util.ArrayList;

public class libssh2_ext {

    public static int libssh2_session_supported_algs(LIBSSH2_SESSION session, int method_type, ArrayList<String> algs) {
        final PointerByReference algsRef = new PointerByReference();
        final int rc = libssh2.libssh2_session_supported_algs(session, method_type, algsRef);

        if (rc > 0) {
            final Pointer algsPointer = algsRef.getValue();
            for (int i = 0; i < rc; i++) {
                final Pointer algPtr = algsPointer.getPointer((long) i * Native.POINTER_SIZE);
                algs.add(algPtr.getString(0));
            }
        }

        return rc;
    }
}
