package com.maybyes.sortbench.app.component.button;

import java.awt.*;

public class GreyButton extends ApplicationButton {
    private final Dimension size;

    public GreyButton(String text, int w, int h) {
        super(text);
        this.size = new Dimension(w, h);
        setPreferredSize(getButtonSize());
    }

    @Override
    public Dimension getButtonSize() {
        return size;
    }

    @Override
    public ButtonColorTheme getIdleTheme() {
        return new ButtonColorTheme(
                new Color(189, 189, 189),
                new Color(33, 33, 33),
                new Color(117, 117, 117)
        );
    }

    @Override
    public ButtonColorTheme getHoverTheme() {
        return new ButtonColorTheme(
                new Color(158, 158, 158),
                new Color(33, 33, 33),
                new Color(97, 97, 97)
        );
    }

    @Override
    public ButtonColorTheme getPressedTheme() {
        return new ButtonColorTheme(
                new Color(117, 117, 117),
                new Color(255, 255, 255),
                new Color(66, 66, 66)
        );
    }
}