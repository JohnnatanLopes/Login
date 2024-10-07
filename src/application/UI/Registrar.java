package application.UI;

import Model.dao.DaoFactory;
import Model.dao.UsuarioDao;
import Model.entities.Usuario;
import Model.exception.AddressException;
import Model.exception.passwordException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class Registrar extends JFrame implements ActionListener {
    private JPanel panel1;
    private JTextField nomeCompleto;
    private JTextField email;
    private JPasswordField senha;
    private JTextField telefone;
    private JButton registrarSeButton;
    private JLabel error;
    private JLabel conf;

    UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

    public Registrar() {

        registrarSeButton.addActionListener(this);

        setContentPane(panel1);
        setTitle("Simple GUI");
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == registrarSeButton) {
            try {
                String nomeUser = nomeCompleto.getText();
                String emailUser = email.getText();

                char[] senhaUser = senha.getPassword();
                String senhaConv = String.valueOf(senhaUser);
                String telefoneUser = telefone.getText();
                LocalDate now = LocalDate.now();

                Usuario usuario = new Usuario(null, nomeUser, senhaConv, emailUser, now, telefoneUser);
                usuarioDao.insert(usuario);
                conf.setText("Usuario Cadastrado!");
            }catch (AddressException ei) {
                error.setText("Formato da senha e invalido");
                System.out.println(ei.getMessage());
            }catch (passwordException ei) {
                error.setText(ei.getMessage());
                System.out.println(ei.getMessage());
            }
        }
    }

}
