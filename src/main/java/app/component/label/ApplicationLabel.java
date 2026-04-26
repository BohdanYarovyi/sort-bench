package app.component.label;

import app.ApplicationProperties;

import javax.swing.*;
import java.awt.*;

public class ApplicationLabel extends JLabel {
    public static final Font DEFAULT_FONT = ApplicationProperties.getDefaultFont();

    public ApplicationLabel() {}

    public ApplicationLabel(String text) {
        super(text);

        setFont(DEFAULT_FONT);
    }

}
