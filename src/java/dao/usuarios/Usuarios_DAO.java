/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.usuarios;

import config.conexion.ConexionBD;
import config.conexion.IConexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import managebean.general.GenericResponse;
import managedbean.usuarios.Usuarios_MB;
import utils.constantes.VariablesSistema;
import utils.constantes.Constantes;
import utils.constantes.Strings;

/**
 *
 * @author Daniel Melendrez
 */
public class Usuarios_DAO {

    public static void setUsuario(String username, String password, Usuarios_MB obj, GenericResponse<Usuarios_MB> response) {

        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);

        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Usuarios_MDAO manipula = new Usuarios_MDAO();

                    int result = manipula.registrar(conexionDB, obj);

                    if (result > 0) {
                        response.setStatus(Constantes.STATUS_EXITO);
                        response.setResponseObject(obj);
                        response.setMensaje(Strings.MSG_EXITO);
                    } else {
                        response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);
                        response.setResponseObject(obj);
                        response.setMensaje(Strings.MSG_ERROR);
                    }
                } catch (SQLException ex) {
                    response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);
                    response.setResponseObject(null);
                    response.setMensaje("ERROR " + ex.getMessage());
                    Logger.getLogger(Usuarios_DAO.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    conexionDB.desconectar();
                }
            } else {
                response.setStatus(Constantes.STATUS_CONEXION_FALLIDA_BD);
                response.setResponseObject(null);
                response.setMensaje(Strings.MSG_ERROR_CONEXION_BD);
            }
        } else {
            response.setStatus(Constantes.STATUS_CONEXION_FALLIDA_BD);
            response.setResponseObject(null);
            response.setMensaje(Strings.MSG_ERROR_SISTEMA);
        }

    }
}
