package com.maybyes.sortbench.app.panel;

import com.maybyes.sortbench.app.ApplicationProperties;
import com.maybyes.sortbench.app.component.button.ApplicationButton;
import com.maybyes.sortbench.app.component.button.ShuffleButton;
import com.maybyes.sortbench.app.component.button.StartButton;
import com.maybyes.sortbench.app.component.button.StopButton;
import com.maybyes.sortbench.app.component.combinedPanel.ContextSupplier;
import com.maybyes.sortbench.app.component.combinedPanel.InsertNumberPanel;
import com.maybyes.sortbench.app.component.combinedPanel.MetricSupplier;
import com.maybyes.sortbench.app.component.combinedPanel.SortingMetricsPanel;
import com.maybyes.sortbench.app.controller.SimpleSortingController;
import com.maybyes.sortbench.app.controller.SortingController;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class BarInteractPanel extends JPanel {
    private final static Color BACKGROUND = new Color(203, 209, 218, 255);

    private final GridBagConstraintsFactory gridBagConstraintsFactory;

    private final ButtonPanel buttonPanel;

    private final InputSectionPanel inputSectionPanel;

    private final SortingMetricsPanel sortingMetricsPanel;

    public BarInteractPanel(SimpleSortingController simpleSortingController) {
        ComponentStateManager componentStateManager = new ComponentStateManager();
        ContextSupplier contextSupplier = simpleSortingController.getContextSupplier();
        MetricSupplier metricSupplier = simpleSortingController.getMetricSupplier();
        this.gridBagConstraintsFactory = new GridBagConstraintsFactory();
        this.buttonPanel = new ButtonPanel(simpleSortingController, componentStateManager);
        this.inputSectionPanel = new InputSectionPanel(simpleSortingController, componentStateManager);
        this.sortingMetricsPanel = new SortingMetricsPanel(contextSupplier, metricSupplier);

        configure();
    }

    private void configure() {
        setLayout(new GridBagLayout());
        setBackground(BACKGROUND);
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

        configureButtonPanel();
        configureInputSectionPanel();
        configureSortingMetricsPanel();

        log.debug("{} was configured", getClass().getName());
    }

    private void configureButtonPanel() {
        add(buttonPanel, gridBagConstraintsFactory.getButtonPanelConstraints());
    }

    private void configureInputSectionPanel() {
        add(inputSectionPanel, gridBagConstraintsFactory.getInputSectionConstraints());
    }

    private void configureSortingMetricsPanel() {
        sortingMetricsPanel.setBackground(BACKGROUND);
        add(sortingMetricsPanel, gridBagConstraintsFactory.getSortingMetricsConstraints());
    }

    private static class ButtonPanel extends JPanel {
        public static final int GAP_HEIGHT = 10;

        public static final int PRIME_BUTTON_HEIGHT = 46;

        public static final int SUB_BUTTON_HEIGHT = 28;

        private final SortingController sortingController;

        private final ComponentStateManager componentStateManager;

        public final ApplicationButton startButton;

        public final ApplicationButton stopButton;

        public final ApplicationButton shuffleButton;

        public ButtonPanel(SortingController sortingController, ComponentStateManager componentStateManager) {
            super();
            this.sortingController = sortingController;
            this.componentStateManager = componentStateManager;
            this.startButton = new StartButton();
            this.stopButton = new StopButton();
            this.shuffleButton = new ShuffleButton();

            configure();
        }

        private void configure() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(BACKGROUND);

            configureStartButton();
            add(Box.createVerticalStrut(GAP_HEIGHT));
            configureStopButton();
            add(Box.createVerticalStrut(GAP_HEIGHT));
            configureShuffleButton();
        }

        private void configureStartButton() {
            var workListener = new StartButtonListener(sortingController);
            var stateListener = new DisableComponentsOnStartClickedListener(componentStateManager);
            startButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, PRIME_BUTTON_HEIGHT));
            componentStateManager.setStartButton(startButton);

            startButton.addActionListener(workListener);
            startButton.addActionListener(stateListener);
            add(startButton);
        }

        private void configureStopButton() {
            var workListener = new StopButtonListener(sortingController);
            var stateListener = new EnableComponentsOnStopClickedListener(componentStateManager);
            stopButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, SUB_BUTTON_HEIGHT));
            stopButton.setEnabled(false);
            componentStateManager.setStopButton(stopButton);

            stopButton.addActionListener(workListener);
            stopButton.addActionListener(stateListener);
            add(stopButton);
        }

        private void configureShuffleButton() {
            ShuffleButtonListener listener = new ShuffleButtonListener(sortingController);
            shuffleButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, SUB_BUTTON_HEIGHT));
            componentStateManager.setShuffleButton(shuffleButton);

            shuffleButton.addActionListener(listener);
            add(shuffleButton);
        }

        private static class StartButtonListener implements ActionListener {
            private final SortingController controller;

            private StartButtonListener(SortingController controller) {
                this.controller = controller;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.start();
                log.debug("Button start was pressed");
            }

        }

        private static class DisableComponentsOnStartClickedListener implements ActionListener {
            private final ComponentStateManager componentStateManager;

            private DisableComponentsOnStartClickedListener(ComponentStateManager componentStateManager) {
                this.componentStateManager = componentStateManager;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                componentStateManager.startButtonClicked();
            }

        }

        private static class StopButtonListener implements ActionListener {
            private final SortingController controller;

            private StopButtonListener(SortingController controller) {
                this.controller = controller;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.stop();

                log.debug("Button stop was pressed");
            }

        }

        private static class EnableComponentsOnStopClickedListener implements ActionListener {
            private final ComponentStateManager componentStateManager;

            private EnableComponentsOnStopClickedListener(ComponentStateManager componentStateManager) {
                this.componentStateManager = componentStateManager;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                componentStateManager.stopButtonClicked();
            }

        }

        private static class ShuffleButtonListener implements ActionListener {
            private final SortingController controller;

            private ShuffleButtonListener(SortingController controller) {
                this.controller = controller;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.shuffle();

                log.debug("Button shuffle was pressed");
            }

        }

    }

    private static class InputSectionPanel extends JPanel {
        private static final Color SEPARATOR_COLOR = Color.BLACK;

        private final SortingController sortingController;

        private final ComponentStateManager componentStateManager;

        private final InsertNumberPanel barsAmountPanel;

        private final InsertNumberPanel stepDelayPanel;

        private final JSeparator separator;

        public InputSectionPanel(SortingController sortingController, ComponentStateManager componentStateManager) {
            super();
            this.sortingController = sortingController;
            this.componentStateManager = componentStateManager;
            this.barsAmountPanel = new InsertNumberPanel();
            this.stepDelayPanel = new InsertNumberPanel();
            this.separator = new JSeparator();

            configure();
        }

        private void configure() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(BACKGROUND);

            configureBarsAmountPanel();
            configureSeparator();
            configureStepDelayPanel();
        }

        private void configureBarsAmountPanel() {
            barsAmountPanel.setLabelText("Bars amount");
            barsAmountPanel.setValue(ApplicationProperties.STARTUP_BARS_AMOUNT);
            barsAmountPanel.setMinValue(ApplicationProperties.MIN_BAR_AMOUNT);
            barsAmountPanel.setMaxValue(ApplicationProperties.MAX_BAR_AMOUNT);
            barsAmountPanel.setIntegerConsumer(sortingController::setBarsAmount);
            barsAmountPanel.setBackground(BACKGROUND);
            componentStateManager.setInsertBarsAmountPanel(barsAmountPanel);

            add(barsAmountPanel);
        }

        private void configureStepDelayPanel() {
            stepDelayPanel.setLabelText("Step delay");
            stepDelayPanel.setValue(ApplicationProperties.STARTUP_STEP_DELAY);
            stepDelayPanel.setMinValue(ApplicationProperties.MIN_STEP_DELAY);
            stepDelayPanel.setMaxValue(ApplicationProperties.MAX_STEP_DELAY);
            stepDelayPanel.setIntegerConsumer(sortingController::setDelay);
            stepDelayPanel.setBackground(BACKGROUND);
            componentStateManager.setInsertStepDelayPanel(stepDelayPanel);

            add(stepDelayPanel);
        }

        private void configureSeparator() {
            separator.setForeground(SEPARATOR_COLOR);

            add(separator);
        }

    }

    private static class ComponentStateManager {
        private JButton startButton;

        private JButton stopButton;

        private JButton shuffleButton;

        private InsertNumberPanel insertBarsAmountPanel;

        private InsertNumberPanel insertStepDelayPanel;

        private boolean isAssembled;

        public ComponentStateManager() {}

        public void startButtonClicked() {
            if (isAssembled) {
                setAll(false);
                stopButton.setEnabled(true);
                insertStepDelayPanel.setEnabled(true);
            }
        }

        public void stopButtonClicked() {
            if (isAssembled) {
                setAll(true);
                stopButton.setEnabled(false);
            }
        }

        private void setAll(boolean value) {
            if (isAssembled) {
                startButton.setEnabled(value);
                stopButton.setEnabled(value);
                shuffleButton.setEnabled(value);
                insertBarsAmountPanel.setEnabled(value);
                insertStepDelayPanel.setEnabled(value);
            }
        }

        private void checkIsAssembled() {
            isAssembled = startButton != null
                          && stopButton != null
                          && shuffleButton != null
                          && insertBarsAmountPanel != null
                          && insertStepDelayPanel != null;
        }

        public void setStartButton(JButton startButton) {
            this.startButton = startButton;
        }

        public void setStopButton(JButton stopButton) {
            this.stopButton = stopButton;
        }

        public void setShuffleButton(JButton shuffleButton) {
            this.shuffleButton = shuffleButton;
        }

        public void setInsertBarsAmountPanel(InsertNumberPanel insertBarsAmountPanel) {
            this.insertBarsAmountPanel = insertBarsAmountPanel;
            checkIsAssembled();
        }

        public void setInsertStepDelayPanel(InsertNumberPanel insertStepDelayPanel) {
            this.insertStepDelayPanel = insertStepDelayPanel;
            checkIsAssembled();
        }

        public void setAssembled(boolean assembled) {
            isAssembled = assembled;
            checkIsAssembled();
        }
    }

    private static class GridBagConstraintsFactory {

        GridBagConstraints getButtonPanelConstraints() {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 0;
            c.weighty = 1.0;
            c.fill = GridBagConstraints.VERTICAL;
            c.anchor = GridBagConstraints.NORTHWEST;
            c.insets = new Insets(8, 8, 8, 5);

            return c;
        }

        GridBagConstraints getInputSectionConstraints() {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 0;
            c.weighty = 1.0;
            c.fill = GridBagConstraints.NONE;
            c.anchor = GridBagConstraints.NORTH;
            c.insets = new Insets(5, 5, 8, 5);

            return c;
        }

        GridBagConstraints getSortingMetricsConstraints() {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 2;
            c.gridy = 0;
            c.weightx = 1.0;
            c.weighty = 1.0;
            c.fill = GridBagConstraints.BOTH;
            c.insets = new Insets(8, 5, 8, 8);

            return c;
        }

    }

}
