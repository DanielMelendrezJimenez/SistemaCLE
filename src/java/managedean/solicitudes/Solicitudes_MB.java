/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedean.solicitudes;

/**
 *
 * @author Daniel Melendrez
 */
public class Solicitudes_MB {
    private int id;
    private String concepto;
    private String descripcion;
    private int id_alumno;
    private String campo_editar;
    private String valor_nuevo;
    private String origen;
    private String estatus;

    public Solicitudes_MB(int id, String concepto, String descripcion, int id_alumno, String campo_editar, String valor_nuevo, String origen, String estatus) {
        this.id = id;
        this.concepto = concepto;
        this.descripcion = descripcion;
        this.id_alumno = id_alumno;
        this.campo_editar = campo_editar;
        this.valor_nuevo = valor_nuevo;
        this.origen = origen;
        this.estatus = estatus;
    }

    public Solicitudes_MB(String concepto, String descripcion, int id_alumno, String campo_editar, String valor_nuevo, String origen, String estatus) {
        this.concepto = concepto;
        this.descripcion = descripcion;
        this.id_alumno = id_alumno;
        this.campo_editar = campo_editar;
        this.valor_nuevo = valor_nuevo;
        this.origen = origen;
        this.estatus = estatus;
    }

    public Solicitudes_MB() {
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

    @Override
    public String toString() {
        return "Solicitudes_MB{" + "id=" + id + ", concepto=" + concepto + ", descripcion=" + descripcion + ", id_alumno=" + id_alumno + ", campo_editar=" + campo_editar + ", valor_nuevo=" + valor_nuevo + ", origen=" + origen + ", estatus=" + estatus + '}';
    }
    
    
}
