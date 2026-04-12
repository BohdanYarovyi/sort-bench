package app.controller;

import app.model.Bar;
import app.model.SortingContext;
import app.panel.SortingDisplay;
import app.util.SortAlgorithm;
import app.util.SortingCollection.Action;
import app.util.impl.BubbleSortAlgorithm;

import javax.swing.*;
import java.util.List;
import java.util.Queue;

public class SortingController {
    private final SortingDisplay display;
    private SortingContext sortingContext;

    public SortingController(SortingDisplay display) {
        this.display = display;

        sortingContext = new SortingContext(50);
        sortingContext.shuffle();
        display.update(sortingContext.getBars());
        System.out.println(sortingContext.getInitialData());
    }

    public void start() {
        SortAlgorithm bubbleSort = new BubbleSortAlgorithm();
        Queue<Action> actions = sortingContext.sort(bubbleSort);

        List<Bar> bars = sortingContext.getBars();
        Timer t = new Timer(200, null);
        t.addActionListener(e -> {
            Action action = actions.remove();
            action.perform(bars);
            display.update(bars);

            if (actions.isEmpty()) {
                t.stop();
            }
        });
        t.start();
    }

}
