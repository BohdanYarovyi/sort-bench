package app.util.impl;

import app.util.SortAlgorithm;
import app.util.SortingCollection;

public class BubbleSortAlgorithm extends SortAlgorithm {

    @Override
    public void sort(SortingCollection collection) {
        int size = collection.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                Integer element1 = collection.peek(j);
                Integer element2 = collection.peek(j + 1);

                if (element1 > element2) {
                    collection.swap(j, j + 1);
                }
            }
        }
    }

}
