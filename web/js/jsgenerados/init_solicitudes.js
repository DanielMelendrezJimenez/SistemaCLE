/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    var bandera = false;
    $('[data-toggle="popover"]').popover();
    $("#pageLoader").hide();
    $(".datepicker").datepicker({
        language: "es",
        inline: true,
        todayHighlight: true,
        daysOfWeekDisabled: '[0]',
        daysOfWeekHighlighted: "[0,6]",
        altFormat: "dd/mm/yyyy",
        clearBtn: true,
        weekStart: '1',
        startDate: new Date()
                //maxDate: "+1M +10D"
    });
    $("#btnGuardar").click(function (event) {
        event.preventDefault();
        var result = validarFormDatos($('.data'));
        if (result.success) {
            bootbox.confirm({
                title: ' <i class="fa fa-info-circle fa-lg iconoInfo"></i> Aviso',
                message: '¿Está seguro/a de que desea guardar los cambios?',
                buttons: {
                    cancel: {
                        label: '<i class="fa fa-times"></i> No, cancelar'
                    },
                    confirm: {
                        label: '<i class="fa fa-check"></i> Sí, guardar'
                    }
                },
                centerVertical: true,
                callback: function (result) {
                    if (result === true) {
                        let inpConcepto = $("#inpConcepto").val();
                        let inpDescripcion = $("#inpDescripcion").val();
                        let inpNo_control = $("#inpNo_control").val();
                        let inpCampo = $("#inpCampo").val();
                        let inpValor = $("#inpValor").val();
                        var data = {
                            inpConcepto,
                            inpDescripcion,
                            inpNo_control,
                            inpCampo,
                            inpValor,
                        };
                        guardarSolicitud(data);
                    }
                }
            });
        } else {
            mensajeError("Revise los siguientes campos", result.mensaje);
        }
    }
    );
});
function validarFormDatos(formData) {

    var result = {
        success: true,
        mensaje: ''
    };
    //result.mensaje = 'Revise los siguientes campos esten de ingresados correctamente:<br>';
    result.mensaje += '<ul>';
    $.each(formData, function (index, field) {
        var value = $(field).val();
        var id = $(field).attr('id');
        switch (id) {
            case 'inpConcepto':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inpDescripcion':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inpNo_control':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;

            case 'inpCampo':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inpValor':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inpOrigen':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
        }
    });
    result.mensaje += '</ul>';
    return result;
}

function recargar() {
    $("#spin_carga").hide();
    $("#pageLoader").hide();
}

function highlightError(field, tipo) {
    if (tipo === "true") {
        $(field).parent('div').removeClass('has-success');
        $(field).parent('div').removeClass('has-warning');
        $(field).parent('div').addClass('has-error');
    } else if (tipo === "false") {
        $(field).parent('div').removeClass('has-error');
        $(field).parent('div').removeClass('has-warning');
        $(field).parent('div').addClass('has-success');
    } else if (tipo === "advertencia") {
        $(field).parent('div').removeClass('has-success');
        $(field).parent('div').removeClass('has-error');
        $(field).parent('div').addClass('has-warning');
    } else if (tipo === "defecto") {
        $(field).parent('div').removeClass('has-warning');
        $(field).parent('div').removeClass('has-success');
        $(field).parent('div').removeClass('has-error');
    }
}

function mensaje(id) {
    switch (id) {
        case 'inpConcepto':
            return '<li>El campo nombre es obligatorio.</li>';
        case 'inpDescripcion':
            return '<li>El campo primer apellido es obligatorio.</li>';
        case 'inpNo_control':
            return '<li>El campo segundo apellido es obligatorio.</li>';
        case 'inpCampo':
            return '<li>El campo CURP es obligatorio.</li>';
        case 'inpValor':
            return '<li>El campo numero de control es obligatorio.</li>';
        case 'inpOrigen':
            return '<li>El campo contrasena es obligatorio.</li>';
    }

}

function formDatostForm(formData) {
    $("#formAddFacilitador")[0].formDatost();
    $.each(formData, function (index, field) {
        var value = $(field).val();
        var id = $(field).attr('id');
        highlightError(field, "defecto");
    });
}

function parseDate(dateString) {
// Separa el día, mes y año de la cadena ingresada
    var parts = dateString.split('/');
    var day = parseInt(parts[0], 10);
    var month = parseInt(parts[1], 10) - 1; // El mes comienza en 0 (enero es 0)
    var year = parseInt(parts[2], 10);
    // Crea un objeto Date con el día, mes y año
    return new Date(year, month, day);
}

function guardarSolicitud(data) {

    $.ajax({
        type: "POST",
        async: true,
        url: "../../app/solicitudes/guardarSolicitud.do",
        dataType: "json",
        data: data,
        beforeSend: function () {
            $("#pageLoader").show();
        },
        success: function (response) {
            if (response.status === 0)
            {
                bootbox.confirm({
                    title: '<h4>' + '<i class="fa fa-check-circle iconoCorrecto"></i>' + ' ' + "Éxito" + '</h4>',
                    message: '<div class="row" style="text-align: justify">' +
                            '<div class="col-md-12">' +
                            '<h3 class="panel-title"></h3>' +
                            '<p>' + "Solicitud guardada exitosamente" + '</p>' +
                            '</div>' +
                            '</div>',
                    buttons: {
                        confirm: {
                            label: 'Aceptar',
                            className: 'btn-agregar'
                        }
                    },
                    callback: function (result) {
                        location.reload();
                    }
                });
            } else if (response.status === -200) {
                mensajeError("Error", response.mensaje);
            } else if (response.status === -601) {
                mensajeError("Error", "No encontramos el numero de control");
            } else {
                mensajeError("Error", "Algo salió mal :/");
            }
        }, complete: function () {
            $('#pageLoader').hide();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (errorThrown !== "") {
                mensajeError('Ocurrió un error', 'Ocurrió un error al realizar la petición:<br/>' +
                        'Estatus: ' + '<strong>' + textStatus + ' </strong><br/>' +
                        'Error: ' + '<strong>' + errorThrown + '</strong><br/>' +
                        'Por favor, notifique a su administrador de este error<br/>.')
            } else {
                mensajeAdvertencia('Aviso', 'Por favor verifique que su conexión a la Red sea correcta y vuelva a intentarlo.')

            }
            $('#pageLoader').hide();
        }
    });
}
