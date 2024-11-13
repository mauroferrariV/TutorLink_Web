package entities;

import java.time.LocalDateTime;
import java.util.Date;

public class EstudianteClase {
	final private String dateFormat="dd/MM/yyyy";
	final private String timeFormat="HH:mm:ss";
	final private String dateTimeFormat=dateFormat+" "+timeFormat;
    private Estudiante estudiante;
    private Tutor tutor;
    private Clase clase;
    private LocalDateTime fechaInscripcion;
    private String estado;

    /*
    public EstudianteClase(Estudiante estudiante, Clase clase, Date fechaInscripcion, String estado) {
        this.estudiante = estudiante;
        this.clase = clase;
        this.fechaInscripcion = fechaInscripcion;
        this.estado = estado;
    }
	*/
    // Getters y Setters
    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

    public Tutor getTutor() { return tutor; }
    public void setTutor(Tutor tutor) { this.tutor = tutor; }
    
    public Clase getClase() { return clase; }
    public void setClase(Clase clase) { this.clase = clase; }

    public LocalDateTime getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(LocalDateTime fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}