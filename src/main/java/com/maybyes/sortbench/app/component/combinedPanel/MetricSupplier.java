package com.maybyes.sortbench.app.component.combinedPanel;

public interface MetricSupplier {

    String getStepsMetric();

    String getCompareActionMetric();

    String getSwapActionMetric();

    String getPeekActionMetric();

    String getSetActionMetric();

}
