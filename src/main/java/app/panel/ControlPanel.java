package app.panel;

import app.component.button.StartButton;
import app.component.button.ShuffleButton;
import app.component.button.StopButton;
import app.component.combinedPanel.NewValuePanel;
import app.component.combinedPanel.StepCounterPanel;
import app.controller.SimpleSortingController;
import app.controller.SortingController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    private final SimpleSortingController sortingController;

    private final StartButton startButton;

    private final StopButton stopButton;

    private final ShuffleButton shuffleButton;

    private final NewValuePanel newValuePanel;

    private final StepCounterPanel stepCounterPanel;

    public ControlPanel(SimpleSortingController sortingController) {
        this.sortingController = sortingController;
        this.startButton = new StartButton();
        this.stopButton = new StopButton();
        this.shuffleButton = new ShuffleButton();
        this.newValuePanel = new NewValuePanel(sortingController);
        this.stepCounterPanel = new StepCounterPanel();

        sortingController.setUpdateStepCounterListener(stepCounterPanel);

        configureButtonStart();
        configureButtonStop();
        configureButtonShuffle();
        configure();
    }

    private void configure() {
        setLayout(new FlowLayout());

        add(startButton);
        add(stopButton);
        add(shuffleButton);
        add(newValuePanel);
        add(stepCounterPanel);
    }

    private void configureButtonStart() {
        startButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingController.start();
                setAllDisabled();
                stopButton.setEnabled(true);
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

    private void setAllDisabled() {
        startButton.setEnabled(false);
        stopButton.setEnabled(false);
        shuffleButton.setEnabled(false);
        newValuePanel.setEnabled(false);
    }

    private void setAllEnabled() {
        startButton.setEnabled(true);
        stopButton.setEnabled(true);
        shuffleButton.setEnabled(true);
        newValuePanel.setEnabled(true);
    }

}
