package app.panel;

import lombok.Getter;
import app.controller.BarAlignmentController;
import app.drawer.SortingDisplayDrawer;
import app.model.Bar;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Dimension2D;
import java.util.List;

@Getter
public class SortingDisplayPanel extends JPanel {
    private final BarAlignmentController alignmentController;

    private final SortingDisplayDrawer sortingDisplayDrawer;

    private List<Bar> bars;

    public SortingDisplayPanel() {
        alignmentController = new BarAlignmentController();
        sortingDisplayDrawer = new SortingDisplayDrawer();

        setBackground(new Color(100, 222, 222));
    }

    public void update(List<Bar> bars) {
        this.bars = bars;
        alignmentController.align(bars, getSize());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bars != null) {
            alignmentController.align(bars, getSize());
            sortingDisplayDrawer.drawBars(bars, g.create());
        }
    }

}
