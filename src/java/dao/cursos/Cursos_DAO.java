/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.cursos;

import config.conexion.ConexionBD;
import config.conexion.IConexion;
import dao.cursos.Cursos_MDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import managebean.general.GenericResponse;
import managedbean.cursos.Cursos_MB;
import utils.constantes.Constantes;
import utils.constantes.Strings;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class Cursos_DAO {

    public static void getCursos(String username, String password, GenericResponse<List<Cursos_MB>> response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        List<Cursos_MB> lstResult = null;
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Cursos_MDAO manipula = new Cursos_MDAO();

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
                    Logger.getLogger(Cursos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void getCursosDisponibles(String username, String password, GenericResponse<List<Cursos_MB>> response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        List<Cursos_MB> lstResult = null;
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Cursos_MDAO manipula = new Cursos_MDAO();

                    lstResult = manipula.consultarDisponibles(conexionDB);
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
                    Logger.getLogger(Cursos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void getExistenciaCurso(String username, String password, GenericResponse response, String aula, String hora_ini) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        Cursos_MB curso = null;
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {

                    Cursos_MDAO manipula = new Cursos_MDAO();
                    curso = manipula.consultar_existencia(conexionDB, aula, hora_ini);
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
                    Logger.getLogger(Cursos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void setCurso(String username, String password, Cursos_MB curso, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);

        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Cursos_MDAO manipula = new Cursos_MDAO();
                    int result = manipula.registrar(conexionDB, curso);
                    if (result > 0) {
                        response.setStatus(Constantes.STATUS_EXITO);
                        response.setResponseObject(curso);
                        response.setMensaje(Strings.MSG_EXITO);

                    } else {
                        response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);
                        response.setResponseObject(curso);
                        response.setMensaje(Strings.MSG_ERROR);

                    }
                } catch (SQLException ex) {
                    response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);
                    response.setResponseObject(null);
                    response.setMensaje("ERROR " + ex.getMessage());
                    Logger.getLogger(Cursos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void getCurso(String username, String password, int iCursoId, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        Cursos_MB curso = null;
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {

                    Cursos_MDAO manipula = new Cursos_MDAO();
                    curso = manipula.encontrarId(conexionDB, iCursoId);
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
                    Logger.getLogger(Cursos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void editCurso(String username, String password, int iCursoId, Cursos_MB curso, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);

        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Cursos_MDAO manipula = new Cursos_MDAO();
                    int result = manipula.editar(conexionDB, iCursoId, curso);
                    if (result > 0) {
                        response.setStatus(Constantes.STATUS_EXITO);
                        response.setResponseObject(curso);
                        response.setMensaje(Strings.MSG_EXITO);

                    } else {
                        response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);
                        response.setResponseObject(curso);
                        response.setMensaje(Strings.MSG_ERROR);

                    }
                } catch (SQLException ex) {
                    response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);
                    response.setResponseObject(null);
                    response.setMensaje("ERROR " + ex.getMessage());
                    Logger.getLogger(Cursos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void removeCurso(String username, String password, String id, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Cursos_MDAO manipula = new Cursos_MDAO();
                    Cursos_MB result = manipula.eliminar(conexionDB, Integer.parseInt(id));
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
                    Logger.getLogger(Cursos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void beforeRemoveCurso(String username, String password, String id, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Cursos_MDAO manipula = new Cursos_MDAO();
                    Cursos_MB result = manipula.consultar_grupo(conexionDB, Integer.parseInt(id));
                    if (result.getNombre() == null) {
                        response.setStatus(Constantes.STATUS_EXITO);
                        response.setMensaje(Strings.MSG_EXITO);

                    } else {
                        response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);
                        response.setResponseObject(result);
                        response.setMensaje("No es posible eliminar cursos asociados a grupos activos");

                    }
                } catch (SQLException ex) {
                    response.setStatus(Constantes.STATUS_REGISTRO_FALLIDO_BD);
                    response.setResponseObject(null);
                    response.setMensaje("ERROR " + ex.getMessage());
                    Logger.getLogger(Cursos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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
