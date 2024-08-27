/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.login;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel Melendrez
 */
public class ValidarCaptcha_Srv extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String respuesta = "no";
        try {
            String cadena_ingresada = request.getParameter("inpCaptcha");
            HttpSession session_nueva = request.getSession(false);
            String key = "";

            if (session_nueva == null) {
                respuesta = "El captcha caducó.";
            } else {
                Enumeration<String> attributeNames = session_nueva.getAttributeNames();
                boolean b = false;
                while (attributeNames.hasMoreElements()) {
                    if ("codigoCaptcha".equals(attributeNames.nextElement())) {
                        b = true;
                        break;
                    }
                }
                if (b) {
                    if (session_nueva.getAttribute("codigoCaptcha") == null) {
                        respuesta = "Error al cargar el código";

                    } else {
                        key = (String) session_nueva.getAttribute("codigoCaptcha");
                        if (cadena_ingresada == null) {
                            respuesta = "El código es obligatorio";

                        } else {
                            if (key.equals(cadena_ingresada)) {
                                respuesta = "si";
                            } else {
                                respuesta = "no";
                            }
                        }
                    }

                } else {
                    respuesta = "Error al cargar el código";
                }
            }

        } catch (Exception e) {
            respuesta = "Error fatal: " + getStackTrace(e);

        }
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }

    }

    public static String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }
}
