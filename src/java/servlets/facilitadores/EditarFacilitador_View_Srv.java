/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.facilitadores;

import dao.facilitadores.Facilitadores_DAO;
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
import managedbean.usuarios.Usuarios_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author wltgm
 */
public class EditarFacilitador_View_Srv extends HttpServlet {

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
                            String FacilitadorId = null;
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
                                FacilitadorId = parametros.get("FacilitadorId");

                            }

                            Usuarios_MB facilitador = null;
                            if (FacilitadorId == null) {
                                respuesta.setMensaje("El campo requerido FacilitadorId es obligatorio");
                            } else {

                                try {

                                    int iFacilitadorId = Integer.parseInt(FacilitadorId);
                                    Facilitadores_DAO.getFacilitador(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, iFacilitadorId, respuesta);
                                    if (respuesta.getResponseObject() != null) {
                                        facilitador = (Usuarios_MB) respuesta.getResponseObject();
                                    }

                                } catch (NumberFormatException e) {
                                    respuesta.setMensaje("El campo requerido iCursoId no es válido debe ser entero");
                                }
                            }
                            request.setAttribute("facilitador", facilitador);

                            redirectView(request, response, "/views/Facilitadores/Paginas/EditarFacilitador_View.jsp");
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
