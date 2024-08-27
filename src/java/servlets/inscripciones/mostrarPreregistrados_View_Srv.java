/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.inscripciones;

import dao.configuracion.Parametros_DAO;
import dao.inscripciones.Inscripciones_DAO;
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
import utils.constantes.VariablesSistema;

/**
 *
 * @author wltgm
 */
public class mostrarPreregistrados_View_Srv extends HttpServlet {

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
                            Parametros_DAO.getExistenciaParametro(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta, "INSCRIPCION");
                            if (respuesta.getResponseObject() == null) {
                                redirectView(request, response, "/views/templates/errores/error404.jsp");
                            } else {
                                long millis = System.currentTimeMillis();
                                Date d = new Date(millis);

                                Parametros_MB fecha = (Parametros_MB) respuesta.getResponseObject();

                                if (d.after(fecha.getFecha_inicio()) && d.before(fecha.getFecha_fin())) {

                                    respuesta = new GenericResponse<>();
                                    Inscripciones_DAO.getAlumnosGruposList(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta);
                                    request.setAttribute("AlumnosGruposList", respuesta.getResponseObject());

                                    redirectView(request, response, "/views/Inscripciones/Paginas/mostrar_Preregistrados_View.jsp");
                                } else {
                                    redirectView(request, response, "/views/templates/errores/error404.jsp");
                                }

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
                    response.sendRedirect("../../app/login/InicioSesion.do");
                }
            } else {
                response.sendRedirect("../../app/autenticacion/sesionExpirada.do");
            }
        } else {
            response.sendRedirect("../../app/login/InicioSesion.do");
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
