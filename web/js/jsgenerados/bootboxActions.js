/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global bootbox */

//Función que genera un alert personalizado con bootbox.
//Recibe como parámetros el título y cuerpo del mensaje.

function bootBoxAlert(icono, titulo, mensaje) {
    bootbox.dialog({
        title: icono + titulo,
        message: '<div class="row" style="text-align: justify">' +
                '<div class=col-md-12>' +
                '<h3 class=panel-title>' + mensaje + '</h3>' +
                '</div>' +
                '</div>',
        buttons: {
            main: {
                label: '<i class="fa fa-check-circle"></i> Aceptar',
                className: "btn-primary"
            }
        }
    });
}

//Función que crea un diálogo de confirmación personalizado en bootbox.
//Sus parámetros son: el título del diálogo, el mensaje de confirmación y una función a ejecutar cuando el usuario oprima Aceptar o Cancelar.
//En su declaración, esta función recibe un booleano: el resultado de Aceptar o Cancelar.
function bootBoxConfirm(icono, titulo, mensaje, callbackFunc) {

    bootbox.dialog({
        title: icono + titulo,
        message: '<div class="row" style="text-align: justify">' +
                '<div class=col-md-1>' +
                '</div>' +
                '<div class=col-md-10>' +
                '<h3 class=panel-title>' + mensaje + '</h3>' +
                '</div>' +
                '<div class=col-md-1>' +
                '</div>' +
                '</div>',
        onEscape: function () {
            callbackFunc(false);
        },
        buttons: {
            //Botón cancelar del diálogo.
            danger: {
                label: '<i class="fa fa-minus-circle"></i> Cancelar',
                class: "btn-default",
                callback: function () {
                    callbackFunc(false);
                }
            },
            //Botón aceptar del diálogo.
            main: {
                label: '<i class="fa fa-check-circle"></i> Aceptar',
                className: "btn-primary",
                callback: function () {
                    callbackFunc(true);
                }

            }
        }
    });
}

//Función que crea un diálogo de confirmación personalizado en bootbox .
//Sus parámetros son: el título del diálogo, el mensaje de confirmación y una función a ejecutar cuando el usuario oprima Aceptar.
//En su declaración, esta función recibe un booleano: el resultado de Aceptar.
function bootBoxOnlyConfirm(icono, titulo, mensaje, callbackFunc) {

    bootbox.dialog({
        title: icono + titulo,
        message: '<div class="row" style="text-align: justify">' +
                '<div class=col-md-1>' +
                '</div>' +
                '<div class=col-md-10>' +
                '<h3 class=panel-title>' + mensaje + '</h3>' +
                '</div>' +
                '<div class=col-md-1>' +
                '</div>' +
                '</div>',
        onEscape: function () {
            callbackFunc(true);
        },
        buttons: {
            //Botón aceptar del diálogo.
            main: {
                label: '<i class="fa fa-check-circle"></i> Aceptar',
                className: "btn-primary",
                callback: function () {
                    callbackFunc(true);
                }

            }
        }
    });
}

function bootBoxAlertHorarios(icono, titulo, mensaje) {
    bootbox.dialog({
        title: icono + titulo,
        message: '<div class="row" style="text-align: justify">' +
                '<div class=col-md-12>' +
                mensaje +
                '</div>' +
                '</div>',
        buttons: {
            main: {
                label: '<i class="fa fa-check-circle"></i> Aceptar',
                className: "btn-primary"
            }
        }
    });
}
