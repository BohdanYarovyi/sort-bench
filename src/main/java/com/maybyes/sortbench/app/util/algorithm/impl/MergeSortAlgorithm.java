package com.maybyes.sortbench.app.util.algorithm.impl;

import com.maybyes.sortbench.abstraction.SortAlgorithm;
import com.maybyes.sortbench.abstraction.SortingCollection;

public class MergeSortAlgorithm extends SortAlgorithm {
    private String name = "Merge Sort";

    @Override
    public void sort(SortingCollection collection) {
        mergeSort(collection, 0, collection.getSize() - 1);
    }

    private void mergeSort(SortingCollection collection, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(collection, low, mid);
            mergeSort(collection, mid + 1, high);
            merge(collection, low, mid, high);
        }
    }

    private void merge(SortingCollection collection, int low, int mid, int high) {
        int leftSize = mid - low + 1;
        int rightSize = high - mid;

        int[] left = new int[leftSize];
        int[] right = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            left[i] = collection.peek(low + i);
        }
        for (int i = 0; i < rightSize; i++) {
            right[i] = collection.peek(mid + 1 + i);
        }

        int i = 0, j = 0, k = low;
        while (i < leftSize && j < rightSize) {
            if (left[i] <= right[j]) {
                collection.set(k++, left[i++]);
            } else {
                collection.set(k++, right[j++]);
            }
        }

        while (i < leftSize) collection.set(k++, left[i++]);
        while (j < rightSize) collection.set(k++, right[j++]);
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