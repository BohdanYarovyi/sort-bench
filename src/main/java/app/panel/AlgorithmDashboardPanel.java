package app.panel;

import app.controller.SimpleSortingController;
import app.controller.SortingController;
import app.drawer.SortingDisplayDrawer;
import app.controller.BarAlignmentController;

import javax.swing.*;
import java.awt.*;

public class AlgorithmDashboardPanel extends JPanel {
    private final SortingDisplayPanel sortingDisplayPanel;

    private final StatisticMonitorPanel statisticMonitorPanel;

    private final SortingController sortingController;

    private final GridBagConstraintConfigurator configurator;

    public AlgorithmDashboardPanel() {
        sortingDisplayPanel = new SortingDisplayPanel();
        sortingController = new SimpleSortingController(sortingDisplayPanel);
        statisticMonitorPanel = new StatisticMonitorPanel(sortingController);
        configurator = new GridBagConstraintConfigurator();

        configurePanels();
        sortingController.shuffle();  // temporary
    }

    private void configurePanels() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints sortingDisplayConstraints = configurator.getSortingDisplayConstraints();
        GridBagConstraints statisticMonitorConstraints = configurator.getStatisticMonitorConstraints();

        setLayout(layout);
        add(sortingDisplayPanel, sortingDisplayConstraints);
        add(statisticMonitorPanel, statisticMonitorConstraints);
    }

    private static class GridBagConstraintConfigurator {
        private static final int SORTING_DISPLAY_POS_X = 0;

        private static final int SORTING_DISPLAY_POS_Y = 0;

        private static final double SORTING_DISPLAY_WEIGHT_X = 1.0;

        private static final double SORTING_DISPLAY_WEIGHT_Y = 0.7;

        private static final int STATISTIC_MONITOR_POS_X = 0;

        private static final int STATISTIC_MONITOR_POS_Y = 1;

        private static final double STATISTIC_MONITOR_WEIGHT_X = 1.0;

        private static final double STATISTIC_MONITOR_WEIGHT_Y = 0.3;

        private GridBagConstraints getSortingDisplayConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = SORTING_DISPLAY_POS_X;
            constraints.gridy = SORTING_DISPLAY_POS_Y;
            constraints.weightx = SORTING_DISPLAY_WEIGHT_X;
            constraints.weighty = SORTING_DISPLAY_WEIGHT_Y;
            constraints.fill = GridBagConstraints.BOTH;

            return constraints;
        }

        private GridBagConstraints getStatisticMonitorConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = STATISTIC_MONITOR_POS_X;
            constraints.gridy = STATISTIC_MONITOR_POS_Y;
            constraints.weightx = STATISTIC_MONITOR_WEIGHT_X;
            constraints.weighty = STATISTIC_MONITOR_WEIGHT_Y;
            constraints.fill = GridBagConstraints.BOTH;

            return constraints;
        }

    }

}
