package app.component.combinedPanel;

import app.component.button.GreyButton;
import app.component.label.ApplicationLabel;
import app.component.textField.NewAmountField;
import app.controller.SortingController;
import app.exception.IntegerBoundsViolationException;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static app.ApplicationProperties.MAX_BAR_AMOUNT;
import static app.ApplicationProperties.MIN_BAR_AMOUNT;

public class NewValuePanel extends JPanel implements ActionListener {
    private static final int HINT_LABEL_FONT_SIZE = 12;

    private final GridBagConstraintsConfigurator configurator;

    private final SortingController sortingController;

    private final ApplicationLabel valueLabel;

    private final ApplicationLabel hintLabel;

    private final NewAmountField valueField;

    private final GreyButton confirmButton;

    public NewValuePanel(SortingController sortingController) {
        this.sortingController = sortingController;
        this.configurator = new GridBagConstraintsConfigurator();
        this.valueLabel = new ApplicationLabel("Amount");
        this.valueField = new NewAmountField(5);
        this.confirmButton = new GreyButton("Insert", 120, 30);
        this.hintLabel = createHintLabel();

        configure();
        configureComponents();
    }

    private void configure() {
        Color borderHighlight = new Color(80, 80, 80);
        Color borderShadow = new Color(30, 30, 30);
        setBorder(new EtchedBorder(EtchedBorder.RAISED, borderHighlight, borderShadow));
    }

    private ApplicationLabel createHintLabel() {
        String text = MIN_BAR_AMOUNT + " <= x <= " + MAX_BAR_AMOUNT;

        return new ApplicationLabel(text, HINT_LABEL_FONT_SIZE);
    }

    private void configureComponents() {
        setLayout(new GridBagLayout());

        GridBagConstraints valueLabelConstraints = configurator.getValueLabelConstraints();
        add(valueLabel, valueLabelConstraints);

        GridBagConstraints valueFieldConstraints = configurator.getValueFieldConstraints();
        add(valueField, valueFieldConstraints);

        GridBagConstraints hintLabelConstraints = configurator.getHintLabelConstraints();
        add(hintLabel, hintLabelConstraints);

        GridBagConstraints confirmButtonConstraints = configurator.getConfirmButtonConstraints();
        confirmButton.addActionListener(this);
        add(confirmButton, confirmButtonConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int fieldValue = valueField.getValidValue();
            sortingController.initNewAmount(fieldValue);
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
