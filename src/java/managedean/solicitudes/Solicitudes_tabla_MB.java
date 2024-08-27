/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managedean.solicitudes;

/**
 *
 * @author wltgm
 */
public class Solicitudes_tabla_MB {

    private int id;
    private String concepto;
    private String descripcion;
    private int id_alumno;
    private String alumno;
    private String no_control;
    private String campo_editar;
    private String valor_nuevo;
    private String origen;
    private String estatus;

    public Solicitudes_tabla_MB(int id, String concepto, String descripcion, int id_alumno, String alumno, String no_control, String campo_editar, String valor_nuevo, String origen, String estatus) {
        this.id = id;
        this.concepto = concepto;
        this.descripcion = descripcion;
        this.id_alumno = id_alumno;
        this.alumno = alumno;
        this.no_control = no_control;
        this.campo_editar = campo_editar;
        this.valor_nuevo = valor_nuevo;
        this.origen = origen;
        this.estatus = estatus;
    }

    public Solicitudes_tabla_MB() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getNo_control() {
        return no_control;
    }

    public void setNo_control(String no_control) {
        this.no_control = no_control;
    }

    public String getCampo_editar() {
        return campo_editar;
    }

    public void setCampo_editar(String campo_editar) {
        this.campo_editar = campo_editar;
    }

    public String getValor_nuevo() {
        return valor_nuevo;
    }

    public void setValor_nuevo(String valor_nuevo) {
        this.valor_nuevo = valor_nuevo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }



}
