package com.maybyes.sortbench.app.panel;

import com.maybyes.sortbench.abstraction.SortAlgorithm;
import com.maybyes.sortbench.app.component.button.ApplicationButton;
import com.maybyes.sortbench.app.component.button.AddNewFileButton;
import com.maybyes.sortbench.app.component.button.SelectAlgorithmButton;
import com.maybyes.sortbench.app.component.dialog.ApplicationFileChooser;
import com.maybyes.sortbench.app.listener.SelectSortAlgorithmListener;
import com.maybyes.sortbench.app.model.AlgorithmListItem;
import com.maybyes.sortbench.app.util.algorithm.loader.BuiltinAlgorithmLoader;
import com.maybyes.sortbench.app.util.algorithm.loader.FileChosenAlgorithmLoader;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@Getter
@Setter
public class AlgorithmSelectorPanel extends JPanel {
    private final static String LIST_TITLE = "Algorithms";

    private final static Dimension MINIMUM_SIZE = new Dimension(200, 0);

    private final GridBagConstraintFactory constraintsFactory;

    private final BuiltinAlgorithmLoader builtinAlgorithmLoader;

    private final FileChosenAlgorithmLoader fileChosenAlgorithmLoader;

    private final DefaultListModel<AlgorithmListItem> items;

    private final JList<AlgorithmListItem> selectionList;

    private final ApplicationButton selectItemButton;

    private final ApplicationButton addFileButton;

    private SelectSortAlgorithmListener selectSortAlgorithmListener;

    public AlgorithmSelectorPanel() {
        constraintsFactory = new GridBagConstraintFactory();
        builtinAlgorithmLoader = new BuiltinAlgorithmLoader();
        fileChosenAlgorithmLoader = new FileChosenAlgorithmLoader();

        items = new DefaultListModel<>();
        selectionList = new JList<>(items);
        selectItemButton = new SelectAlgorithmButton();
        addFileButton = new AddNewFileButton();

        loadBuiltinAlgorithms();
    }

    public void configure() {
        setLayout(new GridBagLayout());

        configureSelectionList();
        configureAddFileButton();
        configureSelectButton();

        setMinimumSize(MINIMUM_SIZE);

        log.debug("{} was configured", getClass().getName());
    }

    private void configureSelectionList() {
        selectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(selectionList);
        scrollPane.setBorder(new TitledBorder(LIST_TITLE));

        GridBagConstraints constraints = constraintsFactory.getScrollPaneConstraints();
        add(scrollPane, constraints);
    }

    private void configureAddFileButton() {
        AddFileButtonListener listener = new AddFileButtonListener(fileChosenAlgorithmLoader, items);
        GridBagConstraints constraints = constraintsFactory.getAddFileButtonConstraints();

        addFileButton.addActionListener(listener);
        add(addFileButton, constraints);
    }

    private void configureSelectButton() {
        SelectButtonListener listener = new SelectButtonListener(selectSortAlgorithmListener, selectionList);
        GridBagConstraints constraints = constraintsFactory.getSelectItemButtonConstraints();

        selectItemButton.addActionListener(listener);
        add(selectItemButton, constraints);
    }

    private void loadBuiltinAlgorithms() {
        List<SortAlgorithm> loadedAlgorithms = builtinAlgorithmLoader.load();
        List<AlgorithmListItem> loadedItems = loadedAlgorithms.stream()
                .map(AlgorithmListItem::new)
                .peek(AlgorithmListItem::markAsBuiltin)
                .toList();

        items.addAll(loadedItems);
    }

    private static class AddFileButtonListener implements ActionListener {
        private final FileChosenAlgorithmLoader loader;

        private final DefaultListModel<AlgorithmListItem> items;

        private final ApplicationFileChooser fileChooser;

        private AddFileButtonListener(FileChosenAlgorithmLoader fileChosenAlgorithmLoader,
                                      DefaultListModel<AlgorithmListItem> defaultListModel) {
            this.loader = fileChosenAlgorithmLoader;
            this.items = defaultListModel;
            this.fileChooser = new ApplicationFileChooser();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            fileChooser.setVisible(true);
            File[] files = fileChooser.getFiles();

            List<SortAlgorithm> algorithms = loader.load(files);
            List<AlgorithmListItem> newItems = convertToItems(algorithms);
            addOnlyNew(newItems);

            log.info("Were added {} new sorting algorithms to the selection list", newItems.size());
            log.debug("Were added: {}", newItems);
        }

        private List<AlgorithmListItem> convertToItems(List<SortAlgorithm> algorithms) {
            return algorithms.stream()
                    .map(AlgorithmListItem::new)
                    .toList();
        }

        private void addOnlyNew(List<AlgorithmListItem> newItems) {
            List<AlgorithmListItem> currentItems = Collections.list(items.elements());

            Set<AlgorithmListItem> currentItemsSet = new TreeSet<>(currentItems);
            Set<AlgorithmListItem> newItemsSet = new TreeSet<>(newItems);

            currentItemsSet.addAll(newItemsSet);
            items.clear();
            items.addAll(currentItemsSet);
        }

    }

    private static class SelectButtonListener implements ActionListener {
        private final SelectSortAlgorithmListener selectSortAlgorithmListener;

        private final JList<AlgorithmListItem> selectionList;

        private SelectButtonListener(SelectSortAlgorithmListener selectSortAlgorithmListener,
                                     JList<AlgorithmListItem> selectionList) {
            this.selectSortAlgorithmListener = selectSortAlgorithmListener;
            this.selectionList = selectionList;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!selectionList.isSelectionEmpty()) {
                SortAlgorithm selected = selectionList.getSelectedValue().getAlgorithm();
                selectSortAlgorithmListener.setNewAlgorithm(selected);

                log.debug("Was selected '{}' algorithm by pressing 'Selection button'", selected.getName());
            }
        }

    }

    private static class GridBagConstraintFactory {
        // scroll pane
        private static final int SCROLL_PANE_POS_X = 0;

        private static final int SCROLL_PANE_POS_Y = 1;

        private static final double SCROLL_PANE_WEIGHT_X = 1.0;

        private static final double SCROLL_PANE_WEIGHT_Y = 0.95;

        private static final Insets SCROLL_PANE_INSETS = new Insets(5, 10, 0, 0);

        // add file button
        private static final int ADD_FILE_BUTTON_POS_X = 0;

        private static final int ADD_FILE_BUTTON_POS_Y = 0;

        private static final double ADD_FILE_BUTTON_WEIGHT_X = 1.0;

        private static final double ADD_FILE_BUTTON_WEIGHT_Y = 0.0;

        private static final Insets ADD_FILE_INSETS = new Insets(10, 10, 0, 0);


        // select item button
        private static final int SELECT_ITEM_BUTTON_POS_X = 0;

        private static final int SELECT_ITEM_BUTTON_POS_Y = 2;

        private static final double SELECT_ITEM_BUTTON_WEIGHT_X = 1.0;

        private static final double SELECT_ITEM_BUTTON_WEIGHT_Y = 0.05;

        private static final Insets SELECT_ITEM_BUTTON_INSETS = new Insets(5, 10, 10, 0);


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

        private GridBagConstraints getAddFileButtonConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = ADD_FILE_BUTTON_POS_X;
            constraints.gridy = ADD_FILE_BUTTON_POS_Y;
            constraints.weightx = ADD_FILE_BUTTON_WEIGHT_X;
            constraints.weighty = ADD_FILE_BUTTON_WEIGHT_Y;
            constraints.insets = ADD_FILE_INSETS;

            return constraints;
        }

        private GridBagConstraints getSelectItemButtonConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = SELECT_ITEM_BUTTON_POS_X;
            constraints.gridy = SELECT_ITEM_BUTTON_POS_Y;
            constraints.weightx = SELECT_ITEM_BUTTON_WEIGHT_X;
            constraints.weighty = SELECT_ITEM_BUTTON_WEIGHT_Y;
            constraints.insets = SELECT_ITEM_BUTTON_INSETS;

            return constraints;
        }

    }

}
