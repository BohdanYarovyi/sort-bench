package com.maybyes.sortbench.app.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FileUtil {

    public static Class<?> createClass(Path path) throws ClassNotFoundException {
        if (!path.isAbsolute()) {
            throw new IllegalArgumentException("Given path is not absolute '%s'".formatted(path.toString()));
        }

        URL[] directories = toDirectories(path);
        try (URLClassLoader urlClassLoader = URLClassLoader.newInstance(directories)) {
            String filename = path.getFileName().toString()
                    .replace(".class", "");

            return urlClassLoader.loadClass(filename);
        } catch (IOException | ClassNotFoundException e) {
            throw new ClassNotFoundException("Failed to create class based on the file '%s'".formatted(path), e);
        }
    }

    private static URL[] toDirectories(Path... paths) {
        List<URL> dirs = new ArrayList<>();
        for (Path path : paths) {
            try {
                URL dirUrl = path.getParent().toUri().toURL();
                dirs.add(dirUrl);
            } catch (MalformedURLException ex) {
                log.warn("Failed to find directory {}", path);
            }
        }

        return dirs.toArray(new URL[0]);
    }


    public static boolean isSubClassOf(Class<?> clazz, Class<?> parent) {
        return parent.isAssignableFrom(clazz);
    }

}
