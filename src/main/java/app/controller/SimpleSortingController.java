package app.controller;

import app.listener.UpdateBarsOnScreenListener;
import app.listener.UpdateStepCounterListener;
import app.model.Bar;
import app.model.SortingContext;
import app.util.SortAlgorithm;
import app.util.SortingCollection.Action;
import app.util.impl.BubbleSortAlgorithm;
import lombok.Setter;

import javax.swing.*;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleSortingController implements SortingController {

    private SortingContext sortingContext;

    private Timer timer;

    @Setter
    private UpdateBarsOnScreenListener updateBarsOnScreenListener;

    @Setter
    private UpdateStepCounterListener updateStepCounterListener;

    @Override
    public void start() {
        SortAlgorithm bubbleSort = new BubbleSortAlgorithm();
        Queue<Action> actions = sortingContext.sort(bubbleSort);

        int allSteps = actions.size();
        AtomicInteger counter = new AtomicInteger(0);
        List<Bar> bars = sortingContext.getBars();
        timer = new Timer(200, e -> {
            if (actions.isEmpty()) {
                timer.stop();
                return;
            }

            Action action = actions.remove();
            action.perform(bars);
            counter.incrementAndGet();

            updateBarsOnScreenListener.update(bars);
            updateStepCounterListener.update(counter.get(), allSteps);
        });
        timer.start();
    }

    @Override
    public void shuffle() {
        sortingContext.shuffle();
        List<Bar> bars = sortingContext.getBars();
        updateBarsOnScreenListener.update(bars);
    }

    @Override
    public void stop() {
        if (timer != null) {
            timer.stop();
        }
    }

    @Override
    public void initNewAmount(int amount) {
        sortingContext = new SortingContext(amount);
        updateBarsOnScreenListener.update(sortingContext.getBars());
    }

}
