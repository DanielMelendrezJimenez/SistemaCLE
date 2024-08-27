/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.grupos;

import com.google.gson.Gson;
import dao.cursos.Cursos_DAO;
import dao.facilitadores.Facilitadores_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import managebean.general.GenericResponse;
import managedbean.cursos.Cursos_MB;
import managedbean.usuarios.Usuarios_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author wltgm
 */
public class consultarFacilitadores_Srv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idcurso = request.getParameter("CursoId");

        HttpSession session = request.getSession(false);
        Usuarios_MB usuario = (Usuarios_MB) session.getAttribute("usuario");

        GenericResponse respuesta = new GenericResponse<>();

        Facilitadores_DAO.getFacilitadoresDispoibles(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta,Integer.parseInt(idcurso));
        request.setAttribute("lstFacilitadores", respuesta.getResponseObject());

        System.out.println(respuesta.getResponseObject());

        if (respuesta.getResponseObject() != null) {
            respuesta.setStatus(0);
            respuesta.setResponseObject(respuesta.getResponseObject());
            respuesta.setMensaje("Facilitador almacenado exitosamente");
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
