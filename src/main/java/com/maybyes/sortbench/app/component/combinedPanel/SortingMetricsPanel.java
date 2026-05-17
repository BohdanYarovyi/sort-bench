package com.maybyes.sortbench.app.component.combinedPanel;

import com.maybyes.sortbench.app.ApplicationProperties;
import com.maybyes.sortbench.app.component.label.ApplicationLabel;

import javax.swing.*;
import java.awt.*;

public class SortingMetricsPanel extends JPanel {
    private static final int UPDATE_VALUE_DELAY = ApplicationProperties.MIN_STEP_DELAY;

    private final ApplicationLabel currentStepValue = new ApplicationLabel("N/A");

    private final ApplicationLabel compareCountValue = new ApplicationLabel("N/A");

    private final ApplicationLabel swapCountValue = new ApplicationLabel("N/A");

    private final ApplicationLabel peekCountValue = new ApplicationLabel("N/A");

    private final ApplicationLabel setCountValue = new ApplicationLabel("N/A");

    private final ApplicationLabel currentAlgorithmValue = new ApplicationLabel("N/A");

    private final ApplicationLabel stepDelayValue = new ApplicationLabel("N/A");

    private final ApplicationLabel sortingStatusValue = new ApplicationLabel("N/A");

    public SortingMetricsPanel(ContextSupplier contextSupplier, MetricSupplier metricSupplier) {
        Timer updateValueTimer = new Timer(UPDATE_VALUE_DELAY, e -> {
            // context
            currentAlgorithmValue.setText(contextSupplier.getCurrentAlgorithm());
            stepDelayValue.setText(contextSupplier.getDelayValue());
            sortingStatusValue.setText(contextSupplier.getSortingStatus());

            // metric
            currentStepValue.setText(metricSupplier.getStepsMetric());
            compareCountValue.setText(metricSupplier.getCompareActionMetric());
            swapCountValue.setText(metricSupplier.getSwapActionMetric());
            peekCountValue.setText(metricSupplier.getPeekActionMetric());
            setCountValue.setText(metricSupplier.getSetActionMetric());
        });

        configure();
        updateValueTimer.start();
    }

    private void configure() {
        setLayout(new GridBagLayout());

        addColumnFiller(0, 0.1);
        addColumnFiller(1, 0.2);
        addColumnFiller(2, 0.3);
        addColumnFiller(3, 0.0);
        addColumnFiller(4, 0.2);
        addColumnFiller(5, 0.2);

        addLeftRow(0, "Algorithm", currentAlgorithmValue);
        addLeftRow(1, "Step Delay", stepDelayValue);
        addLeftRow(2, "Status", sortingStatusValue);

        addVerticalSeparator();

        addRightRow(0, "Current Step", currentStepValue);
        addRightRow(1, "Action Compare", compareCountValue);
        addRightRow(2, "Action Swap", swapCountValue);
        addRightRow(3, "Action Peek", peekCountValue);
        addRightRow(4, "Action Set", setCountValue);

        addBottomFiller();
    }

    private void addColumnFiller(int col, double weight) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = col;
        c.gridy = 99;
        c.weightx = weight;
        c.weighty = 0;
        add(Box.createHorizontalGlue(), c);
    }

    private void addLeftRow(int row, String title, ApplicationLabel valueLabel) {
        addRow(row, 1, title, valueLabel);
    }

    private void addRightRow(int row, String title, ApplicationLabel valueLabel) {
        addRow(row, 4, title, valueLabel);
    }

    private void addRow(int row, int startCol, String title, ApplicationLabel valueLabel) {
        GridBagConstraints titleC = new GridBagConstraints();
        titleC.gridx = startCol;
        titleC.gridy = row;
        titleC.weightx = 0;
        titleC.weighty = 0;
        titleC.anchor = GridBagConstraints.WEST;
        titleC.fill = GridBagConstraints.HORIZONTAL;
        titleC.insets = new Insets(1, 16, 1, 3);
        add(new ApplicationLabel(title), titleC);

        GridBagConstraints valueC = new GridBagConstraints();
        valueC.gridx = startCol + 1;
        valueC.gridy = row;
        valueC.weightx = 0;
        valueC.weighty = 0;
        valueC.fill = GridBagConstraints.HORIZONTAL;
        valueC.anchor = GridBagConstraints.WEST;
        valueC.insets = new Insets(1, 3, 1, 16);
        add(valueLabel, valueC);
    }

    private void addVerticalSeparator() {
        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 0;
        c.gridheight = GridBagConstraints.REMAINDER;
        c.weightx = 0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(4, 4, 4, 4);
        add(separator, c);
    }

    private void addBottomFiller() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 98;
        c.weighty = 1.0;
        add(Box.createVerticalGlue(), c);
    }

}