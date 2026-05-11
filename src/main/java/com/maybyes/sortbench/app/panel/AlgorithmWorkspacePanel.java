package com.maybyes.sortbench.app.panel;

import com.maybyes.sortbench.app.ApplicationProperties;
import com.maybyes.sortbench.app.controller.SimpleSortingController;
import com.maybyes.sortbench.app.listener.SelectSortAlgorithmListener;
import com.maybyes.sortbench.abstraction.SortAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class AlgorithmWorkspacePanel extends JPanel implements SelectSortAlgorithmListener {
    private final GridBagConstraintFactory constraintFactory;

    private final SimpleSortingController sortingController;

    private final BarSortingPanel barSortingPanel;

    private final BarInteractPanel barInteractPanel;

    public AlgorithmWorkspacePanel() {
        constraintFactory = new GridBagConstraintFactory();
        sortingController = new SimpleSortingController();
        barSortingPanel = new BarSortingPanel(sortingController);
        barInteractPanel = new BarInteractPanel(sortingController);
    }

    public void configure() {
        setLayout(new GridBagLayout());

        configureBarSortingPanel();
        configureBarInteractPanel();
        initSortingController();

        log.debug("{} was configured", getClass().getName());
    }

    private void configureBarSortingPanel() {
        GridBagConstraints sortingDisplayConstraints = constraintFactory.getBarSortingPanelConstraints();
        add(barSortingPanel, sortingDisplayConstraints);
    }

    private void configureBarInteractPanel() {
        GridBagConstraints controlPanelConstraints = constraintFactory.getBarInteractPanelConstraints();
        add(barInteractPanel, controlPanelConstraints);
    }

    private void initSortingController() {
        sortingController.setBarsAmount(ApplicationProperties.STARTUP_BARS_AMOUNT);
        sortingController.setAlgorithm(ApplicationProperties.DEFAULT_SORT_ALGORITHM);
    }

    @Override
    public void setNewAlgorithm(SortAlgorithm sortAlgorithm) {
        sortingController.setAlgorithm(sortAlgorithm);
    }

    private static class GridBagConstraintFactory {
        // bar sorting panel
        private static final int BAR_SORTING_PANEL_POS_X = 0;

        private static final int BAR_SORTING_PANEL_POS_Y = 0;

        private static final double BAR_SORTING_PANEL_WEIGHT_X = 1.0;

        private static final double BAR_SORTING_PANEL_WEIGHT_Y = 0.85;

        // bar interact panel
        private static final int BAR_INTERACT_PANEL_POS_X = 0;

        private static final int BAR_INTERACT_PANEL_POS_Y = 1;

        private static final double BAR_INTERACT_PANEL_WEIGHT_X = 1.0;

        private static final double BAR_INTERACT_PANEL_WEIGHT_Y = 0.15;

        private GridBagConstraints getBarSortingPanelConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = BAR_SORTING_PANEL_POS_X;
            constraints.gridy = BAR_SORTING_PANEL_POS_Y;
            constraints.weightx = BAR_SORTING_PANEL_WEIGHT_X;
            constraints.weighty = BAR_SORTING_PANEL_WEIGHT_Y;
            constraints.fill = GridBagConstraints.BOTH;

            return constraints;
        }

        private GridBagConstraints getBarInteractPanelConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = BAR_INTERACT_PANEL_POS_X;
            constraints.gridy = BAR_INTERACT_PANEL_POS_Y;
            constraints.weightx = BAR_INTERACT_PANEL_WEIGHT_X;
            constraints.weighty = BAR_INTERACT_PANEL_WEIGHT_Y;
            constraints.fill = GridBagConstraints.BOTH;

            return constraints;
        }

    }

}
