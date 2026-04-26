package app.util.impl;

import app.util.SortAlgorithm;
import app.util.SortingCollection;

public class BubbleSortAlgorithm implements SortAlgorithm {

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

}
