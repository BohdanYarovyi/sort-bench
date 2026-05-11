package com.maybyes.sortbench.app.drawer;

import com.maybyes.sortbench.app.ApplicationProperties;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.RoundRectangle2D;

public class SortingDisplayHintDrawer {
    private static final int PADDING_TOP = 15;

    private static final int PADDING_LEFT = 20;

    private static final int GAP = 10;

    private static final int HINT_GAP = 5;

    private static final int RECTANGLE_SIZE = 10;

    private final DisplayHint[] hints;

    private final Font font;

    private int hintPointerPosition;

    public SortingDisplayHintDrawer() {
        hints = new DisplayHint[]{
                new DisplayHint(
                        "Compare",
                        Color.BLACK,
                        ApplicationProperties.BAR_COMPARE_COLOR,
                        Color.BLACK
                ),
                new DisplayHint(
                        "Swap",
                        Color.BLACK,
                        ApplicationProperties.BAR_SWAP_COLOR,
                        Color.BLACK
                ),
                new DisplayHint(
                        "Peek",
                        Color.BLACK,
                        ApplicationProperties.BAR_PEEK_COLOR,
                        Color.BLACK
                ),
                new DisplayHint(
                        "Set",
                        Color.BLACK,
                        ApplicationProperties.BAR_SET_COLOR,
                        Color.BLACK
                )
        };
        font = ApplicationProperties.getDefaultFont().deriveFont(20.0f);
    }

    public void drawHints(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics.create();

        graphics.setFont(font);
        hintPointerPosition = PADDING_LEFT;
        for (int i = 0; i < hints.length; i++) {
            drawHint(i, g);
        }
    }

    private void drawHint(int i, Graphics2D g) {
        DisplayHint hint = hints[i];
        FontMetrics fontMetrics = g.getFontMetrics();
        double textWidth = fontMetrics.stringWidth(hint.labelText);
        RoundRectangle2D rectangle = new RoundRectangle2D.Float(hintPointerPosition, PADDING_TOP, RECTANGLE_SIZE, RECTANGLE_SIZE, 4.0f, 4.0f);

        g.setColor(hint.innnerColor);
        g.fill(rectangle);
        g.setColor(hint.borderColor);
        g.draw(rectangle);
        hintPointerPosition += RECTANGLE_SIZE + HINT_GAP;

        g.setColor(hint.textColor);
        g.drawString(hint.labelText, hintPointerPosition, PADDING_TOP  + 10);
        hintPointerPosition += (int) (textWidth + GAP);
    }

    private record DisplayHint(
            String labelText,
            Color textColor,
            Color innnerColor,
            Color borderColor
    ) {
    }

}
