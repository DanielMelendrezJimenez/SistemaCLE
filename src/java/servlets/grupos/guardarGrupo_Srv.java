/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.grupos;

import com.google.gson.Gson;
import dao.grupos.Grupos_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import managebean.general.GenericResponse;
import managedbean.grupos.Grupos_MB;
import managedbean.usuarios.Usuarios_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author wltgm
 */
public class guardarGrupo_Srv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idcurso = request.getParameter("CursoId");
        String idfacilitador = request.getParameter("FacilitadorId");
        String idtrimestre = request.getParameter("TrimestreId");

        HttpSession session = request.getSession(false);
        Usuarios_MB usuario = (Usuarios_MB) session.getAttribute("usuario");

        long millis = System.currentTimeMillis();
        Date d = new Date(millis);

        Grupos_MB grupo = new Grupos_MB();
        grupo.setId_curso(Integer.parseInt(idcurso));
        grupo.setId_facilitador(Integer.parseInt(idfacilitador));
        grupo.setTrimestre(idtrimestre);
        grupo.setId_usuario_modifico(usuario.getId());
        grupo.setUltima_modificacion(d);
        grupo.setEstatus("ACTIVO");

        GenericResponse respuesta = new GenericResponse<>();
        Grupos_DAO.setGrupo(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, grupo, respuesta);

        if (respuesta.getResponseObject() != null) {
            respuesta.setStatus(0);
            respuesta.setResponseObject(null);
            respuesta.setMensaje("Facilitador almacenado exitosamente");
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
