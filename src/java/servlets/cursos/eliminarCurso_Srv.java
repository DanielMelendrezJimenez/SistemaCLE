/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.cursos;

import com.google.gson.Gson;
import dao.cursos.Cursos_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managebean.general.GenericResponse;
import managedbean.cursos.Cursos_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class eliminarCurso_Srv extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("CursoId");

        Cursos_MB curso = new Cursos_MB();

        GenericResponse respuesta = new GenericResponse<>();

        if (id != null) {
            Cursos_DAO.beforeRemoveCurso(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, id, respuesta);
            curso = (Cursos_MB) respuesta.getResponseObject();
            if (curso == null) {
                Cursos_DAO.removeCurso(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, id, respuesta);
            } else {
            respuesta.setStatus(-200);
            respuesta.setMensaje("ERROR: no es posible eliminar un curso con grupo activo");
            }
        } else {
            respuesta.setStatus(-200);
            respuesta.setMensaje("ERROR: Id vacío");
        }
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }
}
