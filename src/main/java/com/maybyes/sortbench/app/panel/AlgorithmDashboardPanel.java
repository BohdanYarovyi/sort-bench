package com.maybyes.sortbench.app.panel;

import com.maybyes.sortbench.app.ApplicationProperties;
import com.maybyes.sortbench.app.controller.SimpleSortingController;
import com.maybyes.sortbench.app.listener.SelectSortAlgorithmListener;
import com.maybyes.sortbench.abstraction.SortAlgorithm;

import javax.swing.*;
import java.awt.*;

public class AlgorithmDashboardPanel extends JPanel implements SelectSortAlgorithmListener {
    private final GridBagConstraintConfigurator configurator;

    private final SimpleSortingController sortingController;

    private final SortingDisplayPanel sortingDisplayPanel;

    private final ControlPanel controlPanel;

    public AlgorithmDashboardPanel() {
        configurator = new GridBagConstraintConfigurator();
        sortingController = new SimpleSortingController();
        sortingDisplayPanel = new SortingDisplayPanel(sortingController);
        controlPanel = new ControlPanel(sortingController);

        configurePanels();
        sortingController.setBarsAmount(ApplicationProperties.STARTUP_BARS_AMOUNT);
        sortingController.setSortAlgorithm(ApplicationProperties.DEFAULT_SORT_ALGORITHM);
    }

    private void configurePanels() {
        setLayout(new GridBagLayout());

        GridBagConstraints sortingDisplayConstraints = configurator.getSortingDisplayConstraints();
        add(sortingDisplayPanel, sortingDisplayConstraints);

        GridBagConstraints controlPanelConstraints = configurator.getControlPanelConstraints();
        add(controlPanel, controlPanelConstraints);
    }

    @Override
    public void setNewAlgorithm(SortAlgorithm sortAlgorithm) {
        sortingController.setSortAlgorithm(sortAlgorithm);
    }

    private static class GridBagConstraintConfigurator {
        // sorting display
        private static final int SORTING_DISPLAY_POS_X = 0;

        private static final int SORTING_DISPLAY_POS_Y = 0;

        private static final double SORTING_DISPLAY_WEIGHT_X = 1.0;

        private static final double SORTING_DISPLAY_WEIGHT_Y = 0.85;

        // control panel
        private static final int CONTROL_PANEL_POS_X = 0;

        private static final int CONTROL_PANEL_POS_Y = 1;

        private static final double CONTROL_PANEL_WEIGHT_X = 1.0;

        private static final double CONTROL_PANEL_WEIGHT_Y = 0.15;

        private GridBagConstraints getSortingDisplayConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = SORTING_DISPLAY_POS_X;
            constraints.gridy = SORTING_DISPLAY_POS_Y;
            constraints.weightx = SORTING_DISPLAY_WEIGHT_X;
            constraints.weighty = SORTING_DISPLAY_WEIGHT_Y;
            constraints.fill = GridBagConstraints.BOTH;

            return constraints;
        }

        private GridBagConstraints getControlPanelConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = CONTROL_PANEL_POS_X;
            constraints.gridy = CONTROL_PANEL_POS_Y;
            constraints.weightx = CONTROL_PANEL_WEIGHT_X;
            constraints.weighty = CONTROL_PANEL_WEIGHT_Y;
            constraints.fill = GridBagConstraints.BOTH;

            return constraints;
        }

    }

}
