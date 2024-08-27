/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import managedbean.usuarios.Usuarios_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class Index_Vw extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Usuarios_MB usuario = (Usuarios_MB) session.getAttribute("usuario");
            if (usuario != null) {
                response.sendRedirect(VariablesSistema.APLICACION + "app/bienvenida/bienvenida.do");
                return;
            }
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/Acceso/Paginas/InicioSesion_View.jsp");
        dispatcher.forward(request, response);
    }

}
