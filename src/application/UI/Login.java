package application.UI;

import Model.dao.DaoFactory;
import Model.dao.UsuarioDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    private JPanel panel1;
    private JTextField email;
    private JPasswordField senha;
    private JButton entrarButton;
    private JButton registrerSeButton;
    private JLabel resp;

    UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

    public Login() {

        entrarButton.addActionListener(this);
        registrerSeButton.addActionListener(this);

        setContentPane(panel1);
        setTitle("Simple GUI");
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == entrarButton) {
                String emailUser = email.getText();
                char[] senhaUser = senha.getPassword();
                String senhaConv = String.valueOf(senhaUser);

                if(!(senhaConv.equals(usuarioDao.findByEmail(emailUser).getSenha()))) {
                    throw new IllegalArgumentException();
                }

                if (emailUser.equals(usuarioDao.findByEmail(emailUser).getEmail())
                        && usuarioDao.findByEmail(emailUser).getSenha().equals(senhaConv)) {
                    dispose();
                    new Principal();
                }
            }
        }catch (NullPointerException ei) {
            resp.setText("Usuario ou Senha Invalido!");
            System.out.println("Usuario ou Senha Invalido!");
        }catch (IllegalArgumentException ei) {
            resp.setText("Usuario ou Senha Invalido!");
            System.out.println("Usuario ou Senha Invalido!");
        }

        if(e.getSource() == registrerSeButton) {
            new Registrar();
        }
    }
}
