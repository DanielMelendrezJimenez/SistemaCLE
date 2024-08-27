/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.calificaciones;

import dao.calificaciones.Calificaciones_DAO;
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
import managedbean.grupos.Grupos_Consultar_MB;
import managedbean.usuarios.Usuarios_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class ver_Calificaciones_Srv_View extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GenericResponse respuesta = new GenericResponse<>();
        String id_grupo = null;
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
            id_grupo = parametros.get("GrupoId");

        }

        HttpSession session = request.getSession(false);
        RequestDispatcher dispatcher = null;
        String rol = "";
        if (session != null) {
            if (!session.isNew()) {
                Usuarios_MB usuario = (Usuarios_MB) session.getAttribute("usuario");
                if (usuario != null) {
                    rol = usuario.getTipo_usuario();
                    switch (rol) {
                        case "COORDINADOR":
                            if (id_grupo == null) {
                                respuesta.setMensaje("El campo requerido GrupoId es obligatorio");
                            } else {

                                try {
                                    Calificaciones_DAO.getcalificacionesGrupoList(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, Integer.parseInt(id_grupo), rol, respuesta);
                                    request.setAttribute("CalificacionesGruposList", respuesta.getResponseObject());

                                    respuesta = new GenericResponse<>();
                                    Grupos_DAO.getGrupoporId(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, Integer.parseInt(id_grupo), respuesta);
                                    Grupos_Consultar_MB gr= (Grupos_Consultar_MB) respuesta.getResponseObject();
                                    request.setAttribute("grupo", gr);
                                    
                                    redirectView(request, response, "/views/Calificaciones/Paginas/ver_Calificaciones_Coordi_View.jsp");

                                } catch (NumberFormatException e) {
                                    respuesta.setMensaje("El campo requerido GrupoId no es válido debe ser entero");
                                }
                            }
                            break;
                        case "FACILITADOR":
                            if (id_grupo == null) {
                                respuesta.setMensaje("El campo requerido GrupoId es obligatorio");
                            } else {

                                try {
                                    Calificaciones_DAO.getcalificacionesGrupoList(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, Integer.parseInt(id_grupo), rol, respuesta);
                                    request.setAttribute("CalificacionesGruposList", respuesta.getResponseObject());
                                    redirectView(request, response, "/views/Calificaciones/Paginas/consultar_Calificaciones_View.jsp");

                                } catch (NumberFormatException e) {
                                    respuesta.setMensaje("El campo requerido GrupoId no es válido debe ser entero");
                                }
                            }
                            break;
                        case "SUPERVISOR":
                            response.sendRedirect("../../app/login/noAutorizado.do");
                            break;
                        default:
                            redirectView(request, response, "/views/templates/errores/error404.jsp");
                            break;
                    }
                } else {
                    response.sendRedirect("../../app/login/InicioSesion.do");
                }
            } else {
                response.sendRedirect("../../app/autenticacion/sesionExpirada.do");
            }
        } else {
            response.sendRedirect("../../app/login/InicioSesion.do");
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
