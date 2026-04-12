package app.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.*;

@Getter
@Setter
@ToString
public class Bar implements Comparable<Bar> {
    private final int value;
    private Dimension size;
    private Point position;
    private Point textPosition;
    private Font font;

    private Color color;
    private Color borderColor;

    public Bar(int value) {
        this.value = value;
        color = new Color(151, 151, 151);
        borderColor = new Color(0, 0, 0);
    }

    @Override
    public int compareTo(Bar other) {
        return this.value - other.value;
    }

}
