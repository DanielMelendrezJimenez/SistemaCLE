<%--
    Document   : index
    Created on : 25/05/2024, 11:37:44 PM
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
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link rel="icon" href="/CDN-ITT/img/logo.png">

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

        <link href="css/cssgenerados/login.css" rel="stylesheet">
        <link href="css/cssgenerados/tables.css" rel="stylesheet">
        <link href="css/lib/jquery-ui.css" rel="stylesheet">
        <link href="css/lib/jquery-ui.theme.css" rel="stylesheet">
        <link href="css/lib/jquery-ui.structure.css" rel="stylesheet">
        <link href="css/cssgenerados/general.css" rel="stylesheet">
        <link href="css/cssgenerados/generadoPosgrado.css" rel="stylesheet">
        <link href="css/cssgenerados/style.css" rel="stylesheet">

        <link href="css/cssgenerados/listaScroll.css" rel="stylesheet">
        <script language="Javascript">
            document.oncontextmenu = function () {
                return false;
            };
        </script>
        
        <title>Sistema CLE</title>
    </head>
    <body>
        <%@include file="views/templates/header.jsp" %>

        <div id="pageLoader">
            <div id="pageSpinner">
                <%@include file="views/templates/spinner.estandarITT.jsp"%>
            </div>
        </div>

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
                <c:if test="${not empty respuesta}">
                    <div class="alert alert-danger alert-dismissible">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>${respuesta}</strong> 
                    </div>
                </c:if>
                <form 
                    action="app/bienvenida/bienvenida.do" id="form-login" name="form-login"  method="GET"
                    role="form"  class="row g-3 needs-validation form-signin">
                    <div class="form-group">
                        <label for="inpUsuario">Usuario</label>
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
                        <label for="inpPass">Contraseña</label>
                        <input id="inpPass"
                               name="inpPass"
                               type="password"
                               class="form-control"
                               placeholder="Contraseña"
                               required
                               >
                    </div>

                    <div id="divCaptcha" class="form-group">
                        <label for="inpCaptcha" style="margin-top: 20px">Código</label>

                        <label class="" >
                            <img id="img_captcha" class="captcha" src="app/login/captcha.png" class="rounded-2">
                            <button
                                id="btnCaptcha"
                                type="button"
                                required
                                autofocus
                                class="btn btn-default">
                                <i class="fa fa-refresh" aria-hidden="true"></i>
                            </button>
                        </label>
                        <input id="inpCaptcha"
                               name="inpCaptcha"
                               type="text"
                               class="form-control"
                               placeholder="Ingrese el Código"
                               required
                               autocomplete="off">
                    </div>

                    <button class="btn btn-lg btn-primary btn-block" type="button" id="btnInicioSesionIngresar" name="btnInicioSesionIngresar">
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

        <script src="js/lib/jquery.dataTables.min.js"></script>
        <script src="js/lib/dataTables.bootstrap.js"></script>

        <script src="js/lib/bootbox.all.min.js"></script><script src="js/lib/bootstrap-submenu.js"></script><script src="js/jsgenerados/activar_submenu.js"></script>
        <script  src="js/jsgenerados/mensajes.js"></script>
        <script src="js/jsgenerados/validaciones.js"></script>
        <script  src="js/jsgenerados/loginVal_Index.js"></script>
    </body>
</html>
