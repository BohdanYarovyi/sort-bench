package com.maybyes.sortbench.app.component.button;

import java.awt.*;

public class StartButton extends ApplicationButton {
    public static final String BUTTON_TEXT = "Start";

    public StartButton() {
        super(BUTTON_TEXT);
    }

    @Override
    public ButtonColorTheme getIdleTheme() {
        return new ButtonColorTheme(
                new Color(30, 160, 30),   // фон — насичений зелений
                new Color(255, 255, 255), // текст — білий
                new Color(20, 110, 20)    // бордер — темніший зелений
        );
    }

    @Override
    public ButtonColorTheme getHoverTheme() {
        return new ButtonColorTheme(
                new Color(50, 185, 50),   // фон — світліший
                new Color(255, 255, 255), // текст — білий
                new Color(20, 110, 20)    // бордер — той самий
        );
    }

    @Override
    public ButtonColorTheme getPressedTheme() {
        return new ButtonColorTheme(
                new Color(15, 110, 15),   // фон — темний, "натиснутий"
                new Color(220, 220, 220), // текст — трохи сірий
                new Color(10, 70, 10)     // бордер — дуже темний
        );
    }
}
