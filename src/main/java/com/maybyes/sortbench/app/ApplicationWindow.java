package com.maybyes.sortbench.app;

import com.maybyes.sortbench.app.panel.AlgorithmWorkspacePanel;
import com.maybyes.sortbench.app.panel.AlgorithmSelectorPanel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;

public class ApplicationWindow extends JFrame {
    private static final Color SPLIT_PANEL_COLOR = new Color(140, 150, 165);

    private final AlgorithmSelectorPanel algorithmSelectorPanel;

    private final AlgorithmWorkspacePanel algorithmWorkspacePanel;

    private final Dimension userScreenSize;

    private final Dimension initialSize;

    private final Dimension minimumSize;

    public ApplicationWindow() {
        algorithmSelectorPanel = new AlgorithmSelectorPanel();
        algorithmWorkspacePanel = new AlgorithmWorkspacePanel();
        userScreenSize = ApplicationProperties.getUserScreenSize();
        initialSize = getInitialFrameSize();
        minimumSize = getMinimumFrameSize();

        algorithmSelectorPanel.setSelectSortAlgorithmListener(algorithmWorkspacePanel);
        algorithmSelectorPanel.configure();
        algorithmWorkspacePanel.configure();

        customizeFrame();
        configurePanels();
        setVisible(true);
    }

    private void customizeFrame() {
        setTitle(ApplicationProperties.APPLICATION_NAME);
        setSize(initialSize);
        setMinimumSize(minimumSize);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configurePanels() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(algorithmSelectorPanel);
        splitPane.setRightComponent(algorithmWorkspacePanel);
        splitPane.setResizeWeight(0);
        splitPane.setBorder(null);
        splitPane.setUI(new BasicSplitPaneUI() {
            @Override
            public BasicSplitPaneDivider createDefaultDivider() {
                return new BasicSplitPaneDivider(this) {
                    @Override
                    public void paint(Graphics g) {
                        g.setColor(SPLIT_PANEL_COLOR);
                        g.fillRect(0, 0, getSize().width, getSize().height);
                    }
                };
            }
        });

        add(splitPane, BorderLayout.CENTER);
    }

    private Dimension getInitialFrameSize() {
        int width = (int) (userScreenSize.width * ApplicationProperties.FRAME_WIDTH_DELTA);
        int height = (int) (userScreenSize.height * ApplicationProperties.FRAME_HEIGHT_DELTA);

        return new Dimension(width, height);
    }

    private Dimension getMinimumFrameSize() {
        int width = (int) (userScreenSize.width * ApplicationProperties.FRAME_MINIMUM_WIDTH_DELTA);
        int height = (int) (userScreenSize.height * ApplicationProperties.FRAME_MINIMUM_HEIGHT_DELTA);

        return new Dimension(width, height);
    }

}
