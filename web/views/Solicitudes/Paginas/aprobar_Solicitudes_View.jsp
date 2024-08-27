<%-- 
    Document   : aprobar_Solicitudes_View
    Created on : Aug 7, 2024, 1:08:44 AM
    Author     : Daniel Melendrez
--%>

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
//            HttpSession session2 = request.getSession(false);

            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Expires", "0");
            response.setDateHeader("Expires", -1);

        %>
        <%@include file="../../templates/header.jsp" %>
        <%@include file="../../templates/navbar/navbar_general.jsp" %>
        <%--
        <div id="pageLoader">
            <div id="pageSpinner">
                <%@include file="/views/templates/spinner.estandarITT.jsp"%>
            </div>
        </div>
        --%>
        <div class="container">
            <div id="contenido">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Solicitudes pendientes
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div class="table table-responsive" id="divTabla">
                            <table id="tabla" name="tabla" class="table table-striped table-bordered tablesorter no-footer dtr-inline dataTable table_margen"  style="width: 100%;">
                                <colgroup>
                                    <col span="1" style="width: 10%;">
                                    <col span="1" style="width: 10%;">
                                    <col span="1" style="width: 10%;">
                                    <col span="1" style="width: 10%;">
                                    <col span="1" style="width: 10%;">
                                    <col span="1" style="width: 10%;">
                                    <col span="1" style="width: 10%;">
                                    <col span="1" style="width: 10%;">
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
                                        <th class="bordeTd"><input id="tabla-colSearch8"  class="form-control" type="text" placeholder="Buscar:" style="width: 100%" data-index="8"></th>


                                    </tr>
                                    <tr class="info">
                                        <th>Concepto</th>
                                        <th>Descripción</th>
                                        <th>Alumno</th>
                                        <th>No. Control</th>
                                        <th>Campo a Editar</th>
                                        <th>Valor nuevo</th>
                                        <th>Origen</th>
                                        <th>Estatus</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="elemento" items="${SolicitudesList}">
                                        <tr id="${elemento.getId()}">
                                            <td>${elemento.getConcepto()}</td>
                                            <td>${elemento.getDescripcion()}</td>
                                            <td>${elemento.getAlumno()}</td>
                                            <td>${elemento.getNo_control()}</td>
                                            <td>${elemento.getCampo_editar()}</td>
                                            <td>${elemento.getValor_nuevo()}</td>
                                            <td>${elemento.getOrigen()}</td>
                                            <td>${elemento.getEstatus()}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="row">
                            <div class="col-md-12" align="right">
                                <button class="btn btn-default  btn-sm" type="button" onclick="window.history.back();" >
                                    <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                    Regresar
                                </button>
     
                                <button id="btnEditar" name="btnEditar" class= "btn btn-editar btn-sm"><i class= "fa fa-pencil"></i>&nbsp;Aprobar</button>

                                <!--<button id="btnEliminar" name="btnEliminar" class= "btn btn-borrar btn-sm"><i class= "fa fa-trash-o"></i>&nbsp;Eliminar</button>-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--<%@include file="../../Operadores/Modal/InfoOperador_Modal.jsp"%>--%>
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
        <script src="../../js/lib/bootbox.all.min.js"></script><script src="../../js/lib/bootstrap-submenu.js"></script><script src="../../js/jsgenerados/activar_submenu.js"></script>
        <script src="../../js/jsgenerados/validaciones.js" ></script>           
        <script  src="../../js/jsgenerados/mensajes.js"></script>
        <script src="../../js/jsgenerados/tabla_solicitudes.js" ></script>           


    </body>
</html>
