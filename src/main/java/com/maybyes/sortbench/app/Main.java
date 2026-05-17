package com.maybyes.sortbench.app;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Runnable app = ApplicationWindow::new;
        EventQueue.invokeLater(app);
    }

}
