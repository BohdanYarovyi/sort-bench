package app.component.button;

import java.awt.*;

public class StopButton extends ApplicationButton {
    public static final String BUTTON_TEXT = "Stop";

    public StopButton() {
        super(BUTTON_TEXT);
    }

    @Override
    public ButtonColorTheme getIdleTheme() {
        return new ButtonColorTheme(
                new Color(200, 30, 30),   // фон — насичений червоний
                new Color(255, 255, 255), // текст — білий
                new Color(140, 20, 20)    // бордер — темніший червоний
        );
    }

    @Override
    public ButtonColorTheme getHoverTheme() {
        return new ButtonColorTheme(
                new Color(220, 50, 50),   // фон — трохи світліший
                new Color(255, 255, 255), // текст — білий
                new Color(140, 20, 20)    // бордер — той самий
        );
    }

    @Override
    public ButtonColorTheme getPressedTheme() {
        return new ButtonColorTheme(
                new Color(150, 20, 20),   // фон — темний, "натиснутий"
                new Color(220, 220, 220), // текст — трохи сірий
                new Color(100, 10, 10)    // бордер — дуже темний
        );
    }

}
