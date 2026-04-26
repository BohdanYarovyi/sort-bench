package app.model;

import app.util.SortAlgorithm;
import app.util.SortingCollection;
import app.util.SortingCollection.Action;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class SortingContext {
    private final List<Integer> initialData;

    private final List<Bar> bars;

    public SortingContext(int maxValue) {
        List<Integer> numbers = IntStream.rangeClosed(1, maxValue)
                .boxed()
                .collect(Collectors.toList());
        this.initialData = Collections.unmodifiableList(numbers);
        this.bars = toBars(initialData);
    }

    private List<Bar> toBars(List<Integer> initialData) {
        return initialData.stream()
                .map(Bar::new)
                .collect(Collectors.toList());
    }

    public void shuffle() {
        Collections.shuffle(bars);
    }

    // todo: move this method to other class
    public Queue<Action> sort(SortAlgorithm sortAlgorithm) {
        List<Integer> integers = bars.stream()
                .map(Bar::getValue)
                .collect(Collectors.toList());
        SortingCollection sortingCollection = new SortingCollection(integers);
        sortAlgorithm.sort(sortingCollection);

        return sortingCollection.getActions();
    }

}
