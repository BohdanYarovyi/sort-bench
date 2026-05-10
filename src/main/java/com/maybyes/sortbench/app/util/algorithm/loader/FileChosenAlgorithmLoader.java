package com.maybyes.sortbench.app.util.algorithm.loader;

import com.maybyes.sortbench.abstraction.SortAlgorithm;
import com.maybyes.sortbench.app.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileChosenAlgorithmLoader {
    private final static String FILE_EXTENSION = ".class";

    public List<SortAlgorithm> load(File[] files) {
        if (files == null || files.length == 0) {
            throw new RuntimeException("Sort algorithm files missing exception");
        }

        List<SortAlgorithm> result = new ArrayList<>();
        for (File file : files) {
            try {
                if (!file.getName().endsWith(FILE_EXTENSION)) {
                    continue;
                }

                SortAlgorithm sortAlgorithm = loadFromFile(file);
                result.add(sortAlgorithm);
            } catch (Exception e) {
                System.err.printf("Failed to load file with name '%s'%n", file.getName());
            }
        }

        return result;
    }

    private SortAlgorithm loadFromFile(File file) throws Exception {
        Class<SortAlgorithm> clazz = FileUtil.createClass(file.toPath(), SortAlgorithm.class);
        return clazz.getDeclaredConstructor().newInstance();
    }

}
