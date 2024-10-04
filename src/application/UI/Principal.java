package application.UI;

import javax.swing.*;

public class Principal extends JFrame {
    private JPanel panel1;

    public Principal() {
        setContentPane(panel1);
        setTitle("Simple GUI");
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

}
