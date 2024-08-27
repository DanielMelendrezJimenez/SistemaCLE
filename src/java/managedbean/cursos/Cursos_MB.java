/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.cursos;

import java.sql.Time;

/**
 *
 * @author Daniel Melendrez
 */
public class Cursos_MB {

    private int id;
    private String nombre;
    private int nivel;
    private String modalidad;
    private Time hora_inicio;
    private Time hora_fin;
    private String aula;
    private String estatus;

    public Cursos_MB() {
    }

    public Cursos_MB(String nombre, int nivel, String modalidad, Time hora_inicio, Time hora_fin, String aula, String estatus) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.modalidad = modalidad;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.aula = aula;
        this.estatus = estatus;
    }

    public Cursos_MB(int id, String nombre, int nivel, String modalidad, Time hora_inicio, Time hora_fin, String aula, String estatus) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.modalidad = modalidad;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.aula = aula;
        this.estatus = estatus;
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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "Cursos_MB{" + "id=" + id + ", nombre=" + nombre + ", nivel=" + nivel + ", modalidad=" + modalidad + ", hora_inicio=" + hora_inicio + ", hora_fin=" + hora_fin + ", aula=" + aula + ", estatus=" + estatus + '}';
    }
    
    
}
