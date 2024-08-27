/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
$(document).ready(function () {
    $("#pageLoader").hide();

//    $("#menu_inicio").on('click', function () {
//        cambiarPagina('../../app/portalAlberca/bienvenida.do', "#contenido")
//    });
//    $("#menu_agregar_modulo").on('click', function () {
//        cambiarPagina('../../app/moduloBloques/agregarBloque.do', "#contenido")
//    });
//    $("#menu_ver_modulos").on('click', function () {
//        cambiarPagina('../../app/moduloModulos/verModulos.do', "#contenido")
//    });
//    $("#menu_agregar_grupo").on('click', function () {
//        cambiarPagina('../../app/moduloGrupos/agregarGrupo.do', "#contenido")
//    });
//    $("#menu_ver_grupos").on('click', function () {
//        cambiarPagina('../../app/moduloGrupos/verGrupos.do', "#contenido")
//    });
//    $("#menu_registrar_operador").on('click', function () {
//        cambiarPagina('../../app/moduloOperadores/agregarOperador.do', "#contenido")
//    });
//    $("#menu_ver_operadores").on('click', function () {
//        cambiarPagina('../../app/moduloOperadores/verOperadores.do', "#contenido")
//    });
//    $("#menu_registrar_instructor").on('click', function () {
//        cambiarPagina('../../app/moduloInstructor/registrarInstructor.do', "#contenido")
//    });
//    $("#menu_ver_instructores").on('click', function () {
//        cambiarPagina('../../app/moduloInstructores/verInstructores.do', "#contenido")
//    });
//    $("#menu_buscar_cliente").on('click', function () {
//        cambiarPagina('../../app/moduloClientes/buscarClientes.do', "#contenido")
//    });
//    $("#menu_ver_clientes").on('click', function () {
//        cambiarPagina('../../app/moduloClientes/verClientes.do', "#contenido")
//    });
//     $("#menu_agregar_tipo_pago").on('click', function () {
//        cambiarPagina('../../app/moduloPagos/agregarTipoPago.do', "#contenido")
//    });
//     $("#menu_ver_catalogo_pagos").on('click', function () {
//        cambiarPagina('../../app/moduloPagos/verTiposPagos.do', "#contenido")
//    });
//    $("#menu_capturar_pago").on('click', function () {
//        cambiarPagina('../../app/moduloPagos/capturarPagos.do', "#contenido")
//    });
//    $("#menu_historial_pagos").on('click', function () {
//        cambiarPagina('../../app/moduloPagos/verPagos.do', "#contenido")
//    });
//    $("#menu_bitacora_acceso").on('click', function () {
//        cambiarPagina('../../app/moduloControlAccesos/bitacoraAccesos.do', "#contenido")
//    });
//    $("#menu_observaciones_sistema").on('click', function () {
//        cambiarPagina('../../app/moduloControlAcceso/observaciones.do', "#contenido")
//    });
//    $("#menu_crear_aviso").on('click', function () {
//        cambiarPagina('../../app/moduloAvisos/crearAviso.do', "#contenido")
//    });
//    $("#menu_ver_avisos").on('click', function () {
//        cambiarPagina('../../app/moduloAvisos/verAvisos.do', "#contenido")
//    });
//    $("#menu_estadisticos").on('click', function () {
//        cambiarPagina('../../app/moduloEstadisticos/estadisticos.do', "#contenido")
//    });
//    $("#menu_catalogos").on('click', function () {
//        cambiarPagina('../../app/moduloCatalogos/verCatalogos.do', "#contenido")
//    });
//    $("#menu_papelera").on('click', function () {
//        cambiarPagina('../../app/moduloPapelera/verPapelera.do', "#contenido")
//    });
//    $("#menu_configuraciones").on('click', function () {
//        cambiarPagina('../../app/moduloAjustes/ajustesGenerales.do', "#contenido")
//    });
  // $("#menu_fechas").on('click', function () {
  //     cambiarPagina('../../app/moduloAjustes/ajustesFechas.do', "#contenido")
  //  });
    $('[data-submenu]').submenupicker();
});

function cambiarPagina(url, contenedor) {
    $("#pageLoader").show();
    $.ajax({
        type: 'GET',
        url: url,
        dataType: 'html',
        success: function (data) {
            $(contenedor).html(data);
        },
        error: function (jqXHR, exception) {
            var msg = '';
            if (jqXHR.status === 0) {
                msg = 'verifique su conexion';
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
            $.ajax({
                type: 'GET',
                data: {msg: msg},
                url: '../../Error_Srv',
                dataType: 'html',
                success: function (data) {
                    $("#contenido").html(data);
                }
            });
        }
    });
    $("#pageLoader").hide();
}
