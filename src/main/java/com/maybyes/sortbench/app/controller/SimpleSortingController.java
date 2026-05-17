package com.maybyes.sortbench.app.controller;

import com.maybyes.sortbench.app.component.combinedPanel.ContextSupplier;
import com.maybyes.sortbench.app.component.combinedPanel.MetricManager;
import com.maybyes.sortbench.app.component.combinedPanel.MetricSupplier;
import com.maybyes.sortbench.app.listener.UpdateBarsOnScreenListener;
import com.maybyes.sortbench.app.model.Bar;
import com.maybyes.sortbench.app.model.SortingContext;
import com.maybyes.sortbench.app.util.BarUtil;
import com.maybyes.sortbench.app.util.algorithm.SortingCollectionImpl;
import com.maybyes.sortbench.app.util.algorithm.action.Action;
import io.github.bohdanyarovyi.abstraction.SortAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

@Slf4j
public class SimpleSortingController implements SortingController {
    private final SortingContext sortingContext;

    private final MetricManager metricManager;

    private Timer timer;

    private UpdateBarsOnScreenListener updateBarsOnScreenListener;

    public SimpleSortingController() {
        this.metricManager = new MetricManager();
        this.sortingContext = new SortingContext();
    }

    @Override
    public void start() {
        log.debug("Sorting process was started");
        SortAlgorithm algorithm = sortingContext.getAlgorithm();
        int timerDelay = sortingContext.getTimerDelay();
        List<Bar> bars = sortingContext.getBars();
        Queue<Action> actions = sort(algorithm);
        metricManager.reset(actions.size());

        timer = new Timer(timerDelay, e -> {
            if (actions.isEmpty()) {
                timer.stop();
                sortingContext.setSorted(true);

                BarUtil.setIdleBars(bars);
                updateBarsOnScreenListener.update(bars);
                log.info("Bars sorting process finished");
                return;
            }
            BarUtil.setIdleBars(bars);
            Action action = actions.remove();
            action.perform(bars);
            metricManager.recordStep(action);

            updateBarsOnScreenListener.update(bars);
        });
        timer.start();
    }

    private Queue<Action> sort(SortAlgorithm sortAlgorithm) {
        List<Integer> values = sortingContext.getBarsValues();
        SortingCollectionImpl sortingCollection = new SortingCollectionImpl(values);
        sortAlgorithm.sort(sortingCollection);

        log.debug("Bars were sorted using '{}' algorithm", sortAlgorithm.getName());
        return sortingCollection.getActions();
    }

    @Override
    public void shuffle() {
        List<Bar> bars = sortingContext.getBars();
        BarUtil.setIdleBars(bars);
        Collections.shuffle(bars);
        updateBarsOnScreenListener.update(bars);
        sortingContext.setSorted(false);

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
        sortingContext.initBars(amount);
        updateBarsOnScreenListener.update(sortingContext.getBars());

        log.debug("Was set new bars amount to {}", amount);
    }

    @Override
    public void setDelay(int delay) {
        sortingContext.setTimerDelay(delay);

        if (timer != null) {
            timer.setDelay(delay);

            log.debug("Timer delay was set with new value {}", delay);
        }
    }

    public void setAlgorithm(SortAlgorithm algorithm) {
        if (timer !=  null) {
            timer.stop();
        }

        sortingContext.setAlgorithm(algorithm);
        log.debug("Was set sorting algorithm '{}'", algorithm.getName());
    }

    public void setUpdateBarsOnScreenListener(UpdateBarsOnScreenListener updateBarsOnScreenListener) {
        this.updateBarsOnScreenListener = updateBarsOnScreenListener;
    }

    public MetricSupplier getMetricSupplier() {
        return metricManager;
    }

    public ContextSupplier getContextSupplier() {
        return sortingContext;
    }

}
