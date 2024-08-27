<%-- 
    Document   : Error
    Created on : Oct 25, 2018, 3:12:27 PM
    Author     : SATELLITE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;">
        <link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet">
        <link href="/Generico-ITT/css/estiloErrores.css" rel="stylesheet">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Página en mantenimiento</title>
    </head>
    <body>
        <div class="container" align="center">
            <img src="/CDN-ITT/img/header.png" class="img-responsive" alt="Responsive image">  
        </div>    

        <div class="container">

            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">Instituto Tecnológico de Toluca</a>
                    </div>

                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">            
                        <ul class="nav navbar-nav navbar-right">                            
                        </ul>
                    </div> <!-- /.navbar-collapse --> 
                </div><!-- /.container-fluid -->
            </nav>

            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h1 class="panel-title">Página en mantenimiento</h1>
                </div>

                <div class="panel-body" id="panel-principal">

                    <div class="col-md-12" id="ocultarLinea">-</div>
                    <div class="col-md-6">
                        <span><img src="/Generico-ITT/img/LogoMantenimientoFinal.png" id="errorImagen"></span>
                    </div>

                    <div class="col-md-6">

                        <div class="tab-content">

                            <div class="col-md-10" >
                                <span id="errorPrincipal">¡Mantenimiento en progreso!</span>
                            </div>

                            <div class="col-md-12" >
                                <br>
                                <span id="errorPagina">Trabajamos para brindarle la mejor experiencia posible.</span>
                            </div>

                            <div class="col-md-12" >
                                <br><span id="errorContacto">Volveremos en breve.</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <footer class="login-footer">                
                <div align="center">                    
                    <br><br>
                    <label >
                        Instituto Tecnológico de Toluca | <a href="//www.ittoluca.edu.mx">www.ittoluca.edu.mx</a>
                        <br>
                        Instituto Tecnológico de Toluca - Algunos derechos reservados © 2013
                        <br>
                    </label>

                    <img   class="img-responsive " alt="Responsive image" src="/CDN-ITT/img/footer.png" title="footer">
                    <br>
                    <label>
                        Av. Tecnológico s/n. Fraccionamiento La Virgen
                        <br>
                        Metepec, Edo. De México, México C.P. 52149
                        <br>
                        Tel. (52) (722) 2 08 72 00
                    </label>
                </div>
            </footer>
        </div>
    </body>
</html>
