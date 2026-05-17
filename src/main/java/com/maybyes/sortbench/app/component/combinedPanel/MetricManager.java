package com.maybyes.sortbench.app.component.combinedPanel;

import com.maybyes.sortbench.app.util.algorithm.action.Action;
import com.maybyes.sortbench.app.util.algorithm.action.CompareAction;
import com.maybyes.sortbench.app.util.algorithm.action.PeekAction;
import com.maybyes.sortbench.app.util.algorithm.action.SetAction;
import com.maybyes.sortbench.app.util.algorithm.action.SwapAction;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class MetricManager implements MetricSupplier {
    private static final String NOT_AVAILABLE = "N/A";

    private Metric metric;

    public MetricManager() {}

    public void reset(int allSteps) {
        metric = Metric.initMetric(allSteps);
    }

    public synchronized void recordStep(Action action) {
        metric.currentStep.incrementAndGet();

        if (action instanceof CompareAction) {
            metric.compareActionCount.incrementAndGet();
        }

        if (action instanceof SwapAction) {
            metric.swapActionCount.incrementAndGet();
        }

        if (action instanceof PeekAction) {
            metric.peekActionCount.incrementAndGet();
        }

        if (action instanceof SetAction) {
            metric.setActionCount.incrementAndGet();
        }
    }

    @Override
    public String getStepsMetric() {
        if (metric == null) {
            return NOT_AVAILABLE;
        }

        return metric.currentStep.get() + " / " + metric.allSteps;
    }

    @Override
    public String getCompareActionMetric() {
        if (metric == null) {
            return NOT_AVAILABLE;
        }

        return metric.compareActionCount.toString();
    }

    @Override
    public String getSwapActionMetric() {
        if (metric == null) {
            return NOT_AVAILABLE;
        }

        return metric.swapActionCount.toString();
    }

    @Override
    public String getPeekActionMetric() {
        if (metric == null) {
            return NOT_AVAILABLE;
        }

        return metric.peekActionCount.toString();
    }

    @Override
    public String getSetActionMetric() {
        if (metric == null) {
            return NOT_AVAILABLE;
        }

        return metric.setActionCount.toString();
    }

    private static class Metric {
        private final int allSteps;

        private final AtomicInteger currentStep;

        private final AtomicInteger compareActionCount;

        private final AtomicInteger swapActionCount;

        private final AtomicInteger peekActionCount;

        private final AtomicInteger setActionCount;

        private Metric(int allSteps,
                       AtomicInteger currentStep,
                       AtomicInteger compareActionCount,
                       AtomicInteger swapActionCount,
                       AtomicInteger peekActionCount,
                       AtomicInteger setActionCount) {
            this.allSteps = allSteps;
            this.currentStep = currentStep;
            this.compareActionCount = compareActionCount;
            this.swapActionCount = swapActionCount;
            this.peekActionCount = peekActionCount;
            this.setActionCount = setActionCount;
        }

        public static Metric initMetric(int allSteps) {
            return new Metric(
                    allSteps,
                    new AtomicInteger(),
                    new AtomicInteger(),
                    new AtomicInteger(),
                    new AtomicInteger(),
                    new AtomicInteger()
            );
        }

    }

}