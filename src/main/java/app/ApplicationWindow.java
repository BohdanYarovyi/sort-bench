package app;

import app.panel.AlgorithmDashboard;
import app.panel.ScratchCollection;

import javax.swing.*;
import java.awt.*;

public class ApplicationWindow extends JFrame {
    public static final String DEFAULT_TITLE = "Algorithm Tester";
    public static final double DEFAULT_WINDOW_WIDTH_DELTA = 0.75;
    public static final double DEFAULT_WINDOW_HEIGHT_DELTA = 0.75;
    public static final double DEFAULT_MINIMUM_WINDOW_WIDTH_DELTA = 0.5;
    public static final double DEFAULT_MINIMUM_WINDOW_HEIGHT_DELTA = 0.5;

    private final Dimension initialSize;
    private final Dimension minimumSize;

    public ApplicationWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.initialSize = new Dimension(
                (int) (screenSize.width * DEFAULT_WINDOW_WIDTH_DELTA),
                (int) (screenSize.height * DEFAULT_WINDOW_HEIGHT_DELTA)
        );
        this.minimumSize = new Dimension(
                (int) (screenSize.width * DEFAULT_MINIMUM_WINDOW_WIDTH_DELTA),
                (int) (screenSize.height * DEFAULT_MINIMUM_WINDOW_HEIGHT_DELTA)
        );

        setupWindow();
        configurePanels();
    }


    private void setupWindow() {
        this.setTitle(DEFAULT_TITLE);
        this.setSize(initialSize);
        this.setMinimumSize(minimumSize);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void configurePanels() {
        this.setLayout(new BorderLayout());
        this.add(new ScratchCollection(), BorderLayout.WEST);
        this.add(new AlgorithmDashboard(), BorderLayout.CENTER);
    }

}
