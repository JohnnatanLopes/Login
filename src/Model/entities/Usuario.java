package Model.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

public class Usuario {

    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataCadastro;
    private String telefone;

    public Usuario() {}

    public Usuario(Integer id, String nome, String senha, String email, LocalDate dataCadastro, String telefone) {

        validadorSenha(senha);

        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataCadastro = dataCadastro;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    private void validadorSenha(String senha) {
        boolean testeTamanho =  false;
        if(senha.length() > 8) {
            testeTamanho = true;
        }
        boolean testeMaisucula = false;
        boolean testeMinuscula = false;
        boolean testeNumerico = false;
        boolean testeEspeciais = false;
        for(int i = 0;i < senha.length();i++) {
            String teste = String.valueOf(senha.charAt(i));
            if(Pattern.matches("[A-Z]", teste)) {
                testeMaisucula = true;
            }
            if(Pattern.matches("[a-z]", teste)) {
                testeMinuscula = true;
            }
            if(Pattern.matches("[0-9]", teste)) {
                testeNumerico = true;
            }
            if(Pattern.matches("[$*&@#]", teste)) {
                testeEspeciais = true;
            }
        }

        if(!(testeTamanho && testeMinuscula && testeMaisucula && testeNumerico && testeEspeciais)) {
            throw new IllegalArgumentException("Erro: A senha inserida está em um formato inválido. Por favor, certifique-se de que a senha atenda aos seguintes requisitos:\n" +
                    "\n" +
                    "    Mínimo de 8 caracteres\n" +
                    "    Pelo menos uma letra maiúscula\n" +
                    "    Pelo menos uma letra minúscula\n" +
                    "    Pelo menos um número\n" +
                    "    Pelo menos um caractere especial (ex: @, #, $, *)\n" +
                    "\n" +
                    "Tente novamente!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Usuario{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", senha='").append(senha).append('\'');
        sb.append(", dataCadastro=").append(dataCadastro);
        sb.append(", telefone='").append(telefone).append('\'');
        sb.append('}');
        sb.append("\n");
        return sb.toString();
    }
}
