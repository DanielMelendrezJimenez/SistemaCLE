/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.parametros;

import com.google.gson.Gson;
import dao.configuracion.Parametros_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managebean.general.GenericResponse;
import managedbean.configuracion.Parametros_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class VerificarParametro_Srv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("inpParametroId");
        String concepto = request.getParameter("inConcepto");
        String fecha_inicio = request.getParameter("inFechaInicioPeriodo");
        String fecha_fin = request.getParameter("inFechaFinPeriodo");
        String estatus = request.getParameter("inStatus");
        
        

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        GenericResponse respuesta;

        respuesta = new GenericResponse<Parametros_MB>();
        Parametros_DAO.getExistenciaParametro(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta, "TRIMESTRE");
        Parametros_MB par_trimestre = null;
        par_trimestre = (Parametros_MB) respuesta.getResponseObject();

        respuesta = new GenericResponse<Parametros_MB>();
        Parametros_DAO.getExistenciaParametro(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta, "PREINSCRIPCION");
        Parametros_MB par_preregistro = null;
        par_preregistro = (Parametros_MB) respuesta.getResponseObject();

        respuesta = new GenericResponse<Parametros_MB>();
        Parametros_DAO.getExistenciaParametro(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta, "INSCRIPCION");
        Parametros_MB par_inscripcion = null;
        par_inscripcion = (Parametros_MB) respuesta.getResponseObject();

        respuesta = new GenericResponse<Parametros_MB>();
        Parametros_DAO.getExistenciaParametro(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta, "CLASES");
        Parametros_MB par_clases = null;
        par_clases = (Parametros_MB) respuesta.getResponseObject();

        respuesta = new GenericResponse<Parametros_MB>();
        Parametros_DAO.getExistenciaParametro(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta, "EVALUACIONES");
        Parametros_MB par_evaluaciones = null;
        par_evaluaciones = (Parametros_MB) respuesta.getResponseObject();

        try {

            java.util.Date utilDateInicio = dateFormat.parse(fecha_inicio);
            long timeInMillisInicioPeriodo = utilDateInicio.getTime();
            Date dFechaInicioPeriodo = new Date(timeInMillisInicioPeriodo);

            java.util.Date utilDateFin = dateFormat.parse(fecha_fin);
            long timeInMillisFinPeriodo = utilDateFin.getTime();
            Date dFechaFinPeriodo = new Date(timeInMillisFinPeriodo);

            switch (concepto) {
                case "TRIMESTRE":
                    
                    if (estatus == null) {
                        if (par_trimestre != null) {
                            respuesta.setStatus(0);
                            respuesta.setResponseObject(respuesta.getResponseObject());
                            respuesta.setMensaje("Ya existe un parametro TRIMESTRE con fecha de inicio: " + par_trimestre.getFecha_inicio() + " y fecha de fin: " + par_trimestre.getFecha_fin());
                        } else {
                            respuesta.setStatus(-200);
                            respuesta.setMensaje("");
                        }
                    } else {
                        respuesta.setStatus(-200);
                        respuesta.setMensaje("");
                    }
                    break;
                case "PREINSCRIPCION":

                    if (par_trimestre != null) {

                        if (par_trimestre.getFecha_inicio().before(dFechaInicioPeriodo)) {
                            if (par_trimestre.getFecha_fin().after(dFechaFinPeriodo)) {

                                if (par_preregistro != null) {

                                    respuesta.setStatus(0);
                                    respuesta.setResponseObject(respuesta.getResponseObject());
                                    respuesta.setMensaje("Ya existe un parametro PREINSCRIPCIÓN con fecha de inicio: " + par_preregistro.getFecha_inicio() + " y fecha de fin: " + par_preregistro.getFecha_fin());
                                } else {
                                    respuesta.setStatus(-200);
                                    respuesta.setMensaje("");
                                }
                            } else {
                                respuesta.setStatus(0);
                                respuesta.setMensaje("La fecha de fin de preinscripción no puede ser posterior a la fecha de fin del trimestre.");
                            }
                        } else {
                            respuesta.setStatus(0);
                            respuesta.setMensaje("La fecha de inicio de preinscripción no puede ser anterior a la fecha de inicio del trimestre.");
                        }
                    } else {
                        respuesta.setStatus(0);
                        respuesta.setMensaje("Debes configurar una fecha de trimestre primero.");
                    }
                    break;

                case "INSCRIPCION":
                    if (par_trimestre != null) {
                        if (par_preregistro != null) {
                            if (par_preregistro.getFecha_fin().before(dFechaInicioPeriodo)) {
                                if (par_trimestre.getFecha_fin().after(dFechaFinPeriodo)) {
                                    if (par_inscripcion != null) {

                                        respuesta.setStatus(0);
                                        respuesta.setResponseObject(respuesta.getResponseObject());
                                        respuesta.setMensaje("Ya existe un parametro INSRIPCIÓN con fecha de inicio: " + par_inscripcion.getFecha_inicio() + " y fecha de fin: " + par_inscripcion.getFecha_fin());
                                    } else {
                                        respuesta.setStatus(-200);
                                        respuesta.setMensaje("");
                                    }
                                } else {
                                    respuesta.setStatus(0);
                                    respuesta.setMensaje("La fecha de fin de inscripción no puede ser posterior a la fecha de fin del trimestre.");
                                }

                            } else {
                                respuesta.setStatus(0);
                                respuesta.setMensaje("La fecha de inicio de inscripción no puede ser anterior a la fecha de fin del preincscripción.");
                            }
                        } else {
                            respuesta.setStatus(0);
                            respuesta.setMensaje("Debes configurar una fecha de preinscripción primero.");
                        }
                    } else {
                        respuesta.setStatus(0);
                        respuesta.setMensaje("Debes configurar una fecha de trimestre primero.");
                    }
                    break;
                case "CLASES":
                    if (par_trimestre != null) {
                        if (par_preregistro != null) {
                            if (par_inscripcion != null) {

                                if (par_inscripcion.getFecha_fin().before(dFechaInicioPeriodo)) {
                                    if (par_trimestre.getFecha_fin().after(dFechaFinPeriodo)) {
                                        if (par_clases != null) {

                                            respuesta.setStatus(0);
                                            respuesta.setResponseObject(respuesta.getResponseObject());
                                            respuesta.setMensaje("Ya existe un parametro CLASES con fecha de inicio: " + par_clases.getFecha_inicio() + " y fecha de fin: " + par_clases.getFecha_fin());
                                        } else {
                                            respuesta.setStatus(-200);
                                            respuesta.setMensaje("");
                                        }
                                    } else {
                                        respuesta.setStatus(0);
                                        respuesta.setMensaje("La fecha de fin de clases no puede ser posterior a la fecha de fin del trimestre.");
                                    }

                                } else {
                                    respuesta.setStatus(0);
                                    respuesta.setMensaje("La fecha de inicio de clases no puede ser anterior a la fecha de fin de incscripción.");
                                }

                            } else {
                                respuesta.setStatus(0);
                                respuesta.setMensaje("Debes configurar una fecha de inscripción primero.");
                            }
                        } else {
                            respuesta.setStatus(0);
                            respuesta.setMensaje("Debes configurar una fecha de preinscripción primero.");
                        }
                    } else {
                        respuesta.setStatus(0);
                        respuesta.setMensaje("Debes configurar una fecha de trimestre primero.");
                    }
                    break;
                case "EVALUACIONES":
                    if (par_trimestre != null) {
                        if (par_preregistro != null) {
                            if (par_inscripcion != null) {
                                if (par_clases != null) {

                                    if (par_clases.getFecha_fin().before(dFechaInicioPeriodo)) {
                                        if (par_trimestre.getFecha_fin().after(dFechaFinPeriodo)) {
                                            if (par_evaluaciones != null) {

                                                respuesta.setStatus(0);
                                                respuesta.setResponseObject(respuesta.getResponseObject());
                                                respuesta.setMensaje("Ya existe un parametro EVALUACIONES con fecha de inicio: " + par_evaluaciones.getFecha_inicio() + " y fecha de fin: " + par_evaluaciones.getFecha_fin());
                                            } else {
                                                respuesta.setStatus(-200);
                                                respuesta.setMensaje("");
                                            }
                                        } else {
                                            respuesta.setStatus(0);
                                            respuesta.setMensaje("La fecha de fin de evaluaciones no puede ser posterior a la fecha de fin del trimestre.");
                                        }

                                    } else {
                                        respuesta.setStatus(0);
                                        respuesta.setMensaje("La fecha de inicio de evaluaciones no puede ser anterior a la fecha de fin de clases.");
                                    }

                                } else {
                                    respuesta.setStatus(0);
                                    respuesta.setMensaje("Debes configurar una fecha de clases primero.");
                                }

                            } else {
                                respuesta.setStatus(0);
                                respuesta.setMensaje("Debes configurar una fecha de inscripción primero.");
                            }
                        } else {
                            respuesta.setStatus(0);
                            respuesta.setMensaje("Debes configurar una fecha de preinscripción primero.");
                        }
                    } else {
                        respuesta.setStatus(0);
                        respuesta.setMensaje("Debes configurar una fecha de trimestre primero.");
                    }
                    break;
                default:
                    respuesta.setStatus(0);
                    respuesta.setMensaje("ERROR.");
                    break;

            }

        } catch (ParseException ex) {
            respuesta.setStatus(-200);
            respuesta.setMensaje("Hubo un error en el formato de la fechas");
        }

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
