package app.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public abstract class AdaptivePanel extends JPanel {

    public AdaptivePanel() {}

    @Override
    public void addNotify() {
        super.addNotify();
        // calls when a panel added to the window
        Container parent = this.getParent();
        updatePreferredSize(parent.getSize());
        updateOnResize();
        repaint();

        parent.addComponentListener(new ComponentAdapter() {
            // when the main frame is resized,
            // the panel adjusts new size
            @Override
            public void componentResized(ComponentEvent e) {
                updatePreferredSize(parent.getSize());
                revalidate();

                SwingUtilities.invokeLater(() -> {
                    updateOnResize();
                    repaint();
                });
            }
        });
    }

    private void updatePreferredSize(Dimension parentSize) {
        int width = (int) (parentSize.width * getWidthCorrelation());
        int height = (int) (parentSize.height * getHeightCorrelation());

        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
    }

    protected void updateOnResize() {
        // noop
    }

    protected abstract float getWidthCorrelation();

    protected abstract float getHeightCorrelation();

}
