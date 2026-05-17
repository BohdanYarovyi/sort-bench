package com.maybyes.sortbench.app.util.algorithm.impl;

import io.github.bohdanyarovyi.abstraction.SortAlgorithm;
import io.github.bohdanyarovyi.abstraction.SortingCollection;

public class QuickSortAlgorithm extends SortAlgorithm {
    private String name = "Quick Sort";

    @Override
    public void sort(SortingCollection collection) {
        quickSort(collection, 0, collection.getSize() - 1);
    }

    private void quickSort(SortingCollection collection, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(collection, low, high);
            quickSort(collection, low, pivotIndex - 1);
            quickSort(collection, pivotIndex + 1, high);
        }
    }

    private int partition(SortingCollection collection, int low, int high) {
        int pivotIndex = low;

        for (int i = low; i < high; i++) {
            if (collection.compare(i, high) < 0) {
                collection.swap(i, pivotIndex);
                pivotIndex++;
            }
        }

        collection.swap(pivotIndex, high);
        return pivotIndex;
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