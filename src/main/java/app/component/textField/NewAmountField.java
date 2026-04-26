package app.component.textField;

import app.ApplicationProperties;
import app.exception.IntegerBoundsViolationException;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class NewAmountField extends JFormattedTextField {
    private static final int MIN_VALUE = ApplicationProperties.MIN_BAR_AMOUNT;

    private static final int MAX_VALUE = ApplicationProperties.MAX_BAR_AMOUNT;

    private static final Font DEFAULT_FONT = ApplicationProperties.getDefaultFont();

    private static final NumberFormat DEFAULT_FORMATTER = NumberFormat.getIntegerInstance();

    public NewAmountField(int columns) {
        super(DEFAULT_FORMATTER);

        configure(columns);
    }

    private void configure(int columns) {
        setColumns(columns);
        setFont(DEFAULT_FONT);
        setValue(ApplicationProperties.STARTUP_BAR_AMOUNT);
    }

    public int getValidValue() throws IntegerBoundsViolationException {
        int value = ((Number) getValue()).intValue();

        if (value > MAX_VALUE || value < MIN_VALUE) {
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
