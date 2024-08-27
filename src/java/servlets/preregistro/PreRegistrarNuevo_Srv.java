package servlets.preregistro;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gson.Gson;
import dao.alumnos.Alumnos_DAO;
import dao.alumnos_por_grupo.Alumnos_por_grupo_DAO;
import dao.configuracion.Parametros_DAO;
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
import managedbean.alumnos.Alumnos_MB;
import managedbean.alumnos_por_grupo.Alumnos_por_grupo_MB;
import managedbean.configuracion.Parametros_MB;
import managedbean.usuarios.Usuarios_MB;
import utils.constantes.Constantes;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class PreRegistrarNuevo_Srv extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
                        case "SUPERVISOR":
                            String grupoid = request.getParameter("inIdGrupo");
                            String nombre = request.getParameter("inNombre");
                            String ap_pat = request.getParameter("inAp_Pat");
                            String ap_mat = request.getParameter("inAp_Mat");
                            String genero = request.getParameter("inGenero");
                            String correo = request.getParameter("inCorreo");
                            String telefono = request.getParameter("inTelefono");

                            Alumnos_MB alumno = new Alumnos_MB();
                            alumno.setNombre(nombre);
                            alumno.setAp_pat(ap_pat);
                            alumno.setAp_mat(ap_mat);
                            alumno.setGenero(genero);
                            alumno.setCorreo(correo);
                            alumno.setTelefono(telefono);
                            alumno.setEstatus("ATEMPORAL");

                            GenericResponse respuesta = new GenericResponse<>();
                            Alumnos_DAO.setAlumno(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, alumno, respuesta);
                            alumno = (Alumnos_MB) respuesta.getResponseObject();

                            if (respuesta.getResponseObject() != null) {

                                respuesta = new GenericResponse<>();
                                Alumnos_por_grupo_MB alugrupo = new Alumnos_por_grupo_MB();
                                alugrupo.setId_grupo(Integer.parseInt(grupoid));
                                alugrupo.setId_alumno(alumno.getId());
                                alugrupo.setEstatus("ATEMPORAL");
                                Alumnos_por_grupo_DAO.setAlumnoGrupo(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, alugrupo, respuesta);

                                if (respuesta.getResponseObject() != null) {
                                    respuesta.setStatus(0);
                                    respuesta.setResponseObject(null);
                                    respuesta.setMensaje("Pre-registro exitoso");
                                }

                            } else {
                                respuesta.setStatus(-200);
                                respuesta.setMensaje("Hubo un error al actualizar los ajustes");
                            }

                            try (PrintWriter out = response.getWriter()) {
                                response.setContentType("application/json");
                                Gson json = new Gson();
                                out.print(json.toJson(respuesta));
                            }
                            break;
                        case "COORDINADOR":
                        case "FACILITADOR":
                            response.sendRedirect("../../app/login/noAutorizado.do");
                            break;
                        default:
                            redirectView(request, response, "/views/templates/errores/error404.jsp");
                            break;
                    }
                } else {
                    sinsesion(request, response);

                }
            } else {
                sinsesion(request, response);

            }
        } else {
            sinsesion(request, response);
        }

    }

    public void sinsesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String grupoid = request.getParameter("inIdGrupo");
        String nombre = request.getParameter("inNombre");
        String ap_pat = request.getParameter("inAp_Pat");
        String ap_mat = request.getParameter("inAp_Mat");
        String genero = request.getParameter("inGenero");
        String correo = request.getParameter("inCorreo");
        String telefono = request.getParameter("inTelefono");

        Alumnos_MB alumno = new Alumnos_MB();
        alumno.setNombre(nombre);
        alumno.setAp_pat(ap_pat);
        alumno.setAp_mat(ap_mat);
        alumno.setGenero(genero);
        alumno.setCorreo(correo);
        alumno.setTelefono(telefono);
        alumno.setEstatus("PREINSCRITO");

        GenericResponse respuesta = new GenericResponse<>();
        Alumnos_DAO.setAlumno(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, alumno, respuesta);
        alumno = (Alumnos_MB) respuesta.getResponseObject();

        if (respuesta.getResponseObject() != null) {

            respuesta = new GenericResponse<>();
            Alumnos_por_grupo_MB alugrupo = new Alumnos_por_grupo_MB();
            alugrupo.setId_grupo(Integer.parseInt(grupoid));
            alugrupo.setId_alumno(alumno.getId());
            alugrupo.setEstatus("PREINSCRITO");
            Alumnos_por_grupo_DAO.setAlumnoGrupo(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, alugrupo, respuesta);

            if (respuesta.getResponseObject() != null) {
                respuesta.setStatus(0);
                respuesta.setResponseObject(null);
                respuesta.setMensaje("Pre-registro exitoso");
            }

        } else {
            respuesta.setStatus(-200);
            respuesta.setMensaje("Hubo un error al actualizar los ajustes");
        }

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }

    private void redirectView(HttpServletRequest req, HttpServletResponse resp, String pathView)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pathView);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
