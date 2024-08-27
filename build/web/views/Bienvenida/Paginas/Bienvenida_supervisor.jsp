<%-- 
    Document   : Bienvenida_Supervisor
    Created on : Jun 16, 2024, 3:37:20 AM
    Author     : Daniel Melendrez
--%>

<%@page import="managedbean.usuarios.Usuarios_MB"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sistema CLE ITTol</title>
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/general.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/jquery-ui.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/botones.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/catalogos-tablas.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/catalogos-modal.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/bootstrap-datepicker.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/spinner.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/tablas.estandarITT.css" rel="stylesheet">

        <link href="../../css/cssgenerados/login.css" rel="stylesheet">
        <link href="../../css/cssgenerados/tables.css" rel="stylesheet">
        <link href="../../css/lib/jquery-ui.css" rel="stylesheet">
        <link href="../../css/lib/jquery-ui.theme.css" rel="stylesheet">
        <link href="../../css/lib/jquery-ui.structure.css" rel="stylesheet">
        <link href="../../css/cssgenerados/general.css" rel="stylesheet">
        <link href="../../css/cssgenerados/generadoPosgrado.css" rel="stylesheet">
        <link href="../../css/cssgenerados/style.css" rel="stylesheet">

        <link href="../../css/cssgenerados/listaScroll.css" rel="stylesheet">
        <link href="../../css/lib/bootstrap.css" rel="stylesheet">
        <link href="../../css/lib/bootstrap-submenu.css" rel="stylesheet">
        <script language="Javascript">
            document.oncontextmenu = function () {
                return false;
            };
        </script>
    </head>
    <body style="overflow: auto" class="cuerpo"> 
        <%
            Usuarios_MB usuario = (Usuarios_MB) session.getAttribute("usuario");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Expires", "0");
            response.setDateHeader("Expires", -1);
        %>
        <%@include file="../../templates/header.jsp" %>
        <%@include file="../../templates/navbar/navbar_general.jsp" %>

        <div id="pageLoader">
            <div id="pageSpinner">
                <%@include file="/views/templates/spinner.estandarITT.jsp"%>
            </div>
        </div>
        <div class="container">
            <div id="contenido">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Información General
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="thumbnail centerfy">

                                    <object
                                        id="im1aAlu"
                                        data="../../img/profile.png"
                                        type="image/png"
                                        height="185">
                                        <img
                                            src="../../img/profile.png"
                                            class="img-responsive" />
                                    </object>

                                    <h4 class="text-center">
                                        <strong>
                                            <c:out value=""/>
                                        </strong>
                                    </h4>
                                </div>
                                <div class="col-md-12 text-center" style=" border-radius: 50px 50px;
                                     background: #d5d5d5;
                                     ">

                                    <b>Correo electrónico: </b>
                                    <c:out value="<%= usuario.getCorreo() %>"></c:out>
                                    </div>
                                </div>
                                <div class="col-md-6"><br>
                                    <ul>
                                        <li>
                                            <p><strong>Rol de usuario: </strong><c:out value="<%= usuario.getTipo_usuario() %>"></c:out></p>
                                        </li>
                                        <li>
                                            <p><strong>Nombre: </strong><c:out value="<%= usuario.getNombre() + " " +usuario.getAp_pat() + " " + usuario.getAp_mat() %>" default="Coordinación de la alberca"/></p>
                                    </li>
                                    <li>
                                        <p><strong>Puesto: </strong><c:out value="" default="Administrador"/></p>
                                    </li>
                                </ul>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div role="tabpanel">
                            <ul class="nav nav-tabs" role="tablist" id="opcionesTabs">
                                <li role="presentation" class="active">
                                    <a href="#bienvenido" aria-controls="bienvenido" role="tab" data-toggle="tab">
                                        Bienvenido
                                    </a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane fade in active" id="bienvenido">
                                    <br/>
                                    <div class="jumbotron centerfy">
                                        <h1 align="center" style="border-radius:14px;background-color:#FF8D57">Bienvenido</h1>
                                        <img class="img-responsive" alt="Responsive image" src="../../img/IttHalcones.png" title="welcome-logo" width="350" height="350">
                                    </div>
                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="carga">
                                    <div id="cargaDiv"></div>
                                    <br>
                                    <br>
                                    <center>
                                        <i class="fa fa-spinner fa-spin fa-5x hidden" id="spinnerCarga"></i>
                                    </center>
                                    <br>
                                    <br>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <%@include file="../../templates/footer.jsp"%>
        <script src="/CDN-ITT/js/jquery.estandarITT.js" ></script>
        <script src="/CDN-ITT/js/jquery-ui.estandarITT.js" ></script>
        <script src="/CDN-ITT/js/jquery.dataTables.estandarITT.js" ></script>
        <script src="/CDN-ITT/js/base.estandarITT.js" ></script>
        <script src="/CDN-ITT/js/tablas.estandarITT.js" ></script>
        <script src="/CDN-ITT/js/jquery.dataTables.estandarITT.js" ></script>
        <script src="/CDN-ITT/js/bootstrap-datepicker.estandarITT.js" ></script>
        <script src ="/CDN-ITT/js/catalogos-modal.estandarITT.js" ></script>
        <script src ="/CDN-ITT/js/catalogos-tablas.estandarITT.js" ></script>
        <script src="/CDN-ITT/js/spinner.estandarITT.js" ></script>
        <script src ="/CDN-ITT/js/bootstrap-datepicker.estandarITT.js" ></script>
        <script src ="/CDN-ITT/js/bootstrap-datepicker.es.estandarITT.js" ></script>

        <script src="../../js/lib/timeOut.estandarITT.js"></script>
        <script src="../../js/lib/bootstrap-submenu.js"></script>
        <script src="../../js/jsgenerados/activar_submenu.js"></script>
        <script src="../../js/jsgenerados/portal.js"></script>
        <script src="../../js/jsgenerados/init_admin.js"></script>
    </body>
</html>

