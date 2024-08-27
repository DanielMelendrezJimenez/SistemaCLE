function mensajeÉxito(title, mensaje) {
    bootbox.dialog({
        title: '<h4>' + '<i class="fa fa-check-circle iconoCorrecto"></i>' + ' ' + title + '</h4>',
        message: '<div class="row" style="text-align: justify">' +
                '<div class="col-md-12">' +
                '<h3 class="panel-title"></h3>' +
                '<p>'+ mensaje +'</p>'+
                '</div>' +
                '</div>',
        buttons: {
            main: {
                label: '<i class="fa fa-check-circle"></i>&nbsp; Aceptar',
                className: 'btn-agregar'
            }
        }
    });
}
function mensajeAdvertencia(title, mensaje) {
    bootbox.dialog({
        title: '<h4>' + '<i class="fa fa-error" style="color:#843534"></i>' + ' ' + title + '</h4>',
        message: '<div class="row" style="text-align: justify">' +
                '<div class="col-md-12">' +
               '<h3 class="panel-title"></h3>' +
                '<p>'+ mensaje +'</p>'+
                '</div>' +
                '</div>',
        buttons: {
            main: {
                label: 'Aceptar',
                className: 'btn-agregar'
            }
        }
    });
}
function mensajeError(title, mensaje) {
    bootbox.dialog({
        title: '<h4>' + '<i class="fa fa-times-circle iconoError"></i>' + ' ' + title + '</h4>',
        message: '<div class="row" style="text-align: justify">' +
                '<div class="col-md-12">' +
               '<h3 class="panel-title"></h3>' +
                '<p>'+ mensaje +'</p>'+
                '</div>' +
                '</div>',
        buttons: {
            main: {
                label: 'Aceptar',
                className: "btn-primary"
            }
        }
    });
}

function mensajeInformacion(title, mensaje) {
    bootbox.dialog({
        title: '<h4>' + '<i class="fa fa-info-circle iconoInfo"></i>' + ' ' + title + '</h4>',
        message: '<div class="row" style="text-align: justify">' +
                '<div class="col-md-12">' +
               '<h3 class="panel-title"></h3>' +
                '<p>'+ mensaje +'</p>'+
                '</div>' +
                '</div>',
        buttons: {
            main: {
                label: 'Aceptar',
                className: 'btn-agregar'
            }
        }
    });
}
