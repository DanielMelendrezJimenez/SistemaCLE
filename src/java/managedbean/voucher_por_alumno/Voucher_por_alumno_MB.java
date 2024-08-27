/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.voucher_por_alumno;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Daniel Melendrez
 */
public class Voucher_por_alumno_MB {

    private int id;
    private int id_alumno;
    private int id_grupo;
    private String folio;
    private String referencia;
    private BigDecimal cantidad;
    private Date fecha;
    private String estatus;

    public Voucher_por_alumno_MB(int id, int id_alumno, int id_grupo, String folio, String referencia, BigDecimal cantidad, Date fecha, String estatus) {
        this.id = id;
        this.id_alumno = id_alumno;
        this.id_grupo = id_grupo;
        this.folio = folio;
        this.referencia = referencia;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.estatus = estatus;
    }

    public Voucher_por_alumno_MB() {
    }

    public Voucher_por_alumno_MB(int id_alumno, int id_grupo, String folio, String referencia, BigDecimal cantidad, Date fecha, String estatus) {
        this.id_alumno = id_alumno;
        this.id_grupo = id_grupo;
        this.folio = folio;
        this.referencia = referencia;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.estatus = estatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    

}
