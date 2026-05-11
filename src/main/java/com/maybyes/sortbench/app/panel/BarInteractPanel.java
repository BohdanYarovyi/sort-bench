package com.maybyes.sortbench.app.panel;

import com.maybyes.sortbench.app.ApplicationProperties;
import com.maybyes.sortbench.app.component.button.StartButton;
import com.maybyes.sortbench.app.component.button.ShuffleButton;
import com.maybyes.sortbench.app.component.button.StopButton;
import com.maybyes.sortbench.app.component.combinedPanel.InsertNumberPanel;
import com.maybyes.sortbench.app.component.combinedPanel.StepCounterPanel;
import com.maybyes.sortbench.app.controller.SimpleSortingController;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@Slf4j
public class BarInteractPanel extends JPanel {
    private final SimpleSortingController sortingController;

    private final StartButton startButton;

    private final StopButton stopButton;

    private final ShuffleButton shuffleButton;

    private final InsertNumberPanel barsAmountPanel;

    private final InsertNumberPanel stepDelayPanel;

    private final StepCounterPanel stepCounterPanel;

    public BarInteractPanel(SimpleSortingController sortingController) {
        this.sortingController = sortingController;
        this.startButton = new StartButton();
        this.stopButton = new StopButton();
        this.shuffleButton = new ShuffleButton();
        this.barsAmountPanel = new InsertNumberPanel(sortingController::setBarsAmount);
        this.stepDelayPanel = new InsertNumberPanel(sortingController::setDelay);
        this.stepCounterPanel = new StepCounterPanel();

        sortingController.setUpdateStepCounterListener(stepCounterPanel);

        configure();
    }

    private void configure() {
        setLayout(new FlowLayout());

        configureButtonStart();
        configureButtonStop();
        configureButtonShuffle();
        configureBarsAmountPanel();
        configureStepDelayPanel();
        configureStepCounterPanel();

        log.debug("{} was configured", getClass().getName());
    }

    private void configureButtonStart() {
        startButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingController.start();
                setAllDisabled();
                stopButton.setEnabled(true);
                stepDelayPanel.setEnabled(true);

                log.debug("Button start was pressed");
            }
        });

        add(startButton);
    }

    private void configureButtonStop() {
        stopButton.setEnabled(false);
        stopButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingController.stop();
                setAllEnabled();
                stopButton.setEnabled(false);

                log.debug("Button stop was pressed");
            }
        });

        add(stopButton);
    }

    private void configureButtonShuffle() {
        shuffleButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingController.shuffle();

                log.debug("Button shuffle was pressed");
            }
        });

        add(shuffleButton);
    }

    private void configureBarsAmountPanel() {
        barsAmountPanel.setLabelText("Bars amount");
        barsAmountPanel.setValue(ApplicationProperties.STARTUP_BARS_AMOUNT);
        barsAmountPanel.setMinValue(ApplicationProperties.MIN_BAR_AMOUNT);
        barsAmountPanel.setMaxValue(ApplicationProperties.MAX_BAR_AMOUNT);

        add(barsAmountPanel);
    }

    private void configureStepDelayPanel() {
        stepDelayPanel.setLabelText("Step delay");
        stepDelayPanel.setValue(ApplicationProperties.STARTUP_STEP_DELAY);
        stepDelayPanel.setMinValue(ApplicationProperties.MIN_STEP_DELAY);
        stepDelayPanel.setMaxValue(ApplicationProperties.MAX_STEP_DELAY);

        add(stepDelayPanel);
    }

    private void configureStepCounterPanel() {
        add(stepCounterPanel);
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
