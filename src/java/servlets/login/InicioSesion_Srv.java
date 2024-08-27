/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.login;

import com.google.gson.Gson;
import config.conexion.ConexionBD;
import config.conexion.IConexion;
import dao.login.Login_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import managebean.general.GenericResponse;
import managebean.general.Logg;
import managedbean.usuarios.Usuarios_MB;
import utils.constantes.Constantes;
import utils.constantes.Strings;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Daniel Melendrez
 */
public class InicioSesion_Srv extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("inpUsuario");
        String password = request.getParameter("inpPass");

        String cadena_ingresada = request.getParameter("inpCaptcha");
        GenericResponse<Usuarios_MB> respuesta = new GenericResponse<>();

        StringBuilder msg = new StringBuilder();

        HashMap<String, String> campos = new HashMap<>();

        campos.put("inpUsuario", username);
        campos.put("inpPass", password);
        campos.put("inpCaptcha", cadena_ingresada);

        if (valida(msg, campos)) {
            respuesta.setMensaje(String.valueOf(msg));
            respuesta.setStatus(-300);
        } else {
            String password_cifrado = managebean.general.Hash.sha1(password);
            IConexion conexionDB = ConexionBD.getConexion(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD, VariablesSistema.NAME_BD);
            if (conexionDB.conectar() == Constantes.CONEXION_EXITOSA_BD) {
                GenericResponse<Usuarios_MB> responseObject = Login_DAO.iniciarSesionUsuario(conexionDB, username, password);

                if (responseObject.getResponseObject() != null) {
                    HttpSession session = request.getSession(true);
                    if (responseObject.getResponseObject().getEstatus() != "0") {

                        respuesta.setStatus(0);
                        respuesta.setMensaje(Strings.MSG_EXITO);
                        respuesta.setResponseObject(responseObject.getResponseObject());

                        session.setAttribute("usuario", respuesta.getResponseObject());

                    } else {
                        session.invalidate();
                        respuesta.setStatus(-403);
                        respuesta.setMensaje("Cuenta suspendida");
                    }
                } else {
                    respuesta.setStatus(-100);
                    respuesta.setMensaje("Datos incorrectos, intentelo de nuevo");
                }
            }

        }
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }

    }

    private boolean valida(StringBuilder msg, HashMap<String, String> campos) {
        boolean bA = false;
        for (String clave : campos.keySet()) {
            String valor = campos.get(clave);
            if (valor == null) {
                msg.append("CAMPO REQUERIDO : ");
                msg.append(clave);
                msg.append(" | ");
                bA = true;
            }

        }
        return bA;

    }

}
