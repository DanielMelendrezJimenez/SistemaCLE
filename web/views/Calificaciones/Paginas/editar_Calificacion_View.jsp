<%-- 
    Document   : agregar_Calificacion_View
    Created on : Aug 7, 2024, 6:09:58 PM
    Author     : wltgm
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
            //HttpSession session2 = request.getSession(false);

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
                            Editar calificacion
                        </h3>
                    </div>
                    <div class=" panel-body">
                        <form  id="formEditCalificacion" 
                               name="formEditCalificacion" 
                               role="form"  
                               class="needs-validation ">

                            <input type="hidden" class="form-control"  name="inpCalificacionId" id="inpCalificacionId" value="<c:out value="${calificacion.getId()}"/>">
                            <div class="row">

                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="small">Listening*</label>
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-bookmark"></i></span>
                                            <input type="number" min="0" max="100" class="form-control dataForm input-sm data" placeholder="" value="<c:out value="${calificacion.getListening()}"/>" id="inpCaluno" name="inpCaluno" required="">                                    
                                        </div>
                                    </div>                                  
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="small">Reading*</label>
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-bookmark"></i></span>
                                            <input type="number" min="0" max="100" class="form-control dataForm input-sm data" placeholder="" value="<c:out value="${calificacion.getReading()}"/>" id="inpCaldos" name="inpCaldos" required="">                                    
                                        </div>
                                    </div>                                  
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="small">Writing*</label>
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-bookmark"></i></span>
                                            <input type="number" min="0" max="100" class="form-control dataForm input-sm data" placeholder="" value="<c:out value="${calificacion.getWriting()}"/>" id="inpCaltres" name="inpCaltres" required="">                                    
                                        </div>
                                    </div>                                  
                                </div> 
                            </div>
                            <div class="row">

                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="small">Speaking*</label>
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-bookmark"></i></span>
                                            <input type="number" min="0" max="100" class="form-control dataForm input-sm data" placeholder="" value="<c:out value="${calificacion.getSpeaking()}"/>" id="inpCalcuatro" name="inpCalcuatro" required="">                                    
                                        </div>
                                    </div>                                  
                                </div>
                            </div>
                        </form>
                        <div class="row">
                            <div class="col-md-12" align="right">
                                <!--                                <button class="btn btn-default  btn-sm" type="button" onclick="window.history.back();" >
                                                                    <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                                                    Regresar
                                                                </button>-->

                                <button id="btnEditar" name="btnEditar" class= "btn btn-primary btn-sm"><i class= "fa fa-plus-square"></i>&nbsp;Guardar</button>

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
        <script src="../../js/jsgenerados/editar_calificacion.js" ></script>           


    </body>
</html>

