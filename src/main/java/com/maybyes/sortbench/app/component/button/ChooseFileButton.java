package com.maybyes.sortbench.app.component.button;

import java.awt.*;

public class ChooseFileButton extends ApplicationButton {
    private final static String TITLE = "Choose File";

    private final static int FONT_SIZE = 16;

    private final static int WIDTH = 120;

    private final static int HEIGHT = 25;

    private final static int ARC_WIDTH = 10;

    private final static int ARC_HEIGHT = 10;

    public ChooseFileButton() {
        super(TITLE);
    }

    @Override
    public Dimension getButtonSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    public Font getFont() {
        return super.getFont().deriveFont(Float.valueOf(FONT_SIZE));
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
