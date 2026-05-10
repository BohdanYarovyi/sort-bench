package com.maybyes.sortbench.app.util.algorithm.loader;

import com.maybyes.sortbench.abstraction.SortAlgorithm;
import com.maybyes.sortbench.app.model.AlgorithmListItem;
import com.maybyes.sortbench.app.util.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Load all classes that implements {@link SortAlgorithm} from {@link #BUILTIN_IMPLEMENTATIONS package}
 * These classes were implemented by developer. Default classes have special {@link AlgorithmListItem#BUILTIN_ALGORITHM_MARKER prefix} in name that displays in list pane.
 */
public class BuiltinAlgorithmLoader {
    private static final Path BUILTIN_IMPLEMENTATIONS = Path.of("com/maybyes/sortbench/app/util/algorithm/impl");

    public List<AlgorithmListItem> load() {
        return loadFromPackage().stream()
                .map(AlgorithmListItem::new)
                .peek(AlgorithmListItem::markAsBuiltin)
                .toList();
    }

    private Set<SortAlgorithm> loadFromPackage() {
        try (InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(BuiltinAlgorithmLoader.BUILTIN_IMPLEMENTATIONS.toString());) {
            assert is != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            return bufferedReader.lines()
                    .filter(filename -> filename.endsWith(".class"))
                    .map(BuiltinAlgorithmLoader.BUILTIN_IMPLEMENTATIONS::resolve)
                    .map(this::createClass)
                    .filter(clazz -> FileUtil.isClassInstanceOfParent(clazz, SortAlgorithm.class))
                    .map(clazz -> {
                        try {
                            return (SortAlgorithm) clazz.getDeclaredConstructor().newInstance();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Class<?> createClass(Path path) {
        try {
            String name = path.toString().replace(".class", "").replace(File.separatorChar, '.');
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
