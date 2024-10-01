package application;

import Model.dao.DaoFactory;
import Model.dao.UsuarioDao;
import Model.entities.Usuario;
import java.time.LocalDate;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

        System.out.println("---- MENU ------");
        System.out.println("1 - LOGIN");
        System.out.println("2 - REGISTRAR-SE");
        System.out.println("------------------");
        System.out.print("=== Escolha uma opcao: ");
        char escolha = sc.next().charAt(0);
        try {
            if (escolha == '1') {
                System.out.println("Email: ");
                String email = sc.next();
                System.out.println("Senha: ");
                String senha = sc.next();

                String newSenha = usuarioDao.findByEmail(email).getSenha();
                if (senha.equals(newSenha)) {
                    System.out.println("\nApplication\n");
                    System.out.println("╮╭╮╭╮╋╋╭╮\n" +
                            "┃┃┃┃┃┃╋╭╯╰╮\n" +
                            "┃┃┃┃┃┣━╋╮╭╋━━╮\n" +
                            "┃╰╯╰╯┃╭╋┫┃┃┃━┫\n" +
                            "╰╮╭╮╭┫┃┃┃╰┫┃━┫\n" +
                            "╋╰╯╰╯╰╯╰┻━┻━━╯\n" +
                            "╭╮╋╭╮\n" +
                            "┃┃╋┃┃\n" +
                            "┃┃╭┫┃╭┳━━╮\n" +
                            "┃┃┣┫╰╯┫┃━┫\n" +
                            "┃╰┫┃╭╮┫┃━┫\n" +
                            "╰━┻┻╯╰┻━━╯\n" +
                            "╋╭╮╭╮\n" +
                            "╭╯╰┫┃\n" +
                            "╰╮╭┫╰━┳┳━━╮\n" +
                            "╋┃┃┃╭╮┣┫━━┫\n" +
                            "╋┃╰┫┃┃┃┣━━┃\n" +
                            "╋╰━┻╯╰┻┻━━╯");
                } else {
                    System.out.println("Senha ou Senha invalido");
                }
            }
            }catch (NullPointerException e) {
            System.out.println("Usuario ou Senha invalido");
            }

            if (escolha == '2'){
                try {
                    System.out.println("Nome completo: ");
                    sc.nextLine();
                    String nome = sc.nextLine();
                    System.out.println("Email: ");
                    String email = sc.next();
                    System.out.println("Senha: ");
                    String senha = sc.next();
                    System.out.println("Telefone");
                    String telefone = sc.next();
                    LocalDate now = LocalDate.now();

                    Usuario usuario = new Usuario(null, nome, senha, email, now, telefone);
                    usuarioDao.insert(usuario);
                }catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
    }
}
