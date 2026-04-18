package app.component.button;

import java.awt.*;

public class ShuffleButton extends ApplicationButton {
    private final int buttonWidth = 200;

    public ShuffleButton() {
        super("Shuffle");
    }

    @Override
    public ButtonColorTheme getIdleTheme() {
        return new ButtonColorTheme(
                new Color(60, 120, 200),
                new Color(255, 255, 255),
                new Color(40, 90, 160)
        );
    }

    @Override
    public ButtonColorTheme getHoverTheme() {
        return new ButtonColorTheme(
                new Color(80, 140, 220),
                new Color(255, 255, 255),
                new Color(40, 90, 160)
        );
    }

    @Override
    public ButtonColorTheme getPressedTheme() {
        return new ButtonColorTheme(
                new Color(40, 90, 160),
                new Color(210, 210, 210),
                new Color(20, 60, 120)
        );
    }

    @Override
    public Dimension getButtonSize() {
        Dimension size = super.getButtonSize();
        return new Dimension(buttonWidth, size.height);
    }
}
