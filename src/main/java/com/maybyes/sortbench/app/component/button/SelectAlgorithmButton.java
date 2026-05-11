package com.maybyes.sortbench.app.component.button;

import java.awt.*;

public class SelectAlgorithmButton extends ApplicationButton {
    private final static String TITLE = "Select";

    private final static int FONT_SIZE = 20;

    private final static int WIDTH = 140;

    private final static int HEIGHT = 30;

    private final static int ARC_WIDTH = 13;

    private final static int ARC_HEIGHT = 13;

    public SelectAlgorithmButton() {
        super(TITLE);
    }

    @Override
    public Dimension getButtonSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    public Integer getArcWidth() {
        return ARC_WIDTH;
    }

    @Override
    public Integer getArcHeight() {
        return ARC_HEIGHT;
    }

    @Override
    public Font getFont() {
        return super.getFont().deriveFont(Float.valueOf(FONT_SIZE));
    }

    @Override
    public ButtonColorTheme getIdleTheme() {
        return new ButtonColorTheme(
                new Color(60, 60, 60),
                new Color(200, 200, 200),
                new Color(90, 90, 90)
        );
    }

    @Override
    public ButtonColorTheme getHoverTheme() {
        return new ButtonColorTheme(
                new Color(80, 80, 80),
                new Color(220, 220, 220),
                new Color(110, 110, 110)
        );
    }

    @Override
    public ButtonColorTheme getPressedTheme() {
        return new ButtonColorTheme(
                new Color(45, 45, 45),
                new Color(180, 180, 180),
                new Color(70, 70, 70)
        );
    }

}
