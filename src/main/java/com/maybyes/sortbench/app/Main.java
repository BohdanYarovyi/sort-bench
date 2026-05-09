package com.maybyes.sortbench.app;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Runnable app = () -> new ApplicationWindow();

        EventQueue.invokeLater(app);
    }

}
