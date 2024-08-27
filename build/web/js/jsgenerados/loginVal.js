/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    $("#spin_carga").hide();
    $("#pageLoader").hide();
});

$(document).ready(function () {

    $('#inpUsuario').keypress(function (event) {
        // Comprobamos si la tecla presionada es "Enter"
        if (event.which === 13) {
            validarDatosSesion();
        }
    });
    $('#inpPass').keypress(function (event) {
        // Comprobamos si la tecla presionada es "Enter"
        if (event.which === 13) {
            validarDatosSesion();
        }
    });
    $('#inpCaptcha').keypress(function (event) {
        // Comprobamos si la tecla presionada es "Enter"
        if (event.which === 13) {
            validarDatosSesion();
        }
    });

    $("#btnCaptcha").click(function (event) {
        recargar();
    });
    $("#btnInicioSesionIngresar").click(function (event) {


        validarDatosSesion();
    });

});

function validarDatosSesion() {
    $("#pageLoader").show();
    var code = $("#inpCaptcha").val();

    if (code.toString().trim() === "") {
        $("#pageLoader").hide();
        mensajeError("Error en la captura del captcha", "El código es obligatorio");
    } else {

        $.ajax({
            type: "POST",
            url: "../../app/login/validarCaptcha.do",
            data: "inpCaptcha=" + code,
            dataType: 'json',
            success: function (data) {
                $("#pageLoader").hide();
                if (data === "no")
                {
                    mensajeError("Error en la captura del captcha", "Código Incorrecto, Ingresalo Nuevamente!!");
                } else if (data === "si") {
                    $("#pageLoader").show();
                    let inpUsuario = $("#inpUsuario").val();
                    let inpPass = $("#inpPass").val();
                    let inpCaptcha = $("#inpCaptcha").val();

                    if (inpUsuario.toString().trim() === "") {
                        $("#pageLoader").hide();
                        mensajeError("Error", "El campo requerido de Usuario es obligatorio.");
                        return;
                    }
                    if (inpPass.toString().trim() === "") {
                        $("#pageLoader").hide();
                        mensajeError("Error", "El campo requerido de la Contraseña es obligatorio.");
                        return;
                    }
                    if (inpUsuario.toString().trim() !== "" && inpPass.toString().trim() !== "") {
                        $.ajax({
                            type: "POST",
                            url: "../../app/login/iniciarSesion.do",
                            data: $("#form-registro").serialize(),

                            success: function (response) {

                                if (response.status === 0) {

                                    $("#form-registro").submit();
                                } else if (response.status === -403) {
                                    $("#pageLoader").hide();
                                    mensajeInformacion("Error", "Ocurrio un error: <br/> " +
                                            "<b>Estatus: </b>" + '403' + "<br/>" +
                                            "<b>Error: </b>" + 'Usuario NO autorizado' + "<br/>" +
                                            "Si hay algun problema, o el problema persiste consulte a su administrador. <br/>");
                                    recargar();
                                } else {
                                    $("#pageLoader").hide();
                                    mensajeInformacion("Error en el inicio de sesión", response.mensaje);
                                    recargar();
                                }
                            },
                            error: function (XMLHttpRequest, textStatus, errorThorwn) {
                                //  event.preventDefault();
                                $("#pageLoader").hide();
                                mensajeInformacion("Aviso", "Ocurrio un error: <br/> " +
                                        "<b>Estatus:</b> " + textStatus + "<br/>" +
                                        "<b>Error: </b> " + errorThorwn + "<br/>" +
                                        "Por favor, notifique a su administrador de este error. <br/>");
                                recargar();
                            }
                        });
                    } else {
                        $("#pageLoader").hide();
                        mensajeError("Error", "Hay campos vacios");
                    }
                } else {
                    recargar();
                    mensajeError("Error en la captura del captcha", data);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThorwn) {
                $("#pageLoader").hide();
                recargar();
                mensajeError("Error en la captura del captcha", "Ocurrio un error: <br/> " +
                        "<b>Estatus:</b> " + textStatus + "<br/>" +
                        "<b>Error: </b>" + errorThorwn + "<br/>" +
                        "Por favor, notifique a su administrador de este error. <br/>");

            }
        });
    }

}

function recargar() {
    $('#inpCaptcha').val('');
    $("#img_captcha").attr("src", "../../app/login/captcha.png?" + new Date().getTime());
    $("#spin_carga").hide();
    $("#btnInicioSesionIngresar").prop("disabled", false);
}
