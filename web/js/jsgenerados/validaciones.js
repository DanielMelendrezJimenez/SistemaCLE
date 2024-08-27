/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function isLetter(valor) {
    let expReg = /^[a-zA-ZáéíóúñÑÁÉÍÓÚäÄëËïÏöÖüÜ]{1,30}$/;
    return expReg.test(valor);
}
function isNombres(valor) {
    let expReg = /^[a-zA-ZáéíóúñÑÁÉÍÓÚäÄëËïÏöÖüÜ ]{1,30}$/;
    return expReg.test(valor);
}
function isNumber(valor) {
    let expReg = /^[0-9]{1,13}$/;
    return expReg.test(valor);
}
function isDouble(valor) {
    let expReg = /^([0-9]{1,13})(\.[0-9]{1,2})?$/;
    return expReg.test(valor);
}
function isNumberCorto(valor) {
    let expReg = /^[0-9]{1,4}$/;
    return expReg.test(valor);
}
function isEmail(valor) {
    let expReg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    return expReg.test(valor);
}
function isDate(valor) {
    let expReg = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
    return expReg.test(valor);
}
function isAlphanumeric(valor) {
    let expReg = /^[0-9a-zA-ZáéíóúñÑÁÉÍÓÚäÄëËïÏöÖüÜ ]{1,30}$/;
    return expReg.test(valor);
}
function isAlphanumericGuiones(valor) {
    let expReg = /^[0-9a-zA-ZñÑáÁéÉíÍóÓúÚäÄëËïÏöÖüÜ .#'\-\s]{1,30}$/;
    return expReg.test(valor);
}
function isAlphanumericUsername(valor) {
    let expReg = /^[0-9a-zA-Z_\s]{1,20}$/;
    return expReg.test(valor);
}
function isAlphanumericComas(valor) {
    let expReg = /^[0-9a-zA-ZñÑáÁéÉíÍóÓúÚäÄëËïÏöÖüÜ ,\s]{1,30}$/;
    return expReg.test(valor);
}
function isNumberInt(valor) {
    let expReg = /^([0-9]{1,8})$/;
    return expReg.test(valor);
}
function isNumberFolio(valor) {
    let expReg = /^([0-9]{4,8})$/;
    return expReg.test(valor);
}
function isNumberCortoInt(valor) {
    let expReg = /^([0-9]{1,2})$/;
    return expReg.test(valor);
}
function istNumberExt(valor) {
    let expReg = /^[0-9]{1,8}$/;
    return expReg.test(valor);
}
function isCURP(valor) {
    let expReg = /^[A-Z]{4}\d{6}[HM][A-Z]{5}[A-Z\d]{2}$/;
    return expReg.test(valor);
}
function isPorcentaje(valor) {
    let expReg = /^(100|[1-9][0-9]|[0-9])(\.[0-9]{1,2})?$/;
    return expReg.test(valor);
}

function isRFC(valor) {
    let expReg = /^[A-ZÑ&]{4}\d{6}[A-Z\d]{3}$/;
    return expReg.test(valor);
}

function evitarCopy(id) {
    const curp = document.getElementById(id);
    if (curp) {
        curp.addEventListener('copy', function (event) {
            event.preventDefault();
        });
    }

}

function validarContrasena(contrasena) {
    // La contraseña debe tener al menos 8 caracteres
    // Al menos una letra minúscula
    // Al menos una letra mayúscula
    // Al menos un número
    // Puede contener caracteres especiales

    var expresionRegular = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;

    return expresionRegular.test(contrasena);
}
function isSelectOpc(id) {
    const select = document.getElementById(id);
    return select.value !== "-1";
}


function evitarPaste(id) {
    const curp = document.getElementById(id);
    if (curp) {
        curp.addEventListener('paste', function (event) {
            event.preventDefault();
        });
    }

}
function validarFechaNacimiento(fecha) {
    // Crea un objeto de fecha a partir de la cadena proporcionada

    let fechaNacimiento = new Date(fecha);

    // Verifica si la cadena proporcionada es una fecha válida
    if (isNaN(fechaNacimiento.getTime())) {
        return false;
    }

    // Verifica si la fecha de nacimiento es mayor a la fecha actual
    let fechaActual = new Date();
    if (fechaNacimiento.getTime() > fechaActual.getTime()) {
        return false;
    }

    // Verifica si el usuario tiene al menos 18 años
    let edadMinima = 7;
    let edadMilisegundos = edadMinima * 365.25 * 24 * 60 * 60 * 1000;
    let fechaMinima = new Date(fechaActual.getTime() - edadMilisegundos);
    if (fechaNacimiento.getTime() > fechaMinima.getTime()) {
        return false;
    }

    // Verifica si el usuario tiene menos de 90 años
    let edadMaxima = 90;
    let edadMaximaMilisegundos = edadMaxima * 365.25 * 24 * 60 * 60 * 1000;
    let fechaMaxima = new Date(fechaActual.getTime() - edadMaximaMilisegundos);
    if (fechaNacimiento.getTime() < fechaMaxima.getTime()) {
        return false;
    }

    // Si todas las verificaciones pasan, la fecha es válida
    return true;
}
function validarFechaNacimiento2(fecha) {
    // Crea un objeto de fecha a partir de la cadena proporcionada
    let partes = fecha.split("/");
    let fechaNacimiento = new Date(partes[2], partes[1] - 1, partes[0]);


    // Verifica si la cadena proporcionada es una fecha válida
    if (isNaN(fechaNacimiento.getTime())) {
        return false;
    }

    // Verifica si la fecha de nacimiento es mayor a la fecha actual
    let fechaActual = new Date();
    if (fechaNacimiento.getTime() > fechaActual.getTime()) {
        return false;
    }

    // Verifica si el usuario tiene al menos 18 años
    let edadMinima = 7;
    let edadMilisegundos = edadMinima * 365.25 * 24 * 60 * 60 * 1000;
    let fechaMinima = new Date(fechaActual.getTime() - edadMilisegundos);
    if (fechaNacimiento.getTime() > fechaMinima.getTime()) {
        return false;
    }

    // Verifica si el usuario tiene menos de 90 años
    let edadMaxima = 90;
    let edadMaximaMilisegundos = edadMaxima * 365.25 * 24 * 60 * 60 * 1000;
    let fechaMaxima = new Date(fechaActual.getTime() - edadMaximaMilisegundos);
    if (fechaNacimiento.getTime() < fechaMaxima.getTime()) {
        return false;
    }

    // Si todas las verificaciones pasan, la fecha es válida
    return true;
}

function validaCatalogo(valor) {

}



function validaTelefonos(cadena) {
    let reg = /^[0-9]{10,13}$/;
    if (reg.test(cadena)) {
        return true;
    }
    return false;
}


function validaTelDup(tel1, tel2) {
    if (tel1 === tel2) {
        return true;
    }
    return false;
}

function validAlfaNum(cadena) {
    let reg = /^[0-9a-zA-ZñÑáÁéÉíÍóÓúÚäÄëËïÏöÖüÜ .'\s]+$/;
    if (reg.test(cadena)) {
        return true;
    }
    return false;
}

function validaFecha(cadena) {
    let fechaActual = new Date().getTime();
    let f = cadena.split('/');
    let format = f[1] + "/" + f[0] + "/" + f[2];
    let fechaNac = new Date(format).getTime();
    if (fechaActual > fechaNac) {
        return true;
    }
    return false;
}

function convertirFecha1(fecha) {
    if (fecha !== "") {
        let fechaJs = new Date(fecha); // Convertir a objeto Date de JavaScript
        let formatoFecha = new Intl.DateTimeFormat('es-ES'); // Crear objeto Intl.DateTimeFormat con el formato deseado
        let fechaFormateada = formatoFecha.format(fechaJs); //
        return fechaFormateada;
    }

}


function convertirFechaDiagonal(fecha) {
    if (fecha !== "") {
        let partes = fecha.split(' '); // Dividir la fecha en partes por espacios
        let monthName = partes[0]; // Obtener el nombre del mes
        let day = partes[1].replace(',', ''); // Obtener el día sin la coma
        let year = partes[2]; // Obtener el año

        // Convertir el nombre del mes a número de mes
        let month = obtenerNumeroMes(monthName);

        // Verificar si el número de mes es válido
        if (month !== -1) {
            // Formatea la fecha en el formato "dd/mm/yyyy"
            let formattedDate = (day < 10 ? '0' : '') + day + '/' + (month < 10 ? '0' : '') + month + '/' + year;
            return formattedDate;
        } else {
            // El nombre del mes no es válido

        }
    }
}

// Función para obtener el número de mes a partir del nombre del mes
function obtenerNumeroMes(monthName) {
    let meses = {
        "ene": 1,
        "feb": 2,
        "mar": 3,
        "abr": 4,
        "may": 5,
        "jun": 6,
        "jul": 7,
        "ago": 8,
        "sep": 9,
        "oct": 10,
        "nov": 11,
        "dic": 12
    };

    return meses[monthName.toLowerCase()] || -1; // Devuelve el número de mes o -1 si no se encuentra el nombre del mes
}