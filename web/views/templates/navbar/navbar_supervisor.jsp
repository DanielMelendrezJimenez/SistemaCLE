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
                            <li class="dropdown-header">Básicos</li>
                            <li class="dropdown dropleft dropdown-submenu">
                                <a class="dropdown-item dropdown-toggle" href="#">
                                    <i class="fa fa-calendar-check-o" aria-hidden="true" ></i>
                                    Grupos
                                </a>
                                <ul class="dropdown-menu" role="menu">
                                    <li class="dropdown-item">
                                        <a href="../../app/moduloGrupos/agregarGrupo.do" class="dropdown-item li-menu menu-nav" id="menu_agregar_grupo" >
                                            <i class="fa fa-calendar-plus-o" aria-hidden="true"></i>&nbsp; Agregar grupo
                                        </a>
                                    </li>
                                    <li class="dropdown-item">
                                        <a href="../../app/grupos/consultarGrupos_View.do"  class="li-menu menu-nav" id="menu_ver_grupos">
                                            <i class="fa fa-calendar-o" aria-hidden="true"></i>&nbsp; Consultar grupos
                                        </a>
                                    </li>
                                </ul>
                            </li>

                            <li class="divider"></li>
                            <li class="dropdown-item">
                                <a href="../../app/moduloInscripciones/mostrarPreregistros.do" id="menu_aviso" >
                                    <i class="fa fa-pie-chart" aria-hidden="true"></i>
                                    Preinscritos
                                </a>
                            </li>

                            <li class="divider"></li>
                            <li class="dropdown dropleft dropdown-submenu">
                                <a class="dropdown-item dropdown-toggle" href="#">
                                    <i class="fa fa-calendar-check-o" aria-hidden="true" ></i>
                                    Alumnos
                                </a>
                                <ul class="dropdown-menu" role="menu">
                                    <li class="dropdown-item">
                                        <a href="../../app/moduloPreRegistro/PreRegistro_View.do" class="dropdown-item li-menu menu-nav" id="menu_agregar_grupo" >
                                            <i class="fa fa-calendar-plus-o" aria-hidden="true"></i>&nbsp; Solicitar atemporal
                                        </a>
                                    </li>
                                    <li class="dropdown-item">
                                        <a href="../../app/grupos/consultarGrupos_View.do"  class="li-menu menu-nav" id="menu_ver_grupos">
                                            <i class="fa fa-calendar-o" aria-hidden="true"></i>&nbsp; Ver inscritos
                                        </a>
                                    </li>
                                </ul>
                            </li>

                            <li class="divider"></li>
                            <li class="dropdown-header">Solicitudes</li>
                            <li class="dropdown-item">
                                <a href="../../app/moduloAlumnos/registrarSolicitud.do" id="menu_aviso" >
                                    <i class="fa fa-pie-chart" aria-hidden="true"></i>
                                    Realizar solicitud
                                </a>
                                <a href="../../app/moduloSolicitudes/verPendientes.do" id="menu_aviso" >
                                    <i class="fa fa-pie-chart" aria-hidden="true"></i>
                                    Consultar solicitudes
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li class="dropdown-item">
                                <a href="../../app/moduloFacilitadores/verFacilitadores.do"  class="li-menu menu-nav" id="menu_ver_operadores" >
                                    <i class="fa fa-list" aria-hidden="true"></i>&nbsp;  Ver facilitadores
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