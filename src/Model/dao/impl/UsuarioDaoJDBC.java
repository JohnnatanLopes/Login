package Model.dao.impl;

import Model.dao.UsuarioDao;
import Model.entities.Usuario;
import db.DB;
import db.DbExeception;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoJDBC implements UsuarioDao {

    private Connection conn;

    public UsuarioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Usuario obj) {

        PreparedStatement st = null;

        try {
           st = conn.prepareStatement("INSERT INTO Usuario "
                   +"(nome,email,senha,dataCadastro,telefone) "
                   +"VALUES "
                   +"(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

           st.setString(1, obj.getNome());
           st.setString(2, obj.getEmail());
           st.setString(3, obj.getSenha());
           st.setDate(4, Date.valueOf(obj.getDataCadastro()));
           st.setString(5, obj.getTelefone());

           int rowsAffectaded = st.executeUpdate();
           if(rowsAffectaded > 0) {
               ResultSet rs = st.getGeneratedKeys();
                 if(rs.next()) {
                     int id = rs.getInt(1);
                     obj.setId(id);
                 }
           }
           else{
               System.out.println("Nenhuma linha afetada!");
           }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Usuario obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update Usuario"
                    + "set nome = ? , email = ?, senha = ?, telefone = ?" +
                    "where id = ? ");

            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setString(3, obj.getSenha());
            st.setString(4, obj.getTelefone());
            st.setInt(4, obj.getId());

            st.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {

       PreparedStatement st = null;

       try {
          st = conn.prepareStatement("DELETE FROM Usuario WHERE id = ? ");

          st.setInt(1, id);

          int linhasAfetadas = st.executeUpdate();

          if(linhasAfetadas > 0) {
              System.out.println("Usuario Excluido");
          }
          else {
              System.out.println("Nenhum Usuario excluido");
          }

       }catch (SQLException e) {
           System.out.println(e.getMessage());
       }finally {
            DB.closeStatement(st);
       }

    }

    @Override
    public Usuario findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("select * from Usuario where id = ? ");

            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()) {
               int idUsuario = rs.getInt(1);
               String nome = rs.getString(2);
               String email = rs.getString(3);
               String senha = rs.getString(4);
               LocalDate ld = rs.getDate(5).toLocalDate();
               String telefone = rs.getString(6);

               Usuario usuario = new Usuario(idUsuario,nome ,senha ,email , ld, telefone);
               return  usuario;
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return  null;
    }

    @Override
    public Usuario findByEmail(String email) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("select * from Usuario where email = ? ");

            st.setString(1, email);

            rs = st.executeQuery();

            if(rs.next()) {
                int idUsuario = rs.getInt(1);
                String nome = rs.getString(2);
                String emailU = rs.getString(3);
                String senha = rs.getString(4);
                LocalDate ld = rs.getDate(5).toLocalDate();
                String telefone = rs.getString(6);

                Usuario usuario = new Usuario(idUsuario,nome ,senha ,emailU , ld, telefone);
                return  usuario;
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return null;
    }

    @Override
    public List<Usuario> findAll() {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("select id,nome,email,telefone from Usuario order by nome ");

            rs = st.executeQuery();

            ArrayList<Usuario> list = new ArrayList<>();

            while(rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNome(rs.getString(2));
                usuario.setEmail(rs.getString(3));
                usuario.setTelefone(rs.getString(4));
                list.add(usuario);
            }

            return list;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return  null;
    }

}
