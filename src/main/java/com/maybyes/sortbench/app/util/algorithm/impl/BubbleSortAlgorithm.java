package com.maybyes.sortbench.app.util.algorithm.impl;

import io.github.bohdanyarovyi.abstraction.SortAlgorithm;
import io.github.bohdanyarovyi.abstraction.SortingCollection;

public class BubbleSortAlgorithm extends SortAlgorithm {
    private String name = "Bubble Sort";

    @Override
    public void sort(SortingCollection collection) {
        int size = collection.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (collection.compare(j, j + 1) > 0) {
                    collection.swap(j, j + 1);
                }
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
