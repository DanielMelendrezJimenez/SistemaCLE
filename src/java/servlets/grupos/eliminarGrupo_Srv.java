/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.grupos;

import com.google.gson.Gson;
import dao.grupos.Grupos_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managebean.general.GenericResponse;
import utils.constantes.VariablesSistema;

/**
 *
 * @author wltgm
 */
public class eliminarGrupo_Srv extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("GrupoId");
        GenericResponse respuesta = new GenericResponse<>();
        Grupos_DAO.removeVerificar(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, id, respuesta);

        if (respuesta.getResponseObject() == null) {
            if (id != null) {
                Grupos_DAO.removeGrupo(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, id, respuesta);
            } else {
                respuesta.setStatus(-200);
                respuesta.setMensaje("ERROR: Id vacío");
            }
        }else {
            respuesta.setStatus(405);
            respuesta.setMensaje("ERROR: grupo con alumnos inscritos");
        }

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }
}
