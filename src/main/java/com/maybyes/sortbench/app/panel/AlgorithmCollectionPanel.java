package com.maybyes.sortbench.app.panel;

import com.maybyes.sortbench.app.component.button.GreyButton;
import com.maybyes.sortbench.app.listener.SelectSortAlgorithmListener;
import com.maybyes.sortbench.app.model.AlgorithmListItem;
import com.maybyes.sortbench.app.util.AlgorithmLoader;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

@Getter
@Setter
public class AlgorithmCollectionPanel extends JPanel {
    private final GridBagConstraintConfigurator configurator;
    private final AlgorithmLoader algorithmLoader;

    private final DefaultListModel<AlgorithmListItem> items;
    private final JList<AlgorithmListItem> selectionList;
    private final GreyButton selectButton;

    private SelectSortAlgorithmListener selectSortAlgorithmListener;

    public AlgorithmCollectionPanel() {
        configurator = new GridBagConstraintConfigurator();
        algorithmLoader = new AlgorithmLoader();

        items = new DefaultListModel<>();
        selectionList = new JList<>(items);
        selectButton = new GreyButton("Select", 150, 35);

        loadFiles();
        configure();
    }

    private void configure() {
        setLayout(new GridBagLayout());

        selectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        var scrollPane = new JScrollPane(selectionList);
        scrollPane.setBorder(new TitledBorder("Algorithms"));

        GridBagConstraints scrollPaneConstraints = configurator.getScrollPaneConstraints();
        add(scrollPane, scrollPaneConstraints);

        GridBagConstraints selectButtonConstraints = configurator.getSelectButtonConstraints();
        add(selectButton, selectButtonConstraints);

        selectButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectSortAlgorithmListener.setNewAlgorithm(selectionList.getSelectedValue().getAlgorithm());
            }
        });
    }

    private void loadFiles() {
        List<AlgorithmListItem> builtin = algorithmLoader.loadBuiltinAlgorithms().stream()
                .map(AlgorithmListItem::new)
                .toList();

        items.addAll(builtin);
    }

    private static class GridBagConstraintConfigurator {
        // scroll pane
        private static final int SCROLL_PANE_POS_X = 0;

        private static final int SCROLL_PANE_POS_Y = 0;

        private static final double SCROLL_PANE_WEIGHT_X = 1.0;

        private static final double SCROLL_PANE_WEIGHT_Y = 0.90;

        private static final Insets SCROLL_PANE_INSETS = new Insets(5, 10, 5, 0);

        // select button
        private static final int SELECT_BUTTON_POS_X = 0;

        private static final int SELECT_BUTTON_POS_Y = 1;

        private static final double SELECT_BUTTON_WEIGHT_X = 1.0;

        private static final double SELECT_BUTTON_WEIGHT_Y = 0.10;

        private GridBagConstraints getScrollPaneConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = SCROLL_PANE_POS_X;
            constraints.gridy = SCROLL_PANE_POS_Y;
            constraints.weightx = SCROLL_PANE_WEIGHT_X;
            constraints.weighty = SCROLL_PANE_WEIGHT_Y;
            constraints.insets = SCROLL_PANE_INSETS;
            constraints.fill = GridBagConstraints.BOTH;

            return constraints;
        }

        private GridBagConstraints getSelectButtonConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = SELECT_BUTTON_POS_X;
            constraints.gridy = SELECT_BUTTON_POS_Y;
            constraints.weightx = SELECT_BUTTON_WEIGHT_X;
            constraints.weighty = SELECT_BUTTON_WEIGHT_Y;

            return constraints;
        }

    }

}
