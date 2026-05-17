package com.maybyes.sortbench.app.model;

import com.maybyes.sortbench.app.ApplicationProperties;
import com.maybyes.sortbench.app.component.combinedPanel.ContextSupplier;
import io.github.bohdanyarovyi.abstraction.SortAlgorithm;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class SortingContext implements ContextSupplier {
    private static final String NOT_AVAILABLE = "N/A";

    private static final String STATUS_SORTED = "SORTED";

    private static final String STATUS_UNSORTED = "UNSORTED";

    private List<Integer> initialData;

    private List<Bar> bars;

    private SortAlgorithm algorithm;

    private int timerDelay = ApplicationProperties.STARTUP_STEP_DELAY;

    private boolean isSorted = false;

    public SortingContext() {
    }

    public void initBars(int maxValue) {
        this.initialData = IntStream.rangeClosed(1, maxValue)
                .boxed()
                .toList();
        this.bars = toBars(initialData);
        isSorted = true;
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

    @Override
    public String getCurrentAlgorithm() {
        if (algorithm == null) {
            return NOT_AVAILABLE;
        }

        return algorithm.getName();
    }

    @Override
    public String getDelayValue() {
        if (algorithm == null) {
            return NOT_AVAILABLE;
        }

        return String.valueOf(timerDelay);
    }

    @Override
    public String getSortingStatus() {
        return isSorted ? STATUS_SORTED : STATUS_UNSORTED;
    }

    public void setAlgorithm(SortAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void setTimerDelay(int timerDelay) {
        this.timerDelay = timerDelay;
    }

    public void setSorted(boolean sorted) {
        isSorted = sorted;
    }

}
