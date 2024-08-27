/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.preregistro;

import dao.cursos.Cursos_DAO;
import dao.grupos.Grupos_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import managebean.general.GenericResponse;
import managebean.general.Misc;
import managedbean.cursos.Cursos_MB;
import managedbean.grupos.Grupos_MB;
import managedbean.usuarios.Usuarios_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class FormPreRegistro_Vw_Srv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GenericResponse respuesta = new GenericResponse<>();
        String GrupoId = null;
        request.setCharacterEncoding("UTF-8");
        String queryString = request.getQueryString();
        String encodedParametros = Misc.obtenerValorParametro(queryString, "params");

        if (encodedParametros != null) {
            String parametrosDecodificados = URLDecoder.decode(encodedParametros, "UTF-8");
            try {
                parametrosDecodificados = new String(Base64.getDecoder().decode(parametrosDecodificados), StandardCharsets.UTF_8);

            } catch (IllegalArgumentException e) {

            }

            // Crear un Map para almacenar los parámetros decodificados
            Map<String, String> parametros = new HashMap<>();

            // Analizar y almacenar los pares clave-valor de los parámetros decodificados
            String[] paresParametros = parametrosDecodificados.split("&");
            for (String par : paresParametros) {
                String[] partes = par.split("=");
                if (partes.length == 2) {
                    String clave = partes[0];
                    String valor = partes[1];
                    parametros.put(clave, valor);
                }
            }

            // Acceder a los valores de los parámetros de manera dinámica
            GrupoId = parametros.get("CursoId");

        }

        Grupos_MB grupo = null;
        Cursos_MB curso = null;
        if (GrupoId == null) {
            respuesta.setMensaje("El campo requerido GrupoId es obligatorio");
        } else {

            try {

                int iGrupoId = Integer.parseInt(GrupoId);
                Grupos_DAO.getGrupo(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, iGrupoId, respuesta);
                if (respuesta.getResponseObject() != null) {
                    grupo = (Grupos_MB) respuesta.getResponseObject();
                    respuesta = new GenericResponse<>();
                    Grupos_DAO.getCurso(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, grupo.getId(), respuesta);
                    curso = (Cursos_MB) respuesta.getResponseObject();
                }

            } catch (NumberFormatException e) {
                respuesta.setMensaje("El campo requerido iCursoId no es válido debe ser entero");
            }
        }
        request.setAttribute("grupo", grupo);
        System.out.println("Curso: " + curso);
        if (curso.getNivel() > 1 || !"Basico".equals(curso.getNombre())) {
            redirectView(request, response, "/views/PreRegistro/Paginas/Preinscripcion_Alum_Regis_View.jsp");
        } else {
            redirectView(request, response, "/views/PreRegistro/Paginas/Preinscripcion_Alum_Nuevo_View.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void redirectView(HttpServletRequest req, HttpServletResponse resp, String pathView)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pathView);
        dispatcher.forward(req, resp);
    }

}
