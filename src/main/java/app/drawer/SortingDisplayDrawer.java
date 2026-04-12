package app.drawer;

import app.model.Bar;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.util.List;

public class SortingDisplayDrawer {
    private static final String FONT_FAMILY = "New Times Roman";
    private static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_MARGIN_BOTTOM = 8;
    private static final int FONT_SIZE_BASE = 5;
    private static final int FONT_SIZE_MAX = 70;
    private static final double FONT_DELTA_SCALE = 0.45;

    public static final Stroke DEFAULT_BORDER_STOKE = new BasicStroke(2);

    public void drawBars(List<Bar> bars, Graphics2D graphics) {
        for (Bar bar : bars) {
            drawBody(bar, graphics);
            drawBorder(bar, graphics);
            drawLabel(bar, graphics);
        }
    }

    private void drawBody(Bar bar, Graphics2D graphics) {
        Rectangle rectangle = new Rectangle(bar.getPosition(), bar.getSize());

        graphics.setColor(bar.getColor());
        graphics.fill(rectangle);
    }

    private void drawBorder(Bar bar, Graphics2D graphics) {
        Rectangle rectangle = new Rectangle(bar.getPosition(), bar.getSize());

        graphics.setColor(bar.getBorderColor());
        graphics.setStroke(DEFAULT_BORDER_STOKE);
        graphics.draw(rectangle);
    }

    private void drawLabel(Bar bar, Graphics2D graphics) {
        Font font = createFont(bar.getSize().width);
        String text = String.valueOf(bar.getValue());
        Point fontPos = calculateFontPosition(text, font, graphics.getFontRenderContext(), bar);

        graphics.setFont(font);
        graphics.drawString(text, fontPos.x, fontPos.y);
    }

    private Point calculateFontPosition(String text, Font font, FontRenderContext frc, Bar bar) {
        double textWidth = font.getStringBounds(text, frc).getWidth();
        double freeSize = bar.getSize().width - textWidth;

        int x = (int) (bar.getPosition().x + freeSize / 2);
        int y = (int) (bar.getPosition().y - FONT_MARGIN_BOTTOM);

        return new Point(x, y);
    }

    private Font createFont(int barWidth) {
        int scaledSize = (int) (FONT_SIZE_BASE + barWidth * FONT_DELTA_SCALE);
        int font = Math.min(scaledSize, FONT_SIZE_MAX);

        return new Font(FONT_FAMILY, FONT_STYLE, font);
    }

}
