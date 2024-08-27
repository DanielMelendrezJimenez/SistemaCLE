/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.parametros;

import com.google.gson.Gson;
import dao.configuracion.Parametros_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managebean.general.GenericResponse;
import managedbean.configuracion.Parametros_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class EditarParametro_Srv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("inId");
        String concepto = request.getParameter("inConcepto");
        String fecha_inicio = request.getParameter("inFechaInicioPeriodo");
        String fecha_fin = request.getParameter("inFechaFinPeriodo");
        String estatus = request.getParameter("inStatus");

        GenericResponse respuesta = new GenericResponse<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {

            java.util.Date utilDateInicio = dateFormat.parse(fecha_inicio);
            long timeInMillisInicioPeriodo = utilDateInicio.getTime();
            Date dFechaInicioPeriodo = new Date(timeInMillisInicioPeriodo);

            java.util.Date utilDateFin = dateFormat.parse(fecha_fin);
            long timeInMillisFinPeriodo = utilDateFin.getTime();
            Date dFechaFinPeriodo = new Date(timeInMillisFinPeriodo);

            Parametros_MB param = new Parametros_MB();
            param.setId(Integer.parseInt(id));
            param.setConcepto(concepto);
            param.setFecha_inicio(dFechaInicioPeriodo);
            param.setFecha_fin(dFechaFinPeriodo);
            param.setEstatus(estatus);

            Parametros_DAO.editParametro(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, Integer.parseInt(id),param, respuesta);

            if (respuesta.getResponseObject() != null) {
                respuesta.setStatus(0);
                respuesta.setResponseObject(null);
                respuesta.setMensaje("Ajsutes actualizados exitosamente");
            } else {
                respuesta.setStatus(-200);
                respuesta.setMensaje("Hubo un error al actualizar los ajustes");
            }

        } catch (ParseException ex) {
            respuesta.setStatus(-200);
            respuesta.setMensaje("Hubo un error en el formato de la fechas");
        }

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }



}
