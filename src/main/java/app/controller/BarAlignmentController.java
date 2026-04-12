package app.controller;

import app.model.Bar;

import java.awt.*;
import java.util.List;

public class BarAlignmentController {
    public static final int BAR_MARGIN_TOP = 100;
    public static final int BAR_MARGIN_BOTTOM = 10;
    public static final int BAR_HORIZONTAL_GAP = 4;
    public static final int BAR_MARGIN_SIDE = 10;

    public void placeBars(List<Bar> bars, Dimension environmentSize) {
        int barWidth = calculateBarWidth(bars, environmentSize);
        int barHeightUnit = calculateHeightUnit(bars, environmentSize);

        for (int i = 0; i < bars.size(); i++) {
            Bar bar = bars.get(i);

            Dimension size = computeBarSize(barWidth, barHeightUnit, bar.getValue());
            Point placement = computeBarPlacement(i, size, environmentSize);
            bar.setSize(size);
            bar.setPosition(placement);
        }
    }

    private int calculateBarWidth(List<Bar> bars, Dimension environmentSize) {
        double gapSpace = (bars.size() - 1) * BAR_HORIZONTAL_GAP + BAR_MARGIN_SIDE * 2;
        double allowedSpace = environmentSize.width - gapSpace;

        return (int) (allowedSpace / bars.size());
    }

    private int calculateHeightUnit(List<Bar> bars, Dimension environmentSize) {
        Bar highestBar = bars.stream()
                .max(Bar::compareTo)
                .orElse(new Bar(0));

        return (environmentSize.height - BAR_MARGIN_TOP - BAR_MARGIN_BOTTOM) / highestBar.getValue();
    }

    private Dimension computeBarSize(int barWidth, int barHeightUnit, int barValue) {
        return new Dimension(barWidth, barHeightUnit * barValue);
    }

    private Point computeBarPlacement(int index, Dimension barSize, Dimension environmentSize) {
        double x = index * (barSize.width + BAR_HORIZONTAL_GAP) + BAR_MARGIN_SIDE;
        double y = environmentSize.height - barSize.height - BAR_MARGIN_BOTTOM;

        return new Point((int) x, (int) y);
    }

}
