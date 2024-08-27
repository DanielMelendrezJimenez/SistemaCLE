/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.alumnos;

import com.google.gson.Gson;
import dao.alumnos.Alumnos_DAO;
import dao.alumnos_por_grupo.Alumnos_por_grupo_DAO;
import dao.voucher_por_alumno.Voucher_por_alumno_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managebean.general.GenericResponse;
import managedbean.alumnos.Alumnos_MB;
import managedbean.alumnos_por_grupo.Alumnos_por_grupo_MB;
import managedbean.voucher_por_alumno.Voucher_por_alumno_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class modificar_alumno_Srv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long millis = System.currentTimeMillis();
        Date d = new Date(millis);

        String inpAlumnoId = request.getParameter("inpAlumnoId");
        String inNombre = request.getParameter("inpNombre");
        String inpAp_pat = request.getParameter("inpAp_pat");
        String inpAp_mat = request.getParameter("inpAp_mat");
        String inpGenero = request.getParameter("inpGenero");
        String inpNo_control = request.getParameter("inpNo_control");
        String inpCorreo = request.getParameter("inpCorreo");
        String inTipoAlumno = request.getParameter("inpTipoAlumno");
        String inpTelefono = request.getParameter("inpTelefono");
        String inCarrera = request.getParameter("inpCarrera");

        Alumnos_MB alumno = new Alumnos_MB();
        alumno.setId(Integer.parseInt(inpAlumnoId));
        alumno.setNombre(inNombre);
        alumno.setAp_pat(inpAp_pat);
        alumno.setAp_mat(inpAp_mat);
        alumno.setGenero(inpGenero);
        alumno.setNo_control(inpNo_control);
        alumno.setCorreo(inpCorreo);
        alumno.setTipo_alumno(inTipoAlumno);
        alumno.setTelefono(inpTelefono);
        alumno.setCarrera(inCarrera);
        alumno.setUltima_modificacion(d);

        GenericResponse respuesta = new GenericResponse<>();
        Alumnos_DAO.modificarAlumno(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, alumno.getId(), alumno, respuesta);

        if (respuesta.getResponseObject() != null) {

            respuesta.setStatus(0);
            respuesta.setResponseObject(null);
            respuesta.setMensaje("Alumno editado correctamente");

        } else {
            respuesta.setStatus(-200);
            respuesta.setMensaje("Hubo un error aleditar");
        }

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }
}
