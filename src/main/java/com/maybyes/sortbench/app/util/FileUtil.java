package com.maybyes.sortbench.app.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static <T> Class<T> createClass(Path path, Class<T> clazz) {
        if (!path.isAbsolute()) {
            throw new RuntimeException("Given path is not absolute '%s'".formatted(path.toString()));
        }

        URL[] directories = toDirectories(path);
        try (URLClassLoader urlClassLoader = URLClassLoader.newInstance(directories)) {
            String filename = path.getFileName().toString().replace(".class", "");
            Class<?> aClass = urlClassLoader.loadClass(filename);

            return (Class<T>) aClass;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to create class based on the file '%s'".formatted(path), e);
        }
    }

    private static URL[] toDirectories(Path... paths) {
        List<URL> dirs = new ArrayList<>();
        for (Path path : paths) {
            try {
                URL dirUrl = path.getParent().toUri().toURL();
                dirs.add(dirUrl);
            } catch (MalformedURLException ex) {
                System.err.printf("Error: Failed to load file with path '%s'%n", path);
            }
        }

        return dirs.toArray(new URL[0]);
    }


    public static boolean isClassInstanceOfParent(Class<?> clazz, Class<?> parent) {
        String className = clazz.getSuperclass().getName();
        String parentName = parent.getName();

        return className.equals(parentName);
    }

}
