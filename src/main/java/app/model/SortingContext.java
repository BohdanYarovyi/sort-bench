package app.model;

import lombok.Getter;

import java.util.Collections;
import java.util.List;
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

    public List<Integer> getBarsValues() {
        return bars.stream()
                .map(Bar::getValue)
                .collect(Collectors.toList());
    }

    public void refreshBars() {
        bars.forEach(Bar::setUnpeeked);
    }

}
