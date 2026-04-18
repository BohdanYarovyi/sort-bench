package app.controller;

import app.model.Bar;
import app.model.SortingContext;
import app.panel.SortingDisplayPanel;
import app.util.SortAlgorithm;
import app.util.SortingCollection.Action;
import app.util.impl.BubbleSortAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Queue;

/*
    TODO: Controller must be reworked and added more methods for operating on display
          Not each public method can be used for buttons on the screen,
          but it is useful to have public methods for controlling algorithm display
 */
public class SimpleSortingController implements SortingController {
    private final SortingDisplayPanel display;

    private SortingContext sortingContext;

    public SimpleSortingController(SortingDisplayPanel display) {
        this.display = display;

        sortingContext = new SortingContext(50);
    }

    @Override
    public void start() {
        SortAlgorithm bubbleSort = new BubbleSortAlgorithm();
        Queue<Action> actions = sortingContext.sort(bubbleSort);

        List<Bar> bars = sortingContext.getBars();
        Timer t = new Timer(200, null);
        t.addActionListener(e -> {
            if (actions.isEmpty()) {
                t.stop();
                return;
            }

            Action action = actions.remove();
            action.perform(bars);
            display.update(bars);
        });
        t.start();
    }

    @Override
    public void shuffle() {
        sortingContext.shuffle();
        List<Bar> bars = sortingContext.getBars();
        display.update(bars);
    }

}
