package app.controller;

import app.ApplicationProperties;
import app.listener.UpdateBarsOnScreenListener;
import app.listener.UpdateStepCounterListener;
import app.model.Bar;
import app.model.SortingContext;
import app.util.algorithm.SortingCollectionImpl;
import app.util.algorithm.action.Action;
import lombok.Setter;
import com.maybyes.sortbench.lib.SortAlgorithm;

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleSortingController implements SortingController {
    @Setter
    private SortAlgorithm sortAlgorithm;

    private SortingContext context;

    private Timer timer;

    private int timerDelay = ApplicationProperties.STARTUP_STEP_DELAY;

    @Setter
    private UpdateBarsOnScreenListener updateBarsOnScreenListener;

    @Setter
    private UpdateStepCounterListener updateStepCounterListener;

    @Override
    public void start() {
        Queue<Action> actions = sort(sortAlgorithm);

        int allSteps = actions.size();
        AtomicInteger counter = new AtomicInteger(0);
        List<Bar> bars = context.getBars();
        timer = new Timer(timerDelay, e -> {
            if (actions.isEmpty()) {
                timer.stop();
                return;
            }
            context.refreshBars();
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
        context.refreshBars();
        List<Bar> bars = context.getBars();
        Collections.shuffle(bars);
        updateBarsOnScreenListener.update(bars);
    }

    @Override
    public void stop() {
        if (timer != null) {
            timer.stop();
        }
    }

    @Override
    public void setBarsAmount(int amount) {
        context = new SortingContext(amount);
        updateBarsOnScreenListener.update(context.getBars());
    }

    @Override
    public void setDelay(int delay) {
        timerDelay = delay;

        if (timer != null) {
            timer.setDelay(delay);
        }
    }

    private Queue<Action> sort(SortAlgorithm sortAlgorithm) {
        List<Integer> values = context.getBarsValues();
        SortingCollectionImpl sortingCollection = new SortingCollectionImpl(values);
        sortAlgorithm.sort(sortingCollection);

        return sortingCollection.getActions();
    }

}
