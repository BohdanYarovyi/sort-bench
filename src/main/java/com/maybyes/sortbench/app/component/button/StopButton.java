package com.maybyes.sortbench.app.component.button;

import java.awt.*;

public class StopButton extends ApplicationButton {
    public static final String BUTTON_TEXT = "Stop";

    public StopButton() {
        super(BUTTON_TEXT);
    }

    @Override
    public ButtonColorTheme getIdleTheme() {
        return new ButtonColorTheme(
                new Color(200, 30, 30),
                new Color(255, 255, 255),
                new Color(140, 20, 20)
        );
    }

    @Override
    public ButtonColorTheme getHoverTheme() {
        return new ButtonColorTheme(
                new Color(220, 50, 50),
                new Color(255, 255, 255),
                new Color(140, 20, 20)
        );
    }

    @Override
    public ButtonColorTheme getPressedTheme() {
        return new ButtonColorTheme(
                new Color(150, 20, 20),
                new Color(220, 220, 220),
                new Color(100, 10, 10)
        );
    }

}
