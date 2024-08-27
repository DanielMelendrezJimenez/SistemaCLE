var tablaCursos;
var tablaFacilitadores;
var re = 0;
var data = {
    CursoId: -1,
    FacilitadorId: -1
};
$(document).ready(function () {
    $("#pageLoader").show();
    initTabla();
    initBotones();
    $("#pageLoader").hide();
});

function initTabla() {
    $("#btnGuardar").prop('disabled', true);
    $("#btnEditar").prop('disabled', true);
    $("#btnDetalles").prop('disabled', true);


    tablaCursos = $('#tablaCursos').DataTable({
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

    tablaFacilitadores = $('#tablaFacilitadores').DataTable({
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



    $('#tablaCursos tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');

            data.CursoId = -1;
            $("#btnGuardar").prop('disabled', true);
            $("#btnEditar").prop('disabled', true);
            $("#btnDetalles").prop('disabled', true);

        } else {
            tablaCursos.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            data.CursoId = $(this).attr('id');
            if (data.CursoId !== -1 && data.CursoId !== null && data.CursoId !== undefined) {
                $("#btnGuardar").prop('disabled', false);
                $("#btnEditar").prop('disabled', false);
                $("#btnDetalles").prop('disabled', false);
                //aqui va el metodo de limpiar la otra tabla
                listaFacilitadores();
            }

        }
    });

    $('#tablaFacilitadores tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');

            data.FacilitadorId = -1;
            $("#btnGuardar").prop('disabled', true);
            $("#btnEditar").prop('disabled', true);
            $("#btnDetalles").prop('disabled', true);

        } else {
            tablaFacilitadores.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            data.FacilitadorId = $(this).attr('id');
            if (data.FacilitadorId !== "" && data.FacilitadorId !== null && data.FacilitadorId !== undefined && data.CursoId !== -1 && data.CursoId !== null && data.CursoId !== undefined) {
                $("#btnGuardar").prop('disabled', false);
                $("#btnEditar").prop('disabled', false);
                $("#btnDetalles").prop('disabled', false);
            }

        }
    });

    filtradoColumna(tablaCursos, "tablaCursos");
    crearTabla("tablaCursos", agregar, seleccionar, eliminar, actualiza);

    filtradoColumna(tablaFacilitadores, "tablaFacilitadores");
    crearTabla("tablaFacilitadores", agregar, seleccionar, eliminar, actualiza);
}

function initBotones() {
    $('#btnEditar').click(function () {
        agregar();
    });
    $('#btnEliminar').click(function () {
        eliminar();
    });
    $('#btnDetalles').click(function () {
        seleccionar();
    });
}

function checkSeleccion() {
    if (tablaCursos.row('.selected').length !== 0) {
        return true;
    } else {

        return false;
    }

    if (tablaFacilitadores.row('.selected').length !== 0) {
        return true;
    } else {

        return false;
    }
}

function cancela() {
    $('#eliminar').show();
    $('#canc').hide();
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

function agregar() {

    $.ajax({
        type: "POST",
        async: true,
        url: "../../app/grupos/guardarGrupo.do",
        dataType: "json",
        data: {FacilitadorId: data.FacilitadorId, CursoId: data.CursoId, TrimestreId: $("#idTrimestre").val()},
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
                            '<p>' + "Grupo guardado exitosamente" + '</p>' +
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
function seleccionar() {}
function eliminar() {}
function actualiza() {}


function listaFacilitadores() {
    $.ajax({
        type: "GET",
        async: true,
        url: "../../app/grupos/consultarFacilitadores.do",
        dataType: "json",
        data: {CursoId: data.CursoId},
        beforeSend: function () {
            $("#pageLoader").show();
        },
        success: function (response) {
            if (response.status === 0) {
                const facilitadores = response.responseObject;
                console.log(facilitadores);
                // Verificar que facilitadores sea un array
                if (Array.isArray(facilitadores)) {
                    $("#tablaFacilitadores tbody").empty();

                    facilitadores.forEach(function (facilitador) {
                        const fila = `<tr id=${facilitador.id}>
                                
                        <td>${facilitador.nombre}</td>
                                                    <td>${facilitador.ap_pat}</td>
                                                    <td>${facilitador.ap_mat}</td>
                                                    <td>${facilitador.no_control}</td>
                                                    <td>${facilitador.correo}</td>
                                                    <td>${facilitador.estatus}</td>
                              </tr>`;
                        $("#tablaFacilitadores tbody").append(fila);
                    });
                } else {
                    mensajeError("Error", "No se encontraron facilitadores.");
                }
            } else if (response.status === -200) {
                mensajeError("Error", response.mensaje);
            } else if (response.status === -601) {
                mensajeError("Error", "La fecha ya existe");
            } else {
                mensajeError("Error", "Algo salió mal :/");
            }
        },

        complete: function () {
            $('#pageLoader').hide();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (errorThrown !== "") {
                mensajeError('Ocurrió un error', 'Ocurrió un erroooooooooooooor al realizar la petición:<br/>' +
                        'Estatus: ' + '<strong>' + textStatus + ' </strong><br/>' +
                        'Error: ' + '<strong>' + errorThrown + '</strong><br/>' +
                        'Por favor, notifique a su administrador de este error<br/>.')
            } else {
                mensajeAdvertencia('Aviso', 'Por favor verifique que su conexión a la Red sea correcta y vuelva a intentarlo.');
            }
            $('#pageLoader').hide();
        }
    });
}





