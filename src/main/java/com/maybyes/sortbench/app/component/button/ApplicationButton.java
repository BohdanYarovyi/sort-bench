package com.maybyes.sortbench.app.component.button;

import lombok.Getter;
import com.maybyes.sortbench.app.ApplicationProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

@Getter
public abstract class ApplicationButton extends JButton {
    public static final Font DEFAULT_FONT = ApplicationProperties.getDefaultFont();

    public static final Dimension DEFAULT_BUTTON_SIZE = new Dimension(120, 35);

    public static final Integer DEFAULT_ARC_WIDTH = 25;

    public static final Integer DEFAULT_ARC_HEIGHT = 20;

    public static final Stroke DEFAULT_BORDER_STROKE = new BasicStroke(2);

    private Color borderColor = Color.BLACK;

    public ApplicationButton(String text) {
        super(text);
        setFont(getFont());
        setPreferredSize(getButtonSize());

        setIdleColor();
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    public abstract ButtonColorTheme getIdleTheme();

    public abstract ButtonColorTheme getHoverTheme();

    public abstract ButtonColorTheme getPressedTheme();

    @Override
    protected void paintComponent(Graphics g) {
        changeColorTheme();
        drawBackground(g);
        super.paintComponent(g);
    }

    private void changeColorTheme() {
        setIdleColor();

        if (getModel().isPressed()) {
            setPressedColor();
        } else if (getModel().isRollover()) {
            setHoverColor();
        }
    }

    private void setIdleColor() {
        ButtonColorTheme colors = getIdleTheme();
        setBackground(colors.background);
        setForeground(colors.foreground);
        borderColor = colors.border;
    }

    private void setHoverColor() {
        ButtonColorTheme colors = getHoverTheme();
        setBackground(colors.background);
        setForeground(colors.foreground);
        borderColor = colors.border;
    }

    private void setPressedColor() {
        ButtonColorTheme colors = getPressedTheme();
        setBackground(colors.background);
        setForeground(colors.foreground);
        borderColor = colors.border;
    }

    private void drawBackground(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape backgroundShape = getButtonBackgroundShape();
        g.setColor(getBackground());
        g.fill(backgroundShape);
        g.dispose();
    }

    @Override
    protected void paintBorder(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics.create();

        if (isBorderPainted()) {
            Shape backgroundShape = getButtonBackgroundShape();
            g.setColor(getBorderColor());
            g.setStroke(getBorderStroke());
            g.draw(backgroundShape);
        }

        g.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        return getButtonBackgroundShape().contains(x, y);
    }

    private Shape getButtonBackgroundShape() {
        return new RoundRectangle2D.Float(
                0,
                0,
                getWidth(),
                getHeight(),
                getArcWidth(),
                getArcHeight()
        );
    }

    public Font getFont() {
        return DEFAULT_FONT;
    }

    public Dimension getButtonSize() {
        return DEFAULT_BUTTON_SIZE;
    }

    public Integer getArcWidth() {
        return DEFAULT_ARC_WIDTH;
    }

    public Integer getArcHeight() {
        return DEFAULT_ARC_HEIGHT;
    }

    public Stroke getBorderStroke() {
        return DEFAULT_BORDER_STROKE;
    }

    public record ButtonColorTheme(
            Color background,
            Color foreground,
            Color border
    ) { }

}
