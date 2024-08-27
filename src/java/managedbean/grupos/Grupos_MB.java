/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managedbean.grupos;

import java.sql.Date;

/**
 *
 * @author wltgm
 */
public class Grupos_MB {

    private int id;
    private int id_curso;
    private int id_facilitador;
    private String trimestre;
    private Date ultima_modificacion;
    private int id_usuario_modifico;
    private String estatus;

    public Grupos_MB() {
    }

    public Grupos_MB(int id_curso, int id_facilitador, String trimestre, Date ultima_modificacion, int id_usuario_modifico, String estatus) {
        this.id_curso = id_curso;
        this.id_facilitador = id_facilitador;
        this.trimestre = trimestre;
        this.ultima_modificacion = ultima_modificacion;
        this.id_usuario_modifico = id_usuario_modifico;
        this.estatus = estatus;
    }

    public Grupos_MB(int id, int id_curso, int id_facilitador, String trimestre, Date ultima_modificacion, int id_usuario_modifico, String estatus) {
        this.id = id;
        this.id_curso = id_curso;
        this.id_facilitador = id_facilitador;
        this.trimestre = trimestre;
        this.ultima_modificacion = ultima_modificacion;
        this.id_usuario_modifico = id_usuario_modifico;
        this.estatus = estatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public int getId_facilitador() {
        return id_facilitador;
    }

    public void setId_facilitador(int id_facilitador) {
        this.id_facilitador = id_facilitador;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public Date getUltima_modificacion() {
        return ultima_modificacion;
    }

    public void setUltima_modificacion(Date ultima_modificacion) {
        this.ultima_modificacion = ultima_modificacion;
    }

    public int getId_usuario_modifico() {
        return id_usuario_modifico;
    }

    public void setId_usuario_modifico(int id_usuario_modifico) {
        this.id_usuario_modifico = id_usuario_modifico;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }


}
