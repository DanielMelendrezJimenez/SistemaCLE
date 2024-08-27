/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.calificaciones;

/**
 *
 * @author Daniel Melendrez
 */
public class Calificaciones_consultar_MB {
    private int id;
    private String alumno;
    private String no_control;
    private String correo;
    private int listening;
    private int reading;
    private int writing;
    private int speaking;
    private int promedio;

    public Calificaciones_consultar_MB(int id, String alumno, String no_control, int listening, int reading, int writing, int speaking, int promedio) {
        this.id = id;
        this.alumno = alumno;
        this.no_control = no_control;
        this.listening = listening;
        this.reading = reading;
        this.writing = writing;
        this.speaking = speaking;
        this.promedio=promedio;
    }

    public Calificaciones_consultar_MB() {
    }
    
    public Calificaciones_consultar_MB(int id, String alumno, String no_control, String correo,int listening, int reading, int writing, int speaking, int promedio) {
        this.id = id;
        this.alumno = alumno;
        this.no_control = no_control;
        this.correo = correo;
        this.listening = listening;
        this.reading = reading;
        this.writing = writing;
        this.speaking = speaking;
        this.promedio=promedio;
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
    
    public String getCorreo(){
        return correo;
    }
    
    public void setCorreo(String correo){
        this.correo=correo;
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
    
}
