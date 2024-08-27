/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.facilitadores;

import com.google.gson.Gson;
import dao.facilitadores.Facilitadores_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managebean.general.GenericResponse;
import managedbean.usuarios.Usuarios_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author wltgm
 */
public class modificarFacilitador_Srv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("inpFacilitadorId");
        String nombre = request.getParameter("inNombre");
        String ap_pat = request.getParameter("inAp_pat");
        String ap_mat = request.getParameter("inAp_mat");
        String no_control = request.getParameter("inNo_control");
        String password = request.getParameter("inPassword");
        String correo = request.getParameter("inCorreo");

        Usuarios_MB facilitador = new Usuarios_MB();
        facilitador.setNombre(nombre);
        facilitador.setAp_pat(ap_pat);
        facilitador.setAp_mat(ap_mat);
        facilitador.setNo_control(no_control);
        facilitador.setContrasena(password);
        facilitador.setCorreo(correo);
        facilitador.setTipo_usuario("FACILITADOR");
        facilitador.setEstatus("ACTIVO");

        GenericResponse respuesta = new GenericResponse<>();

        Facilitadores_DAO.editFacilitador(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, Integer.parseInt(id), facilitador, respuesta);

        if (respuesta.getResponseObject() != null) {
            respuesta.setStatus(0);
            respuesta.setResponseObject(null);
            respuesta.setMensaje("Curso actualizado exitosamente");
        } else {
            respuesta.setStatus(-200);
            respuesta.setMensaje("Hubo un error al actualizar el curso");
        }

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }

}
