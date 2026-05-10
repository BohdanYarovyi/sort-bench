package com.maybyes.sortbench.app.component.dialog;

import com.maybyes.sortbench.app.ApplicationProperties;

import javax.swing.*;
import java.awt.*;

public class ApplicationFileChooser extends FileDialog {
    private final static float WIDTH = 0.33f;

    private final static float HEIGHT = 0.33f;

    private final static String TITLE = "Choose File";

    public ApplicationFileChooser() {
        super((Frame) null);

        configure();
    }

    private void configure() {
        setTitle(TITLE);
        setMode(FileDialog.LOAD);
        setMultipleMode(true);
        setSize(getCustomSize());
        setLocation(0, 0);
    }

    private Dimension getCustomSize() {
        Dimension userScreenSize = ApplicationProperties.getUserScreenSize();
        int w = (int) (WIDTH * userScreenSize.width);
        int h = (int) (HEIGHT * userScreenSize.height);

        return new Dimension(w, h);
    }

}
