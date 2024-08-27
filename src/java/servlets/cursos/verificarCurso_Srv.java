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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class verificarCurso_Srv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("inId");
        String nombre = request.getParameter("inNombre");
        String nivel = request.getParameter("inNivel");
        String modalidad = request.getParameter("inModalidad");
        String hora_ini = request.getParameter("inHoraIni");
        String hora_fin = request.getParameter("inHoraFin");
        String aula = request.getParameter("inAula");

        GenericResponse respuesta = new GenericResponse<Cursos_MB>();

        Cursos_DAO.getExistenciaCurso(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, respuesta, aula, hora_ini);

        Cursos_MB res_curso = (Cursos_MB) respuesta.getResponseObject();

        if (id == null) {
            if (res_curso != null) {
                respuesta.setStatus(0);
                respuesta.setResponseObject(respuesta.getResponseObject());
                respuesta.setMensaje("Ya existe un curso con esa aula: " + res_curso.getAula() + " y hora de inicio: " + res_curso.getHora_inicio());
            } else {
                respuesta.setStatus(-200);
                respuesta.setMensaje("");
            }
        } else {
            if (res_curso != null) {
                if (Integer.parseInt(id) == res_curso.getId()) {
                    respuesta.setStatus(-200);
                    respuesta.setMensaje("");
                } else {
                    respuesta.setStatus(0);
                    respuesta.setResponseObject(respuesta.getResponseObject());
                    respuesta.setMensaje("Ya existe un curso con esa aula: " + res_curso.getAula() + " y hora de inicio: " + res_curso.getHora_inicio());
                }
            } else {
                respuesta.setStatus(-200);
                respuesta.setMensaje("");
            }
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
