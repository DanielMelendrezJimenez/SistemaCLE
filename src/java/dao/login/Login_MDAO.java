/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.login;

import config.conexion.IConexion;
import dao.manipula.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import managebean.general.Logg;
import managedbean.usuarios.Usuarios_MB;

/**
 *
 * @author Daniel Melendrez
 */
public class Login_MDAO implements DAO<Usuarios_MB> {
    
        public Usuarios_MB getUsuario(IConexion conexionDB, String correo, String contrasenia) {
            
        Usuarios_MB response = null;
        if (conexionDB.getConexion() != null) {
            
            try {
                
                String sql = "SELECT "
                        + "id, "
                        + "nombre, "
                        + "ap_pat, "
                        + "ap_mat, "
                        + "no_control, "
                        + "contrasena, "
                        + "correo, "
                        + "tipo_usuario, "
                        + "status "
                        + "FROM usuarios_tab "
                        + "WHERE no_control=? AND contrasena=?";
                PreparedStatement ps = conexionDB.getConexion().prepareStatement(sql);
                ps.setString(1, correo);
                ps.setString(2, contrasenia);
                ResultSet rs;
                rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println("ENCONTRO ALGO");
                    response = new Usuarios_MB();
                    response.setId(rs.getInt("id"));
                    response.setNombre(rs.getString("nombre"));
                    response.setAp_pat(rs.getString("ap_pat"));
                    response.setAp_mat(rs.getString("ap_mat"));
                    response.setNo_control(rs.getString("no_control"));
                    response.setContrasena(rs.getString("contrasena"));
                    response.setCorreo(rs.getString("correo"));
                    response.setTipo_usuario(rs.getString("tipo_usuario"));
                    response.setEstatus(rs.getString("status"));
                } else {
                    System.out.println("NO ENCONTRO ALGO");
                    Logg.error("No se encontro ningun registro");
                }
            } catch (SQLException ex) {
                Logg.error("Comunicación fallida con la base de datos " + ex.getMessage());
            } finally {

            }
        } else {
            Logg.error("Conexión fallida con la base de datos");
        }
        return response;
    }

    @Override
    public int registrar(IConexion conexionDB, Usuarios_MB obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
