package Model.dao;

import Model.dao.impl.UsuarioDaoJDBC;
import db.DB;

public class DaoFactory {

    public static UsuarioDao createUsuarioDao() {
        return new UsuarioDaoJDBC(DB.getConnection());
    }


}
