/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.bienvenida;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import managedbean.usuarios.Usuarios_MB;

/**
 *
 * @author Daniel Melendrez
 */
public class Bienvenida_Srv extends HttpServlet implements Filter {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        RequestDispatcher dispatcher = null;
        String tipo_usu = "";
        if (session != null) {
            if (!session.isNew()) {
                Usuarios_MB usuario = (Usuarios_MB) session.getAttribute("usuario");
                if (usuario != null) {
                    tipo_usu = (String) usuario.getTipo_usuario();
                    String ruta = "/views/templates/errores/error403.jsp";
                    boolean info = true;
                    switch (tipo_usu) {
                        case "COORDINADOR":
                            ruta = "/views/Bienvenida/Paginas/Bienvenida_coordinador.jsp";
                            break;
                        case "SUPERVISOR":
                            ruta = "/views/Bienvenida/Paginas/Bienvenida_supervisor.jsp";
                            break;
                        case "FACILITADOR":
                            ruta = "/views/Bienvenida/Paginas/Bienvenida_facilitador.jsp";
                            break;
                        default:
                            ruta = "/views/templates/errores/error404.jsp";

                            break;
                    }
                    if (info) {
                        redirectView(request, response, ruta);
                    } else {

                        response.sendRedirect("../../app/login/sesionExpirada.do");
                    }
                } else {
                    response.sendRedirect("../../app/login/Index_Vw.do");
                }

            } else {
                response.sendRedirect("../../app/login/Index_Vw.do");
            }
        } else {
            response.sendRedirect("../../app/login/Index_Vw.do");
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false); // Obtener la sesión actual sin crear una nueva

        if (session == null || session.isNew()) {
            // La sesión ha sido destruida o es nueva, redirigir a la página JSP deseada
            httpResponse.sendRedirect("../../app/login/sesionExpirada.do");
            return;
        }

        // La sesión existe, continuar con la cadena de filtros o la solicitud
        chain.doFilter(request, response);
    }

    private void redirectView(HttpServletRequest req, HttpServletResponse resp, String pathView)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pathView);
        dispatcher.forward(req, resp);
    }
}
