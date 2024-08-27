/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.configuracion;

import config.conexion.ConexionBD;
import config.conexion.IConexion;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import managebean.general.GenericResponse;
import managedbean.configuracion.Parametros_MB;
import utils.constantes.Constantes;
import utils.constantes.Strings;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class Parametros_DAO {

    public static void setParametro(String username, String password, Parametros_MB obj, GenericResponse<Parametros_MB> response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);

        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Parametros_MDAO manipula = new Parametros_MDAO();
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
                    Logger.getLogger(Parametros_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void editParametro(String username, String password, int id, Parametros_MB obj, GenericResponse<Parametros_MB> response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);

        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Parametros_MDAO manipula = new Parametros_MDAO();
                    int result = manipula.editar(conexionDB, id, obj);
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
                    Logger.getLogger(Parametros_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void getParametros(String username, String password, GenericResponse<List<Parametros_MB>> response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        List<Parametros_MB> lstResult = null;
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Parametros_MDAO manipula = new Parametros_MDAO();

                    lstResult = manipula.consultar(conexionDB);
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
                    Logger.getLogger(Parametros_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void getExistenciaParametro(String username, String password, GenericResponse response, String concepto) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        Parametros_MB parametro = null;
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {

                    Parametros_MDAO manipula = new Parametros_MDAO();
                    parametro = manipula.consultar_existencia(conexionDB, concepto);
                    if (parametro.getConcepto() != null) {
                        response.setStatus(Constantes.STATUS_EXITO);
                        response.setResponseObject(parametro);
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
                    Logger.getLogger(Parametros_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void getParametro(String username, String password, int iParametroId, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        Parametros_MB parametro = null;
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {

                    Parametros_MDAO manipula = new Parametros_MDAO();
                    parametro = manipula.encontrarId(conexionDB, iParametroId);
                    if (parametro.getConcepto() != null) {
                        response.setStatus(Constantes.STATUS_EXITO);
                        response.setResponseObject(parametro);
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
                    Logger.getLogger(Parametros_DAO.class.getName()).log(Level.SEVERE, null, ex);
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
