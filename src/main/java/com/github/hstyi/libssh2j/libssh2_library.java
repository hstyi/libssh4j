package com.github.hstyi.libssh2j;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.PointerByReference;
import org.jetbrains.annotations.Nullable;

/**
 * https://libssh2.org/docs.html
 */
interface libssh2_library extends Library {


    int libssh2_init(int flags);

    void libssh2_exit();

    @Nullable
    Pointer libssh2_session_init_ex(@Nullable Pointer alloc_func, @Nullable Pointer free_func, @Nullable Pointer realloc_func, @Nullable PointerByReference _abstract);

    void libssh2_session_set_blocking(Pointer session, int blocking);

    int libssh2_session_get_blocking(Pointer session);

    int libssh2_session_handshake(Pointer session, Pointer sock);

    Pointer libssh2_hostkey_hash(Pointer session, int hash_type);

    int libssh2_userauth_publickey_fromfile_ex(Pointer session, byte[] username, int ousername_len, byte[] publickey,
                                               byte[] privatekey, byte[] passphrase);

    int libssh2_userauth_publickey_frommemory(Pointer session, byte[] username, int username_len, byte[] publickeydata, int publickeydata_len, byte[] privatekeydata, int privatekeydata_len, byte[] passphrase);

    int libssh2_userauth_password_ex(
            Pointer session, byte[] username, int username_len,
            byte[] password, int password_len,
            @Nullable LIBSSH2_PASSWD_CHANGEREQ_FUNC passwdChangeCallback
    );

    Pointer libssh2_channel_open_ex(
            Pointer session, byte[] channel_type, int channel_type_len,
            int window_size, int packet_size,
            byte @Nullable [] message, int message_len
    );

    int libssh2_channel_process_startup(
            Pointer channel,
            byte[] request,
            int request_len,
            byte @Nullable [] message,
            int message_len
    );

    int libssh2_channel_read_ex(
            Pointer channel,
            int stream_id,
            byte[] buf,
            int buflen
    );

    int libssh2_channel_close(Pointer channel);

    int libssh2_channel_free(Pointer channel);

    int libssh2_session_disconnect_ex(
            Pointer session,
            int reason,
            byte[] description,
            byte[] lang
    );

    int libssh2_session_free(Pointer session);

    int libssh2_channel_request_pty_ex(
            Pointer channel,
            byte[] term, int term_len,
            byte @Nullable [] modes, int modes_len,
            int width, int height,
            int width_px, int height_px
    );

    int libssh2_channel_write_ex(
            Pointer channel,
            int stream_id,
            byte[] buf,
            int buflen
    );

    /* SFTP */
    @Nullable
    Pointer libssh2_sftp_init(Pointer session);

    @Nullable
    Pointer libssh2_sftp_open_ex(
            Pointer sftp,
            byte[] filename,
            int filename_len,
            long flags,
            long mode,
            int open_type
    );

    int libssh2_sftp_readdir_ex(
            Pointer handle,
            byte[] buffer,
            int buffer_maxlen,
            byte @Nullable [] longentry,
            int longentry_maxlen,
            LIBSSH2_SFTP_ATTRIBUTES attrs
    );

    int libssh2_sftp_close_handle(Pointer handle);

    int libssh2_sftp_shutdown(Pointer sftp);

    int libssh2_sftp_symlink_ex(
            Pointer sftp,
            byte[] path,
            int path_len,
            byte[] target,
            int target_len,
            int link_type
    );

    int libssh2_sftp_read(
            Pointer handle,
            byte[] buffer,
            int buffer_maxlen
    );

    int libssh2_sftp_write(
            Pointer handle,
            byte[] buffer,
            int count
    );

    int libssh2_sftp_rename_ex(
            Pointer sftp,
            byte[] source_filename,
            int source_filename_len,
            byte[] dest_filename,
            int dest_filename_len,
            long flags
    );

    int libssh2_sftp_rmdir_ex(
            Pointer sftp,
            byte[] path,
            int path_len
    );

    int libssh2_sftp_mkdir_ex(
            Pointer sftp,
            byte[] path,
            int path_len,
            long mode
    );

    int libssh2_sftp_fstat_ex(Pointer handle, LIBSSH2_SFTP_ATTRIBUTES attrs, int setstat);

    int libssh2_sftp_stat_ex(Pointer sftp,
                             byte[] path,
                             int path_len,
                             int stat_type,
                             LIBSSH2_SFTP_ATTRIBUTES attrs);


    int libssh2_sftp_unlink_ex(Pointer sftp, byte[] filename, int filename_len);

    int libssh2_channel_setenv_ex(
            Pointer channel,
            byte[] varname,
            int varname_len,
            byte[] value,
            int value_len
    );

    int libssh2_channel_eof(Pointer channel);

    @Nullable
    String libssh2_userauth_list(
            Pointer session,
            byte[] username,
            int username_len
    );

    int libssh2_userauth_authenticated(Pointer session);

    int libssh2_userauth_keyboard_interactive_ex(
            Pointer session,
            byte[] username,
            int username_len,
            LIBSSH2_USERAUTH_KBDINT_RESPONSE_FUNC callback
    );

    void libssh2_trace(Pointer session, int bitmask);

    int libssh2_channel_x11_req_ex(Pointer channel, int single_connection, byte[] auth_proto, byte[] auth_cookie, int screen_number);

    String libssh2_version(int required_version);

    int libssh2_channel_signal_ex(Pointer pointer, byte[] signame, int signameLen);

    @Nullable Pointer libssh2_agent_init(Pointer session);

    int libssh2_agent_set_identity_path(Pointer agent, byte[] path);

    int libssh2_agent_userauth(Pointer pointer, byte[] username, libssh2_agent_publickey identity);

    int libssh2_agent_list_identities(LIBSSH2_AGENT agent);

    void libssh2_agent_free(Pointer pointer);

    int libssh2_agent_connect(Pointer pointer);

    int libssh2_agent_disconnect(Pointer pointer);

    int libssh2_agent_get_identity(
            Pointer agent,
            PointerByReference store,
            @Nullable libssh2_agent_publickey prev
    );

    int libssh2_channel_send_eof(Pointer pointer);

    int libssh2_channel_request_pty_size_ex(Pointer channel, int width, int height, int widthPx, int heightPx);

    int libssh2_channel_flush_ex(Pointer pointer, int streamid);

    void libssh2_channel_set_blocking(Pointer pointer, int blocking);

    int libssh2_channel_get_exit_status(Pointer pointer);

    int libssh2_channel_wait_eof(Pointer pointer);

    int libssh2_channel_wait_closed(Pointer pointer);

    long libssh2_channel_window_read_ex(Pointer pointer, LongByReference readAvail, LongByReference windowSizeInitial);

    long libssh2_channel_window_write_ex(Pointer pointer, LongByReference windowSizeInitial);

    int libssh2_session_method_pref(Pointer pointer, int methodType, byte[] prefs);

    int libssh2_session_supported_algs(Pointer pointer, int methodType, PointerByReference algs);

    Pointer libssh2_session_methods(Pointer session, int methodType);

    int libssh2_session_flag(Pointer pointer, int flag, int value);

    int libssh2_keepalive_send(Pointer pointer, IntByReference secondsToNext);

    int libssh2_keepalive_config(Pointer pointer, int wantReply, int interval);

    Pointer libssh2_session_callback_set2(Pointer pointer, int cbtype, libssh2_cb_generic callback);

    int libssh2_session_startup(Pointer pointer, int sock);
}
