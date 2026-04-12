package app.panel;

import app.component.button.StartButton;
import app.component.button.StopButton;

import javax.swing.*;
import java.awt.*;

public class StatisticMonitor extends AdaptivePanel {
    private final JButton buttonStart = new StartButton();
    private final JButton buttonStop = new StopButton();

    public StatisticMonitor() {
        setBackground(new Color(150, 200, 100));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(buttonStart);
        add(buttonStop);
    }

    public void setActionOnStart(Runnable runnable) {
        buttonStart.addActionListener(e -> runnable.run());
    }

    public void setActionOnStop(Runnable runnable) {
        buttonStart.addActionListener(e -> runnable.run());
    }

    @Override
    protected float getWidthCorrelation() {
        return 1.0f;
    }

    @Override
    protected float getHeightCorrelation() {
        return 0.40f;
    }

}
