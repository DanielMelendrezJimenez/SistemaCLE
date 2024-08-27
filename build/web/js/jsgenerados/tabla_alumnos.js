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

            data.AlumnoId = -1;
            $("#btnEditar").prop('disabled', true);
            $("#btnEliminar").prop('disabled', true);
            $("#btnDetalles").prop('disabled', true);

        } else {
            tabla.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
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
    $('#btnEliminar').click(function () {
        eliminar();
    });
    $('#btnEditar').click(function () {
        actualiza();
    });
}

function seleccionar() {
    console.log("Seleccionar");
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
            var parametros = 'AlumnoId=' + data.AlumnoId;
            var base64Parametros = encodeURIComponent(btoa(parametros));
            var url = '../../app/moduloAlumnos/editarAlumno.do?params=' + base64Parametros;


            window.open(url, "_self");
        } else {
            mensajeError("Error", "Elija un registro");
        }
    } else {
        mensajeError("Error", "Elija un registro");
    }
}

function eliminar() {
    if (tabla.row('.selected').length !== 0) {
        let dataRow = tabla.row('.selected').data();
        bootbox.confirm({
            title: '<h4>' + '<i class="fa fa-info-circle iconoInfo"></i>' + ' ' + "Mensaje de confirmación" + '</h4>',
            message: "¿Esta seguro de querer pre registrar el siguiente alumno?" +
                    "<br><b>Nombre:</b> " + dataRow[0] + " " + dataRow[1] + " " + dataRow[2] +
                    "<br><b>No.Control:</b> " + dataRow[3] +
                    "<br>",
            buttons: {
                confirm: {
                    label: 'Sí',
                    className: 'btn-agregar'
                },
                cancel: {
                    label: 'No',
                    className: 'btn-borrar'
                }
            },
            callback: function (result) {
                if (result === true) {
                    eliminarSrv();
                }
            }
        });
    } else {
        mensajeError("Error", "Elija un registro");
    }
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
    $.ajax({
        type: "POST",
        async: false,
        url: "../../app/alumnos/eliminarAlumno.do",
        data: {AlumnoId: data.AlumnoId},
        dataType: 'json',

        beforeSend: function () {
            $('#pageLoader').show();
        },
        complete: function () {
            $('#pageLoader').hide();
        },
        success: function (respuesta) {
            if (respuesta.status === 0) {
                tabla.row('.selected').remove().draw();
                mensajeÉxito("Éxito", "Registro editado");
            } else {
                mensajeError("Error", respuesta.mensaje);
            }
        }, error: function (jqXHR, exception) {
            var msg = '';
            if (jqXHR.status === 0) {
                msg = 'verifique su conexion';
            } else if (jqXHR.status === 405) {
                msg = 'Error de petición';
            } else if (jqXHR.status === 404) {
                msg = 'no se pudo encontrar la página';
            } else if (jqXHR.status === 500) {
                msg = 'ocurrio un error en el servidor';
            } else if (exception === 'parseerror') {
                msg = 'error en el formato de JSON';
            } else if (exception === 'abort') {
                msg = 'peticion abortada';
            } else if (exception === 'timeout') {
                msg = 'sin tiempo de espera';
            } else {
                msg = 'Error ' + jqXHR.responseText;
            }
            mensajeError("Error", msg);
        }
    });
}





