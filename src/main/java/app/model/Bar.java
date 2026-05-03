package app.model;

import app.ApplicationProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

@Getter
@Setter
@ToString
public class Bar implements Comparable<Bar> {
    private final int value;

    private Dimension2D barSize;

    private Point2D barPosition;

    private Color innerColor;

    private Color borderColor;

    public Bar(int value) {
        this.value = value;
        this.innerColor = ApplicationProperties.BAR_IDLE_COLOR;
        this.borderColor = ApplicationProperties.BAR_BORDER_COLOR;
    }

    @Override
    public int compareTo(Bar other) {
        return this.value - other.value;
    }

    public void setCompared() {
        innerColor = ApplicationProperties.BAR_COMPARE_COLOR;
    }

    public void setSwapped() {
        innerColor = ApplicationProperties.BAR_SWAP_COLOR;
    }

    public void setPeeked() {
        innerColor = ApplicationProperties.BAR_PEEK_COLOR;
    }

    public void setSet() {
        innerColor = ApplicationProperties.BAR_SET_COLOR;
    }

    public void setIdle() {
        innerColor = ApplicationProperties.BAR_IDLE_COLOR;
    }

}
