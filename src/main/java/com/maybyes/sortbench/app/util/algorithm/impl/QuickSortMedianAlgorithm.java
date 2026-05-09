package com.maybyes.sortbench.app.util.algorithm.impl;

import com.maybyes.sortbench.abstraction.SortAlgorithm;
import com.maybyes.sortbench.abstraction.SortingCollection;

public class QuickSortMedianAlgorithm extends SortAlgorithm {
    private String name = "Quick Sort Median of Three";

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
        medianOfThree(collection, low, high);
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

    private void medianOfThree(SortingCollection collection, int low, int high) {
        int mid = low + (high - low) / 2;

        if (collection.compare(low, mid) > 0) collection.swap(low, mid);
        if (collection.compare(low, high) > 0) collection.swap(low, high);
        if (collection.compare(mid, high) > 0) collection.swap(mid, high);

        collection.swap(mid, high);
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