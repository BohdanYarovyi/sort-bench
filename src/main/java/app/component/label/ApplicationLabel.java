package app.component.label;

import app.ApplicationProperties;

import javax.swing.*;
import java.awt.*;

public class ApplicationLabel extends JLabel {
    private static final Font DEFAULT_FONT = ApplicationProperties.getDefaultFont();

    public ApplicationLabel(String text) {
        super(text);

        setFont(DEFAULT_FONT);
    }

    public ApplicationLabel(String text, int fontSize) {
        super(text);

        setFont(DEFAULT_FONT.deriveFont(Float.valueOf(fontSize)));
    }

}
