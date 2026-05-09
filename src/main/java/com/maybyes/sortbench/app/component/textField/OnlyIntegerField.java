package com.maybyes.sortbench.app.component.textField;

import com.maybyes.sortbench.app.ApplicationProperties;
import com.maybyes.sortbench.app.exception.IntegerBoundsViolationException;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

@Setter
public class OnlyIntegerField extends JFormattedTextField {
    private static final NumberFormat DEFAULT_FORMATTER = NumberFormat.getIntegerInstance();

    private static final Font DEFAULT_FONT = ApplicationProperties.getDefaultFont();

    private int minValue = 0;

    private int maxValue = 0;

    public OnlyIntegerField() {
        super(DEFAULT_FORMATTER);

        configure();
    }

    private void configure() {
        setFont(DEFAULT_FONT);
    }

    public int getValidValue() throws IntegerBoundsViolationException {
        int value = ((Number) getValue()).intValue();

        if (value < minValue || value > maxValue) {
            throw new IntegerBoundsViolationException();
        }

        return value;
    }

    public void setNormalAppearance() {
        setForeground(Color.BLACK);
    }

    public void setErrorAppearance() {
        setForeground(Color.RED);
    }

}
