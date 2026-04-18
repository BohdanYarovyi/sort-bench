package app.panel;

import app.component.button.StartButton;
import app.component.button.ShuffleButton;
import app.component.button.StopButton;
import app.controller.SortingController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StatisticMonitorPanel extends JPanel {
    private final SortingController controller;

    public StatisticMonitorPanel(SortingController sortingController) {
        controller = sortingController;
        setLayout(new FlowLayout());

        configureButtonStart();
        configureButtonShuffle();
        configureButtonStop();
    }

    private void configureButtonStart() {
        JButton button = new StartButton();
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.start();
            }
        });

        add(button);
    }

    private void configureButtonShuffle() {
        JButton button = new ShuffleButton();
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.shuffle();
            }
        });

        add(button);
    }

    private void configureButtonStop() {
        JButton button = new StopButton();
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button stop was clicked");
            }
        });

        add(button);
    }

}
