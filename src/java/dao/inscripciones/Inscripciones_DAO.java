/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.inscripciones;

import config.conexion.ConexionBD;
import config.conexion.IConexion;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import managebean.general.GenericResponse;
import managedbean.inscripciones.Preinscritos_MB;
import utils.constantes.Constantes;
import utils.constantes.Strings;
import utils.constantes.VariablesSistema;

/**
 *
 * @author wltgm
 */
public class Inscripciones_DAO {

    public static void getAlumnosGruposList(String username, String password, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        List<Preinscritos_MB> lstResult = null;
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Inscripciones_MDAO manipula = new Inscripciones_MDAO();

                    lstResult = manipula.consultarPreinscritos(conexionDB);
                    if (!lstResult.isEmpty()) {
                        response.setStatus(Constantes.STATUS_EXITO);
                        response.setResponseObject(lstResult);
                        response.setMensaje(Strings.MSG_EXITO);
                    } else {
                        response.setStatus(Constantes.STATUS_NO_DATA);
                        response.setResponseObject(null);
                        response.setMensaje(Strings.MSG_SIN_DATOS);
                    }
                } catch (SQLException ex) {
                    response.setStatus(Constantes.STATUS_NO_DATA);
                    response.setResponseObject(null);
                    response.setMensaje("ERROR " + ex.getMessage());
                    Logger.getLogger(Inscripciones_DAO.class.getName()).log(Level.SEVERE, null, ex);
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
