package com.maybyes.sortbench.app.util.algorithm.loader;

import io.github.bohdanyarovyi.abstraction.SortAlgorithm;
import com.maybyes.sortbench.app.ApplicationProperties;
import com.maybyes.sortbench.app.util.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;

public class BuiltinAlgorithmLoader {
    private final static String CLASS_EXTENSION = ".class";

    public List<SortAlgorithm> load() {
        return loadFromPackage();
    }

    private List<SortAlgorithm> loadFromPackage() {
        Path location = Path.of(ApplicationProperties.BUILTIN_ALGORITHM_LOCATION);
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(location.toString());

        if (is == null) {
            throw new RuntimeException("Builtin algorithm package not found: '%s'".formatted(location));
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return reader.lines()
                    .filter(filename -> filename.endsWith(CLASS_EXTENSION))
                    .map(location::resolve)
                    .map(this::createClass)
                    .filter(clazz -> FileUtil.isSubClassOf(clazz, SortAlgorithm.class))
                    .map(this::instantiate)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read builtin algorithm package '%s'".formatted(location), e);
        }
    }

    private Class<?> createClass(Path path) {
        try {
            String normalizedName = path.toString()
                    .replace(CLASS_EXTENSION, "")
                    .replace(File.separatorChar, '.');

            return Class.forName(normalizedName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Builtin algorithm class not found: '%s'".formatted(path), e);
        }
    }

    private SortAlgorithm instantiate(Class<?> clazz) {
        try {
            return (SortAlgorithm) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate algorithm class '%s'".formatted(clazz.getName()), e);
        }
    }

}