package managedbean.usuarios;

/**
 *
 * @author Daniel Melendrez
 * @objetivo:
 * @fecha_creacion: 13/06/2024
 * @fecha_modificacion:
 */
public class Usuarios_MB {

    private int id;
    private String nombre;
    private String ap_pat;
    private String ap_mat;
    private String curp;
    private String no_control;
    private String contrasena;
    private String correo;
    private String tipo_usuario;
    private String estatus;

    //Constructor vacío
    public Usuarios_MB() {
    }

    //Constructor para insertar un nuevo usuario
    public Usuarios_MB(String nombre, String ap_pat, String ap_mat, String curp, String no_control, String contrasena, String correo, String tipo_usuario, String status) {
        this.nombre = nombre;
        this.ap_pat = ap_pat;
        this.ap_mat = ap_mat;
        this.curp = curp;
        this.no_control = no_control;
        this.contrasena = contrasena;
        this.correo = correo;
        this.tipo_usuario = tipo_usuario;
        this.estatus = status;
    }

    //Constructor para traer todos los datos de un usuario
    public Usuarios_MB(int id, String nombre, String ap_pat, String ap_mat, String curp, String no_control, String correo, String tipo_usuario, String status) {
        this.id = id;
        this.nombre = nombre;
        this.ap_pat = ap_pat;
        this.ap_mat = ap_mat;
        this.curp = curp;
        this.no_control = no_control;
        this.correo = correo;
        this.tipo_usuario = tipo_usuario;
        this.estatus = status;
    }

    public Usuarios_MB(int id, String nombre, String ap_pat, String ap_mat, String curp, String no_control, String correo, String status) {
        this.id = id;
        this.nombre = nombre;
        this.ap_pat = ap_pat;
        this.ap_mat = ap_mat;
        this.curp = curp;
        this.no_control = no_control;
        this.correo = correo;
        this.estatus = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp_pat() {
        return ap_pat;
    }

    public void setAp_pat(String ap_pat) {
        this.ap_pat = ap_pat;
    }

    public String getAp_mat() {
        return ap_mat;
    }

    public void setAp_mat(String ap_mat) {
        this.ap_mat = ap_mat;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNo_control() {
        return no_control;
    }

    public void setNo_control(String no_control) {
        this.no_control = no_control;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String status) {
        this.estatus = status;
    }

    @Override
    public String toString() {
        return "Usuarios_MB{" + "id=" + id + ", nombre=" + nombre + ", ap_pat=" + ap_pat + ", ap_mat=" + ap_mat + ", no_control=" + no_control + ", contrasena=" + contrasena + ", correo=" + correo + ", tipo_usuario=" + tipo_usuario + ", status=" + estatus + '}';
    }

}
