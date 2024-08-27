/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.grupos;

import config.conexion.ConexionBD;
import config.conexion.IConexion;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import managebean.general.GenericResponse;
import managedbean.cursos.Cursos_MB;
import managedbean.grupos.Grupos_Consultar_MB;
import managedbean.grupos.Grupos_MB;
import utils.constantes.Constantes;
import utils.constantes.Strings;
import utils.constantes.VariablesSistema;

/**
 *
 * @author wltgm
 */
public class Grupos_DAO {

    public static void getGrupo(String username, String password, int iCursoId, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        Grupos_MB grupo = null;
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {

                    Grupos_MDAO manipula = new Grupos_MDAO();
                    grupo = manipula.encontrarId(conexionDB, iCursoId);
                    if (grupo != null) {
                        response.setStatus(Constantes.STATUS_EXITO);
                        response.setResponseObject(grupo);
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
                    Logger.getLogger(Grupos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

                    Grupos_MDAO manipula = new Grupos_MDAO();
                    curso = manipula.encontrarCurso(conexionDB, iCursoId);
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
                    Logger.getLogger(Grupos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void setGrupo(String username, String password, Grupos_MB facilitador, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);

        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Grupos_MDAO manipula = new Grupos_MDAO();
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
                    Logger.getLogger(Grupos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void getGruposList(String username, String password, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        List<Grupos_Consultar_MB> lstResult = null;
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Grupos_MDAO manipula = new Grupos_MDAO();

                    lstResult = manipula.consultarGrupos(conexionDB);
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
                    Logger.getLogger(Grupos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void getGrupoporId(String username, String password, int id_grupo,GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        Grupos_Consultar_MB grupo = null;
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Grupos_MDAO manipula = new Grupos_MDAO();

                    grupo = manipula.consultarGrupoPorId(conexionDB,id_grupo);
                    if (grupo != null) {
                        response.setStatus(Constantes.STATUS_EXITO);
                        response.setResponseObject(grupo);
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
                    Logger.getLogger(Grupos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void removeGrupo(String username, String password, String id, GenericResponse response) {
        IConexion conexionDB = ConexionBD.getConexion(username, password, VariablesSistema.NAME_BD);
        if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
            if (conexionDB.getConexion() != null) {

                try {
                    Grupos_MDAO manipula = new Grupos_MDAO();
                    Grupos_MB result = manipula.eliminar(conexionDB, Integer.parseInt(id));
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
                    Logger.getLogger(Grupos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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
                    Grupos_MDAO manipula = new Grupos_MDAO();
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
                    Logger.getLogger(Grupos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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
