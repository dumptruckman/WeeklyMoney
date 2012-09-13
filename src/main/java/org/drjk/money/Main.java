package org.drjk.money;

import java.awt.EventQueue;

class Main {
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow window = new MainWindow();
                    window.getFrame().setVisible(true);
                } catch (Exception e) {
                    ErrorDialog.show("Uknown error", e);
                }
            }
        });
    }
}
