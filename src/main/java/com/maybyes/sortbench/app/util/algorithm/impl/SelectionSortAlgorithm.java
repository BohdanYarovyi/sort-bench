package com.maybyes.sortbench.app.util.algorithm.impl;

import com.maybyes.sortbench.abstraction.SortAlgorithm;
import com.maybyes.sortbench.abstraction.SortingCollection;

public class SelectionSortAlgorithm extends SortAlgorithm {
    private String name = "Selection Sort";

    @Override
    public void sort(SortingCollection collection) {
        int size = collection.getSize();
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (collection.compare(j, minIndex) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                collection.swap(i, minIndex);
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}