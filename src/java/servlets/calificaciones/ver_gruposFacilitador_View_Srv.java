/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.calificaciones;

import dao.calificaciones.Calificaciones_DAO;
import dao.configuracion.Parametros_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import managebean.general.GenericResponse;
import managedbean.usuarios.Usuarios_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author wltgm
 */
public class ver_gruposFacilitador_View_Srv extends HttpServlet {

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
                        case "FACILITADOR":
                            GenericResponse respuesta = new GenericResponse<>();
                            Calificaciones_DAO.getGruposList(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, usuario.getId(), respuesta);
                            request.setAttribute("lstGrupos", respuesta.getResponseObject());
                            redirectView(request, response, "/views/Calificaciones/Paginas/ver_gruposFacilitadores_View.jsp");
                            break;
                        case "COORDINADOR":
                            GenericResponse respuestac = new GenericResponse<>();
                            Calificaciones_DAO.getGruposCoordiList(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuestac);
                            request.setAttribute("lstGrupos", respuestac.getResponseObject());
                            redirectView(request, response, "/views/Calificaciones/Paginas/ver_gruposCoordi_View.jsp");
                            break;
                        case "SUPERVISOR":
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
