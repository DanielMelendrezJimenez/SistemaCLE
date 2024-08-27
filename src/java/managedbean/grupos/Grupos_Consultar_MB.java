/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managedbean.grupos;

/**
 *
 * @author wltgm
 */
public class Grupos_Consultar_MB {

    private int id;
    private String curso;
    private String facilitador;
    private String modalidad;
    private String horario;
    private String trimestre;
    private String estatus;

    public Grupos_Consultar_MB(int id, String curso, String facilitador, String modalidad, String horario, String trimestre, String estatus) {
        this.id = id;
        this.curso = curso;
        this.facilitador = facilitador;
        this.modalidad = modalidad;
        this.horario = horario;
        this.trimestre = trimestre;
        this.estatus = estatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getFacilitador() {
        return facilitador;
    }

    public void setFacilitador(String facilitador) {
        this.facilitador = facilitador;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

}
