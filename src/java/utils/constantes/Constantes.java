package utils.constantes;

/**
 * @author Kevin Ivan Sanchez Valdin
 * @objetivo
 * @creado 27/03/2023
 * @actualizado 14/06/2023
 */
public class Constantes {

    public static final String RUTA_PRINCIPAL_APP = "/index.jsp";

    public static final String CONTENT_TYPE_JSON = "application/json";

    public static final int CONEXION_EXITOSA_BD = 1;
    public static final int CONEXION_FALLIDA_BD = 0;

    public static final int INTERNO_OPC = 1;
    public static final int AFILIADO_OPC = 2;
    public static final int EXTERNO_OPC = 3;
    public static final String VINTERNO_OPC = "INTERNO";
    public static final String VAFILIADO_OPC = "AFILIADO";
    public static final String VEXTERNO_OPC = "EXTERNO";

    public static final String GRUPAL_OPC = "GRUPAL";
    public static final String GENERAL_OPC = "GENERAL";
    public static final String ALUMNADO_OPC = "ALUMNADO";

    public static final int CREDITO_OPC = 4;
    public static final int REPRESENTATIVO_OPC = 5;
    public static final int ACTIVO_OPC = 1;
    public static final int INACTIVO_OPC = 0;
    public static final String PRINCIPIANTE_OPC = "PRINCIPIANTE";
    public static final String INTERMEDIO_OPC = "INTERMEDIO";
    public static final String AVANZADO_OPC = "AVANZADO";

    public static final String[] MONTHS = {
        "NA",
        "Enero",
        "Febrero",
        "Marzo",
        "Abril",
        "Mayo",
        "Junio",
        "Julio",
        "Agosto",
        "Septiembre",
        "Octubre",
        "Noviembre",
        "Diciembre"
    };

    //CONSTANTES DE STATUS
    public static final int LOGIC_DEFAULT = 0;
    public static final int LOGIC_SUCCESS = 1;
    public static final int LOGIC_WARNING = 2;
    public static final int LOGIC_ERROR = 3;
    public static final int LOGIC_RED = 4;
    public static final int LOGIC_NO_DATA = 5;

    public static final int STATUS_EXITO = 0;
    public static final int STATUS_NO_DATA = 50;
    //STATUS DE RESPUESTA INFORMATIVA
    public static final int STATUS_CONTINUE = 100;
    public static final int STATUS_SWITCHING_PROTOCOLS = 101;
    public static final int STATUS_PROCESSING = 102;
    public static final int STATUS_EARLY_HINTS = 103;
    //ESTATUS DE EXITO
    public static final int STATUS_OK = 200;
    public static final int STATUS_CREATE = 201;
    public static final int STATUS_ACCEPTED = 202;
    public static final int STATUS_NON_AUTORITATIVE_INFORMATION = 203;
    public static final int STATUS_NO_CONTENT = 204;
    public static final int STATUS_RESET_CONTENT = 205;
    public static final int STATUS_PARTIAL_CONTENT = 206;
    public static final int STATUS_MULTI = 207;
    public static final int STATUS_ALREADY_REPORTED = 208;
    public static final int STATUS_IM_USED = 226;
    //STATUS DE REDIRECCION
    public static final int STATUS_MULTIPLES_CHOICES = 300;
    public static final int STATUS_MOVED_PERMANETLY = 301;
    public static final int STATUS_FOUD = 302;
    public static final int STATUS_SEE_OTHER = 303;
    public static final int STATUS_NOT_MODIFIED = 304;
    public static final int STATUS_USE_PROXY = 305;
    public static final int STATUS_SWITCH_PROXY = 306;
    public static final int STATUS_TEMPORARY_REDIRECT = 307;
    public static final int STATUS_PERMANENT_REDIRECT = 308;
    //STATUS DE CLIENTE
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_UNATHORIZED = 401;
    public static final int STATUS_PAYMENT_REQUIRED = 402;
    public static final int STATUS_FORBIDDEN = 403;
    public static final int STATUS_NOT_FOUNT = 404;
    public static final int STATUS_METHOD_NOT_ALLOWED = 405;
    public static final int STATUS_NOT_ACCEPTABLE = 406;
    public static final int STATUS_PROXY_AUTHENTICATION_REQUIRED = 407;
    public static final int STATUS_REQUEST_TIMEOUT = 408;
    public static final int STATUS_CONFLICT = 409;
    public static final int STATUS_GONE = 410;
    public static final int STATUS_LENGTH_REQUIRED = 411;
    public static final int STATUS_PRECONDITION_FAILED = 412;
    public static final int STATUS_PAYLOAD_TOO_LARGE = 413;
    public static final int STATUS_URI_TOO_LONG = 414;
    public static final int STATUS_UNSOPPORTED_MEDIA_TYPE = 415;
    public static final int STATUS_RANGE_NOT_SATISFIABLE = 416;
    public static final int STATUS_EXPECTATION_FAILED = 417;
    public static final int STATUS_IM_A_TEAPOT = 418;
    public static final int STATUS_PAGE_EXPIRED = 419;
    public static final int STATUS_METHOD_FAILURE = 420;
    public static final int STATUS_MISDIRECT_REQUEST = 421;
    public static final int STATUS_UNPROCESSABLE_ENTITY = 422;
    public static final int STATUS_LOCKED = 423;
    public static final int STATUS_FAILED_DEPENDENCY = 424;
    public static final int STATUS_TOO_EARLY = 425;
    public static final int STATUS_UPGRADE_REQUIRED = 426;
    public static final int STATUS_PRECONDITION_REQUIRED = 428;
    public static final int STATUS_TOO_MANY_REQUESTS = 429;
    public static final int STATUS_REQUEST_HEADER_FLIEDS_TOO_LARGE = 431;
    public static final int STATUS_LOGIN_TIME_OUT = 440;
    public static final int STATUS_NO_RESPONSE = 444;
    public static final int STATUS_RETRY_WITH = 449;
    public static final int STATUS_UNAVAILABLE_FOR_LEGAL_REASONS = 451;
    public static final int STATUS_TOKEN_NO_VALIDO = 498;
    public static final int STATUS_TOKEN_REQUERIDO = 499;
    //STATUS DE SERVIDOR
    public static final int STATUS_INTERNAL_SERVER_ERROR = 500;
    public static final int STATUS_NOT_IMPLEMENTED = 501;
    public static final int STATUS_BAD_GATEWAY = 502;
    public static final int STATUS_SERVICE_UNAVAILABLE = 503;
    public static final int STATUS_GATEWAY_TIMEAOUT = 504;
    public static final int STATUS_HTTP_VERSION_NOT_SUPPORTED = 505;
    public static final int STATUS_VARIANT_ALSO_NEGOTIATES = 506;
    public static final int STATUS_INSUFFICIENT_STORANGE = 507;
    public static final int STATUS_LOOP_DETECTED = 508;
    public static final int STATUS_NOT_EXTENDED = 510;
    public static final int STATUS_NETWORK_AUTHENTICATION_REQUIRED = 511;
    public static final int STATUS_WEB_SERVER_RETURNED_AN_UNKNOWN_ERROR = 520;
    public static final int STATUS_WEB_SERVER_IS_DOWN = 521;
    public static final int STATUS_CONNECTION_TIME_OUT = 522;
    public static final int STATUS_ORINGIN_IS_UNREACHABLE = 523;
    public static final int STATUS_A_TIMEOUT_OCURRED = 524;
    public static final int STATUS_SITE_IS_ORVERLOADED = 529;
    public static final int STATUS_SITE_IS_FROZEN = 530;
    public static final int STATUS_UNAUTHORIZED = 561;
    //STATUS DE BASE DE DATOS
    public static final int STATUS_CONEXION_EXITOSA_BD = 600;
    public static final int STATUS_REGISTRO_EXITOSO_BD = 601;
    public static final int STATUS_ACTUALIZACION_EXITOSA_BD = 602;
    public static final int STATUS_ELIMINACION_EXITOSA_BD = 603;
    public static final int STATUS_CONSULTA_EXITOSA_BD = 604;
    public static final int STATUS_BUSQUEDA_EXITOSA_BD = 605;
    public static final int STATUS_CONEXION_FALLIDA_BD = -600;
    public static final int STATUS_REGISTRO_FALLIDO_BD = -601;
    public static final int STATUS_ACTUALIZACION_FALLIDA_BD = -602;
    public static final int STATUS_ELIMINACION_FALLIDA_BD = -603;
    public static final int STATUS_CONSULTA_FALLIDA_BD = -604;
    public static final int STATUS_BUSQUEDA_FALLIDA_BD = -605;
    public static final String SIN_CREDITO_OPC = "LIB CREDITO";
    public static final String SIN_REPRESENTATIVO_OPC = "LIB REPRESENTATIVO";
    public static final int TODOS = 0;

}
