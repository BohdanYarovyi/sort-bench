package app.drawer;

import app.ApplicationProperties;
import app.model.Bar;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class SortingDisplayDrawer {
    private static final Stroke DEFAULT_BORDER_STOKE = new BasicStroke(2);

    private final FontManager fontManager;

    private final LabelAlignmentProcessor labelAlignmentProcessor;

    public SortingDisplayDrawer() {
        fontManager = new FontManager();
        labelAlignmentProcessor = new LabelAlignmentProcessor();
    }

    public void drawBars(List<Bar> bars, Graphics graphics) {
        for (Bar bar : bars) {
            drawBody(bar, graphics);
            drawBorder(bar, graphics);
            drawLabel(bar, graphics);
        }
    }

    private void drawBody(Bar bar, Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics.create();
        Color innerColor = bar.getInnerColor();
        Rectangle2D.Double rectangle = toRectangle(bar);

        g.setColor(innerColor);
        g.fill(rectangle);
    }

    private void drawBorder(Bar bar, Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics.create();
        Color borderColor = bar.getBorderColor();
        Rectangle2D.Double rectangle = toRectangle(bar);

        g.setStroke(DEFAULT_BORDER_STOKE);
        g.setColor(borderColor);
        g.draw(rectangle);
    }

    private Rectangle2D.Double toRectangle(Bar bar) {
        double x = bar.getBarPosition().getX();
        double y = bar.getBarPosition().getY();
        double w = bar.getBarSize().getWidth();
        double h = bar.getBarSize().getHeight();

        return new Rectangle2D.Double(x, y, w, h);
    }

    private void drawLabel(Bar bar, Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics.create();
        String text = String.valueOf(bar.getValue());
        Dimension2D barSize = bar.getBarSize();
        Point2D barPosition = bar.getBarPosition();
        FontRenderContext frc = g.getFontRenderContext();
        Font font = fontManager.createFont(barSize.getWidth());
        Point2D position = labelAlignmentProcessor.findLabelPosition(text, font, barPosition, barSize, frc);

        g.setFont(font);
        g.drawString(text, (float) position.getX(), (float) position.getY());
    }

    private static class FontManager {
        private static final String FONT_FAMILY = ApplicationProperties.APPLICATION_FONT;

        private static final int FONT_SIZE_BASE = 5;

        private static final int FONT_SIZE_MAX = 70;

        private static final double FONT_DELTA_SCALE = 0.45;

        private Font createFont(double barWidth) {
            int scaledSize = (int) (FONT_SIZE_BASE + barWidth * FONT_DELTA_SCALE);
            int size = Math.min(scaledSize, FONT_SIZE_MAX);

            return new Font(FONT_FAMILY, Font.BOLD, size);
        }

    }

    private static class LabelAlignmentProcessor {
        private static final double MARGIN_BOTTOM = 10.0;

        private Point2D findLabelPosition(String text, Font font, Point2D barPosition, Dimension2D barSize, FontRenderContext frc) {
            double freeSize = barSize.getWidth() - getTextWidth(text, font, frc);
            double x = barPosition.getX() + freeSize / 2;
            double y = barPosition.getY() - MARGIN_BOTTOM;

            return new Point2D.Double(x, y);
        }

        private double getTextWidth(String text, Font font, FontRenderContext frc) {
            return font.getStringBounds(text, frc).getWidth();
        }

    }

}
