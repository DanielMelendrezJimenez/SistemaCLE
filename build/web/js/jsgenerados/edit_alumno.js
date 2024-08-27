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

    $("#inpTelefono").on("input", function () {
        let value = $(this).val();

        // Elimina caracteres no numéricos
        value = value.replace(/\D/g, '');

        // Limita la longitud a 10 caracteres
        if (value.length > 10) {
            value = value.slice(0, 10);
        }

        // Actualiza el campo con el valor modificado
        $(this).val(value);
    });

    // Función para validar y filtrar la entrada
    function filterInputToLettersOnly(event) {
        let value = $(this).val();
        // Reemplaza cualquier cosa que no sea una letra (incluidas letras acentuadas)
        value = value.replace(/[^a-zA-ZñÑáéíóúÁÉÍÓÚ\s]/g, '');
        // Actualiza el campo con el valor filtrado
        $(this).val(value);
    }

    // Aplicar la validación a los campos deseados
    $("#inpNombre, #inpAp_pat, #inpAp_mat").on("input", filterInputToLettersOnly);


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
                        let inpAlumnoId = $("#inpAlumnoId").val();
                        let inpNombre = $("#inpNombre").val();
                        let inpAp_pat = $("#inpAp_pat").val();
                        let inpAp_mat = $("#inpAp_mat").val();
                        let inpGenero = $("#inpGenero").val();
                        let inpNo_control = $("#inpNo_control").val();
                        let inpCorreo = $("#inpCorreo").val();
                        let inpTipoAlumno = $("#inpTipoAlumno").val();
                        let inpTelefono = $("#inpTelefono").val();
                        let inpCarrera = $("#inpCarrera").val();
                        var data = {
                            inpAlumnoId,
                            inpNombre,
                            inpAp_pat,
                            inpAp_mat,
                            inpGenero,
                            inpNo_control,
                            inpCorreo,
                            inpTipoAlumno,
                            inpTelefono,
                            inpCarrera
                        };
                        if (inpTelefono.length !== 10) {
                            mensajeError("ERROR", "El telefono debe tener 10 caracteres");
                        } else {
                            if (validarcorreo(inpCorreo)) {
                                editarAlumno(data);
                            } else {
                                mensajeError("ERROR", "Correo no válido");
                            }
                        }
                    }
                }
            });
        } else {
            mensajeError("Revise los siguientes campos", result.mensaje);
        }
    }
    );
});

function validarcorreo(correo) {
    // Expresión regular para validar el formato general del correo electrónico
    var emailPattern = /^[^\s@]+@[^\s@]+\.[a-zA-Z]{2,6}$/;

    // Verificar el formato general del correo electrónico
    if (!emailPattern.test(correo)) {
        return false;
    }

    // Permitir específicamente el formato @toluca.tecnm.mx
    if (correo.endsWith('@toluca.tecnm.mx')) {
        return true;
    }

    // Verificar que no haya espacios, puntos consecutivos o más de un punto después del dominio
    if (/\s/.test(correo) || /\.{2,}/.test(correo) || correo.match(/@.*\..*\./)) {
        return false;
    }

    return true;
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
            case 'inpNombre':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inpAp_pat':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inpAp_mat':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inpGenero':
                if (!($(this).val().trim() === "-1")) {
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
            case 'inpCorreo':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inpTipoAlumno':
                if (!($(this).val() === null)) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inpTelefono':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inpCarrera':
                if (!($(this).val() === null)) {
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
        case 'inpNombre':
            return '<li>El campo nombre es obligatorio.</li>';
        case 'inpAp_Pat':
            return '<li>El campo primer appelido es obligatorio.</li>';
        case 'inpAp_Mat':
            return '<li>El segundo apellido es obligatorio.</li>';
        case 'inpGenero':
            return '<li>El campo género es obligatorio.</li>';
        case 'inpNo_Control':
            return '<li>El campo numero de control es obligatorio.</li>';
        case 'inpCorreo':
            return '<li>El campo correo es obligatorio.</li>';
        case 'inpTipoAlumno':
            return '<li>El campo tipo de alumno es obligatorio.</li>';
        case 'inpTelefono':
            return '<li>El campo telefono es obligatorio.</li>';
        case 'inpCarrera':
            return '<li>El campo carrera es obligatorio.</li>';
    }

}

function formDatostForm(formData) {
    $("#formEditarAlumno")[0].formDatost();
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

function editarAlumno(data) {

    $.ajax({
        type: "POST",
        async: true,
        url: "../../app/alumnos/modificarAlumno.do",
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
                            '<p>' + "Alumno editado" + '</p>' +
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
                mensajeError("Error", "Hubo un problema");
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
