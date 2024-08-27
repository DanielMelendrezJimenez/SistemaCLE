/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.cursos;

import com.google.gson.Gson;
import dao.configuracion.Parametros_DAO;
import dao.cursos.Cursos_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managebean.general.GenericResponse;
import managedbean.configuracion.Parametros_MB;
import managedbean.cursos.Cursos_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class modificarCurso_Srv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
        String id = request.getParameter("inId");
        String nombre = request.getParameter("inNombre");
        String nivel = request.getParameter("inNivel");
        String modalidad = request.getParameter("inModalidad");
        String hora_inicio = request.getParameter("inHoraIni");
        String hora_fin = request.getParameter("inHoraFin");
        String aula = request.getParameter("inAula");
        String status = request.getParameter("inStatus");
        

        GenericResponse respuesta = new GenericResponse<>();
        try {
            long ms = 0;
            ms = sdf.parse(hora_inicio).getTime();
            Time t = new Time(ms);

            long msf = 0;
            msf = sdf.parse(hora_fin).getTime();
            Time tf = new Time(msf);

            Cursos_MB curso = new Cursos_MB();
            curso.setId(Integer.parseInt(id));
            curso.setNombre(nombre);
            curso.setNivel(Integer.parseInt(nivel));
            curso.setModalidad(modalidad);
            curso.setHora_inicio(t);
            curso.setHora_fin(tf);
            curso.setAula(aula);
            curso.setEstatus(status);

            Cursos_DAO.editCurso(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, Integer.parseInt(id), curso, respuesta);

            if (respuesta.getResponseObject() != null) {
                respuesta.setStatus(0);
                respuesta.setResponseObject(null);
                respuesta.setMensaje("Curso actualizado exitosamente");
            } else {
                respuesta.setStatus(-200);
                respuesta.setMensaje("Hubo un error al actualizar el curso");
            }

        } catch (ParseException ex) {
            respuesta.setStatus(-200);
            respuesta.setMensaje("Hubo un error en el formato de la horas");
        }

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }
}
