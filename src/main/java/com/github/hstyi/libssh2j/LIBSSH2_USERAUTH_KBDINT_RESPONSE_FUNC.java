package com.github.hstyi.libssh2j;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface LIBSSH2_USERAUTH_KBDINT_RESPONSE_FUNC extends Callback {

    void invoke(
        Pointer name, int name_len,
        Pointer instruction, int instruction_len,
        int num_prompts,
        Pointer prompts,      // Pointer to array of JNA_LIBSSH2_USERAUTH_KBDINT_PROMPT
        Pointer responses,    // Pointer to array of JNA_LIBSSH2_USERAUTH_KBDINT_RESPONSE
        Pointer abstractContext // optional user data
    );
}
