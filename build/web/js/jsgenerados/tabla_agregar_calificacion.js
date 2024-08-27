var tabla;
var re = 0;
var data = {
    AlumnoId: -1
};
$(document).ready(function () {
    $("#pageLoader").show();
    initTabla();
    initBotones();
    $("#pageLoader").hide();
});

document.addEventListener('DOMContentLoaded', function() {
        // Función para validar la entrada en tiempo real
        function validarEntrada(event) {
            var input = event.target;
            var valor = input.value;

            // Permitir solo dígitos numéricos
            valor = valor.replace(/[^0-9]/g, '');

            // Convertir el valor a un número entero para eliminar ceros a la izquierda
            var numero = parseInt(valor, 10);

            // Limitar a 3 dígitos y a un máximo de 100
            if (isNaN(numero)) {
                numero = '';
            } else if (numero > 100) {
                numero = 100;
            }

            // Asignar el valor modificado al campo
            input.value = numero;
        }

        // Obtener todos los campos de calificación
        var camposCalificaciones = [
            document.getElementById('inpCaluno'),
            document.getElementById('inpCaldos'),
            document.getElementById('inpCaltres'),
            document.getElementById('inpCalcuatro')
        ];

        // Asignar la función de validación a cada campo de calificación
        camposCalificaciones.forEach(function(campo) {
            campo.addEventListener('input', validarEntrada);
        });
    });

function initTabla() {
    $("#btnEditar").prop('disabled', true);
    $("#btnEliminar").prop('disabled', true);
    $("#btnDetalles").prop('disabled', true);


    tabla = $('#tabla').DataTable({
        searching: true,
        responsive: true,
        ordering: true,
        order: [[1, 'asc']],
        language: {
            "lengthMenu": "Mostrar _MENU_ filas por página",
            "zeroRecords": "No se ha encontrado ningún registro.",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": " ",
            "search": "Buscar: ",
            "infoFiltered": "(filtered from _MAX_ total records)",
            "destroy": true,
            "paginate": {
                "previous": "Anterior",
                "next": "Siguiente"
            }
        }
    });


    $('#tabla tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            $("#inpNo_control").val(""); // Asigna el ID al campo de texto
            $("#inpAlumnoId").val(""); // Asigna el ID al campo de texto
            data.AlumnoId = -1;
            $("#btnEditar").prop('disabled', true);
            $("#btnEliminar").prop('disabled', true);
            $("#btnDetalles").prop('disabled', true);

        } else {
            tabla.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            var control = $(this).find('td').eq(3).text();
            var id_alumno = $(this).attr('id');
            $("#inpNo_control").val(control); // Asigna el ID al campo de texto
            $("#inpAlumnoId").val(id_alumno); // Asigna el ID al campo de texto

            data.AlumnoId = $(this).attr('id');
            if (data.AlumnoId !== "" && data.AlumnoId !== null && data.AlumnoId !== undefined) {
                $("#btnEditar").prop('disabled', false);
                $("#btnEliminar").prop('disabled', false);
                $("#btnDetalles").prop('disabled', false);

            }

        }
    });

    filtradoColumna(tabla, "tabla");
    crearTabla("tabla", agregar, seleccionar, eliminar, actualiza);
}
function initBotones() {
//    $('#btnEliminar').click(function () {
//        eliminar();
//    });
    $('#btnEditar').click(function () {
        actualiza();
    });
}


function validarCalificaciones() {
    var camposCalificaciones = [
        document.getElementById('inpCaluno'),
        document.getElementById('inpCaldos'),
        document.getElementById('inpCaltres'),
        document.getElementById('inpCalcuatro'),
    ];

    for (var i = 0; i < camposCalificaciones.length; i++) {
        var valor = camposCalificaciones[i].value;

        // Comprobar si el valor es un número y está en el rango adecuado
        if (isNaN(valor) || valor < 0 || valor > 100) {
            mensajeError("Error", "Por favor, ingrese una calificación válida entre 0 y 100 en la calificacion " + (i + 1) + ".");
            return false;
        }
    }
    return true;
}

function seleccionar() {
    if (checkSeleccion) {
        if (data.FacilitadorId !== -1) {
            var parametros = 'FacilitadorId=' + data.FacilitadorId;
            var base64Parametros = encodeURIComponent(btoa(parametros));
            var url = '../../app/inscripciones/completarInformacion.do?params=' + base64Parametros;
            window.open(url, "_self");
        } else {
            mensajeError("Error", "Elija un registro");
        }
    } else {
        mensajeError("Error", "Elija un registro");
    }
}
function agregar() {}
function guardar() {}
function actualiza() {
    if (checkSeleccion) {
        if (data.AlumnoId !== -1) {
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
                            let inpGrupoId = $("#inpGrupoId").val();
                            let inpAlumnoId = $("#inpAlumnoId").val();
                            let inpCaluno = $("#inpCaluno").val();
                            let inpCaldos = $("#inpCaldos").val();
                            let inpCaltres = $("#inpCaltres").val();
                            let inpCalcuatro = $("#inpCalcuatro").val();
                            var data = {
                                inpGrupoId,
                                inpAlumnoId,
                                inpCaluno,
                                inpCaldos,
                                inpCaltres,
                                inpCalcuatro
                            };

                            if (validarCalificaciones()) {
                                guardarCalificaciones(data);
                            }
                        }
                    }
                });
            } else {
                mensajeError("Revise los siguientes campos", result.mensaje);
            }
        } else {
            mensajeError("Error", "Elija un alumno");
        }
    } else {
        mensajeError("Error", "Elija un registro");
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
            case 'inpGrupoId':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inpCaluno':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inpCaldos':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inpCaltres':
                if (!($(this).val().trim() === "")) {
                    highlightError(field, "false");
                } else {
                    result.success = false;
                    result.mensaje += mensaje(id);
                    highlightError(field, "true");
                }
                break;
            case 'inpCalcuatro':
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

function eliminar() {

}


function checkSeleccion() {
    if (tabla.row('.selected').length !== 0) {
        return true;
    } else {

        return false;
    }
}

function cancela() {
    $('#eliminar').show();
    $('#canc').hide();
}

function eliminarSrv() {
}

function mensaje(id) {
    switch (id) {
        case 'inpGrupoId':
            return '<li>El campo grupo es obligatorio.</li>';
        case 'inpCaluno':
            return '<li>El campo Listening es obligatorio.</li>';
        case 'inpCaldos':
            return '<li>El campo Reading es obligatorio.</li>';
        case 'inpCaltres':
            return '<li>El campo Writing es obligatorio.</li>';
        case 'inpCalcuatro':
            return '<li>El campo Speaking es obligatorio.</li>';

    }

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

function guardarCalificaciones(data) {

    $.ajax({
        type: "POST",
        async: true,
        url: "../../app/calificaciones/guardarCalificaciones.do",
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
                            '<p>' + "Calificaciones guardadas exitosamente" + '</p>' +
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




