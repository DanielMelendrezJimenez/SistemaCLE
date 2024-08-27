/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.configuracion;

import java.sql.Date;

/**
 *
 * @author Daniel Melendrez
 */
public class Parametros_MB {

    private int id;
    private String concepto;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String estatus;

    public Parametros_MB() {
    }

    public Parametros_MB(String concepto, Date fecha_incio, Date fecha_fin, String estatus) {
        this.concepto = concepto;
        this.fecha_inicio = fecha_incio;
        this.fecha_fin = fecha_fin;
        this.estatus = estatus;
    }

    public Parametros_MB(int id, String concepto, Date fecha_incio, Date fecha_fin, String estatus) {
        this.id = id;
        this.concepto = concepto;
        this.fecha_inicio = fecha_incio;
        this.fecha_fin = fecha_fin;
        this.estatus = estatus;
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

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_incio) {
        this.fecha_inicio = fecha_incio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "Parametros_MB{" + "id=" + id + ", concepto=" + concepto + ", fecha_incio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + '}';
    }

}
