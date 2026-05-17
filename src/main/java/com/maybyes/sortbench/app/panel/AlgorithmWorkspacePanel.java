package com.maybyes.sortbench.app.panel;

import com.maybyes.sortbench.app.ApplicationProperties;
import com.maybyes.sortbench.app.controller.SimpleSortingController;
import com.maybyes.sortbench.app.listener.SelectSortAlgorithmListener;
import io.github.bohdanyarovyi.abstraction.SortAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class AlgorithmWorkspacePanel extends JPanel implements SelectSortAlgorithmListener {
    private final GridBagConstraintFactory gridBagConstraintFactory;

    private final SimpleSortingController sortingController;

    private final BarSortingPanel barSortingPanel;

    private final BarInteractPanel barInteractPanel;

    public AlgorithmWorkspacePanel() {
        gridBagConstraintFactory = new GridBagConstraintFactory();
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
        GridBagConstraints sortingDisplayConstraints = gridBagConstraintFactory.getBarSortingPanelConstraints();
        add(barSortingPanel, sortingDisplayConstraints);
    }

    private void configureBarInteractPanel() {
        GridBagConstraints controlPanelConstraints = gridBagConstraintFactory.getBarInteractPanelConstraints();
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

        private GridBagConstraints getBarSortingPanelConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 1.0;
            constraints.weighty = 1.0;
            constraints.fill = GridBagConstraints.BOTH;

            return constraints;
        }

        private GridBagConstraints getBarInteractPanelConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.weightx = 1.0;
            constraints.weighty = 0.0;
            constraints.fill = GridBagConstraints.HORIZONTAL;

            return constraints;
        }

    }

}
