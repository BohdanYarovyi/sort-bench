package app.panel;

import app.ApplicationProperties;
import app.controller.SimpleSortingController;

import javax.swing.*;
import java.awt.*;

public class AlgorithmDashboardPanel extends JPanel {
    private final GridBagConstraintConfigurator configurator;

    private final SortingDisplayPanel sortingDisplayPanel;

    private final ControlPanel controlPanel;

    public AlgorithmDashboardPanel() {
        SimpleSortingController sortingController = new SimpleSortingController();
        configurator = new GridBagConstraintConfigurator();
        sortingDisplayPanel = new SortingDisplayPanel(sortingController);
        controlPanel = new ControlPanel(sortingController);

        configurePanels();
        sortingController.initNewAmount(ApplicationProperties.STARTUP_BAR_AMOUNT);
    }

    private void configurePanels() {
        setLayout(new GridBagLayout());

        GridBagConstraints sortingDisplayConstraints = configurator.getSortingDisplayConstraints();
        add(sortingDisplayPanel, sortingDisplayConstraints);

        GridBagConstraints controlPanelConstraints = configurator.getControlPanelConstraints();
        add(controlPanel, controlPanelConstraints);
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
