package com.maybyes.sortbench.app;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;


public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        // todo: Розібратись з ось цими компачами, які змінюють мені весь UI
        FlatDarkLaf.setup();
        UIManager.setLookAndFeel(new FlatDarkLaf());

        Runnable app = () -> new ApplicationWindow();
        EventQueue.invokeLater(app);
    }

}
