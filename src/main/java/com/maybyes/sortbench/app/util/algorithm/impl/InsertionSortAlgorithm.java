package com.maybyes.sortbench.app.util.algorithm.impl;

import com.maybyes.sortbench.abstraction.SortAlgorithm;
import com.maybyes.sortbench.abstraction.SortingCollection;

public class InsertionSortAlgorithm extends SortAlgorithm {
    private String name = "Insertion Sort";

    @Override
    public void sort(SortingCollection collection) {
        int size = collection.getSize();
        for (int i = 1; i < size; i++) {
            int j = i;
            while (j > 0 && collection.compare(j - 1, j) > 0) {
                collection.swap(j - 1, j);
                j--;
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