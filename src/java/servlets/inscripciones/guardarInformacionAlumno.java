/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.inscripciones;

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
public class guardarInformacionAlumno extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long millis = System.currentTimeMillis();
        Date d = new Date(millis);

        String inpAluGrupo = request.getParameter("inpAluGrupo");
        GenericResponse respuesta = new GenericResponse<>();
        Alumnos_por_grupo_DAO.getAlumnoporgrupo(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, Integer.parseInt(inpAluGrupo), respuesta);
        Alumnos_por_grupo_MB alugrupo = null;

        if (respuesta.getResponseObject() != null) {
            alugrupo = (Alumnos_por_grupo_MB) respuesta.getResponseObject();
        }

        String inNombre = request.getParameter("inNombre");
        String inpAp_pat = request.getParameter("inpAp_pat");
        String inpAp_mat = request.getParameter("inpAp_mat");
        String inpGenero = request.getParameter("inpGenero");
        String inpNo_control = request.getParameter("inpNo_control");
        String inpCorreo = request.getParameter("inpCorreo");
        String inTipoAlumno = request.getParameter("inTipoAlumno");
        String inpTelefono = request.getParameter("inpTelefono");
        String inCarrera = request.getParameter("inCarrera");

        String inFolio = request.getParameter("inFolio");
        String inpReferencia = request.getParameter("inpReferencia");
        BigDecimal inpCantidad = new BigDecimal(request.getParameter("inpCantidad"));

        Alumnos_MB alumno = new Alumnos_MB();
        alumno.setId(alugrupo.getId_alumno());
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
        alumno.setEstatus("INSCRITO");

        Voucher_por_alumno_MB vpalu = new Voucher_por_alumno_MB();
        vpalu.setId_alumno(alugrupo.getId_alumno());
        vpalu.setId_grupo(alugrupo.getId_grupo());
        vpalu.setFolio(inFolio);
        vpalu.setReferencia(inpReferencia);
        vpalu.setCantidad(inpCantidad);
        vpalu.setFecha(d);
        vpalu.setEstatus("VALIDADO");
        respuesta = new GenericResponse<>();
        Alumnos_DAO.completarAlumno(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, alumno.getId(), alumno, respuesta);
        alumno = (Alumnos_MB) respuesta.getResponseObject();

        if (respuesta.getResponseObject() != null) {
            respuesta = new GenericResponse<>();
            Voucher_por_alumno_DAO.validarFolio(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, vpalu.getFolio(), respuesta);
            if (respuesta.getResponseObject() == null) {
                respuesta = new GenericResponse<>();
                Voucher_por_alumno_DAO.setAlumnoVoucher(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, vpalu, respuesta);
                if (respuesta.getResponseObject() != null) {
                    respuesta = new GenericResponse<>();
                    alugrupo.setEstatus("INSCRITO");
                    Alumnos_por_grupo_DAO.editAlumnoGrupo(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, alugrupo, respuesta);
                    if (respuesta.getResponseObject() != null) {
                        respuesta.setStatus(0);
                        respuesta.setResponseObject(null);
                        respuesta.setMensaje("Informacion almacenada correctamente");
                    } else {
                        respuesta.setStatus(-200);
                        respuesta.setMensaje("Hubo un error al preregistrar");
                    }
                } else {
                    respuesta.setStatus(-200);
                    respuesta.setMensaje("Hubo un error al registrar váucher");
                }
            } else {
                respuesta.setStatus(-200);
                respuesta.setMensaje("Folio ya existente");
            }

        } else {
            respuesta.setStatus(-200);
            respuesta.setMensaje("Hubo un error al completar informacion");
        }

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }
}
