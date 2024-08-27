var tabla;
var re = 0;
var data = {
    ParametroId: -1
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
            
            data.ParametroId = -1;
            $("#btnEditar").prop('disabled', true);
            $("#btnEliminar").prop('disabled', true);
            $("#btnDetalles").prop('disabled', true);
        } else {
            tabla.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            data.ParametroId = $(this).attr('id');
            if (data.ParametroId !== "" && data.ParametroId !== null && data.ParametroId !== undefined) {
                $("#btnEditar").prop('disabled', false);
                $("#btnEliminar").prop('disabled', false);
                $("#btnDetalles").prop('disabled', false);
            }

        }
    });

    filtradoColumna(tabla, "tabla");
    crearTabla("tabla", agregar, guardar, eliminar, actualiza);
}
function initBotones() {
    $('#btnDetalles').click(function () {
        verDetalle();
    });
    $('#btnEditar').click(function () {
        actualiza();
    });
    $('#btnEliminar').click(function () {
        eliminar();
    });
}

function agregar() {}
function guardar() {}
function actualiza() {
    if (checkSeleccion) {
        if (data.ParametroId !== -1) {
            var parametros = 'ParametroId=' + data.ParametroId;
            var base64Parametros = encodeURIComponent(btoa(parametros));
            var url = '../../app/parametros/editarParametro_VW.do?params=' + base64Parametros;
            

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
            message: "¿Esta seguro de querer eliminar el siguiente operador? <br>" +
                    "<b>Nombre del operador:</b> " + dataRow[1] + " " + dataRow[2] + " " + dataRow[3] +
                    "<br>" +
                    "Al eliminar el operador, se eliminará toda la información asociada. ",
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
function verDetalle() {
    if (checkSeleccion) {
        if (data.ParametroId !== -1) {
            infoInstructor(data.iEmpleadoId);
        } else {
            mensajeError("Error", "Elija un registro");
        }

    } else {
        mensajeError("Error", "Elija un registro");
    }


}


function infoInstructor(dato) {
    $.ajax({
        type: "POST",
        async: false,
        url: "../../app/operadores/infoOperador.do",
        data: {
            iEmpleadoId: dato
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
                $('#mdNombreCompleto').val(respuesta.responseObject.vNombres + " " + respuesta.responseObject.vApellidoPaterno + " " + respuesta.responseObject.vApellidoMaterno);
                $('#mdCURP').val(respuesta.responseObject.vCURP !== null ? respuesta.responseObject.vCURP : 'Sin información');
                $('#mdFechaNac').val(respuesta.responseObject.vFechaNacimiento !== null ? convertirFechaDiagonal(respuesta.responseObject.vFechaNacimiento) : 'Sin información');
                $('#mdSexo').val(respuesta.responseObject.vSexo === 'F' ? 'Femenino' : 'Masculino');
                $('#mdTel').val(respuesta.responseObject.nTelefonoOpc1 === null ? respuesta.responseObject.nTelefonoOpc2 : respuesta.responseObject.nTelefonoOpc1);
                $('#mdCorreoElectronico').val(respuesta.responseObject.vCorreoInstitucional !== null ? respuesta.responseObject.vCorreoInstitucional : 'Sin información');

                $('#mdFolio').val(respuesta.responseObject.vFolio !== null ? respuesta.responseObject.vFolio : 'Sin información');
                $('#mdPuesto').val(respuesta.responseObject.vPuesto !== null ? respuesta.responseObject.vPuesto : 'Sin información');
                $('#mdInicioContrato').val(respuesta.responseObject.dInicioContrato !== null ? convertirFechaDiagonal(respuesta.responseObject.dInicioContrato) : 'Sin información');
                $('#mdFinContrato').val(respuesta.responseObject.dFinContrato !== null ? convertirFechaDiagonal(respuesta.responseObject.dFinContrato) : 'Sin información');
                $('#mdDiasActividad').val(respuesta.responseObject.vDiasLaborales !== null ? respuesta.responseObject.vDiasLaborales : 'Sin información');
                $('#mdHorario').val(respuesta.responseObject.dHorario !== null ? respuesta.responseObject.dHorario : 'Sin información');


                $('#mdEstado').val(respuesta.responseObject.vEstadoFed !== null ? respuesta.responseObject.vEstadoFed : 'Sin información');
                $('#mdMunicipio').val(respuesta.responseObject.vMunicipio !== null ? respuesta.responseObject.vMunicipio : 'Sin información');
                $('#mdCP').val(respuesta.responseObject.iCodPostal !== null ? respuesta.responseObject.iCodPostal : 'Sin información');
                $('#mdColonia').val(respuesta.responseObject.vColonia !== null ? respuesta.responseObject.vColonia : 'Sin información');
                $('#mdCalle').val(respuesta.responseObject.vCalle !== null ? respuesta.responseObject.vCalle : 'Sin información');
                $('#mdNoExt').val(respuesta.responseObject.iNoExt !== null ? respuesta.responseObject.iNoExt : 'Sin información');


                if (respuesta.responseObject.iEmpleadoId !== null && respuesta.responseObject.iEmpleadoId !== "") {
                    $("#objImgOperador").attr("data", "../../app/foto/empleado.do?idUser=" + respuesta.responseObject.iEmpleadoId);
                } else {
                    $("#objImgOperador").attr("data", "../../img/profile.png");
                }
                $('#modalDetalleOperador').modal('show');
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
$('#canc').on('click', function () {
    cancela();
});
function cancela() {
    $('#eliminar').show();
    $('#canc').hide();
}

function eliminarSrv() {
    $.ajax({
        type: "POST",
        async: false,
        url: "../../app/operadores/eliminarOperador.do",
        data: {iOperadorId: data.iEmpleadoId},
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
                mensajeÉxito("Éxito", "Registro eliminado");
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





