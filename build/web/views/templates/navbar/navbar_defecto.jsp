<%-- 
    Document   : navbar
    Created on : Jan 21, 2016, 4:38:08 PM
    Author     : Luis
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">

            <div class="navbar-header">
                <button type="button"
                        class="navbar-toggle collapsed"
                        data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="fa fa-bars" />
                </button>
                <strong><a class="navbar-brand" href="../../app/portalAlberca/bienvenida.do">Sistema de la Alberca ITToluca</a></strong>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-bell"></i>
                            <span class="badge" >0</span>
                            <b class="caret"></b>
                        </a>
                        <!-- Sirve para colocar el submenu al icono de alerta-->
                    </li>
                    <li>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-gear"></i> 
                            Opciones
                            <b class="caret"></b>
                        </a>

                        <ul class="dropdown-menu dropdown-user">
                            <li>
                                <a href="../../app/portalAlberca/bienvenida.do" id="menu_inicio">
                                    <i class="fa fa-home" aria-hidden="true"></i>
                                    Inicio
                                </a>
                            </li>
                            <li>
                                <a href="../../app/moduloReglamento/verReglamento.do" target="_self">
                                    <i class="fa fa-book" aria-hidden="true"></i>
                                    Reglamento de la alberca
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="../../app/sesion/logOut">
                                    <i class="fa fa-sign-out"></i>
                                    Salir
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>