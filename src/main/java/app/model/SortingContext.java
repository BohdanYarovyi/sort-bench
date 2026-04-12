package app.model;

import app.util.SortAlgorithm;
import app.util.SortingCollection;
import app.util.SortingCollection.Action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortingContext {
    private final List<Integer> initialData;
    private List<Bar> bars;
    private boolean isSorted;

    public SortingContext(int maxValue) {
        this.initialData = IntStream.rangeClosed(1, maxValue)
                .boxed()
                .collect(Collectors.toList());

        this.bars = toBars(initialData);
    }

    public void shuffle() {
        Collections.shuffle(initialData);
        bars = toBars(initialData);
        isSorted = false;
    }

    private List<Bar> toBars(List<Integer> initialData) {
        return initialData.stream()
                .map(Bar::new)
                .collect(Collectors.toList());
    }

    public Queue<Action> sort(SortAlgorithm sortAlgorithm) {
        SortingCollection sortingCollection = new SortingCollection(new ArrayList<>(initialData));
        sortAlgorithm.sort(sortingCollection);
        isSorted = true;

        return sortingCollection.getActions();
    }

    public List<Integer> getInitialData() {
        return new ArrayList<>(initialData);
    }

    public List<Bar> getBars() {
        return bars;
    }

    public boolean isSorted() {
        return isSorted;
    }
}
