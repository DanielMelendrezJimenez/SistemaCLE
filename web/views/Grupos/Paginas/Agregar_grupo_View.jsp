<%-- 
    Document   : Agregar_parametro_View
    Created on : Jun 16, 2024, 3:25:43 AM
    Author     : Daniel Melendrez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

        <link href="../../css/cssgenerados/login.css" rel="stylesheet">
        <link href="../../css/cssgenerados/tables.css" rel="stylesheet">

        <link href="../../css/lib/jquery-ui.css" rel="stylesheet">
        <link href="../../css/lib/jquery-ui.theme.css" rel="stylesheet">
        <link href="../../css/lib/jquery-ui.structure.css" rel="stylesheet">

        <link href="../../css/cssgenerados/general.css" rel="stylesheet">
        <link href="../../css/cssgenerados/style.css" rel="stylesheet">
        <link href="../../css/cssgenerados/listaScroll.css" rel="stylesheet">

        <link href="../../css/lib/bootstrap.css" rel="stylesheet">
        <link href="../../css/lib/bootstrap-submenu.css" rel="stylesheet">

        <script language="Javascript">
            document.oncontextmenu = function () {
                return false;
            };
        </script>


        <title>Sistema CLE</title>
    </head>
    <body>
        <%
            //HttpSession session2 = request.getSession(false);

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
                            Agregar Grupo
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title">
                                    <div class="form-inline">
                                        <b>Cursos disponibles</b>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="table table-responsive" id="divTabla">
                                    <table id="tablaCursos" name="tablaCursos" class="table table-striped table-bordered tablesorter no-footer dtr-inline dataTable table_margen"  style="width: 100%;">
                                        <colgroup>
                                            <col span="1" style="width: 15%;">
                                            <col span="1" style="width: 15%;">
                                            <col span="1" style="width: 15%;">
                                            <col span="1" style="width: 15%;">
                                            <col span="1" style="width: 15%;">
                                            <col span="1" style="width: 15%;">
                                            <col span="1" style="width: 15%;">
                                        </colgroup>
                                        <thead>
                                            <tr class="info itt-catalogos-search-row">
                                                <th class="bordeTd"><input id="tabla-colSearch1"  class="form-control" type="text" placeholder="Buscar:" style="width: 100%" data-index="1"></th>
                                                <th class="bordeTd"><input id="tabla-colSearch2"  class="form-control" type="text" placeholder="Buscar:" style="width: 100%" data-index="2"></th>
                                                <th class="bordeTd"><input id="tabla-colSearch3"  class="form-control" type="text" placeholder="Buscar:" style="width: 100%" data-index="3"></th>
                                                <th class="bordeTd"><input id="tabla-colSearch4"  class="form-control" type="text" placeholder="Buscar:" style="width: 100%" data-index="4"></th>
                                                <th class="bordeTd"><input id="tabla-colSearch5"  class="form-control" type="text" placeholder="Buscar:" style="width: 100%" data-index="5"></th>
                                                <th class="bordeTd"><input id="tabla-colSearch6"  class="form-control" type="text" placeholder="Buscar:" style="width: 100%" data-index="6"></th>
                                                <th class="bordeTd"><input id="tabla-colSearch7"  class="form-control" type="text" placeholder="Buscar:" style="width: 100%" data-index="7"></th>

                                            </tr>
                                            <tr class="info">
                                                <th>Nombre</th>
                                                <th>Nivel</th>
                                                <th>Modalidad</th>
                                                <th>Hora inicio</th>
                                                <th>Hora fin</th>
                                                <th>Aula</th>
                                                <th>Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="elemento" items="${lstCursos}">
                                                <tr id="${elemento.getId()}">
                                                    <td>${elemento.getNombre()}</td>
                                                    <td>${elemento.getNivel()}</td>
                                                    <td>${elemento.getModalidad()}</td>
                                                    <td>${elemento.getHora_inicio()}</td>
                                                    <td>${elemento.getHora_fin()}</td>
                                                    <td>${elemento.getAula()}</td>
                                                    <td>${elemento.getEstatus()}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="panel-heading">
                                <div class="panel-title">
                                    <div class="form-inline">
                                        <b>Facilitadores disponibles</b>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="table table-responsive" id="divTabla">
                                    <table id="tablaFacilitadores" name="tablaFacilitadores" class="table table-striped table-bordered tablesorter no-footer dtr-inline dataTable table_margen"  style="width: 100%;">
                                        <colgroup>
                                            <col span="1" style="width: 15%;">
                                            <col span="1" style="width: 15%;">
                                            <col span="1" style="width: 15%;">
                                            <col span="1" style="width: 15%;">
                                            <col span="1" style="width: 15%;">
                                            <col span="1" style="width: 15%;">
                                        </colgroup>
                                        <thead>
                                            <tr class="info itt-catalogos-search-row">
                                                <th class="bordeTd"><input id="tabla-colSearch1"  class="form-control" type="text" placeholder="Buscar:" style="width: 100%" data-index="1"></th>
                                                <th class="bordeTd"><input id="tabla-colSearch2"  class="form-control" type="text" placeholder="Buscar:" style="width: 100%" data-index="2"></th>
                                                <th class="bordeTd"><input id="tabla-colSearch3"  class="form-control" type="text" placeholder="Buscar:" style="width: 100%" data-index="3"></th>
                                                <th class="bordeTd"><input id="tabla-colSearch4"  class="form-control" type="text" placeholder="Buscar:" style="width: 100%" data-index="4"></th>
                                                <th class="bordeTd"><input id="tabla-colSearch5"  class="form-control" type="text" placeholder="Buscar:" style="width: 100%" data-index="5"></th>
                                                <th class="bordeTd"><input id="tabla-colSearch6"  class="form-control" type="text" placeholder="Buscar:" style="width: 100%" data-index="6"></th>


                                            </tr>
                                            <tr class="info">
                                                <th>Nombre</th>
                                                <th>Primer Apellido</th>
                                                <th>Segundo Apellido</th>
                                                <th>No.Control</th>
                                                <th>Correo</th>
                                                <th>Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="elemento" items="${lstFacilitadores}">
                                                <tr id="${elemento.getId()}">
                                                    <td>${elemento.getNombre()}</td>
                                                    <td>${elemento.getAp_pat()}</td>
                                                    <td>${elemento.getAp_mat()}</td>
                                                    <td>${elemento.getNo_control()}</td>
                                                    <td>${elemento.getCorreo()}</td>
                                                    <td>${elemento.getEstatus()}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>                                    
                        <input type="hidden" class="form-control"  name="idTrimestre" id="idTrimestre" value="<c:out value="${idTrimestre}"/>">
                        <div class="row">
                            <div class="col-md-12" align="right">
                                <button type="button" class="btn btn-primary" id="btnEditar" ><i class= "fa fa-floppy-o"></i> Guardar</button>
                            </div>
                        </div>  
                    </div>
                    <div class="panel-footer">Nota: Los campos con * son obligatorios.</div>
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
        <script src="../../js/lib/jquery.dataTables.min.js"></script>
        <script src="../../js/lib/dataTables.bootstrap.js"></script>
        <script src="../../js/lib/bootbox.all.min.js"></script>
        <script src="../../js/lib/bootstrap-submenu.js"></script>

        <script src="../../js/jsgenerados/activar_submenu.js"></script>
        <script  src="../../js/jsgenerados/mensajes.js"></script>
        <script src="../../js/jsgenerados/validaciones.js"></script>

        <script src="../../js/lib/quill.min.js"></script>

        <script  src="../../js/jsgenerados/init_grupos.js"></script>
    </body>
</html>
