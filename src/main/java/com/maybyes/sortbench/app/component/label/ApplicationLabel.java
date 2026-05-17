package com.maybyes.sortbench.app.component.label;

import com.maybyes.sortbench.app.ApplicationProperties;

import javax.swing.*;
import java.awt.*;

public class ApplicationLabel extends JLabel {
    public static final int MAX_LINE_LENGTH = 25;

    public static final Font DEFAULT_FONT = ApplicationProperties.getDefaultFont();

    public ApplicationLabel() {}

    public ApplicationLabel(String text) {
        super(text);

        setFont(DEFAULT_FONT);
    }

    @Override
    public void setText(String text) {
        if (text != null && text.length() > MAX_LINE_LENGTH) {
            text = text.substring(0, MAX_LINE_LENGTH) + "...";
        }

        super.setText(text);
    }
}
