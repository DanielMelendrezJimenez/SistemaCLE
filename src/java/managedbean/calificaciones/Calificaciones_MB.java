/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managedbean.calificaciones;

import java.sql.Date;

/**
 *
 * @author wltgm
 */
public class Calificaciones_MB {
    private int id;
private int id_grupo;
private int id_alumno;
private int listening;
private int reading;
private int writing;
private int speaking;
private int promedio;
private Date ultima_mod;
private int id_usuario_modifico;

    public Calificaciones_MB(int id, int id_grupo, int id_alumno, int caluno, int caldos, int caltres, int calcuatro, int promedio, Date ultima_mod, int id_usuario_modifico) {
        this.id = id;
        this.id_grupo = id_grupo;
        this.id_alumno = id_alumno;
        this.listening = caluno;
        this.reading = caldos;
        this.writing = caltres;
        this.speaking = calcuatro;
        this.promedio = promedio;
        this.ultima_mod = ultima_mod;
        this.id_usuario_modifico = id_usuario_modifico;
    }

    public Calificaciones_MB(int id_grupo, int id_alumno, int caluno, int caldos, int caltres, int calcuatro, int calcinco, int calseis, int calsiete, int promedio, Date ultima_mod, int id_usuario_modifico) {
        this.id_grupo = id_grupo;
        this.id_alumno = id_alumno;
        this.listening = caluno;
        this.reading = caldos;
        this.writing = caltres;
        this.speaking = calcuatro;
        this.promedio = promedio;
        this.ultima_mod = ultima_mod;
        this.id_usuario_modifico = id_usuario_modifico;
    }

    public Calificaciones_MB() {
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

    public int getListening() {
        return listening;
    }

    public void setListening(int listening) {
        this.listening = listening;
    }

    public int getReading() {
        return reading;
    }

    public void setReading(int reading) {
        this.reading = reading;
    }

    public int getWriting() {
        return writing;
    }

    public void setWriting(int writing) {
        this.writing = writing;
    }

    public int getSpeaking() {
        return speaking;
    }

    public void setSpeaking(int speaking) {
        this.speaking = speaking;
    }

    public int getPromedio() {
        return promedio;
    }

    public void setPromedio(int promedio) {
        this.promedio = promedio;
    }

    public Date getUltima_mod() {
        return ultima_mod;
    }

    public void setUltima_mod(Date ultima_mod) {
        this.ultima_mod = ultima_mod;
    }

    public int getId_usuario_modifico() {
        return id_usuario_modifico;
    }

    public void setId_usuario_modifico(int id_usuario_modifico) {
        this.id_usuario_modifico = id_usuario_modifico;
    }



}
