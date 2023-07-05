package pkg2dsandbox;

import java.awt.BorderLayout;
import javax.swing.*;

public class MyFrame extends JFrame {

    private SandboxPanel sandbox;
    private GUI gui;

    MyFrame() {
        sandbox = new SandboxPanel();
        gui = new GUI();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("2D Sandbox");
        this.add(sandbox, BorderLayout.NORTH);
        this.add(gui, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
