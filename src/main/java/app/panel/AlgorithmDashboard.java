package app.panel;

import app.controller.SortingController;
import app.drawer.SortingDisplayDrawer;
import app.controller.BarAlignmentController;

import java.awt.*;

public class AlgorithmDashboard extends AdaptivePanel {
    private final SortingDisplay sortingDisplay;
    private final StatisticMonitor statisticMonitor;
    private final SortingController controller;

    public AlgorithmDashboard() {
        setBackground(new Color(160, 160,255));
        sortingDisplay = new SortingDisplay(new BarAlignmentController(), new SortingDisplayDrawer());
        statisticMonitor = new StatisticMonitor();
        controller = new SortingController(sortingDisplay);

        statisticMonitor.setActionOnStart(controller::start);

        configurePanels();
    }

    private void configurePanels() {
        this.setLayout(new BorderLayout());
        this.add(sortingDisplay, BorderLayout.NORTH);
        this.add(statisticMonitor, BorderLayout.SOUTH);
    }

    @Override
    protected float getWidthCorrelation() {
        return 0.775f;
    }

    @Override
    protected float getHeightCorrelation() {
        return 1.0f;
    }

}
