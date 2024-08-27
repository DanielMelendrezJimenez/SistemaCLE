/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managedbean.inscripciones;

/**
 *
 * @author wltgm
 */
public class Preinscritos_MB {

    private int id;
    private String alumno;
    private String no_control;
    private String estatus;
    private int id_grupo;
    private String grupo;
    private String aula;
    private String modalidad;
    private String horario;

    public Preinscritos_MB(int id, String alumno, String no_control, String estatus, int id_grupo, String grupo, String aula, String modalidad, String horario) {
        this.id = id;
        this.alumno = alumno;
        this.no_control = no_control;
        this.estatus = estatus;
        this.id_grupo = id_grupo;
        this.grupo = grupo;
        this.aula = aula;
        this.modalidad = modalidad;
        this.horario = horario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
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


}
