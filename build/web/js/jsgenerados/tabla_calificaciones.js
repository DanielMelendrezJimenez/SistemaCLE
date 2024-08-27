var tabla;
var re = 0;
var data = {
    CalificacionId: -1
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
//            "search": "Buscar: ",
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

            data.CalificacionId = -1;
            $("#btnEditar").prop('disabled', true);
            $("#btnEliminar").prop('disabled', true);
            $("#btnDetalles").prop('disabled', true);

        } else {
            tabla.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            data.CalificacionId = $(this).attr('id');
            if (data.CalificacionId !== "" && data.CalificacionId !== null && data.CalificacionId !== undefined) {
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
    $('#btnEditar').click(function () {
        actualiza();
    });
//    $('#btnEditar').click(function () {
//        eliminar();
//    });
    $('#btnDetalles').click(function () {
        seleccionar();
    });
}
function seleccionar() {
    if (tabla.row('.selected').length !== 0) {
        let dataRow = tabla.row('.selected').data();
        bootbox.confirm({
            title: '<h4>' + '<i class="fa fa-info-circle iconoInfo"></i>' + ' ' + "Mensaje de confirmación" + '</h4>',
            message: "¿Esta seguro de querer enviar correo?" +
                    "<br><b>Alumno:</b> " + dataRow[0] +
                    "<br><b>Correo:</b> " + dataRow[2] +
                    "<br><b>Promedio:</b> " + dataRow[7] +
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
                    enviarCorreoSrv();
                }
            }
        });
    } else {
        mensajeError("Error", "Elija un registro");
    }
}
function agregar() {}
function guardar() {}
function actualiza() {
    if (checkSeleccion) {
        if (data.CalificacionId !== -1) {
            var parametros = 'CalificacionId=' + data.CalificacionId;
            var base64Parametros = encodeURIComponent(btoa(parametros));
            var url = '../../app/moduloCalificaciones/editarCalificacion.do?params=' + base64Parametros;


            window.open(url, "_self");
        } else {
            mensajeError("Error", "Elija un registro");
        }
    } else {
        mensajeError("Error", "Elija un registro");
    }
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

function enviarCorreoSrv() {
    var grupo = "${grupo.getCurso()}";
    let dataRow = tabla.row('.selected').data();

    $.ajax({
        type: "GET",
        async: false,
        url: "../../app/moduloCorreos/enviarCalificaciones.do",
        data: {CalificacionId: data.CalificacionId,
            Grupo: grupo,
            Alumno: dataRow[0],
            NumControl: dataRow[1],
            Correo: dataRow[2],
            Caluno: dataRow[3],
            Caldos: dataRow[4],
            Caltres: dataRow[5],
            Calcuatro: dataRow[6],
            Promedio: dataRow[7]
        },
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
                mensajeÉxito("Éxito", "Correo enviado");
            } else {
                mensajeError("Error", respuesta.mensaje);
            }
        }, error: function (jqXHR, exception) {
            var msg = '';
            if (jqXHR.status === 0) {
                msg = 'verifique su conexion';
            } else if (jqXHR.status === 405) {
                msg = 'Error: grupo con alumnos inscritos';
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





