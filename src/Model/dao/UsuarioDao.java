package Model.dao;

import Model.entities.Usuario;

import java.util.List;

public interface UsuarioDao {

        void insert(Usuario obj);
        void update(Usuario obj);
        void deleteById(Integer id);
        Usuario findById(Integer id);
        Usuario findByEmail(String email);
        List<Usuario> findAll();

}
