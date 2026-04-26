package app.component.combinedPanel;

import app.component.button.GreyButton;
import app.component.label.ApplicationLabel;
import app.component.textField.OnlyIntegerField;
import app.exception.IntegerBoundsViolationException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.IntConsumer;

public class InsertNumberPanel extends JPanel implements ActionListener {
    private static final int HINT_LABEL_FONT_SIZE = 12;

    private static Border getDefaultBorder() {
        Color borderHighlight = new Color(80, 80, 80);
        Color borderShadow = new Color(30, 30, 30);

        return new EtchedBorder(EtchedBorder.RAISED, borderHighlight, borderShadow);
    }

    private final GridBagConstraintsConfigurator configurator;

    private final ApplicationLabel valueLabel;

    private final ApplicationLabel hintLabel;

    private final OnlyIntegerField valueField;

    private final GreyButton confirmButton;

    private final IntConsumer integerConsumer;

    private int minValue = 0;

    private int maxValue = 0;

    public InsertNumberPanel(IntConsumer integerConsumer) {
        this.integerConsumer = integerConsumer;
        this.configurator = new GridBagConstraintsConfigurator();
        this.valueLabel = new ApplicationLabel();
        this.hintLabel = new ApplicationLabel();
        this.valueField = new OnlyIntegerField();
        this.confirmButton = new GreyButton("Insert", 120, 30);

        configureHintLabel();
        configureOnlyIntegerField();
        configure();
    }

    private void configure() {
        setLayout(new GridBagLayout());

        GridBagConstraints valueLabelConstraints = configurator.getValueLabelConstraints();
        add(valueLabel, valueLabelConstraints);

        GridBagConstraints hintLabelConstraints = configurator.getHintLabelConstraints();
        add(hintLabel, hintLabelConstraints);

        GridBagConstraints valueFieldConstraints = configurator.getValueFieldConstraints();
        add(valueField, valueFieldConstraints);

        GridBagConstraints confirmButtonConstraints = configurator.getConfirmButtonConstraints();
        confirmButton.addActionListener(this);
        add(confirmButton, confirmButtonConstraints);

        setBorder(getDefaultBorder());
    }

    private void configureHintLabel() {
        Font font = ApplicationLabel.DEFAULT_FONT.deriveFont(Float.valueOf(HINT_LABEL_FONT_SIZE));
        String text = getHintText();

        hintLabel.setFont(font);
        hintLabel.setText(text);
    }

    private String getHintText() {
        return minValue + " <= x <= " + maxValue;
    }

    private void configureOnlyIntegerField() {
        valueField.setColumns(5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int fieldValue = valueField.getValidValue();
            integerConsumer.accept(fieldValue);
            valueField.setNormalAppearance();
        } catch (IntegerBoundsViolationException ex) {
            valueField.setErrorAppearance();
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (Component component : getComponents()) {
            component.setEnabled(enabled);
        }
    }

    public void setValue(int value) {
        valueField.setValue(value);
    }

    public void setLabelText(String text) {
        valueLabel.setText(text);
    }

    public void setMinValue(int value) {
        this.minValue = value;
        valueField.setMinValue(value);
        hintLabel.setText(getHintText());
    }

    public void setMaxValue(int value) {
        this.maxValue = value;
        valueField.setMaxValue(value);
        hintLabel.setText(getHintText());
    }

    private static class GridBagConstraintsConfigurator {
        // value label
        private static final int VALUE_LABEL_POS_X = 0;

        private static final int VALUE_LABEL_POS_Y = 0;

        private static final double VALUE_LABEL_WEIGHT_X = 1.0;

        private static final double VALUE_LABEL_WEIGHT_Y = 1.00;

        private static final Insets VALUE_LABEL_PADDING = new Insets(5, 10, 0, 10);

        // value field
        private static final int VALUE_FIELD_POS_X = 1;

        private static final int VALUE_FIELD_POS_Y = 0;

        private static final double VALUE_FIELD_WEIGHT_X = 1.00;

        private static final double VALUE_FIELD_WEIGHT_Y = 1.00;

        private static final Insets VALUE_FIELD_PADDING = new Insets(5, 10, 0, 10);

        // hint label
        private static final int HINT_LABEL_POS_X = 1;

        private static final int HINT_LABEL_POS_Y = 1;

        private static final double HINT_LABEL_WEIGHT_X = 1.00;

        private static final double HINT_LABEL_WEIGHT_Y = 1.00;

        private static final Insets HINT_LABEL_PADDING = new Insets(0, 20, 5, 20);

        // confirm button
        private static final int CONFIRM_BUTTON_POS_X = 0;

        private static final int CONFIRM_BUTTON_POS_Y = 2;

        private static final double CONFIRM_BUTTON_WEIGHT_X = 1.00;

        private static final double CONFIRM_BUTTON_WEIGHT_Y = 1.00;

        private static final int CONFIRM_BUTTON_WIDTH_X = 2;

        private static final Insets CONFIRM_BUTTON_PADDING = new Insets(10, 20, 10, 20);

        private GridBagConstraints getValueLabelConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = VALUE_LABEL_POS_X;
            constraints.gridy = VALUE_LABEL_POS_Y;
            constraints.weightx = VALUE_LABEL_WEIGHT_X;
            constraints.weighty = VALUE_LABEL_WEIGHT_Y;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = VALUE_LABEL_PADDING;

            return constraints;
        }

        private GridBagConstraints getValueFieldConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = VALUE_FIELD_POS_X;
            constraints.gridy = VALUE_FIELD_POS_Y;
            constraints.weightx = VALUE_FIELD_WEIGHT_X;
            constraints.weighty = VALUE_FIELD_WEIGHT_Y;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = VALUE_FIELD_PADDING;

            return constraints;
        }

        private GridBagConstraints getHintLabelConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = HINT_LABEL_POS_X;
            constraints.gridy = HINT_LABEL_POS_Y;
            constraints.weightx = HINT_LABEL_WEIGHT_X;
            constraints.weighty = HINT_LABEL_WEIGHT_Y;
            constraints.fill = GridBagConstraints.NONE;
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.insets = HINT_LABEL_PADDING;

            return constraints;
        }

        private GridBagConstraints getConfirmButtonConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = CONFIRM_BUTTON_POS_X;
            constraints.gridy = CONFIRM_BUTTON_POS_Y;
            constraints.weightx = CONFIRM_BUTTON_WEIGHT_X;
            constraints.weighty = CONFIRM_BUTTON_WEIGHT_Y;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridwidth = CONFIRM_BUTTON_WIDTH_X;
            constraints.insets = CONFIRM_BUTTON_PADDING;

            return constraints;
        }

    }

}
