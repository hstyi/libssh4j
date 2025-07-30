package com.github.hstyi.libssh2j;

import com.sun.jna.Native;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

class libssh2_loader {

    private static final String os = System.getProperty("os.name").toLowerCase();
    private static final String arch = System.getProperty("os.arch").toLowerCase();
    static final boolean isWin = os.contains("win");
    private static final boolean isMac = os.contains("mac");
    private static final boolean isUnix = os.contains("nix") || os.contains("nux");
    private static final boolean isArm64 = arch.contains("aarch64") || arch.contains("arm64");
    private static final boolean isX86 = arch.equals("x86") || arch.equals("i386") || arch.equals("i686");
    private static final boolean isX64 = arch.equals("x86_64") || arch.equals("amd64") || arch.equals("x64");

    private static volatile libssh2_library instance;

    static libssh2_library getInstance() {
        if (instance == null) {
            synchronized (libssh2_loader.class) {
                if (instance == null) {
                    final String libraryFile = getLibraryFile();
                    instance = Native.load(libraryFile, libssh2_library.class);
                }
            }
        }
        return instance;
    }

    private static String getLibraryFile() {

        final String lfile = System.getProperty("libssh4j.library.file");
        if (lfile != null && !lfile.trim().isEmpty()) {
            return lfile;
        }

        final String path = System.getProperty("libssh4j.library.path");
        if (path != null && !path.trim().isEmpty()) {
            return new File(path, getFilename()).getAbsolutePath();
        }

        final String filename = getFilename();
        try (InputStream resource = libssh2_loader.class.getResourceAsStream(filename)) {
            if (resource == null) {
                throw new IllegalStateException("Can't find library resource " + filename);
            }

            final Path dir = Files.createTempDirectory("libssh2j");
            final Path file = Paths.get(dir.toString(), filename);
            Files.createDirectories(file.getParent());

            try (FileOutputStream fos = new FileOutputStream(file.toFile())) {
                byte[] buffer = new byte[4096];
                int len;
                while ((len = resource.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            }

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    Files.walkFileTree(dir,
                            new SimpleFileVisitor<Path>() {
                                @Override
                                public @NotNull FileVisitResult postVisitDirectory(
                                        @NotNull Path dir, IOException exc) throws IOException {
                                    Files.delete(dir);
                                    return FileVisitResult.CONTINUE;
                                }

                                @Override
                                public @NotNull FileVisitResult visitFile(
                                        @NotNull Path file, @NotNull BasicFileAttributes attrs)
                                        throws IOException {
                                    Files.delete(file);
                                    return FileVisitResult.CONTINUE;
                                }
                            });
                } catch (Exception ignored) {

                }
            }));

            return file.toAbsolutePath().toString();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }


    private static String getFilename() {
        final StringBuilder sb = new StringBuilder();
        if (isMac) {
            sb.append("/darwin");
        } else if (isUnix) {
            sb.append("/linux");
        } else if (isWin) {
            sb.append("/win32");
        } else {
            throw new UnsupportedOperationException("Unsupported operating system: " + os);
        }

        if (isArm64) {
            sb.append("/aarch64");
        } else if (isX64) {
            sb.append("/x86-64");
        } else if (isX86) {
            sb.append("/x86");
        } else {
            throw new UnsupportedOperationException("Unsupported arch: " + arch);
        }

        if (isMac) {
            sb.append("/libssh2.dylib");
        } else if (isUnix) {
            sb.append("/libssh2.so");
        } else {
            sb.append("/libssh2.dll");
        }

        return sb.toString();
    }
}
