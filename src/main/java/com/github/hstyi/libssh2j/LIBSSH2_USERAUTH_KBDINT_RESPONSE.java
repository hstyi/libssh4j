package com.github.hstyi.libssh2j;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class LIBSSH2_USERAUTH_KBDINT_RESPONSE extends Structure {

    /**
     * response text pointer
     */
    public Pointer text;

    /**
     * length of the response
     */
    public NativeLong length;

    public LIBSSH2_USERAUTH_KBDINT_RESPONSE() {
    }

    public LIBSSH2_USERAUTH_KBDINT_RESPONSE(Pointer pointer) {
        super(pointer);
        read(); // initialize fields from native memory
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("text", "length");
    }

    public static class ByReference extends LIBSSH2_USERAUTH_KBDINT_RESPONSE implements Structure.ByReference {
    }
}
