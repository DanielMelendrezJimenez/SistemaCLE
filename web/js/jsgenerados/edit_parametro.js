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

                        let inId = $("#inpParametroId").val();
                        let inConcepto = $("#inConcepto").val();
                        let inFechaInicioPeriodo = $("#inFechaInicioPeriodo").val();
                        let inFechaFinPeriodo = $("#inFechaFinPeriodo").val();
                        let inStatus = $("#inStatus").val();
                        var data = {
                            inId,
                            inConcepto,
                            inFechaInicioPeriodo,
                            inFechaFinPeriodo,
                            inStatus
                        };
                        editarParametro(data);
                    }
                }
            });
        } else {
            mensajeError("Revise los siguientes campos", result.mensaje);
        }
    });



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
            case 'inConcepto':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");

                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inFechaInicioPeriodo':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");

                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inFechaFinPeriodo':
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

    if (!($("#inFechaInicioPeriodo").val().trim() === "") && !($("#inFechaFinPeriodo").val().trim() === "")) {
        const fechaActual = new Date();
        let fecha1 = $("#inFechaInicioPeriodo").val();
        let fecha2 = $("#inFechaFinPeriodo").val();

        const fechaInicio = new Date(fecha1.split('/').reverse().join('-'));
        const fechaLimite = new Date(fecha2.split('/').reverse().join('-'));

        if (fechaInicio > fechaLimite) {
            result.success = false;
            result.mensaje += "<li>La fecha de inicio de periodo no puede ser mayor a la fecha de fin de periodo.</li>";
            $("#inFechaInicioPeriodo").parent().addClass('has-error');
            $("#inFechaInicioPeriodo").parent().removeClass('has-success');
            $("#inFechaFinPeriodo").parent().addClass('has-error');
            $("#inFechaFinPeriodo").parent().removeClass('has-success');
        }
    }

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
        case 'inConcepto':
            return '<li>El campo del concepto es obligatorio.</li>';
        case 'inFechaInicioPeriodo':
            return '<li>El campo de fecha de inicio es obligatorio.</li>';
        case 'inFechaFinPeriodo':
            return '<li>El campo de fecha de fin es obligatorio.</li>';
    }

}

function formDatostForm(formData) {
    $("#formAviso")[0].formDatost();

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

function editarParametro(data) {

    $.ajax({
        type: "POST",
        async: true,
        url: "../../app/parametros/editarParametro.do",
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
                            '<p>' + "Ajustes y configuraciones actualizados exitosamente" + '</p>' +
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
                mensajeError("Error", "La fecha ya existe");
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

function updateInfo(formData) {

    $.ajax({
        type: "POST",
        async: true,
        url: "../../app/parametros/editarParametro.do",
        data: formData,
        dataType: 'json',
        cache: false,
        contentType: false,
        processData: false,
        beforeSend: function () {
            $('#pageLoader').show();
        },
        complete: function () {
            $('#pageLoader').hide();
        },
        success: function (data) {
            if (data.status === 0) {
                bootbox.dialog({
                    title: '<h4>' + '<i class="fa fa-check-circle iconoCorrecto"></i>' + ' ' + "Éxito" + '</h4>',
                    message: '<div class="row" style="text-align: justify">' +
                            '<div class="col-md-12">' +
                            '<h3 class="panel-title"></h3>' +
                            '<p>' + "El parámetro fue editado exitosamente." + '</p>' +
                            '</div>' +
                            '</div>',
                    buttons: {
                        main: {
                            label: '<i class="fa fa-check-circle"></i> Aceptar',
                            className: "btn-primary", //  className: 'btn-agregar'
                            callback: function () {
                                window.history.back();
                            }

                        }
                    }
                });
            } else {
                mensajeError(' Aviso', data.mensaje)
            }
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

function consultarParametros(data) {

    $.ajax({
        type: "GET",
        async: true,
        url: "../../app/parametros/verificarParametro.do",
        dataType: "json",
        data: data,
        beforeSend: function () {
            $("#pageLoader").show();
        },
        success: function (response) {
            if (response.status === 0)
            {
                mensajeError("Error", response.mensaje);
            } else if (response.status === -200) {
                let inId = $("#inpParametroId").val();
                let inConcepto = $("#inConcepto").val();
                let inFechaInicioPeriodo = $("#inFechaInicioPeriodo").val();
                let inFechaFinPeriodo = $("#inFechaFinPeriodo").val();
                let inStatus = $("#inStatus").val();
                const formData = new FormData();

                formData.append('inId', inId);
                formData.append('inConcepto', inConcepto);
                formData.append('inFechaInicioPeriodo', inFechaInicioPeriodo);
                formData.append('inFechaFinPeriodo', inFechaFinPeriodo);
                formData.append('inStatus', inStatus);
                
                
                updateInfo(formData);

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