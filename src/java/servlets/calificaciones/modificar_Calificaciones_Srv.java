/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.calificaciones;

import com.google.gson.Gson;
import dao.calificaciones.Calificaciones_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managebean.general.GenericResponse;
import managedbean.calificaciones.Calificaciones_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author wltgm
 */
public class modificar_Calificaciones_Srv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_calificacion = request.getParameter("inpCalificacionId");
        String cal_uno = request.getParameter("inpCaluno");
        String cal_dos = request.getParameter("inpCaldos");
        String cal_tres = request.getParameter("inpCaltres");
        String cal_cuatro = request.getParameter("inpCalcuatro");

        GenericResponse respuesta = new GenericResponse<>();

        Calificaciones_MB param = new Calificaciones_MB();
        param.setId(Integer.parseInt(id_calificacion));
        param.setListening(Integer.parseInt(cal_uno));
        param.setReading(Integer.parseInt(cal_dos));
        param.setWriting(Integer.parseInt(cal_tres));
        param.setSpeaking(Integer.parseInt(cal_cuatro));


        int promedio = (Integer.parseInt(cal_uno) + Integer.parseInt(cal_dos)
                + Integer.parseInt(cal_tres) + Integer.parseInt(cal_cuatro) ) / 4;

        param.setPromedio(promedio);

        Calificaciones_DAO.editarCalificacion(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, param, respuesta);

        if (respuesta.getResponseObject() != null) {
            respuesta.setStatus(0);
            respuesta.setResponseObject(null);
            respuesta.setMensaje("Calificaciones almacenadas exitosamente");
        } else {
            respuesta.setStatus(-200);
            respuesta.setMensaje("Hubo un error al guardar calificaciones");
        }

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }
}
