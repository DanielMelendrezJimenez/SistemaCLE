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
                            Agregar Parámetro
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
                                       class="needs-validation ">
                                    <%--
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label class="small">Concepto*</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-bookmark"></i></span>
                                                    <input type="text" maxlength="30" class="form-control dataForm input-sm" placeholder="" value="" id="inConcepto" name="inConcepto" required="">                                    
                                                </div>
                                            </div>                                  
                                        </div>
                                    </div>
                                    --%>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label class="small">Concepto*</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <span class="fa fa-users"></span>
                                                    </span>
                                                    <select id="inConcepto" 
                                                            name="inConcepto"
                                                            class="form-control input-sm data"
                                                            required>
                                                        <option disabled hidden selected value="-1">Seleccione</option>
                                                        <option value="TRIMESTRE"  >TRIMESTRE</option>
                                                        <option value="PREINSCRIPCION"  >PREINSCRIPCION</option>
                                                        <option value="INSCRIPCION"  >INSCRIPCION</option>
                                                        <option value="CLASES"  >CLASES</option>
                                                        <option value="EVALUACIONES"  >EVALUACIONES</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="small">Fecha de inicio del periodo</label>
                                                <div class="input-group">

                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                    <input type="text"  id="inFechaInicioPeriodo" name="inFechaInicioPeriodo"
                                                           class="form-control input-sm data datepicker" 
                                                           placeholder="dd/mm/yyyy"
                                                           value="<fmt:formatDate pattern = "dd/MM/yyyy"  value = "${parametros.getdFechaInicioPeriodo()}" />" 
                                                           required pattern="^[0-9]{2,2}/[0-9]{2,2}/[0-9]{4,4}$" title="El formato no coincide con el solicitado (DD/MM/YYYY)"
                                                           autocomplete="off">
                                                </div>
                                            </div>                                  
                                        </div> 
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="small">Fecha de fin del periodo</label>
                                                <div class="input-group">

                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                    <input type="text"  id="inFechaFinPeriodo" name="inFechaFinPeriodo"
                                                           class="form-control input-sm data datepicker" 
                                                           placeholder="dd/mm/yyyy"
                                                           value="<fmt:formatDate pattern = "dd/MM/yyyy"  value = "${parametros.getdFechaInicioPeriodo()}" />" 
                                                           required pattern="^[0-9]{2,2}/[0-9]{2,2}/[0-9]{4,4}$" title="El formato no coincide con el solicitado (DD/MM/YYYY)"
                                                           autocomplete="off">
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

        <script  src="../../js/jsgenerados/init_parametros.js"></script>
    </body>
</html>
