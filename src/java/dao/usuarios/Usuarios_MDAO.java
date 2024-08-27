/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.usuarios;

import config.conexion.IConexion;
import dao.manipula.DAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import managebean.general.GeneradorSQL;
import managedbean.usuarios.Usuarios_MB;

/**
 *
 * @author Daniel Melendrez
 */
public class Usuarios_MDAO implements DAO<Usuarios_MB> {
    private static final String USUARIOS_TAB = "usuarios_tab";
    
@Override
    public int registrar(IConexion conexionDB, Usuarios_MB obj) throws SQLException {
            int resulSet;
        List<String> cols = GeneradorSQL.columnas(
                "nombre",
                "ap_pat",
                "ap_mat",
                "no_control",
                "contrasena",
                "correo",
                "tipo_usuario",
                "status"
        );

        String camposSQL = GeneradorSQL.camposInsertSQL(cols);
        String valuesSQL = GeneradorSQL.valuesSQL(cols.size());

        String sql = "INSERT INTO " + USUARIOS_TAB
                + " (" + camposSQL + ") "
                + "VALUES (" + valuesSQL + ")";

        PreparedStatement registro = conexionDB.getConexion().prepareStatement(sql);
        registro.setString(1, obj.getNombre());
        registro.setString(2, obj.getAp_pat());
        registro.setString(3, obj.getAp_mat());
        registro.setString(4, obj.getNo_control());
        registro.setString(5, obj.getContrasena());
        registro.setString(6, obj.getCorreo());
        registro.setString(7, obj.getTipo_usuario());
        registro.setString(8, obj.getEstatus());

        resulSet = registro.executeUpdate();

        return resulSet;
    }

    @Override
    public int editar(IConexion conexionDB, int id, Usuarios_MB nvoObj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuarios_MB eliminar(IConexion conexionDB, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuarios_MB> consultar(IConexion conexionDB) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuarios_MB encontrarId(IConexion conexionDB, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
