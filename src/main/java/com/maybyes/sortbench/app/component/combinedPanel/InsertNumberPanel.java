package com.maybyes.sortbench.app.component.combinedPanel;

import com.maybyes.sortbench.app.component.button.GreyButton;
import com.maybyes.sortbench.app.component.label.ApplicationLabel;
import com.maybyes.sortbench.app.component.textField.OnlyIntegerField;
import com.maybyes.sortbench.app.exception.IntegerBoundsViolationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.IntConsumer;

public class InsertNumberPanel extends JPanel implements ActionListener {
    private static final int HINT_LABEL_FONT_SIZE = 12;

    private final GridBagConstraintsFactory gridBagConstraintsFactory;

    private final ApplicationLabel valueLabel;

    private final ApplicationLabel hintLabel;

    private final OnlyIntegerField valueField;

    private final GreyButton confirmButton;

    private IntConsumer integerConsumer;

    private int minValue = 0;

    private int maxValue = 0;

    public InsertNumberPanel() {
        this.gridBagConstraintsFactory = new GridBagConstraintsFactory();
        this.valueLabel = new ApplicationLabel();
        this.hintLabel = new ApplicationLabel();
        this.valueField = new OnlyIntegerField();
        this.confirmButton = new GreyButton("Insert", 110, 25);

        configure();
    }

    private void configure() {
        setLayout(new GridBagLayout());

        configureValueLabel();
        configureOnlyIntegerField();
        configureHintLabel();
        configureConfirmButton();
    }

    private void configureValueLabel() {
        GridBagConstraints valueLabelConstraints = gridBagConstraintsFactory.getValueLabelConstraints();

        add(valueLabel, valueLabelConstraints);
    }

    private void configureOnlyIntegerField() {
        GridBagConstraints valueFieldConstraints = gridBagConstraintsFactory.getValueFieldConstraints();
        valueField.setColumns(5);

        add(valueField, valueFieldConstraints);
    }

    private void configureHintLabel() {
        GridBagConstraints hintLabelConstraints = gridBagConstraintsFactory.getHintLabelConstraints();
        Font font = ApplicationLabel.DEFAULT_FONT.deriveFont(Float.valueOf(HINT_LABEL_FONT_SIZE));
        String text = getHintText();
        hintLabel.setFont(font);
        hintLabel.setText(text);

        add(hintLabel, hintLabelConstraints);
    }

    private String getHintText() {
        return minValue + " <= x <= " + maxValue;
    }

    private void configureConfirmButton() {
        GridBagConstraints confirmButtonConstraints = gridBagConstraintsFactory.getConfirmButtonConstraints();
        confirmButton.addActionListener(this);

        add(confirmButton, confirmButtonConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        assert(integerConsumer != null);

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

    public void setIntegerConsumer(IntConsumer intConsumer) {
        this.integerConsumer = intConsumer;
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

    private static class GridBagConstraintsFactory {

        private GridBagConstraints getValueLabelConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridheight = 2;
            constraints.weightx = 1.0;
            constraints.weighty = 1.0;
            constraints.fill = GridBagConstraints.NONE;
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.insets = new Insets(3, 6, 3, 8);
            return constraints;
        }

        private GridBagConstraints getValueFieldConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 1;
            constraints.gridy = 0;
            constraints.weightx = 1.0;
            constraints.weighty = 0;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.anchor = GridBagConstraints.SOUTH;
            constraints.insets = new Insets(6, 4, 2, 6);
            return constraints;
        }

        private GridBagConstraints getHintLabelConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 1;
            constraints.gridy = 1;
            constraints.weightx = 1.0;
            constraints.weighty = 0;
            constraints.fill = GridBagConstraints.NONE;
            constraints.anchor = GridBagConstraints.NORTH;
            constraints.insets = new Insets(2, 4, 6, 4);
            return constraints;
        }

        private GridBagConstraints getConfirmButtonConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.weightx = 1.0;
            constraints.weighty = 0;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridwidth = 2;
            constraints.insets = new Insets(4, 10, 6, 10);
            return constraints;
        }

    }

}
