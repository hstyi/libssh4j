package com.github.hstyi.libssh2j;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class LIBSSH2_USERAUTH_KBDINT_PROMPT extends Structure {

    public Pointer text;         // const char*
    public NativeLong length;    // size_t or unsigned long
    public char echo;            // char, actually treated as boolean (0 or 1)

    public LIBSSH2_USERAUTH_KBDINT_PROMPT() {}

    public LIBSSH2_USERAUTH_KBDINT_PROMPT(Pointer pointer) {
        super(pointer);
        read(); // read fields from pointer
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("text", "length", "echo");
    }

    public static class ByReference extends LIBSSH2_USERAUTH_KBDINT_PROMPT implements Structure.ByReference {}
}
