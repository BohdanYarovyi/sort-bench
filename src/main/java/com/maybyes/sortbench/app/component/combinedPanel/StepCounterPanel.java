package com.maybyes.sortbench.app.component.combinedPanel;

import com.maybyes.sortbench.app.component.label.ApplicationLabel;
import com.maybyes.sortbench.app.listener.UpdateStepCounterListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class StepCounterPanel extends JPanel implements UpdateStepCounterListener {

    private static Border getDefaultBorder() {
        Color borderHighlight = new Color(80, 80, 80);
        Color borderShadow = new Color(30, 30, 30);

        return new EtchedBorder(EtchedBorder.RAISED, borderHighlight, borderShadow);
    }

    private final GridBagConstraintsConfigurator configurator;

    private final ApplicationLabel titleLabel;

    private final ApplicationLabel valueLabel;

    public StepCounterPanel() {
        this.configurator = new GridBagConstraintsConfigurator();
        this.titleLabel = new ApplicationLabel("Step");
        this.valueLabel = new ApplicationLabel("N/A");

        configure();
    }

    private void configure() {
        setLayout(new GridBagLayout());

        GridBagConstraints titleLabelConstraints = configurator.getTitleLabelConstraints();
        add(titleLabel, titleLabelConstraints);

        GridBagConstraints valueLabelConstraints = configurator.getValueLabelConstraints();
        add(valueLabel, valueLabelConstraints);

        setBorder(getDefaultBorder());
    }

    @Override
    public void update(int currentStep, int allSteps) {
        String text = currentStep + " / " + allSteps;
        valueLabel.setText(text);
    }

    private static class GridBagConstraintsConfigurator {
        // title label
        public static final int TITLE_LABEL_POS_X = 0;

        public static final int TITLE_LABEL_POS_Y = 0;

        public static final double TITLE_LABEL_WEIGHT_X = 1.00;

        public static final double TITLE_LABEL_WEIGHT_Y = 1.00;

        private static final Insets TITLE_LABEL_PADDING = new Insets(10, 10, 10, 10);

        // value label
        public static final int VALUE_LABEL_POS_X = 1;

        public static final int VALUE_LABEL_POS_Y = 0;

        public static final double VALUE_LABEL_WEIGHT_X = 1.00;

        public static final double VALUE_LABEL_WEIGHT_Y = 1.00;

        private static final Insets VALUE_LABEL_PADDING = new Insets(10, 10, 10, 10);

        private GridBagConstraints getTitleLabelConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = TITLE_LABEL_POS_X;
            constraints.gridy = TITLE_LABEL_POS_Y;
            constraints.weightx = TITLE_LABEL_WEIGHT_X;
            constraints.weighty = TITLE_LABEL_WEIGHT_Y;
            constraints.insets = TITLE_LABEL_PADDING;

            return constraints;
        }

        private GridBagConstraints getValueLabelConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = VALUE_LABEL_POS_X;
            constraints.gridy = VALUE_LABEL_POS_Y;
            constraints.weightx = VALUE_LABEL_WEIGHT_X;
            constraints.weighty = VALUE_LABEL_WEIGHT_Y;
            constraints.insets = VALUE_LABEL_PADDING;

            return constraints;
        }

    }

}
