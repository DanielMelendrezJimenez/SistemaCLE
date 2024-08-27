/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.preregistro;

import dao.configuracion.Parametros_DAO;
import dao.cursos.Cursos_DAO;
import dao.grupos.Grupos_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import managebean.general.GenericResponse;
import managedbean.configuracion.Parametros_MB;
import managedbean.usuarios.Usuarios_MB;
import utils.constantes.Constantes;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class PreRegistro_View_Srv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        RequestDispatcher dispatcher = null;
        String rol = "";
        if (session != null) {
            if (!session.isNew()) {
                Usuarios_MB usuario = (Usuarios_MB) session.getAttribute("usuario");
                if (usuario != null) {
                    rol = usuario.getTipo_usuario();
                    switch (rol) {
                        case "COORDINADOR":
                        case "SUPERVISOR":
                            GenericResponse respuesta = new GenericResponse<>();
                            Grupos_DAO.getGruposList(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta);
                            if (respuesta.getResponseObject() != null) {
                                respuesta.setStatus(Constantes.STATUS_EXITO);
                                request.setAttribute("lstGrupos", respuesta.getResponseObject());
                                redirectView(request, response, "/views/PreRegistro/Paginas/PreRegistro_View.jsp");
                            } else {
                                redirectView(request, response, "/views/templates/errores/error404.jsp");
                            }

                            break;
                        case "FACILITADOR":
                            response.sendRedirect("../../app/login/noAutorizado.do");
                            break;
                        default:
                            redirectView(request, response, "/views/templates/errores/error404.jsp");
                            break;
                    }
                } else {
                    GenericResponse respuesta = new GenericResponse<>();
                    Parametros_DAO.getExistenciaParametro(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta, "PREINSCRIPCION");
                    if (respuesta.getResponseObject() == null) {
                        redirectView(request, response, "/views/templates/errores/error404.jsp");
                    } else {
                        long millis = System.currentTimeMillis();
                        Date d = new Date(millis);

                        Parametros_MB fecha = (Parametros_MB) respuesta.getResponseObject();

                        if (d.after(fecha.getFecha_inicio()) && d.before(fecha.getFecha_fin())) {
                            respuesta = new GenericResponse<>();
                            Grupos_DAO.getGruposList(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta);

                            respuesta.setStatus(Constantes.STATUS_EXITO);
                            request.setAttribute("lstGrupos", respuesta.getResponseObject());

                            redirectView(request, response, "/views/PreRegistro/Paginas/PreRegistro_View.jsp");
                        } else {
                            redirectView(request, response, "/views/templates/errores/error404.jsp");
                        }

                    }
                }
            } else {
//                response.sendRedirect("../../app/autenticacion/sesionExpirada.do");
                GenericResponse respuesta = new GenericResponse<>();
                Parametros_DAO.getExistenciaParametro(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta, "PREINSCRIPCION");
                if (respuesta.getResponseObject() == null) {
                    redirectView(request, response, "/views/templates/errores/error404.jsp");
                } else {
                    long millis = System.currentTimeMillis();
                    Date d = new Date(millis);

                    Parametros_MB fecha = (Parametros_MB) respuesta.getResponseObject();

                    if (d.after(fecha.getFecha_inicio()) && d.before(fecha.getFecha_fin())) {
                        respuesta = new GenericResponse<>();
                        Grupos_DAO.getGruposList(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta);

                        respuesta.setStatus(Constantes.STATUS_EXITO);
                        request.setAttribute("lstGrupos", respuesta.getResponseObject());

                        redirectView(request, response, "/views/PreRegistro/Paginas/PreRegistro_View.jsp");
                    } else {
                        redirectView(request, response, "/views/templates/errores/error404.jsp");
                    }

                }
            }
        } else {
            GenericResponse respuesta = new GenericResponse<>();
            Parametros_DAO.getExistenciaParametro(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta, "PREINSCRIPCION");
            if (respuesta.getResponseObject() == null) {
                redirectView(request, response, "/views/templates/errores/error404.jsp");
            } else {
                long millis = System.currentTimeMillis();
                Date d = new Date(millis);

                Parametros_MB fecha = (Parametros_MB) respuesta.getResponseObject();

                if (d.after(fecha.getFecha_inicio()) && d.before(fecha.getFecha_fin())) {
                    respuesta = new GenericResponse<>();
                    Grupos_DAO.getGruposList(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta);

                    respuesta.setStatus(Constantes.STATUS_EXITO);
                    request.setAttribute("lstGrupos", respuesta.getResponseObject());

                    redirectView(request, response, "/views/PreRegistro/Paginas/PreRegistro_View.jsp");
                } else {
                    redirectView(request, response, "/views/templates/errores/error404.jsp");
                }

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void redirectView(HttpServletRequest req, HttpServletResponse resp, String pathView)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pathView);
        dispatcher.forward(req, resp);
    }
}
