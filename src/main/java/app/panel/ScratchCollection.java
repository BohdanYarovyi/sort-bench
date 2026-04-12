package app.panel;

import java.awt.*;

public class ScratchCollection extends AdaptivePanel {

    public ScratchCollection() {
        this.setBackground(new Color(100, 0,0));

    }

    @Override
    protected float getWidthCorrelation() {
        return 0.225f;
    }

    @Override
    protected float getHeightCorrelation() {
        return 1.0f;
    }

}
