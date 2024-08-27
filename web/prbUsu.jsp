<%-- 
    Document   : prbUsu
    Created on : Jun 13, 2024, 3:56:32 PM
    Author     : Daniel Melendrez
--%>

<%@page import="java.util.Random"%>
<%@page import="java.awt.Color"%>
<%@page import="java.awt.Color"%>
<%@page import="java.awt.Font"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.awt.Graphics"%>
<%@page import="javax.imageio.ImageIO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%--Links al CDN--%>
        <link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/bootstrap-datepicker.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/botones.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/catalogos-modal.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/catalogos-tablas.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/general.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/jquery-ui.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/spinner.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/tablas.estandarITT.css" rel="stylesheet">


        <title>Sistema CLE</title>
    </head>
    <body>
        <%@include file="views/templates/header.jsp" %>
        <%-- Navigation Bar Header --%>
        <div class="container">
            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button"
                                class="navbar-toggle collapsed"
                                data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">Sistema de la Coordinación de Lenguas Extranjeras del ITToluca</a>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <li  class="active"><a href="index.jsp">Iniciar sesión</a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
        </div>
        <%-- Aqui comienza el formulario --%>
        <div class="container">
            <div class="text-left" >
                <form 
                    action="app/Usuarios/registroUsuario.do" id="form-usuarios-registrar" name="form-usuarios-registrar"  method="post"
                    role="form"  class="row g-3 needs-validation form-signin">
                    <div class="form-group">
                        <label for="inpUsuario">Nombre</label>
                        <input id="inpUsuario"
                               name="inpUsuario"
                               type="text"
                               class="form-control"
                               placeholder="Usuario"
                               required
                               autofocus
                               autocomplete="off"
                               >
                    </div>
                    <div class="form-group">
                        <label for="inpAp_pat">Primer Apellido</label>
                        <input id="inpAp_pat"
                               name="inpAp_pat"
                               type="text"
                               class="form-control"
                               placeholder="Usuario"
                               required
                               autofocus
                               autocomplete="off"
                               >
                    </div>
                    <div class="form-group">
                        <label for="inpAp_mat">Segundo Apellido</label>
                        <input id="inpAp_mat"
                               name="inpAp_mat"
                               type="text"
                               class="form-control"
                               placeholder="Usuario"
                               required
                               autofocus
                               autocomplete="off"
                               >
                    </div>
                    <div class="form-group">
                        <label for="inpNoControl">No Control</label>
                        <input id="inpNoControl"
                               name="inpNoControl"
                               type="text"
                               class="form-control"
                               placeholder="Usuario"
                               required
                               autofocus
                               autocomplete="off"
                               >
                    </div>
                    <div class="form-group">
                        <label for="inpPass">Contraseña</label>
                        <input id="inpPass"
                               name="inpPass"
                               type="password"
                               class="form-control"
                               placeholder="Contraseña"
                               required
                               >
                    </div>
                    <div class="form-group">
                        <label for="inpCorreo">Correo</label>
                        <input id="inpCorreo"
                               name="inpCorreo"
                               type="text"
                               class="form-control"
                               placeholder="Usuario"
                               required
                               autofocus
                               autocomplete="off"
                               >
                    </div>
                    <div class="form-group">
                        <label for="inpTipoUsu">Tipo Usuario</label>
                        <input id="inpTipoUsu"
                               name="inpTipoUsu"
                               type="text"
                               class="form-control"
                               placeholder="Usuario"
                               required
                               autofocus
                               autocomplete="off"
                               >
                    </div>
                    <button class="btn btn-lg btn-primary btn-block" type="button" id="btnRegistrarUsuarios" name="btnRegistrarUsuarios">
                        Ingresar
                        <i id="spin_carga" class="fa fa-spinner fa-spin"></i>
                    </button>
                </form>
            </div>
        </div>
        <%@include file="views/templates/footer.jsp"%>
        <script src="/CDN-ITT/js/jquery.estandarITT.js" type="text/javascript"></script>
        <script src="/CDN-ITT/js/jquery-ui.estandarITT.js" type="text/javascript"></script>
        <script src="/CDN-ITT/js/jquery.dataTables.estandarITT.js" type="text/javascript"></script>
        <script src="/CDN-ITT/js/base.estandarITT.js" type="text/javascript"></script>
        <script src="/CDN-ITT/js/tablas.estandarITT.js" type="text/javascript"></script>
        <script src="/CDN-ITT/js/bootstrap-datepicker.estandarITT.js" type="text/javascript"></script>
        <script src ="/CDN-ITT/js/catalogos-modal.estandarITT.js" type="text/javascript"></script>
        <script src ="/CDN-ITT/js/catalogos-tablas.estandarITT.js" type="text/javascript"></script>
        <script src="/CDN-ITT/js/spinner.estandarITT.js" type="text/javascript"></script>
        <script src ="/CDN-ITT/js/bootstrap-datepicker.estandarITT.js" type="text/javascript"></script>
        <script src ="/CDN-ITT/js/bootstrap-datepicker.es.estandarITT.js" type="text/javascript"></script>
    </body>

</html>
