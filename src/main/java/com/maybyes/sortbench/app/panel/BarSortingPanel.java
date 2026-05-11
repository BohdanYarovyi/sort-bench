package com.maybyes.sortbench.app.panel;

import com.maybyes.sortbench.app.controller.SimpleSortingController;
import com.maybyes.sortbench.app.drawer.SortingDisplayHintDrawer;
import com.maybyes.sortbench.app.listener.UpdateBarsOnScreenListener;
import lombok.Getter;
import com.maybyes.sortbench.app.controller.BarAlignmentController;
import com.maybyes.sortbench.app.drawer.SortingDisplayBarDrawer;
import com.maybyes.sortbench.app.model.Bar;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Slf4j
@Getter
public class BarSortingPanel extends JPanel implements UpdateBarsOnScreenListener {
    private final SimpleSortingController sortingController;

    private final BarAlignmentController alignmentController;

    private final SortingDisplayBarDrawer sortingDisplayBarDrawer;

    private final SortingDisplayHintDrawer sortingDisplayHintDrawer;

    private List<Bar> bars;

    public BarSortingPanel(SimpleSortingController sortingController) {
        this.sortingController = sortingController;
        this.alignmentController = new BarAlignmentController();
        this.sortingDisplayBarDrawer = new SortingDisplayBarDrawer();
        this.sortingDisplayHintDrawer = new SortingDisplayHintDrawer();

        this.sortingController.setUpdateBarsOnScreenListener(this);

        configure();
    }

    private void configure() {
        setBackground(new Color(100, 222, 222));

        log.debug("{} was configured", getClass().getName());
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
            sortingDisplayBarDrawer.drawBars(bars, g.create());
            sortingDisplayHintDrawer.drawHints(g.create());
        }
    }

}
