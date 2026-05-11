package com.maybyes.sortbench.app;

import com.maybyes.sortbench.app.util.algorithm.impl.BubbleSortAlgorithm;
import com.maybyes.sortbench.abstraction.SortAlgorithm;

import java.awt.*;

public interface ApplicationProperties {
    String APPLICATION_NAME = "SortBench";

    String APPLICATION_FONT = "Times New Roman";

    String BUILTIN_ALGORITHM_LOCATION = "com/maybyes/sortbench/app/util/algorithm/impl";

    int APPLICATION_FONT_SIZE = 20;

    int STARTUP_BARS_AMOUNT = 20;

    int STARTUP_STEP_DELAY = 150;

    int MIN_BAR_AMOUNT = 5;

    int MAX_BAR_AMOUNT = 100;

    int MIN_STEP_DELAY = 10;

    int MAX_STEP_DELAY = 10_000;

    float FRAME_WIDTH_DELTA = 0.75f;

    float FRAME_HEIGHT_DELTA = 0.75f;

    float FRAME_MINIMUM_WIDTH_DELTA = 0.50F;

    float FRAME_MINIMUM_HEIGHT_DELTA = 0.50F;

    Color BAR_IDLE_COLOR = new Color(151, 151, 151);

    Color BAR_COMPARE_COLOR = new Color(220, 220, 220);

    Color BAR_SWAP_COLOR = new Color(255, 40, 40);

    Color BAR_PEEK_COLOR = new Color(5, 98, 189);

    Color BAR_SET_COLOR = new Color(0, 108, 22);

    Color BAR_BORDER_COLOR = new Color(0,0,0);

    SortAlgorithm DEFAULT_SORT_ALGORITHM = new BubbleSortAlgorithm();

    static Font getDefaultFont() {
        String fontFamily = ApplicationProperties.APPLICATION_FONT;
        int fontSize = ApplicationProperties.APPLICATION_FONT_SIZE;
        int fontStyle = Font.PLAIN;

        return new Font(fontFamily, fontStyle, fontSize);
    }

    static Dimension getUserScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

}
