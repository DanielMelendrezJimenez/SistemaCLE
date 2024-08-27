/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.facilitadores;

import config.conexion.ConexionBD;
import config.conexion.IConexion;
import dao.cursos.Cursos_DAO;
import dao.usuarios.Usuarios_MDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import managebean.general.GenericResponse;
import managedbean.cursos.Cursos_MB;
import managedbean.usuarios.Usuarios_MB;
import utils.constantes.Constantes;
import utils.constantes.Strings;
import utils.constantes.VariablesSistema;

/**
 *
 * @author wltgm
 */
public class Facilitadores_DAO {

    public static void getFacilitadores(String username, String password, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        List<Usuarios_MB> lstResult = null;
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Facilitadores_MDAO manipula = new Facilitadores_MDAO();

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
                    Logger.getLogger(Facilitadores_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void setFacilitador(String username, String password, Usuarios_MB facilitador, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);

        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Facilitadores_MDAO manipula = new Facilitadores_MDAO();
                    int result = manipula.registrar(conexionDB, facilitador);
                    if (result > 0) {
                        response.setStatus(Constantes.STATUS_EXITO);
                        response.setResponseObject(facilitador);
                        response.setMensaje(Strings.MSG_EXITO);

                    } else {
                        response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);
                        response.setResponseObject(facilitador);
                        response.setMensaje(Strings.MSG_ERROR);

                    }
                } catch (SQLException ex) {
                    response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);
                    response.setResponseObject(null);
                    response.setMensaje("ERROR " + ex.getMessage());
                    Logger.getLogger(Facilitadores_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void validarFacilitador(String username, String password, String curp, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        String resultado = "";
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {

                    Facilitadores_MDAO manipula = new Facilitadores_MDAO();
                    resultado = manipula.encontrarCurp(conexionDB, curp);
                    if ("".equals(resultado)) {
                        response.setStatus(Constantes.STATUS_EXITO);
                        response.setResponseObject(null);
                        response.setMensaje(Strings.MSG_EXITO);
                    } else {
                        response.setStatus(Constantes.STATUS_NO_DATA);
                        response.setResponseObject(resultado);
                        response.setMensaje(Strings.MSG_SIN_DATOS);
                    }
                } catch (SQLException ex) {
                    response.setStatus(Constantes.STATUS_NO_DATA);
                    response.setResponseObject(null);
                    response.setMensaje("ERROR " + ex.getMessage());
                    Logger.getLogger(Facilitadores_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void validarNoControl(String username, String password, String no_control, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        String resultado = "";
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {

                    Facilitadores_MDAO manipula = new Facilitadores_MDAO();
                    resultado = manipula.encontrarNo_control(conexionDB, no_control);
                    if ("".equals(resultado)) {
                        response.setStatus(Constantes.STATUS_EXITO);
                        response.setResponseObject(null);
                        response.setMensaje(Strings.MSG_EXITO);
                    } else {
                        response.setStatus(Constantes.STATUS_NO_DATA);
                        response.setResponseObject(resultado);
                        response.setMensaje(Strings.MSG_SIN_DATOS);
                    }
                } catch (SQLException ex) {
                    response.setStatus(Constantes.STATUS_NO_DATA);
                    response.setResponseObject(null);
                    response.setMensaje("ERROR " + ex.getMessage());
                    Logger.getLogger(Facilitadores_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void removeFacilitador(String username, String password, String id, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Facilitadores_MDAO manipula = new Facilitadores_MDAO();
                    Usuarios_MB result = manipula.eliminar(conexionDB, Integer.parseInt(id));
                    if (result != null) {
                        response.setStatus(Constantes.STATUS_EXITO);

                        response.setMensaje(Strings.MSG_EXITO);

                    } else {
                        response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);

                        response.setMensaje(Strings.MSG_ERROR);

                    }
                } catch (SQLException ex) {
                    response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);
                    response.setResponseObject(null);
                    response.setMensaje("ERROR " + ex.getMessage());
                    Logger.getLogger(Facilitadores_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void removeVerificar(String username, String password, String id, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {
                try {
                    Facilitadores_MDAO manipula = new Facilitadores_MDAO();
                    int result = manipula.verificarEliminar(conexionDB, Integer.parseInt(id));
                    if (result == 0) {
                        response.setStatus(Constantes.STATUS_EXITO);
                        response.setResponseObject(null);
                        response.setMensaje(Strings.MSG_EXITO);

                    } else {
                        response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);
                        response.setResponseObject("activo");
                        response.setMensaje(Strings.MSG_ERROR);

                    }
                } catch (SQLException ex) {
                    response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);
                    response.setResponseObject(null);
                    response.setMensaje("ERROR " + ex.getMessage());
                    Logger.getLogger(Facilitadores_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void getFacilitador(String username, String password, int iFacilitadorId, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        Usuarios_MB curso = null;
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {

                    Facilitadores_MDAO manipula = new Facilitadores_MDAO();
                    curso = manipula.encontrarId(conexionDB, iFacilitadorId);
                    if (curso.getNombre() != null) {
                        response.setStatus(Constantes.STATUS_EXITO);
                        response.setResponseObject(curso);
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
                    Logger.getLogger(Facilitadores_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void getFacilitadoresDispoibles(String username, String password, GenericResponse response, int idcurso) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        List<Usuarios_MB> lstResult = null;
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {

                    Cursos_DAO.getCurso(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, idcurso, response);
                    Cursos_MB curso = new Cursos_MB();
                    curso = (Cursos_MB) response.getResponseObject();

                    Facilitadores_MDAO manipula = new Facilitadores_MDAO();
                    lstResult = manipula.consultarDisponibles(conexionDB, curso.getHora_inicio());
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
                    Logger.getLogger(Facilitadores_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void editFacilitador(String username, String password, int iFacilitadorId, Usuarios_MB facilitador, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);

        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Facilitadores_MDAO manipula = new Facilitadores_MDAO();
                    int result = manipula.editar(conexionDB, iFacilitadorId, facilitador);
                    if (result > 0) {
                        response.setStatus(Constantes.STATUS_EXITO);
                        response.setResponseObject(facilitador);
                        response.setMensaje(Strings.MSG_EXITO);

                    } else {
                        response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);
                        response.setResponseObject(facilitador);
                        response.setMensaje(Strings.MSG_ERROR);

                    }
                } catch (SQLException ex) {
                    response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);
                    response.setResponseObject(null);
                    response.setMensaje("ERROR " + ex.getMessage());
                    Logger.getLogger(Facilitadores_DAO.class.getName()).log(Level.SEVERE, null, ex);
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
