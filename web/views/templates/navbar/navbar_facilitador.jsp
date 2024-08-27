<%-- 
    Document   : navbar_instructor
    Created on : 29/03/2023, 06:44:10 AM
    Author     : Kevin Ivan Sanchez Vadin
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
                <strong><a class="navbar-brand" href="../../app/bienvenida/bienvenida.do">Sistema de la Coordinación de Lenguas Extranjeras ITToluca</a></strong>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#" class="nav-link  dropdown-toggle" data-toggle="dropdown" data-submenu="" aria-expanded="false">
                            <i class="fa fa-gear"></i> 
                            Opciones
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-right">

                            <li class="dropdown-item">
                                <a href="../../app/bienvenida/bienvenida.do" id="menu_inicio">
                                    <i class="fa fa-home" aria-hidden="true"></i>
                                    Inicio
                                </a>
                            </li>
                            
                            <li class="divider"></li>
                            <li class="dropdown-header">Calificaciones</li>
                            <li class="dropdown-item">
                                <a href="../../app/moduloCalificaciones/mostrarGruposFacilitador.do" id="menu_aviso" >
                                    <i class="fa fa-pie-chart" aria-hidden="true"></i>
                                    Ver grupos
                                </a>
                            </li>
                            <li class="dropdown-item">
                                <a href="../../app/moduloAlumnos/registrarSolicitud.do" id="menu_aviso" >
                                    <i class="fa fa-pie-chart" aria-hidden="true"></i>
                                    Solicitar Editar
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li class="dropdown-item">
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