/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.solicitudes;

import com.google.gson.Gson;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import dao.alumnos.Alumnos_DAO;
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
import managedbean.alumnos.Alumnos_MB;
import managedbean.usuarios.Usuarios_MB;
import managedean.solicitudes.Solicitudes_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class guardar_Solicitud_Srv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Solicitudes_MB solicitud = new Solicitudes_MB();

        HttpSession session = request.getSession(false);
        RequestDispatcher dispatcher = null;
        String rol = "";
        if (session != null) {
            if (!session.isNew()) {
                Usuarios_MB usuario = (Usuarios_MB) session.getAttribute("usuario");
                if (usuario != null) {
                    rol = usuario.getTipo_usuario();
                    switch (rol) {
                        case "SUPERVISOR":
                            solicitud.setOrigen("ALUMNOS");
                            break;
                        case "COORDINADOR":
                            response.sendRedirect("../../app/login/noAutorizado.do");
                            break;
                        case "FACILITADOR":
                            solicitud.setOrigen("CALIFICACIONES");
                            break;
                        default:
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

        String concepto = request.getParameter("inpConcepto");
        String descripcion = request.getParameter("inpDescripcion");
        String no_control = request.getParameter("inpNo_control");
        String campo_editar = request.getParameter("inpCampo");
        String valor_nuevo = request.getParameter("inpValor");

        Alumnos_MB alumno = new Alumnos_MB();

        GenericResponse respuesta = new GenericResponse<>();
        Alumnos_DAO.buscarAlumno(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, no_control, respuesta);

        if (respuesta.getResponseObject() != null) {
            alumno = (Alumnos_MB) respuesta.getResponseObject();

            solicitud.setConcepto(concepto);
            solicitud.setDescripcion(descripcion);
            solicitud.setId_alumno(alumno.getId());
            solicitud.setCampo_editar(campo_editar);
            solicitud.setValor_nuevo(valor_nuevo);
            solicitud.setEstatus("PENDIENTE");

            respuesta = new GenericResponse<>();
            Solicitudes_DAO.setSolicitud(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, solicitud, respuesta);

            if (respuesta.getResponseObject() != null) {
                respuesta.setStatus(0);
                respuesta.setResponseObject(null);
                respuesta.setMensaje("Solicitud almacenada exitosamente");
            } else {
                respuesta.setStatus(-200);
                respuesta.setMensaje("Hubo un error al registrar solicitud");
            }
        } else {
            respuesta.setStatus(-601);
            respuesta.setMensaje("No existe alumno con ese numero de control");
        }

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }
}
