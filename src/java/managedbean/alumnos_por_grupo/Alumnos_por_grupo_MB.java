/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managedbean.alumnos_por_grupo;

import java.sql.Date;

/**
 *
 * @author wltgm
 */
public class Alumnos_por_grupo_MB {

    private int id;
    private int id_grupo;
    private int id_alumno;
    private Date ultima_mod;
    private int id_usuario_mod;
    private String estatus;

    public Alumnos_por_grupo_MB(int id, int id_grupo, int id_alumno, Date ultima_mod, int id_usuario_mod, String estatus) {
        this.id = id;
        this.id_grupo = id_grupo;
        this.id_alumno = id_alumno;
        this.ultima_mod = ultima_mod;
        this.id_usuario_mod = id_usuario_mod;
        this.estatus = estatus;
    }

    public Alumnos_por_grupo_MB() {
    }

    public Alumnos_por_grupo_MB(int id_grupo, int id_alumno, Date ultima_mod, int id_usuario_mod, String estatus) {
        this.id_grupo = id_grupo;
        this.id_alumno = id_alumno;
        this.ultima_mod = ultima_mod;
        this.id_usuario_mod = id_usuario_mod;
        this.estatus = estatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public Date getUltima_mod() {
        return ultima_mod;
    }

    public void setUltima_mod(Date ultima_mod) {
        this.ultima_mod = ultima_mod;
    }

    public int getId_usuario_mod() {
        return id_usuario_mod;
    }

    public void setId_usuario_mod(int id_usuario_mod) {
        this.id_usuario_mod = id_usuario_mod;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

}
