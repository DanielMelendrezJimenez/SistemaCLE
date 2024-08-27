var tabla;
var re = 0;
var data = {
    AluGrupoId: -1
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

            data.AluGrupoId = -1;
            $("#btnEditar").prop('disabled', true);
            $("#btnEliminar").prop('disabled', true);
            $("#btnDetalles").prop('disabled', true);

        } else {
            tabla.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            data.AluGrupoId = $(this).attr('id');
            if (data.AluGrupoId !== "" && data.AluGrupoId !== null && data.AluGrupoId !== undefined) {
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
//    $('#btnEliminar').click(function () {
//        eliminar();
//    });
//    $('#btnDetalles').click(function () {
//        seleccionar();
//    });
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
        if (data.AluGrupoId !== -1) {
            var parametros = 'AluGrupoId=' + data.AluGrupoId;
            var base64Parametros = encodeURIComponent(btoa(parametros));
            var url = '../../app/moduloInscripciones/completarInformacion.do?params=' + base64Parametros;


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

function eliminarSrv() {

}





