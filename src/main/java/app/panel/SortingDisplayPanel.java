package app.panel;

import app.controller.SimpleSortingController;
import app.listener.UpdateBarsOnScreenListener;
import lombok.Getter;
import app.controller.BarAlignmentController;
import app.drawer.SortingDisplayDrawer;
import app.model.Bar;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
public class SortingDisplayPanel extends JPanel implements UpdateBarsOnScreenListener {
    private final BarAlignmentController alignmentController;

    private final SortingDisplayDrawer sortingDisplayDrawer;

    private final SimpleSortingController sortingController;

    private List<Bar> bars;

    public SortingDisplayPanel(SimpleSortingController sortingController) {
        this.sortingController = sortingController;
        this.alignmentController = new BarAlignmentController();
        this.sortingDisplayDrawer = new SortingDisplayDrawer();

        configure();
        sortingController.setUpdateBarsOnScreenListener(this);
    }

    private void configure() {
        setBackground(new Color(100, 222, 222));
    }

    @Override
    public void update(List<Bar> bars) {
        this.bars = bars;
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
