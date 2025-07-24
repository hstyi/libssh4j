package com.github.hstyi.libssh2j;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class libssh2_agent_publickey extends Structure {
    public static class ByReference extends libssh2_agent_publickey implements Structure.ByReference {
    }

    public static class ByValue extends libssh2_agent_publickey implements Structure.ByValue {
    }

    public libssh2_agent_publickey() {

    }

    public libssh2_agent_publickey(Pointer p) {
        super(p);
    }

    public int magic;                // unsigned int
    public Pointer node;            // void*
    public Pointer blob;            // unsigned char*
    public NativeLong blob_len;     // size_t
    public Pointer comment;         // char*

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("magic", "node", "blob", "blob_len", "comment");
    }
}
