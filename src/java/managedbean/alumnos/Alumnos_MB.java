/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.alumnos;

import java.sql.Date;

/**
 *
 * @author Daniel Melendrez
 */
public class Alumnos_MB {

    private int id;
    private String nombre;
    private String ap_pat;
    private String ap_mat;
    private String genero;
    private String no_control;
    private String correo;
    private String tipo_alumno;
    private String telefono;
    private String carrera;
    private String fecha_naci;
    private Date ultima_modificacion;
    private int ultimo_usuario;
    private String estatus;

    public Alumnos_MB() {
    }

    public Alumnos_MB(String nombre, String ap_pat, String ap_mat, String genero, String no_control, String correo, String tipo_alumno, String telefono, String carrera, String fecha_naci, Date ultima_modificacion, int ultimo_usuario, String status) {
        this.nombre = nombre;
        this.ap_pat = ap_pat;
        this.ap_mat = ap_mat;
        this.genero = genero;
        this.no_control = no_control;
        this.correo = correo;
        this.tipo_alumno = tipo_alumno;
        this.telefono = telefono;
        this.carrera = carrera;
        this.fecha_naci = fecha_naci;
        this.ultima_modificacion = ultima_modificacion;
        this.ultimo_usuario = ultimo_usuario;
        this.estatus = status;
    }

    public Alumnos_MB(int id, String nombre, String ap_pat, String ap_mat, String genero, String no_control, String correo, String tipo_alumno, String telefono, String carrera, String fecha_naci, Date ultima_modificacion, int ultimo_usuario, String status) {
        this.id = id;
        this.nombre = nombre;
        this.ap_pat = ap_pat;
        this.ap_mat = ap_mat;
        this.genero = genero;
        this.no_control = no_control;
        this.correo = correo;
        this.tipo_alumno = tipo_alumno;
        this.telefono = telefono;
        this.carrera = carrera;
        this.fecha_naci = fecha_naci;
        this.ultima_modificacion = ultima_modificacion;
        this.ultimo_usuario = ultimo_usuario;
        this.estatus = status;
    }

 public Alumnos_MB(int id, String nombre, String ap_pat, String ap_mat, String no_control, String correo, String tipo_alumno, String status) {
        this.id = id;
        this.nombre = nombre;
        this.ap_pat = ap_pat;
        this.ap_mat = ap_mat;
        this.no_control = no_control;
        this.correo = correo;
        this.tipo_alumno = tipo_alumno;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNo_control() {
        return no_control;
    }

    public void setNo_control(String no_control) {
        this.no_control = no_control;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo_alumno() {
        return tipo_alumno;
    }

    public void setTipo_alumno(String tipo_alumno) {
        this.tipo_alumno = tipo_alumno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getFecha_naci() {
        return fecha_naci;
    }

    public void setFecha_naci(String fecha_naci) {
        this.fecha_naci = fecha_naci;
    }

    public Date getUltima_modificacion() {
        return ultima_modificacion;
    }

    public void setUltima_modificacion(Date ultima_modificacion) {
        this.ultima_modificacion = ultima_modificacion;
    }

    public int getUltimo_usuario() {
        return ultimo_usuario;
    }

    public void setUltimo_usuario(int ultimo_usuario) {
        this.ultimo_usuario = ultimo_usuario;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String status) {
        this.estatus = status;
    }

    @Override
    public String toString() {
        return "Alumnos_MB{" + "id=" + id + ", nombre=" + nombre + ", ap_pat=" + ap_pat + ", ap_mat=" + ap_mat + ", genero=" + genero + ", no_control=" + no_control + ", correo=" + correo + ", tipo_alumno=" + tipo_alumno + ", telefono=" + telefono + ", carrera=" + carrera + ", fecha_naci=" + fecha_naci + ", ultima_modificacion=" + ultima_modificacion + ", ultimo_usuario=" + ultimo_usuario + ", status=" + estatus + '}';
    }
}
