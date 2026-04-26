package app.controller;

import app.model.Bar;
import app.model.DoubleDimension;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.List;

public class BarAlignmentController {
    private static final double BAR_MARGIN_TOP = 100.0;

    private static final double BAR_MARGIN_BOTTOM = 10.0;

    private static final double BAR_HORIZONTAL_GAP = 4.0;

    private static final double BAR_MARGIN_SIDE = 10.0;

    public void align(List<Bar> bars, Dimension2D environmentSize) {
        double barWidth = calculateBarWidth(bars, environmentSize);
        double barHeightUnit = calculateHeightUnit(bars, environmentSize);

        for (int i = 0; i < bars.size(); i++) {
            placeBar(bars.get(i), i, barWidth, barHeightUnit, environmentSize);
        }
    }

    private double calculateBarWidth(List<Bar> bars, Dimension2D environmentSize) {
        double spacing = (bars.size() - 1) * BAR_HORIZONTAL_GAP + BAR_MARGIN_SIDE * 2;
        double allowedSpace = environmentSize.getWidth() - spacing;

        return allowedSpace / bars.size();
    }

    private double calculateHeightUnit(List<Bar> bars, Dimension2D environmentSize) {
        double allowedSpace = environmentSize.getHeight() - BAR_MARGIN_TOP - BAR_MARGIN_BOTTOM;
        Bar highestBar = bars.stream()
                .max(Bar::compareTo)
                .orElseThrow(RuntimeException::new);

        return allowedSpace / highestBar.getValue();
    }

    private void placeBar(Bar bar, int index, double barWidth, double heightUnit, Dimension2D environmentSize) {
        Dimension2D size = new DoubleDimension(barWidth, heightUnit * bar.getValue());
        Point2D.Double position = computeBarPlacement(index, size, environmentSize);

        bar.setBarSize(size);
        bar.setBarPosition(position);
    }

    private Point2D.Double computeBarPlacement(int index, Dimension2D barSize, Dimension2D environmentSize) {
        double x = index * (barSize.getWidth() + BAR_HORIZONTAL_GAP) + BAR_MARGIN_SIDE;
        double y = environmentSize.getHeight() - barSize.getHeight() - BAR_MARGIN_BOTTOM;

        return new Point2D.Double(x, y);
    }

}
