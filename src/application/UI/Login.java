package application.UI;

import Model.dao.DaoFactory;
import Model.dao.UsuarioDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Login extends JFrame implements ActionListener {
    private JPanel panel1;
    private JTextField email;
    private JPasswordField senha;
    private JButton entrarButton;
    private JButton registrerSeButton;

    UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

    public Login() {

        entrarButton.addActionListener(this);
        registrerSeButton.addActionListener(this);

        setContentPane(panel1);
        setTitle("Simple GUI");
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == entrarButton) {
                String emailUser = email.getText();
                char[] senhaUser = senha.getPassword();
                String senhaConv = String.valueOf(senhaUser);

                if (emailUser.equals(usuarioDao.findByEmail(emailUser).getEmail())
                        && usuarioDao.findByEmail(emailUser).getSenha().equals(senhaConv)) {
                    dispose();
                    new Principal();
                }
            }
        }catch (NullPointerException ei) {
            System.out.println("Usuario ou Senha Invalido!");
        }

        if(e.getSource() == registrerSeButton) {
            new Registrar();
        }

    }
}
