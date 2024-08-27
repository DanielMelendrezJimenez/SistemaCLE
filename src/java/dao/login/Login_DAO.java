/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.login;

import config.conexion.IConexion;
import managebean.general.GenericResponse;
import managedbean.usuarios.Usuarios_MB;
import static utils.constantes.Constantes.STATUS_CONEXION_FALLIDA_BD;
import utils.constantes.Strings;

/**
 *
 * @author Daniel Melendrez
 */
public class Login_DAO {
     public static GenericResponse<Usuarios_MB> iniciarSesionUsuario(IConexion conexionDB, String username, String password_cifrado) {
        GenericResponse<Usuarios_MB> gr = new GenericResponse<>();
        if (conexionDB.getConexion() != null) {
            Login_MDAO manipula = new Login_MDAO();
            Usuarios_MB result = manipula.getUsuario(conexionDB, username, password_cifrado);

            if (result != null) {
                //response.setStatus(STATUS_REGISTRO_EXITOSO_BD);
                gr.setResponseObject(result);
                gr.setMensaje("Registro exitoso en la base de datos");
            } else {
                //response.setStatus(STATUS_REGISTRO_FALLIDO_BD);
                gr.setResponseObject(null);
                gr.setMensaje("Error en el sistema");
            }
        } else {
            gr.setStatus(STATUS_CONEXION_FALLIDA_BD);
            gr.setResponseObject(null);
            gr.setMensaje(Strings.MSG_ERROR_CONEXION_BD);
        }
        return gr;
    }
}
