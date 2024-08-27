




<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sistema Alberca ITTol</title>
        <meta content="Sistema para control de asistencia para la alberca del ITTOl " name="description">
        <meta content="alberca, tecnm, horarios, asistencia, estudiantes, inscripciones" name="keywords">



        <link rel="manifest" href="manifest.json">
        <meta name="theme-color" content="#1B396A" />
        <meta name="theme-color" media="(prefers-color-scheme: light)" content="#1B396A">
        <meta name="theme-color" media="(prefers-color-scheme: dark)" content="#1B396A">
        <link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/general.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/botones.estandarITT.css" rel="stylesheet">
        <link rel="icon" href="/CDN-ITT/img/logo.png">

        <link rel="apple-touch-startup-image" href="img/iconos/apple_touch_640.png"
              media="(device-width: 320px) and (device-height: 568px) and (-webkit-device-pixel-ratio: 2) and (orientation: portrait)">
        <link rel="apple-touch-startup-image" href="img/iconos/apple_touch_750.png"
              media="(device-width: 375px) and (device-height: 667px) and (-webkit-device-pixel-ratio: 2) and (orientation: portrait)">
        <link rel="apple-touch-startup-image" href="img/iconos/apple_touch_1242.png"
              media="(device-width: 414px) and (device-height: 736px) and (-webkit-device-pixel-ratio: 3) and (orientation: portrait)">
        <link rel="apple-touch-startup-image" href="img/iconos/apple_touch_1125.png"
              media="(device-width: 375px) and (device-height: 812px) and (-webkit-device-pixel-ratio: 3) and (orientation: portrait)">
        <link rel="apple-touch-startup-image" href="img/iconos/apple_touch_1536.png"
              media="(min-device-width: 768px) and (max-device-width: 1024px) and (-webkit-min-device-pixel-ratio: 2) and (orientation: portrait)">
        <link rel="apple-touch-startup-image" href="img/iconos/apple_touch_1668.png"
              media="(min-device-width: 834px) and (max-device-width: 834px) and (-webkit-min-device-pixel-ratio: 2) and (orientation: portrait)">
        <link rel="apple-touch-startup-image" href="img/iconos/apple_touch_2048.png"
              media="(min-device-width: 1024px) and (max-device-width: 1024px) and (-webkit-min-device-pixel-ratio: 2) and (orientation: portrait)">


    </head>
    <body>
        <%@include file="../../templates/header.jsp"%>
        <div class="container">


            <div class="panel panel-default">
                <div class="panel-body">

                    <div class="jumbotron" style='text-align: justify;'>
                        <center>
                            <h2>Su sesión ha expirado, por favor vuelva a iniciar sesión</h2>
                            <p>Si persisten los problemas comuniquese con su administrador</p>
                            <br/>

                            <p><a class="btn btn-primary btn-lg" href="/alberca" role="button">Iniciar Sesión</a></p>


                        </center>
                    </div>
                </div>
            </div>

        </div>
        <%@include file="../../templates/footer.jsp"%>
        <script type="text/javascript" src ="\CDN-ITT\js\jquery.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\jquery-ui.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\base.estandarITT.js"></script>
    </body>
</html>