package com.maybyes.sortbench.app.controller;

import com.maybyes.sortbench.app.ApplicationProperties;
import com.maybyes.sortbench.app.listener.UpdateBarsOnScreenListener;
import com.maybyes.sortbench.app.listener.UpdateStepCounterListener;
import com.maybyes.sortbench.app.model.Bar;
import com.maybyes.sortbench.app.model.SortingContext;
import com.maybyes.sortbench.app.util.algorithm.SortingCollectionImpl;
import com.maybyes.sortbench.app.util.algorithm.action.Action;
import lombok.Setter;
import com.maybyes.sortbench.abstraction.SortAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SimpleSortingController implements SortingController {
    private SortAlgorithm algorithm;

    private SortingContext context;

    private Timer timer;

    private int timerDelay = ApplicationProperties.STARTUP_STEP_DELAY;

    @Setter
    private UpdateBarsOnScreenListener updateBarsOnScreenListener;

    @Setter
    private UpdateStepCounterListener updateStepCounterListener;

    @Override
    public void start() {
        log.debug("Sorting process was started");
        Queue<Action> actions = sort(algorithm);

        int allSteps = actions.size();
        AtomicInteger counter = new AtomicInteger(0);
        List<Bar> bars = context.getBars();
        timer = new Timer(timerDelay, e -> {
            if (actions.isEmpty()) {
                timer.stop();
                log.info("Bars sorting process finished");
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

        log.debug("Was shuffled all bars");
    }

    @Override
    public void stop() {
        if (timer != null) {
            timer.stop();

            log.debug("Sorting process was stopped");
        }
    }

    @Override
    public void setBarsAmount(int amount) {
        context = new SortingContext(amount);
        updateBarsOnScreenListener.update(context.getBars());

        log.debug("Was set new bars amount to {}", amount);
    }

    @Override
    public void setDelay(int delay) {
        timerDelay = delay;

        if (timer != null) {
            timer.setDelay(delay);

            log.debug("Timer delay was set with new value {}", delay);
        }
    }

    private Queue<Action> sort(SortAlgorithm sortAlgorithm) {
        List<Integer> values = context.getBarsValues();
        SortingCollectionImpl sortingCollection = new SortingCollectionImpl(values);
        sortAlgorithm.sort(sortingCollection);

        log.debug("Bars were sorted using '{}' algorithm", sortAlgorithm.getName());
        return sortingCollection.getActions();
    }

    public void setAlgorithm(SortAlgorithm algorithm) {
        this.algorithm = algorithm;
        log.debug("Was set sorting algorithm '{}'", algorithm.getName());
    }

}
