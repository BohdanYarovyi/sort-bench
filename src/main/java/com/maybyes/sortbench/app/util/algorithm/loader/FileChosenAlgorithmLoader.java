package com.maybyes.sortbench.app.util.algorithm.loader;

import com.maybyes.sortbench.abstraction.SortAlgorithm;
import com.maybyes.sortbench.app.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class FileChosenAlgorithmLoader {
    private final static String CLASS_EXTENSION = ".class";

    public List<SortAlgorithm> load(File[] files) {
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        }

        List<SortAlgorithm> result = new ArrayList<>();
        for (File file : files) {
            try {
                if (!file.getName().endsWith(CLASS_EXTENSION)) {
                    log.debug("Chosen file by user with name {} was ignored because the extension of file is not valid", file);
                    continue;
                }

                Class<?> clazz = FileUtil.createClass(file.toPath());
                if (!FileUtil.isSubClassOf(clazz, SortAlgorithm.class)) {
                    log.debug("Class with name {} isn't instance of {}, so it was ignored", clazz, SortAlgorithm.class);
                    continue;
                }

                SortAlgorithm sortAlgorithm = (SortAlgorithm) clazz.getDeclaredConstructor().newInstance();
                result.add(sortAlgorithm);
            } catch (ClassNotFoundException e) {
                log.warn("Not found class {}", file.getName());
            } catch (Exception e) {
                log.warn("Failed to load algorithm from file {}", file.getName());
            }
        }

        return result;
    }

}
