package com.github.hstyi.libssh2j;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class LIBSSH2_SFTP_ATTRIBUTES extends Structure {

    public static class ByReference extends LIBSSH2_SFTP_ATTRIBUTES implements Structure.ByReference {
    }

    /**
     * unsigned long
     */
    public NativeLong flags;

    /**
     * libssh2_uint64_t — typically unsigned long long
     */
    public long filesize;

    /**
     * unsigned long
     */
    public NativeLong uid;

    /**
     * unsigned long
     */
    public NativeLong gid;

    /**
     * unsigned long
     */
    public NativeLong permissions;

    /**
     * unsigned long
     */
    public NativeLong atime;

    /**
     * unsigned long
     */
    public NativeLong mtime;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("flags", "filesize", "uid", "gid", "permissions", "atime", "mtime");
    }
}
