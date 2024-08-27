/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.alumnos;

import com.google.gson.Gson;
import dao.alumnos.Alumnos_DAO;
import dao.alumnos_por_grupo.Alumnos_por_grupo_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managebean.general.GenericResponse;
import managedbean.alumnos_por_grupo.Alumnos_por_grupo_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class editar_atemporal_Srv extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("AlumnoId");
        
        GenericResponse respuesta = new GenericResponse<>();
        Alumnos_por_grupo_DAO.getAlumnoporgrupo(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, Integer.parseInt(id), respuesta);
        Alumnos_por_grupo_MB alugrupo = new Alumnos_por_grupo_MB();
        
        if (respuesta.getResponseObject() != null) {
            alugrupo = (Alumnos_por_grupo_MB) respuesta.getResponseObject();
        }
        
        if (id != null) {
            respuesta = new GenericResponse<>();
            Alumnos_DAO.editAtemporal(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, String.valueOf(alugrupo.getId_alumno()), respuesta);
            if (respuesta.getResponseObject() != null) {
                respuesta = new GenericResponse<>();
                alugrupo.setEstatus("PREINSCRITO");
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
                respuesta.setMensaje("ERROR");
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
