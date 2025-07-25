package com.github.hstyi.libssh2j;

import org.junit.jupiter.api.Test;

import static com.github.hstyi.libssh2j.libssh2.LIBSSH2_ERROR_NONE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class libssh2_versionTest {
    @Test
    void test() {
        assertEquals(LIBSSH2_ERROR_NONE, libssh2.libssh2_init(0));
        assertEquals("1.11.1_DEV", libssh2.libssh2_version(1));
    }
}
