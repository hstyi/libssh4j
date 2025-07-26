package com.github.hstyi.libssh2j;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.trilead.ssh2.crypto.Base64;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static com.github.hstyi.libssh2j.libssh2.LIBSSH2_ERROR_NONE;
import static com.github.hstyi.libssh2j.libssh2.LIBSSH2_ERROR_SFTP_PROTOCOL;
import static com.github.hstyi.libssh2j.libssh2.LIBSSH2_FXF_CREAT;
import static com.github.hstyi.libssh2j.libssh2.LIBSSH2_FXF_READ;
import static com.github.hstyi.libssh2j.libssh2.LIBSSH2_FXF_TRUNC;
import static com.github.hstyi.libssh2j.libssh2.LIBSSH2_FXF_WRITE;
import static com.github.hstyi.libssh2j.libssh2.LIBSSH2_HOSTKEY_HASH_SHA256;
import static com.github.hstyi.libssh2j.libssh2.LIBSSH2_SFTP_S_ISDIR;
import static com.github.hstyi.libssh2j.libssh2.libssh2_exit;
import static com.github.hstyi.libssh2j.libssh2.libssh2_hostkey_hash;
import static com.github.hstyi.libssh2j.libssh2.libssh2_init;
import static com.github.hstyi.libssh2j.libssh2.libssh2_session_disconnect;
import static com.github.hstyi.libssh2j.libssh2.libssh2_session_free;
import static com.github.hstyi.libssh2j.libssh2.libssh2_session_handshake;
import static com.github.hstyi.libssh2j.libssh2.libssh2_session_init;
import static com.github.hstyi.libssh2j.libssh2.libssh2_sftp_close_handle;
import static com.github.hstyi.libssh2j.libssh2.libssh2_sftp_init;
import static com.github.hstyi.libssh2j.libssh2.libssh2_sftp_mkdir;
import static com.github.hstyi.libssh2j.libssh2.libssh2_sftp_open;
import static com.github.hstyi.libssh2j.libssh2.libssh2_sftp_opendir;
import static com.github.hstyi.libssh2j.libssh2.libssh2_sftp_read;
import static com.github.hstyi.libssh2j.libssh2.libssh2_sftp_readdir;
import static com.github.hstyi.libssh2j.libssh2.libssh2_sftp_realpath;
import static com.github.hstyi.libssh2j.libssh2.libssh2_sftp_rmdir;
import static com.github.hstyi.libssh2j.libssh2.libssh2_sftp_shutdown;
import static com.github.hstyi.libssh2j.libssh2.libssh2_sftp_stat;
import static com.github.hstyi.libssh2j.libssh2.libssh2_sftp_unlink;
import static com.github.hstyi.libssh2j.libssh2.libssh2_sftp_write;
import static com.github.hstyi.libssh2j.libssh2.libssh2_userauth_authenticated;
import static com.github.hstyi.libssh2j.libssh2.libssh2_userauth_password;
import static com.github.hstyi.libssh2j.libssh2.libssh2_userauth_publickey_fromfile;
import static com.github.hstyi.libssh2j.libssh2.libssh2_userauth_publickey_frommemory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers(disabledWithoutDocker = true)
class libssh2Test {

    private static final String ED25519_256_PUB = "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIFU3mtV10iUK9JPROLkeeUbc1te6OtdXf4c34VNDQJXG";
    private static final String ED25519_256_PRI = "-----BEGIN OPENSSH PRIVATE KEY-----\n" +
            "b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\n" +
            "QyNTUxOQAAACBVN5rVddIlCvST0Ti5HnlG3NbXujrXV3+HN+FTQ0CVxgAAAIg1ZlUzNWZV\n" +
            "MwAAAAtzc2gtZWQyNTUxOQAAACBVN5rVddIlCvST0Ti5HnlG3NbXujrXV3+HN+FTQ0CVxg\n" +
            "AAAEBZhxJSo64f5zfuDjzVGp0+rIEDjHk+5ywvVEjowlFPIlU3mtV10iUK9JPROLkeeUbc\n" +
            "1te6OtdXf4c34VNDQJXGAAAAAAECAwQF\n" +
            "-----END OPENSSH PRIVATE KEY-----\n";

    @SuppressWarnings("rawtypes")
    @Container
    private final GenericContainer sshd = new GenericContainer("linuxserver/openssh-server")
            .withEnv("PUID", "1000")
            .withEnv("PGID", "1000")
            .withEnv("TZ", "Etc/UTC")
            .withEnv("SUDO_ACCESS", "true")
            .withEnv("PASSWORD_ACCESS", "true")
            .withEnv("USER_NAME", "foo")
            .withEnv("USER_PASSWORD", "pass")
            .withEnv("SUDO_ACCESS", "true")
            .withEnv("PUBLIC_KEY", ED25519_256_PUB)
            .withExposedPorts(2222);

    @BeforeAll
    public static void beforeAll() {
        assertEquals(0, libssh2_init(0));
        assertEquals("1.11.1_DEV", libssh2.libssh2_version(LIBSSH2_ERROR_NONE));
    }

    @AfterAll
    public static void afterAll() {
        libssh2_exit();
    }

    @Test
    void test_username_password() throws Exception {
        final libssh2_socket_t sock = createSocket("127.0.0.1", sshd.getMappedPort(2222));
        final LIBSSH2_SESSION session = libssh2_session_init();
        assertNotNull(session);

        assertEquals(LIBSSH2_ERROR_NONE, libssh2_session_handshake(session, sock));
        final byte[] bytes = libssh2_hostkey_hash(session, LIBSSH2_HOSTKEY_HASH_SHA256);
        assertNotNull(bytes);
        System.out.println(new String(Base64.encode(bytes)));

        assertEquals(LIBSSH2_ERROR_NONE, libssh2_userauth_authenticated(session));
        assertEquals(LIBSSH2_ERROR_NONE, libssh2_userauth_password(session, "foo", "pass"));
        assertEquals(1, libssh2_userauth_authenticated(session));
        assertEquals(LIBSSH2_ERROR_NONE, libssh2_session_disconnect(session, "Bye"));

        assertEquals(LIBSSH2_ERROR_NONE, libssh2_session_free(session));
        closeSocket(sock);
    }

    @Test
    void test_username_public_fromfile() throws Exception {
        final libssh2_socket_t sock = createSocket("127.0.0.1", sshd.getMappedPort(2222));
        final LIBSSH2_SESSION session = libssh2_session_init();
        assertNotNull(session);

        final File pub = new File("target", "ED25519_256_PUB");
        pub.deleteOnExit();
        try (FileOutputStream fos = new FileOutputStream(pub)) {
            IOUtils.write(ED25519_256_PUB, fos, StandardCharsets.UTF_8);
        }
        final File pri = new File("target", "ED25519_256_PRI");
        pri.deleteOnExit();
        try (FileOutputStream fos = new FileOutputStream(pri)) {
            IOUtils.write(ED25519_256_PRI, fos, StandardCharsets.UTF_8);
        }

        assertEquals(LIBSSH2_ERROR_NONE, libssh2_session_handshake(session, sock));
        assertEquals(LIBSSH2_ERROR_NONE, libssh2_userauth_authenticated(session));

        assertEquals(LIBSSH2_ERROR_NONE, libssh2_userauth_publickey_fromfile(session, "foo", pub.getAbsolutePath().getBytes(StandardCharsets.UTF_8),
                pri.getAbsolutePath().getBytes(StandardCharsets.UTF_8), null));
        assertEquals(1, libssh2_userauth_authenticated(session));

        assertEquals(LIBSSH2_ERROR_NONE, libssh2_session_disconnect(session, "Bye"));

        assertEquals(LIBSSH2_ERROR_NONE, libssh2_session_free(session));
        closeSocket(sock);
    }

    @Test
    void test_username_public_frommemory() throws Exception {
        final libssh2_socket_t sock = createSocket("127.0.0.1", sshd.getMappedPort(2222));
        final LIBSSH2_SESSION session = libssh2_session_init();
        assertNotNull(session);

        assertEquals(LIBSSH2_ERROR_NONE, libssh2_session_handshake(session, sock));
        assertEquals(LIBSSH2_ERROR_NONE, libssh2_userauth_authenticated(session));

        final byte[] pub = ED25519_256_PUB.getBytes(StandardCharsets.UTF_8);
        final byte[] pri = ED25519_256_PRI.getBytes(StandardCharsets.UTF_8);

        assertEquals(LIBSSH2_ERROR_NONE, libssh2_userauth_publickey_frommemory(session, "foo".getBytes(StandardCharsets.UTF_8), 3,
                pub, pub.length, pri, pri.length, null));

        assertEquals(1, libssh2_userauth_authenticated(session));

        assertEquals(LIBSSH2_ERROR_NONE, libssh2_session_disconnect(session, "Bye"));

        assertEquals(LIBSSH2_ERROR_NONE, libssh2_session_free(session));
        closeSocket(sock);
    }

    @Test
    void test_sftp() throws Exception {
        final libssh2_socket_t sock = createSocket("127.0.0.1", sshd.getMappedPort(2222));
        final LIBSSH2_SESSION session = libssh2_session_init();
        assertNotNull(session);

        assertEquals(LIBSSH2_ERROR_NONE, libssh2_session_handshake(session, sock));
        assertEquals(LIBSSH2_ERROR_NONE, libssh2_userauth_password(session, "foo", "pass"));

        final LIBSSH2_SFTP sftp = libssh2_sftp_init(session);
        assertNotNull(sftp);
        final byte[] buffer = new byte[1024];
        int len = libssh2_sftp_realpath(sftp, ".", buffer, buffer.length);
        assertEquals("/config", new String(buffer, 0, len));


        // mkdir
        final String filepath = "/config/test";
        assertEquals(LIBSSH2_ERROR_NONE, libssh2_sftp_mkdir(sftp, filepath, 493));

        LIBSSH2_SFTP_ATTRIBUTES attrs = new LIBSSH2_SFTP_ATTRIBUTES();
        assertFalse(LIBSSH2_SFTP_S_ISDIR(attrs.permissions.longValue()));
        assertEquals(LIBSSH2_ERROR_NONE, libssh2_sftp_stat(sftp, filepath, attrs));
        assertTrue(LIBSSH2_SFTP_S_ISDIR(attrs.permissions.longValue()));

        // ls
        LIBSSH2_SFTP_HANDLE handle = libssh2_sftp_opendir(sftp, "/");
        assertNotNull(handle);
        while (true) {
            attrs = new LIBSSH2_SFTP_ATTRIBUTES();
            len = libssh2_sftp_readdir(handle, buffer, buffer.length, attrs);
            if (len <= LIBSSH2_ERROR_NONE) break;
            System.out.println(new String(buffer, 0, len));
            System.out.println(attrs.atime.longValue());
        }
        assertEquals(LIBSSH2_ERROR_NONE, libssh2_sftp_close_handle(handle));

        // write
        final String filename = filepath + "/test.txt";
        final String content = UUID.randomUUID().toString();
        handle = libssh2_sftp_open(sftp, filename, LIBSSH2_FXF_WRITE | LIBSSH2_FXF_CREAT | LIBSSH2_FXF_TRUNC, 493);
        assertNotNull(handle);
        assertEquals(content.length(), libssh2_sftp_write(handle, content.getBytes(StandardCharsets.UTF_8), content.length()));
        libssh2_sftp_close_handle(handle);

        // read
        handle = libssh2_sftp_open(sftp, filename, LIBSSH2_FXF_READ, 0L);
        assertNotNull(handle);
        len = libssh2_sftp_read(handle, buffer, buffer.length);
        assertEquals(content, new String(buffer, 0, len));
        libssh2_sftp_close_handle(handle);

        // rmdir
        assertEquals(LIBSSH2_ERROR_SFTP_PROTOCOL, libssh2_sftp_rmdir(sftp, filepath));

        // delete
        assertEquals(LIBSSH2_ERROR_NONE, libssh2_sftp_unlink(sftp, filename));

        // rmdir
        assertEquals(LIBSSH2_ERROR_NONE, libssh2_sftp_rmdir(sftp, filepath));


        assertEquals(LIBSSH2_ERROR_NONE, libssh2_sftp_shutdown(sftp));

        assertEquals(LIBSSH2_ERROR_NONE, libssh2_session_disconnect(session, "Bye"));
        assertEquals(LIBSSH2_ERROR_NONE, libssh2_session_free(session));

        closeSocket(sock);
    }

    public static libssh2_socket_t createSocket(String host, int port) throws Exception {
        final libssh2_socket_t sock = libssh2_ext.create_libssh2_socket_t(host, port);
        assertNotNull(sock);
        return sock;
    }

    public static void closeSocket(libssh2_socket_t sock) {
        assertEquals(LIBSSH2_ERROR_NONE, libssh2.LIBSSH2_SOCKET_CLOSE(sock));
    }
}