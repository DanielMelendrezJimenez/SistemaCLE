/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.solicitudes;

import dao.inscripciones.Inscripciones_DAO;
import dao.solicitudes.Solicitudes_DAO;
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
 * @author Daniel Melendrez
 */
public class ver_SolicitudesPendientes_Srv_View extends HttpServlet {

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
                            GenericResponse respuesta = new GenericResponse<>();
                            Solicitudes_DAO.verPendietes(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, "PENDIENTE", respuesta);
                            request.setAttribute("SolicitudesList", respuesta.getResponseObject());

                            redirectView(request, response, "/views/Solicitudes/Paginas/aprobar_Solicitudes_View.jsp");

                            break;
                        case "SUPERVISOR":
                            GenericResponse respuestaS = new GenericResponse<>();
                            Solicitudes_DAO.verPendietes(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, "APROBADA", respuestaS);
                            request.setAttribute("SolicitudesList", respuestaS.getResponseObject());

                            redirectView(request, response, "/views/Solicitudes/Paginas/mostrar_Aprobadas_View.jsp");

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
