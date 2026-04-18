package app;

import app.panel.AlgorithmDashboardPanel;
import app.panel.AlgorithmCollectionPanel;

import javax.swing.*;
import java.awt.*;

public class ApplicationWindow extends JFrame {
    private final GridBagConstraintsConfigurator configurator;

    private final Dimension userScreenSize;

    private final Dimension initialSize;

    private final Dimension minimumSize;

    public ApplicationWindow() {
        userScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        initialSize = getInitialFrameSize();
        minimumSize = getMinimumFrameSize();
        configurator = new GridBagConstraintsConfigurator();

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
        setLayout(new GridBagLayout());

        JPanel collection = new AlgorithmCollectionPanel();
        GridBagConstraints collectionConstraints = configurator.getCollectionConstraints();
        add(collection, collectionConstraints);

        JPanel dashboard = new AlgorithmDashboardPanel();
        GridBagConstraints dashboardConstraints = configurator.getDashboardConstraints();
        add(dashboard, dashboardConstraints);
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

    private static class GridBagConstraintsConfigurator {
        private static final int ALGORITHM_COLLECTION_POS_X = 0;

        private static final int ALGORITHM_COLLECTION_POS_Y = 0;

        private static final double ALGORITHM_COLLECTION_WEIGHT_X = 0.25;

        private static final double ALGORITHM_COLLECTION_WEIGHT_Y = 1.00;

        private static final int ALGORITHM_DASHBOARD_POS_X = 1;

        private static final int ALGORITHM_DASHBOARD_POS_Y = 0;

        private static final double ALGORITHM_DASHBOARD_WEIGHT_X = 0.75;

        private static final double ALGORITHM_DASHBOARD_WEIGHT_Y = 1.0;

        private GridBagConstraints getCollectionConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = ALGORITHM_COLLECTION_POS_X;
            constraints.gridy = ALGORITHM_COLLECTION_POS_Y;
            constraints.weightx = ALGORITHM_COLLECTION_WEIGHT_X;
            constraints.weighty = ALGORITHM_COLLECTION_WEIGHT_Y;
            constraints.fill = GridBagConstraints.BOTH;

            return constraints;
        }

        private GridBagConstraints getDashboardConstraints() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = ALGORITHM_DASHBOARD_POS_X;
            constraints.gridy = ALGORITHM_DASHBOARD_POS_Y;
            constraints.weightx = ALGORITHM_DASHBOARD_WEIGHT_X;
            constraints.weighty = ALGORITHM_DASHBOARD_WEIGHT_Y;
            constraints.fill = GridBagConstraints.BOTH;

            return constraints;
        }

    }

}
