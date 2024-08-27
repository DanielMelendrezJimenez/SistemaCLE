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
import managedbean.cursos.Cursos_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class guardarCurso_Srv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("inNombre");
        String nivel = request.getParameter("inNivel");
        String modalidad = request.getParameter("inModalidad");
        String hora_inicio = request.getParameter("inHoraIni");
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
        long ms = 0;
        try {
            ms = sdf.parse(hora_inicio).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(guardarCurso_Srv.class.getName()).log(Level.SEVERE, null, ex);
        }
        Time t = new Time(ms);

        String hora_fin = request.getParameter("inHoraFin");
        long msf = 0;
        try {
            msf = sdf.parse(hora_fin).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(guardarCurso_Srv.class.getName()).log(Level.SEVERE, null, ex);
        }
        Time tf = new Time(msf);

        String aula = request.getParameter("inAula");

        Cursos_MB curso = new Cursos_MB();
        curso.setNombre(nombre);
        curso.setNivel(Integer.parseInt(nivel));
        curso.setModalidad(modalidad);
        curso.setHora_inicio(t);
        curso.setHora_fin(tf);
        curso.setAula(aula);
        curso.setEstatus("OFERTADO");
        
        GenericResponse respuesta = new GenericResponse<>();
        Cursos_DAO.setCurso(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, curso, respuesta);

        if (respuesta.getResponseObject() != null) {
            respuesta.setStatus(0);
            respuesta.setResponseObject(null);
            respuesta.setMensaje("Curso almacenado exitosamente");
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

}
