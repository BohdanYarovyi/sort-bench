package app;

import app.panel.AlgorithmDashboardPanel;
import app.panel.AlgorithmCollectionPanel;

import javax.swing.*;
import java.awt.*;

public class ApplicationWindow extends JFrame {
    private final AlgorithmCollectionPanel algorithmCollectionPanel;

    private final AlgorithmDashboardPanel algorithmDashboardPanel;

    private final Dimension userScreenSize;

    private final Dimension initialSize;

    private final Dimension minimumSize;

    public ApplicationWindow() {
        this.algorithmCollectionPanel = new AlgorithmCollectionPanel();
        this.algorithmDashboardPanel = new AlgorithmDashboardPanel();
        this.userScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.initialSize = getInitialFrameSize();
        this.minimumSize = getMinimumFrameSize();

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
        splitPane.setLeftComponent(algorithmCollectionPanel);
        splitPane.setRightComponent(algorithmDashboardPanel);
        splitPane.setResizeWeight(0.60);
        splitPane.setBorder(null);

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
