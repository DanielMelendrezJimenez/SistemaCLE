
package managebean.general;

import static utils.constantes.Constantes.STATUS_EXITO;


/**
 *
 * @author Kevin Ivan Sanchez Valdin
 * @param <T> El objeto que se retornará en la respuesta.
 */
public class GenericResponse<T> {

    private int status;
    private String mensaje;
    private T responseObject;

    public GenericResponse() {
        this.status = STATUS_EXITO;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(T responseObject) {
        this.responseObject = responseObject;
    }

    @Override
    public String toString() {
        return "GenericResponse{" + "status=" + status + ", mensaje=" + mensaje + ", responseObject=" + responseObject + '}';
    }

}
