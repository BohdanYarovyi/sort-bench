package app.panel;

import app.ApplicationProperties;
import app.component.button.StartButton;
import app.component.button.ShuffleButton;
import app.component.button.StopButton;
import app.component.combinedPanel.InsertNumberPanel;
import app.component.combinedPanel.StepCounterPanel;
import app.controller.SimpleSortingController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    private final SimpleSortingController sortingController;

    private final StartButton startButton;

    private final StopButton stopButton;

    private final ShuffleButton shuffleButton;

    private final InsertNumberPanel barsAmountPanel;

    private final InsertNumberPanel stepDelayPanel;

    private final StepCounterPanel stepCounterPanel;

    public ControlPanel(SimpleSortingController sortingController) {
        this.sortingController = sortingController;
        this.startButton = new StartButton();
        this.stopButton = new StopButton();
        this.shuffleButton = new ShuffleButton();
        this.barsAmountPanel = new InsertNumberPanel(sortingController::setBarsAmount);
        this.stepDelayPanel = new InsertNumberPanel(sortingController::setDelay);
        this.stepCounterPanel = new StepCounterPanel();

        sortingController.setUpdateStepCounterListener(stepCounterPanel);

        configureButtonStart();
        configureButtonStop();
        configureButtonShuffle();
        configureBarsAmountPanel();
        configureStepDelayPanel();
        configure();
    }

    private void configure() {
        setLayout(new FlowLayout());

        add(startButton);
        add(stopButton);
        add(shuffleButton);
        add(barsAmountPanel);
        add(stepDelayPanel);
        add(stepCounterPanel);
    }

    private void configureButtonStart() {
        startButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingController.start();
                setAllDisabled();
                stopButton.setEnabled(true);
                stepDelayPanel.setEnabled(true);
            }
        });
    }

    private void configureButtonStop() {
        stopButton.setEnabled(false);
        stopButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingController.stop();
                setAllEnabled();
                stopButton.setEnabled(false);
            }
        });
    }

    private void configureButtonShuffle() {
        shuffleButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingController.shuffle();
            }
        });
    }

    private void configureBarsAmountPanel() {
        barsAmountPanel.setLabelText("Bars amount");
        barsAmountPanel.setValue(ApplicationProperties.STARTUP_BARS_AMOUNT);
        barsAmountPanel.setMinValue(ApplicationProperties.MIN_BAR_AMOUNT);
        barsAmountPanel.setMaxValue(ApplicationProperties.MAX_BAR_AMOUNT);
    }

    private void configureStepDelayPanel() {
        stepDelayPanel.setLabelText("Step delay");
        stepDelayPanel.setValue(ApplicationProperties.STARTUP_STEP_DELAY);
        stepDelayPanel.setMinValue(ApplicationProperties.MIN_STEP_DELAY);
        stepDelayPanel.setMaxValue(ApplicationProperties.MAX_STEP_DELAY);
    }

    private void setAllDisabled() {
        startButton.setEnabled(false);
        stopButton.setEnabled(false);
        shuffleButton.setEnabled(false);
        barsAmountPanel.setEnabled(false);
        stepDelayPanel.setEnabled(false);
    }

    private void setAllEnabled() {
        startButton.setEnabled(true);
        stopButton.setEnabled(true);
        shuffleButton.setEnabled(true);
        barsAmountPanel.setEnabled(true);
        stepDelayPanel.setEnabled(true);
    }

}
