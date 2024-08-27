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

    document.getElementById('inHora_ini').addEventListener('change', function () {
        ajustarTiempo(this);
        validarHoras();
    });

    document.getElementById('inHora_fin').addEventListener('change', function () {
        ajustarTiempo(this);
        validarHoras();
    });

    $("#inNivel").on("input", function () {
        let value = $(this).val();

        // Reemplaza todo lo que no sea un dígito
        value = value.replace(/[^0-9]/g, '');

        // Convierte el valor a un número entero
        let numericValue = parseInt(value, 10);

        // Asegúrate de que el valor esté en el rango de 1 a 9
        if (isNaN(numericValue)) {
            $(this).val('');
        } else if (numericValue > 10) {
            $(this).val(10);
        } else if (numericValue < 1) {
            $(this).val(1);
        } else {
            $(this).val(numericValue);
        }
    });

    $("#inNombre").on("input", function () {
        let value = $(this).val();

        // Reemplaza cualquier cosa que no sea una letra (incluidas letras acentuadas)
        value = value.replace(/[^a-zA-ZñÑáéíóúÁÉÍÓÚ\s]/g, '');

        // Actualiza el campo con el valor filtrado
        $(this).val(value);
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
                        let inNombre = $("#inNombre").val();
                        let inNivel = $("#inNivel").val();
                        let inModalidad = $("#inModalidad").val();
                        let inHoraIni = $("#inHora_ini").val();
                        let inHoraFin = $("#inHora_fin").val();
                        let inAula = $("#inAula").val();
                        let inStatus = $("#inStatus").val();
                        console.log(inHoraIni)
                        var data = {
                            inNombre,
                            inNivel,
                            inModalidad,
                            inHoraIni,
                            inHoraFin,
                            inAula,
                            inStatus
                        };
                        consultarCursos(data);
                    }
                }
            });
        } else {
            mensajeError("Revise los siguientes campos", result.mensaje);
        }
    }
    );
});

function ajustarTiempo(element) {
    let time = element.value;
    let [hours, minutes] = time.split(':');
    minutes = "00"; // Ajusta los minutos a 00
    element.value = `${hours}:${minutes}`;
}

function validarHoras() {
    let horaInicio = document.getElementById('inHora_ini').value;
    let horaFin = document.getElementById('inHora_fin').value;

    if (horaInicio === horaFin) {
        mensajeError("Error", "La hora de inicio y fin no pueden ser iguales.");
//        alert("La hora de inicio y fin no pueden ser iguales.");
        document.getElementById('inHora_fin').value = ""; // Limpia el campo de hora fin
        document.getElementById('inHora_fin').focus(); // Enfoca el campo de hora fin
    }
}

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
            case 'inNombre':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inNivel':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inModalidad':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inHora_ini':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inHora_fin':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inAula':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inStatus':
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
    if (!($("#inHora_ini").val().trim() === "") && !($("#inHora_fin").val().trim() === "")) {
        let hora1 = $("#inHora_ini").val();
        let hora2 = $("#inHora_fin").val();
        if (hora1 > hora2) {
            result.success = false;
            result.mensaje += "<li>La hora de inicio del curso no puede ser mayor a la hora de fin del curso.</li>";
            $("#inHora_ini").parent().addClass('has-error');
            $("#inHora_ini").parent().removeClass('has-success');
            $("#inHora_fin").parent().addClass('has-error');
            $("#inHora_fin").parent().removeClass('has-success');
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
        case 'inNombre':
            return '<li>El campo nombre es obligatorio.</li>';
        case 'inNivel':
            return '<li>El campo nivel es obligatorio.</li>';
        case 'inModalidad':
            return '<li>El campo modalidad es obligatorio.</li>';
        case 'inHora_ini':
            return '<li>El campo hora de inicio es obligatorio.</li>';
        case 'inHora_fin':
            return '<li>El campo hora de fin es obligatorio.</li>';
        case 'inAula':
            return '<li>El campo aula es obligatorio.</li>';
        case 'inModalidad':
            return '<li>El campo modalidad es obligatorio.</li>';
        case 'inStatus':
            return '<li>El campo status es obligatorio.</li>';
    }

}

function formDatostForm(formData) {
    $("#formAddCurso")[0].formDatost();
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

function guardarCursos(data) {

    $.ajax({
        type: "POST",
        async: true,
        url: "../../app/cursos/guardarCursos.do",
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
                            '<p>' + "Curso guardado exitosamente" + '</p>' +
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
                mensajeError('Ocurrió un error', 'Ocurrió un erroooooooooooooor al realizar la petición:<br/>' +
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

function consultarCursos(data) {

    $.ajax({
        type: "GET",
        async: true,
        url: "../../app/cursos/verificarCurso.do",
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
                guardarCursos(data);
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