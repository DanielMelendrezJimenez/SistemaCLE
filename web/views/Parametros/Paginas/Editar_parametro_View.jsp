<%-- 
    Document   : RegistrarOperador
    Created on : 17/03/2023, 12:53:39 PM
    Author     : Kevin Ivan Sanchez Vadin
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sistema Alberca ITTol</title>
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
                            Editar Parámetro
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title">
                                    <div class="form-inline">
                                        <b>Datos</b>
                                    </div>
                                </div>
                            </div>
                            <div class=" panel-body">
                                <form  id="formAddParametro" 
                                       name="formAddParametro" 
                                       role="form"  
                                       class="needs-validation "  enctype="multipart/form-data">
                                    <input type="hidden" class="form-control"  name="inpParametroId" id="inpParametroId" value="<c:out value="${parametros.getId()}"/>">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label class="small">Concepto*</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <span class="fa fa-users"></span>
                                                    </span>
                                                    <select id="inConcepto" name="inConcepto" class="form-control input-sm data" required>
                                                        <option disabled hidden selected value="-1">Seleccione</option>
                                                        <option value="TRIMESTRE" <c:out value="${parametros.getConcepto()=='TRIMESTRE'?'selected':''}" /> >TRIMESTRE</option>
                                                        <option value="PREINSCRIPCION" <c:out value="${parametros.getConcepto()=='PREINSCRIPCION'?'selected':''}" />  >PREINSCRIPCION</option>
                                                        <option value="INSCRIPCION" <c:out value="${parametros.getConcepto()=='INSCRIPCION'?'selected':''}" /> >INSCRIPCION</option>
                                                        <option value="CLASES" <c:out value="${parametros.getConcepto()=='CLASES'?'selected':''}" /> >CLASES</option>
                                                        <option value="EVALUACIONES" <c:out value="${parametros.getConcepto()=='EVALUACIONES'?'selected':''}" /> >EVALUACIONES</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="small">Fecha de inicio del periodo</label>
                                                <div class="input-group">

                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                    <input type="text"  id="inFechaInicioPeriodo" name="inFechaInicioPeriodo"
                                                           class="form-control input-sm data datepicker" 
                                                           placeholder="dd/mm/yyyy"
                                                           value="<fmt:formatDate pattern = "dd/MM/yyyy"  value = "${parametros.getFecha_inicio()}" />" 
                                                           required pattern="^[0-9]{2,2}/[0-9]{2,2}/[0-9]{4,4}$" title="El formato no coincide con el solicitado (DD/MM/YYYY)"
                                                           autocomplete="off">
                                                </div>
                                            </div>                                  
                                        </div> 
                                    </div>
                                    <div class="row">
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="small">Fecha de fin del periodo</label>
                                                <div class="input-group">

                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                    <input type="text"  id="inFechaFinPeriodo" name="inFechaFinPeriodo"
                                                           class="form-control input-sm data datepicker" 
                                                           placeholder="dd/mm/yyyy"
                                                           value="<fmt:formatDate pattern = "dd/MM/yyyy"  value = "${parametros.getFecha_fin()}" />" 
                                                           required pattern="^[0-9]{2,2}/[0-9]{2,2}/[0-9]{4,4}$" title="El formato no coincide con el solicitado (DD/MM/YYYY)"
                                                           autocomplete="off">
                                                </div>
                                            </div>                                  
                                        </div> 
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label class="small">Status*</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <span class="fa fa-users"></span>
                                                    </span>
                                                    <select id="inStatus" name="inStatus" class="form-control input-sm data" required>
                                                        <option disabled hidden selected value="-1">Seleccione</option>
                                                        <option value="ACTIVO" <c:out value="${parametros.getEstatus()=='ACTIVO'?'selected':''}" /> >ACTIVO</option>
                                                        <option value="INACTIVO" <c:out value="${parametros.getEstatus()=='INACTIVO'?'selected':''}" /> >INACTIVO</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12" align="right">
                                <button type="button" class="btn btn-primary" id="btnGuardar" ><i class= "fa fa-floppy-o"></i> Guardar</button>
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

        <script  src="../../js/jsgenerados/edit_parametro.js"></script>
    </body>
</html>

<!--
<script src="../../js/lib/bootbox.all.min.js"></script><script src="../../js/lib/bootstrap-submenu.js"></script><script src="../../js/jsgenerados/activar_submenu.js"></script>
        <script  src="../../js/jsgenerados/mensajes.js"></script>
        <script src="../../js/jsgenerados/data.js"></script>
        <script src="../../js/jsgenerados/validaciones.js"></script>
        <script src="../../js/jsgenerados/edit_operador.js"></script>
        <script src="../../js/jsgenerados/script_perfil_edicion.js"></script>
-->