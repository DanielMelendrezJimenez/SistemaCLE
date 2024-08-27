/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.parametros;

import dao.configuracion.Parametros_DAO;
import dao.usuarios.Usuarios_DAO;
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
import managedbean.configuracion.Parametros_MB;
import managedbean.usuarios.Usuarios_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class EditarParametro_View_Srv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

                            GenericResponse respuesta = new GenericResponse<>();
                            String ParametroId = null;
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
                                ParametroId = parametros.get("ParametroId");

                            }

                            Parametros_MB parametro = null;
                            if (ParametroId == null) {
                                respuesta.setMensaje("El campo requerido ParametroId es obligatorio");
                            } else {

                                try {

                                    int iParametroId = Integer.parseInt(ParametroId);
                                    Parametros_DAO.getParametro(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, iParametroId, respuesta);
                                    if (respuesta.getResponseObject() != null) {
                                        parametro = (Parametros_MB) respuesta.getResponseObject();
                                    }

                                } catch (NumberFormatException e) {
                                    respuesta.setMensaje("El campo requerido iParametroId no es válido debe ser entero");
                                }
                            }
                            request.setAttribute("parametros", parametro);

                            redirectView(request, response, "/views/Parametros/Paginas/Editar_parametro_View.jsp");
                            break;
                        case "SUPERVISOR":
                        case "FACILITADOR":
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
