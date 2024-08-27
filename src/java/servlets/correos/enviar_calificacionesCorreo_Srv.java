/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.correos;

import com.google.gson.Gson;
import dao.correos.Correos_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managebean.general.Email;
import managebean.general.GenericResponse;
import managedbean.calificaciones.Calificaciones_consultar_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class enviar_calificacionesCorreo_Srv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        GenericResponse respuesta = new GenericResponse();
        String header = request.getServletContext().getRealPath("/") + "img/header.png";
        String footer = request.getServletContext().getRealPath("/") + "img/footer.png";
        
        String CalificacionId = request.getParameter("CalificacionId");
        String Grupo = request.getParameter("Grupo");
        String Alumno = request.getParameter("Alumno");
        String NumControl = request.getParameter("NumControl");
        String Correo = request.getParameter("Correo");
        String Caluno = request.getParameter("Caluno");
        String Caldos = request.getParameter("Caldos");
        String Caltres = request.getParameter("Caltres");
        String Calcuatro = request.getParameter("Calcuatro");
        String Promedio = request.getParameter("Promedio");

        Calificaciones_consultar_MB cali = new Calificaciones_consultar_MB();
        cali.setId(Integer.parseInt(CalificacionId));
        cali.setAlumno(Alumno);
        cali.setNo_control(NumControl);
        cali.setCorreo(Correo);
        cali.setListening(Integer.parseInt(Caluno));
        cali.setReading(Integer.parseInt(Caldos));
        cali.setWriting(Integer.parseInt(Caltres));
        cali.setSpeaking(Integer.parseInt(Calcuatro));
        cali.setPromedio(Integer.parseInt(Promedio));

        try {
            Correos_DAO.enviarCorreoCalificaciones(cali, Grupo, header, footer);
            respuesta.setStatus(0);
            respuesta.setMensaje("Correo enviado");
        } catch (AddressException ex) {
            Logger.getLogger(enviar_calificacionesCorreo_Srv.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }
}
