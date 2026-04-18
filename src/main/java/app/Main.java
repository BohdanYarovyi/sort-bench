package app;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Runnable app = new Runnable() {
            @Override
            public void run() {
                new ApplicationWindow();
            }
        };

        EventQueue.invokeLater(app);
    }

}
