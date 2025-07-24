package com.github.hstyi.libssh2j;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;

public class libssh2 {


    /* Global API */
    public static final int LIBSSH2_INIT_NO_CRYPTO = 0x0001;

    /**
     * This is the numeric version of the libssh2 version number, meant for easier
     * parsing and comparisons by programs. The LIBSSH2_VERSION_NUM define will
     * always follow this syntax:
     * <p>
     * 0xXXYYZZ
     * <p>
     * Where XX, YY and ZZ are the main version, release and patch numbers in
     * hexadecimal (using 8 bits each). All three numbers are always represented
     * using two digits.  1.2 would appear as "0x010200" while version 9.11.7
     * appears as "0x090b07".
     * <p>
     * This 6-digit (24 bits) hexadecimal number does not show pre-release number,
     * and it is always a greater number in a more recent release. It makes
     * comparisons with greater than and less than work.
     */
    public static final int LIBSSH2_VERSION_NUM = 0x010b01;

    /**
     * Error Codes (defined by libssh2)
     */
    public static final int LIBSSH2_ERROR_NONE = 0;
    public static final int LIBSSH2_ERROR_BANNER_RECV = -2;
    public static final int LIBSSH2_ERROR_BANNER_SEND = -3;
    public static final int LIBSSH2_ERROR_INVALID_MAC = -4;
    public static final int LIBSSH2_ERROR_KEX_FAILURE = -5;
    public static final int LIBSSH2_ERROR_ALLOC = -6;
    public static final int LIBSSH2_ERROR_SOCKET_SEND = -7;
    public static final int LIBSSH2_ERROR_KEY_EXCHANGE_FAILURE = -8;
    public static final int LIBSSH2_ERROR_TIMEOUT = -9;
    public static final int LIBSSH2_ERROR_HOSTKEY_INIT = -10;
    public static final int LIBSSH2_ERROR_HOSTKEY_SIGN = -11;
    public static final int LIBSSH2_ERROR_DECRYPT = -12;
    public static final int LIBSSH2_ERROR_SOCKET_DISCONNECT = -13;
    public static final int LIBSSH2_ERROR_PROTO = -14;
    public static final int LIBSSH2_ERROR_PASSWORD_EXPIRED = -15;
    public static final int LIBSSH2_ERROR_FILE = -16;
    public static final int LIBSSH2_ERROR_METHOD_NONE = -17;
    public static final int LIBSSH2_ERROR_AUTHENTICATION_FAILED = -18;
    public static final int LIBSSH2_ERROR_PUBLICKEY_UNRECOGNIZED = LIBSSH2_ERROR_AUTHENTICATION_FAILED;
    public static final int LIBSSH2_ERROR_PUBLICKEY_UNVERIFIED = -19;
    public static final int LIBSSH2_ERROR_CHANNEL_OUTOFORDER = -20;
    public static final int LIBSSH2_ERROR_CHANNEL_FAILURE = -21;
    public static final int LIBSSH2_ERROR_CHANNEL_REQUEST_DENIED = -22;
    public static final int LIBSSH2_ERROR_CHANNEL_UNKNOWN = -23;
    public static final int LIBSSH2_ERROR_CHANNEL_WINDOW_EXCEEDED = -24;
    public static final int LIBSSH2_ERROR_CHANNEL_PACKET_EXCEEDED = -25;
    public static final int LIBSSH2_ERROR_CHANNEL_CLOSED = -26;
    public static final int LIBSSH2_ERROR_CHANNEL_EOF_SENT = -27;
    public static final int LIBSSH2_ERROR_SCP_PROTOCOL = -28;
    public static final int LIBSSH2_ERROR_ZLIB = -29;
    public static final int LIBSSH2_ERROR_SOCKET_TIMEOUT = -30;
    public static final int LIBSSH2_ERROR_SFTP_PROTOCOL = -31;
    public static final int LIBSSH2_ERROR_REQUEST_DENIED = -32;
    public static final int LIBSSH2_ERROR_METHOD_NOT_SUPPORTED = -33;
    public static final int LIBSSH2_ERROR_INVAL = -34;
    public static final int LIBSSH2_ERROR_INVALID_POLL_TYPE = -35;
    public static final int LIBSSH2_ERROR_PUBLICKEY_PROTOCOL = -36;
    public static final int LIBSSH2_ERROR_EAGAIN = -37;
    public static final int LIBSSH2_ERROR_BUFFER_TOO_SMALL = -38;
    public static final int LIBSSH2_ERROR_BAD_USE = -39;
    public static final int LIBSSH2_ERROR_COMPRESS = -40;
    public static final int LIBSSH2_ERROR_OUT_OF_BOUNDARY = -41;
    public static final int LIBSSH2_ERROR_AGENT_PROTOCOL = -42;
    public static final int LIBSSH2_ERROR_SOCKET_RECV = -43;
    public static final int LIBSSH2_ERROR_ENCRYPT = -44;
    public static final int LIBSSH2_ERROR_BAD_SOCKET = -45;
    public static final int LIBSSH2_ERROR_KNOWN_HOSTS = -46;
    public static final int LIBSSH2_ERROR_CHANNEL_WINDOW_FULL = -47;
    public static final int LIBSSH2_ERROR_KEYFILE_AUTH_FAILED = -48;
    public static final int LIBSSH2_ERROR_RANDGEN = -49;
    public static final int LIBSSH2_ERROR_MISSING_USERAUTH_BANNER = -50;
    public static final int LIBSSH2_ERROR_ALGO_UNSUPPORTED = -51;
    public static final int LIBSSH2_ERROR_MAC_FAILURE = -52;
    public static final int LIBSSH2_ERROR_HASH_INIT = -53;
    public static final int LIBSSH2_ERROR_HASH_CALC = -54;

    /* Channel API */
    public static final int LIBSSH2_CHANNEL_WINDOW_DEFAULT = (2 * 1024 * 1024);
    public static final int LIBSSH2_CHANNEL_PACKET_DEFAULT = 32768;
    public static final int LIBSSH2_CHANNEL_MINADJUST = 1024;


    public static final int SSH_EXTENDED_DATA_STDERR = 1;

    /* Disconnect Codes (defined by SSH protocol) */
    public static final int SSH_DISCONNECT_HOST_NOT_ALLOWED_TO_CONNECT = 1;
    public static final int SSH_DISCONNECT_PROTOCOL_ERROR = 2;
    public static final int SSH_DISCONNECT_KEY_EXCHANGE_FAILED = 3;
    public static final int SSH_DISCONNECT_RESERVED = 4;
    public static final int SSH_DISCONNECT_MAC_ERROR = 5;
    public static final int SSH_DISCONNECT_COMPRESSION_ERROR = 6;
    public static final int SSH_DISCONNECT_SERVICE_NOT_AVAILABLE = 7;
    public static final int SSH_DISCONNECT_PROTOCOL_VERSION_NOT_SUPPORTED = 8;
    public static final int SSH_DISCONNECT_HOST_KEY_NOT_VERIFIABLE = 9;
    public static final int SSH_DISCONNECT_CONNECTION_LOST = 10;
    public static final int SSH_DISCONNECT_BY_APPLICATION = 11;
    public static final int SSH_DISCONNECT_TOO_MANY_CONNECTIONS = 12;
    public static final int SSH_DISCONNECT_AUTH_CANCELLED_BY_USER = 13;
    public static final int SSH_DISCONNECT_NO_MORE_AUTH_METHODS_AVAILABLE = 14;
    public static final int SSH_DISCONNECT_ILLEGAL_USER_NAME = 15;

    /* Defaults for pty requests */
    public static final int LIBSSH2_TERM_WIDTH = 80;
    public static final int LIBSSH2_TERM_HEIGHT = 24;
    public static final int LIBSSH2_TERM_WIDTH_PX = 0;
    public static final int LIBSSH2_TERM_HEIGHT_PX = 0;

    /* SFTP File Transfer Flags -- (e.g. flags parameter to sftp_open())
     * Danger will robinson... APPEND doesn't have any effect on OpenSSH servers */
    public static final long LIBSSH2_FXF_READ = 0x00000001;
    public static final long LIBSSH2_FXF_WRITE = 0x00000002;
    public static final long LIBSSH2_FXF_APPEND = 0x00000004;
    public static final long LIBSSH2_FXF_CREAT = 0x00000008;
    public static final long LIBSSH2_FXF_TRUNC = 0x00000010;
    public static final long LIBSSH2_FXF_EXCL = 0x00000020;


    /* Hash Types */
    public static final int LIBSSH2_HOSTKEY_HASH_MD5 = 1;
    public static final int LIBSSH2_HOSTKEY_HASH_SHA1 = 2;
    public static final int LIBSSH2_HOSTKEY_HASH_SHA256 = 3;

    /*
     * Reproduce the POSIX file modes here for systems that are not POSIX
     * compliant.
     *
     * These is used in "permissions" of "struct _LIBSSH2_SFTP_ATTRIBUTES"
     */
    /* File type */
    public static final long LIBSSH2_SFTP_S_IFMT = 61440L;     /* type of file mask */
    public static final long LIBSSH2_SFTP_S_IFIFO = 4096L;     /* named pipe (fifo) */
    public static final long LIBSSH2_SFTP_S_IFCHR = 8192L;     /* character special */
    public static final long LIBSSH2_SFTP_S_IFDIR = 16384L;     /* directory */
    public static final long LIBSSH2_SFTP_S_IFBLK = 24576L;     /* block special */
    public static final long LIBSSH2_SFTP_S_IFREG = 32768L;     /* regular */
    public static final long LIBSSH2_SFTP_S_IFLNK = 40960L;     /* symbolic link */
    public static final long LIBSSH2_SFTP_S_IFSOCK = 49152L;     /* socket */


    /* Flags for stat_ex() */
    public static final int LIBSSH2_SFTP_STAT = 0;
    public static final int LIBSSH2_SFTP_LSTAT = 1;
    public static final int LIBSSH2_SFTP_SETSTAT = 2;


    /* Flags for open_ex() */
    public static final int LIBSSH2_SFTP_OPENFILE = 0;
    public static final int LIBSSH2_SFTP_OPENDIR = 1;


    /* Flags for rename_ex() */
    public static final long LIBSSH2_SFTP_RENAME_OVERWRITE = 0x00000001;
    public static final long LIBSSH2_SFTP_RENAME_ATOMIC = 0x00000002;
    public static final long LIBSSH2_SFTP_RENAME_NATIVE = 0x00000004;


    /* Flags for symlink_ex() */
    public static final int LIBSSH2_SFTP_SYMLINK = 0;
    public static final int LIBSSH2_SFTP_READLINK = 1;
    public static final int LIBSSH2_SFTP_REALPATH = 2;

    public static final int LIBSSH2_TRACE_TRANS = (1 << 1);
    public static final int LIBSSH2_TRACE_KEX = (1 << 2);
    public static final int LIBSSH2_TRACE_AUTH = (1 << 3);
    public static final int LIBSSH2_TRACE_CONN = (1 << 4);
    public static final int LIBSSH2_TRACE_SCP = (1 << 5);
    public static final int LIBSSH2_TRACE_SFTP = (1 << 6);
    public static final int LIBSSH2_TRACE_ERROR = (1 << 7);
    public static final int LIBSSH2_TRACE_PUBLICKEY = (1 << 8);
    public static final int LIBSSH2_TRACE_SOCKET = (1 << 9);

    // @formatter:off
    /* macros to check for specific file types, added in 1.2.5 */
    public static boolean LIBSSH2_SFTP_S_ISLNK(long m) { return (m & LIBSSH2_SFTP_S_IFMT) == LIBSSH2_SFTP_S_IFLNK;}
    public static boolean LIBSSH2_SFTP_S_ISREG(long m) { return (m & LIBSSH2_SFTP_S_IFMT) == LIBSSH2_SFTP_S_IFREG;}
    public static boolean LIBSSH2_SFTP_S_ISDIR(long m) { return (m & LIBSSH2_SFTP_S_IFMT) == LIBSSH2_SFTP_S_IFDIR;}
    public static boolean LIBSSH2_SFTP_S_ISCHR(long m) { return (m & LIBSSH2_SFTP_S_IFMT) == LIBSSH2_SFTP_S_IFCHR;}
    public static boolean LIBSSH2_SFTP_S_ISBLK(long m) { return (m & LIBSSH2_SFTP_S_IFMT) == LIBSSH2_SFTP_S_IFBLK;}
    public static boolean LIBSSH2_SFTP_S_ISFIFO(long m) { return (m & LIBSSH2_SFTP_S_IFMT) == LIBSSH2_SFTP_S_IFIFO;}
    public static boolean LIBSSH2_SFTP_S_ISSOCK(long m) { return (m & LIBSSH2_SFTP_S_IFMT) == LIBSSH2_SFTP_S_IFSOCK;}
    // @formatter:on

    private static libssh2_library libssh2_library() {
        return libssh2_loader.getInstance();
    }

    public static int libssh2_init(int flags) {
        return libssh2_library().libssh2_init(flags);
    }

    public static void libssh2_exit() {
        libssh2_library().libssh2_exit();
    }

    @Nullable
    public static LIBSSH2_SESSION libssh2_session_init_ex(@Nullable Pointer alloc_func, @Nullable Pointer free_func, @Nullable Pointer realloc_func, @Nullable PointerByReference _abstract) {
        final Pointer pointer = libssh2_library().libssh2_session_init_ex(alloc_func, free_func, realloc_func, _abstract);
        if (pointer == null) return null;
        return new LIBSSH2_POINTER(pointer);
    }

    @Nullable
    public static LIBSSH2_SESSION libssh2_session_init() {
        final Pointer pointer = libssh2_library().libssh2_session_init_ex(null, null, null, null);
        if (pointer == null) return null;
        return new LIBSSH2_POINTER(pointer);
    }

    public static void libssh2_session_set_blocking(LIBSSH2_SESSION session, int blocking) {
        libssh2_library().libssh2_session_set_blocking(getPointer(session), blocking);
    }

    public static int libssh2_session_get_blocking(LIBSSH2_SESSION session) {
        return libssh2_library().libssh2_session_get_blocking(getPointer(session));
    }

    public static int libssh2_session_handshake(LIBSSH2_SESSION session, int sock) {
        return libssh2_library().libssh2_session_handshake(getPointer(session), sock);
    }

    public static byte @Nullable [] libssh2_hostkey_hash(LIBSSH2_SESSION session, int hash_type) {
        final Pointer pointer = libssh2_library().libssh2_hostkey_hash(getPointer(session), hash_type);
        if (pointer == null) return null;
        if (hash_type == LIBSSH2_HOSTKEY_HASH_MD5) {
            return pointer.getByteArray(0, 16);
        } else if (hash_type == LIBSSH2_HOSTKEY_HASH_SHA1) {
            return pointer.getByteArray(0, 20);
        } else if (hash_type == LIBSSH2_HOSTKEY_HASH_SHA256) {
            return pointer.getByteArray(0, 32);
        }
        throw new UnsupportedOperationException("Unsupported hash type: " + hash_type);
    }

    public static int libssh2_userauth_publickey_fromfile_ex(LIBSSH2_SESSION session, byte[] username, int ousername_len, byte[] publickey,
                                                             byte[] privatekey, byte[] passphrase) {
        return libssh2_library().libssh2_userauth_publickey_fromfile_ex(getPointer(session), username, ousername_len, publickey, privatekey, passphrase);
    }

    public static int libssh2_userauth_publickey_fromfile(LIBSSH2_SESSION session, String username, byte[] publickey,
                                                          byte[] privatekey, byte[] passphrase) {
        final byte[] bytes = username.getBytes(StandardCharsets.UTF_8);
        return libssh2_userauth_publickey_fromfile_ex(session, bytes, bytes.length, publickey, privatekey, passphrase);
    }

    public static int libssh2_userauth_publickey_frommemory(LIBSSH2_SESSION session, byte[] username, int username_len, byte[] publickeydata, int publickeydata_len, byte[] privatekeydata, int privatekeydata_len, byte[] passphrase) {
        return libssh2_library().libssh2_userauth_publickey_frommemory(getPointer(session), username, username_len, publickeydata, publickeydata_len, privatekeydata, privatekeydata_len, passphrase);
    }


    public static int libssh2_userauth_password_ex(
            LIBSSH2_SESSION session,
            byte[] username,
            int username_len,
            byte[] password,
            int password_len,
            @Nullable LIBSSH2_PASSWD_CHANGEREQ_FUNC passwdChangeCallback
    ) {
        return libssh2_library().libssh2_userauth_password_ex(getPointer(session), username, username_len, password, password_len, passwdChangeCallback);
    }

    public static int libssh2_userauth_password(LIBSSH2_SESSION session, String username, String password) {
        final byte[] bytes1 = username.getBytes();
        final byte[] bytes2 = password.getBytes();
        return libssh2_userauth_password_ex(session, bytes1, bytes1.length,
                bytes2, bytes2.length, null);
    }

    @Nullable
    public static LIBSSH2_CHANNEL libssh2_channel_open_ex(
            LIBSSH2_SESSION session,
            byte[] channel_type,
            int channel_type_len,
            int window_size,
            int packet_size,
            byte @Nullable [] message,
            int message_len
    ) {
        final Pointer pointer = libssh2_library().libssh2_channel_open_ex(getPointer(session), channel_type, channel_type_len, window_size, packet_size, message, message_len);
        if (pointer == null) return null;
        return new LIBSSH2_POINTER(pointer);
    }

    public static int libssh2_channel_process_startup(
            LIBSSH2_CHANNEL channel,
            byte[] request,
            int request_len,
            byte @Nullable [] message,
            int message_len
    ) {
        return libssh2_library().libssh2_channel_process_startup(getPointer(channel), request, request_len, message, message_len);
    }


    /**
     * This is a macro defined in a public libssh2 header file that is using the underlying function [libssh2_channel_process_startup].
     */
    public static int libssh2_channel_exec(LIBSSH2_CHANNEL channel, String command) {
        final byte[] execArray = "exec".getBytes(StandardCharsets.UTF_8);
        final byte[] commandArray = command.getBytes(StandardCharsets.UTF_8);
        return libssh2_channel_process_startup(channel, execArray, execArray.length, commandArray, commandArray.length);
    }

    /**
     * This is a macro defined in a public libssh2 header file that is using the underlying function [libssh2_channel_open_ex](https://libssh2.org//libssh2_channel_open_ex.html).
     */
    @Nullable
    public static LIBSSH2_CHANNEL libssh2_channel_open_session(LIBSSH2_SESSION session) {
        final byte[] array = "session".getBytes(StandardCharsets.UTF_8);
        return libssh2_channel_open_ex(
                session,
                array,
                array.length,
                LIBSSH2_CHANNEL_WINDOW_DEFAULT,
                LIBSSH2_CHANNEL_PACKET_DEFAULT,
                null,
                0
        );
    }

    public static int libssh2_channel_read_ex(
            LIBSSH2_CHANNEL channel,
            int stream_id,
            byte[] buf,
            int buflen
    ) {
        return libssh2_library().libssh2_channel_read_ex(getPointer(channel), stream_id, buf, buflen);
    }

    public static int libssh2_channel_close(LIBSSH2_CHANNEL channel) {
        return libssh2_library().libssh2_channel_close(getPointer(channel));
    }

    public static int libssh2_channel_free(LIBSSH2_CHANNEL channel) {
        return libssh2_library().libssh2_channel_free(getPointer(channel));
    }

    public static int libssh2_session_disconnect_ex(
            LIBSSH2_SESSION session,
            int reason,
            byte[] description,
            byte[] lang
    ) {
        return libssh2_library().libssh2_session_disconnect_ex(getPointer(session), reason, description, lang);
    }

    public static int libssh2_session_disconnect(
            LIBSSH2_SESSION session,
            String description
    ) {
        final byte[] bytes = description.getBytes(StandardCharsets.UTF_8);
        return libssh2_session_disconnect_ex(session, SSH_DISCONNECT_BY_APPLICATION, bytes, new byte[0]);
    }

    public static int libssh2_session_free(LIBSSH2_SESSION session) {
        return libssh2_library().libssh2_session_free(getPointer(session));
    }

    public static int libssh2_channel_request_pty_ex(
            LIBSSH2_CHANNEL channel,
            byte[] term,
            int term_len,
            byte @Nullable [] modes,
            int modes_len,
            int width,
            int height,
            int width_px,
            int height_px
    ) {
        return libssh2_library().libssh2_channel_request_pty_ex(getPointer(channel), term, term_len, modes, modes_len, width, height, width_px, height_px);
    }

    public static int libssh2_channel_write_ex(
            LIBSSH2_CHANNEL channel,
            int stream_id,
            byte[] buf,
            int buflen
    ) {
        return libssh2_library().libssh2_channel_write_ex(getPointer(channel), stream_id, buf, buflen);
    }

    @Nullable
    public static LIBSSH2_SFTP libssh2_sftp_init(LIBSSH2_SESSION session) {
        final Pointer pointer = libssh2_library().libssh2_sftp_init(getPointer(session));
        if (pointer == null) return null;
        return new LIBSSH2_POINTER(pointer);
    }

    public static int libssh2_sftp_fstat_ex(LIBSSH2_SFTP_HANDLE handle, LIBSSH2_SFTP_ATTRIBUTES attrs, int setstat) {
        return libssh2_library().libssh2_sftp_fstat_ex(getPointer(handle), attrs, setstat);
    }


    public static int libssh2_sftp_fstat(LIBSSH2_SFTP_HANDLE handle, LIBSSH2_SFTP_ATTRIBUTES attrs) {
        return libssh2_sftp_fstat_ex(handle, attrs, 0);
    }


    public static int libssh2_sftp_fsetstat(LIBSSH2_SFTP_HANDLE handle, LIBSSH2_SFTP_ATTRIBUTES attrs) {
        return libssh2_sftp_fstat_ex(handle, attrs, 1);
    }

    public static int libssh2_sftp_stat_ex(LIBSSH2_SFTP sftp,
                                           byte[] path,
                                           int path_len,
                                           int stat_type,
                                           LIBSSH2_SFTP_ATTRIBUTES attrs) {
        return libssh2_library().libssh2_sftp_stat_ex(getPointer(sftp), path, path_len, stat_type, attrs);
    }


    public static int libssh2_sftp_stat(LIBSSH2_SFTP sftp,
                                        String path,
                                        LIBSSH2_SFTP_ATTRIBUTES attrs) {
        final byte[] bytes = path.getBytes(StandardCharsets.UTF_8);
        return libssh2_sftp_stat_ex(sftp, bytes, bytes.length, LIBSSH2_SFTP_STAT, attrs);
    }

    public static int libssh2_sftp_lstat(LIBSSH2_SFTP sftp,
                                         String path,
                                         LIBSSH2_SFTP_ATTRIBUTES attrs) {
        final byte[] bytes = path.getBytes(StandardCharsets.UTF_8);
        return libssh2_sftp_stat_ex(sftp, bytes, bytes.length, LIBSSH2_SFTP_LSTAT, attrs);
    }

    public static int libssh2_sftp_setstat(LIBSSH2_SFTP sftp,
                                           String path,
                                           LIBSSH2_SFTP_ATTRIBUTES attrs) {
        final byte[] bytes = path.getBytes(StandardCharsets.UTF_8);
        return libssh2_sftp_stat_ex(sftp, bytes, bytes.length, LIBSSH2_SFTP_SETSTAT, attrs);
    }


    @Nullable
    public static LIBSSH2_SFTP_HANDLE libssh2_sftp_open_ex(
            LIBSSH2_SFTP sftp,
            byte[] filename,
            int filename_len,
            long flags,
            long mode,
            int open_type
    ) {
        final Pointer pointer = libssh2_library().libssh2_sftp_open_ex(getPointer(sftp), filename, filename_len, flags, mode, open_type);
        if (pointer == null) return null;
        return new LIBSSH2_POINTER(pointer);
    }

    public static int libssh2_sftp_readdir_ex(
            LIBSSH2_SFTP_HANDLE handle,
            byte[] buffer,
            int buffer_maxlen,
            byte @Nullable [] longentry,
            int longentry_maxlen,
            LIBSSH2_SFTP_ATTRIBUTES attrs
    ) {
        return libssh2_library().libssh2_sftp_readdir_ex(getPointer(handle), buffer, buffer_maxlen, longentry, longentry_maxlen, attrs);
    }

    public static int libssh2_sftp_close_handle(LIBSSH2_SFTP_HANDLE handle) {
        return libssh2_library().libssh2_sftp_close_handle(getPointer(handle));
    }


    /**
     * This is a macro defined in a public libssh2 header file that is using the underlying function [libssh2_sftp_close_handle].
     */
    public static int libssh2_sftp_closedir(LIBSSH2_SFTP_HANDLE handle) {
        return libssh2_sftp_close_handle(handle);
    }

    /**
     * This is a macro defined in a public libssh2 header file that is using the underlying function [libssh2_sftp_close_handle].
     */
    public static int libssh2_sftp_close(LIBSSH2_SFTP_HANDLE handle) {
        return libssh2_sftp_close_handle(handle);
    }


    public static int libssh2_sftp_shutdown(LIBSSH2_SFTP sftp) {
        return libssh2_library().libssh2_sftp_shutdown(getPointer(sftp));
    }

    public static int libssh2_sftp_symlink_ex(
            LIBSSH2_SFTP sftp,
            byte[] path,
            int path_len,
            byte[] target,
            int target_len,
            int link_type
    ) {
        return libssh2_library().libssh2_sftp_symlink_ex(getPointer(sftp), path, path_len, target, target_len, link_type);
    }

    /**
     * This is a macro defined in a public libssh2 header file that is using the underlying function [libssh2_sftp_symlink_ex].
     */
    public static int libssh2_sftp_realpath(LIBSSH2_SFTP sftp, String path, byte[] target, int maxlen) {
        final byte[] pathArray = path.getBytes(StandardCharsets.UTF_8);
        return libssh2_sftp_symlink_ex(sftp, pathArray, pathArray.length, target, maxlen, LIBSSH2_SFTP_REALPATH);
    }

    public static int libssh2_sftp_read(
            LIBSSH2_SFTP_HANDLE handle,
            byte[] buffer,
            int buffer_maxlen
    ) {
        return libssh2_library().libssh2_sftp_read(getPointer(handle), buffer, buffer_maxlen);
    }

    public static int libssh2_sftp_write(
            LIBSSH2_SFTP_HANDLE handle,
            byte[] buffer,
            int count
    ) {
        return libssh2_library().libssh2_sftp_write(getPointer(handle), buffer, count);
    }

    public static int libssh2_sftp_rename_ex(
            LIBSSH2_SFTP sftp,
            byte[] source_filename,
            int source_filename_len,
            byte[] dest_filename,
            int dest_filename_len,
            long flags
    ) {
        return libssh2_library().libssh2_sftp_rename_ex(getPointer(sftp), source_filename, source_filename_len, dest_filename, dest_filename_len, flags);
    }

    public static int libssh2_sftp_rmdir_ex(LIBSSH2_SFTP sftp, byte[] path, int path_len) {
        return libssh2_library().libssh2_sftp_rmdir_ex(getPointer(sftp), path, path_len);
    }

    public static int libssh2_sftp_rmdir(LIBSSH2_SFTP sftp, String path) {
        final byte[] bytes = path.getBytes(StandardCharsets.UTF_8);
        return libssh2_sftp_rmdir_ex(sftp, bytes, bytes.length);
    }

    public static int libssh2_sftp_mkdir_ex(LIBSSH2_SFTP sftp, byte[] path, int path_len, long mode) {
        return libssh2_library().libssh2_sftp_mkdir_ex(getPointer(sftp), path, path_len, mode);
    }

    /**
     * This is a macro defined in a public libssh2 header file that is using the underlying function libssh2_sftp_mkdir_ex.
     */
    public static int libssh2_sftp_mkdir(LIBSSH2_SFTP sftp, String path, long mode) {
        byte[] array = path.getBytes(StandardCharsets.UTF_8);
        return libssh2_sftp_mkdir_ex(sftp, array, array.length, mode);
    }


    /**
     * This is a macro defined in a public libssh2 header file that is using the underlying function [libssh2_sftp_open_ex].
     */
    @Nullable
    public static LIBSSH2_SFTP_HANDLE libssh2_sftp_opendir(LIBSSH2_SFTP sftp, String path) {
        final byte[] pathArray = path.getBytes(StandardCharsets.UTF_8);
        return libssh2_sftp_open_ex(sftp, pathArray, pathArray.length, 0, 0, LIBSSH2_SFTP_OPENDIR);
    }

    /**
     * This is a macro defined in a public libssh2 header file that is using the underlying function [libssh2_sftp_open_ex].
     */
    @Nullable
    public static LIBSSH2_SFTP_HANDLE libssh2_sftp_open(
            LIBSSH2_SFTP sftp,
            String filename,
            long flags,
            long mode
    ) {
        final byte[] bytes = filename.getBytes(StandardCharsets.UTF_8);
        return libssh2_sftp_open_ex(sftp, bytes, bytes.length, flags, mode, LIBSSH2_SFTP_OPENFILE);
    }

    /**
     * This is a macro defined in a public libssh2 header file that is using the underlying function [libssh2_sftp_readdir_ex].
     */
    public static int libssh2_sftp_readdir(
            LIBSSH2_SFTP_HANDLE handle,
            byte[] buffer,
            int buffer_maxlen,
            LIBSSH2_SFTP_ATTRIBUTES attrs
    ) {
        return libssh2_sftp_readdir_ex(handle, buffer, buffer_maxlen, null, 0, attrs);
    }

    public static int libssh2_sftp_unlink_ex(LIBSSH2_SFTP sftp, byte[] filename, int filename_len) {
        return libssh2_library().libssh2_sftp_unlink_ex(getPointer(sftp), filename, filename_len);
    }

    public static int libssh2_sftp_unlink(LIBSSH2_SFTP sftp, String filename) {
        final byte[] bytes = filename.getBytes(StandardCharsets.UTF_8);
        return libssh2_sftp_unlink_ex(sftp, bytes, bytes.length);
    }

    public static int libssh2_channel_setenv_ex(
            LIBSSH2_CHANNEL channel,
            byte[] varname,
            int varname_len,
            byte[] value,
            int value_len
    ) {
        return libssh2_library().libssh2_channel_setenv_ex(getPointer(channel), varname, varname_len, value, value_len);
    }

    public static int libssh2_channel_eof(LIBSSH2_CHANNEL channel) {
        return libssh2_library().libssh2_channel_eof(getPointer(channel));
    }

    @Nullable
    public static String libssh2_userauth_list(
            LIBSSH2_SESSION session,
            byte[] username,
            int username_len
    ) {
        return libssh2_library().libssh2_userauth_list(getPointer(session), username, username_len);
    }

    public static int libssh2_userauth_authenticated(LIBSSH2_SESSION session) {
        return libssh2_library().libssh2_userauth_authenticated(getPointer(session));
    }

    public static int libssh2_userauth_keyboard_interactive_ex(
            LIBSSH2_SESSION session,
            byte[] username,
            int username_len,
            LIBSSH2_USERAUTH_KBDINT_RESPONSE_FUNC callback
    ) {
        return libssh2_library().libssh2_userauth_keyboard_interactive_ex(getPointer(session), username, username_len, callback);
    }

    public static void libssh2_trace(LIBSSH2_SESSION session, int bitmask) {
        libssh2_library().libssh2_trace(getPointer(session), bitmask);
    }

    public static String libssh2_version(int required_version) {
        return libssh2_library().libssh2_version(required_version);
    }


    private static Pointer getPointer(Object object) {
        if (object instanceof LIBSSH2_POINTER) {
            return ((LIBSSH2_POINTER) object).pointer;
        }
        throw new IllegalArgumentException(object + " is not a LIBSSH2_POINTER");
    }
}
