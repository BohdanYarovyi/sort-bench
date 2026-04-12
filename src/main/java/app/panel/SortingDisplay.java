package app.panel;

import lombok.Getter;
import app.controller.BarAlignmentController;
import app.drawer.SortingDisplayDrawer;
import app.model.Bar;

import java.awt.*;
import java.util.List;

@Getter
public class SortingDisplay extends AdaptivePanel {
    private final BarAlignmentController alignmentController;
    private final SortingDisplayDrawer sortingDisplayDrawer;
    private List<Bar> bars;

    public SortingDisplay(BarAlignmentController alignmentController, SortingDisplayDrawer sortingDisplayDrawer) {
        this.alignmentController = alignmentController;
        this.sortingDisplayDrawer = sortingDisplayDrawer;

        setBackground(new Color(100, 222, 222));
    }

    public void update(List<Bar> bars) {
        alignmentController.placeBars(bars, getSize());
        this.bars = bars;
        repaint();
    }

    @Override
    protected float getWidthCorrelation() {
        return 1.0f;
    }

    @Override
    protected float getHeightCorrelation() {
        return 0.60f;
    }

    @Override
    protected void updateOnResize() {
        if (bars != null) {
            alignmentController.placeBars(bars, getSize());
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bars != null) {
            sortingDisplayDrawer.drawBars(bars, (Graphics2D) g);
        }
    }

}
