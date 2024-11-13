package entities;

import java.util.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Clase {
	final private String dateFormat="dd/MM/yyyy";
	final private String timeFormat="HH:mm:ss";
	final private String dateTimeFormat=dateFormat+" "+timeFormat;
    private int id;
    private Tutor tutor;
    private LocalDateTime fechaHora;
    private String direccion;
    private String estado;
    private int cupo;
    private LinkedList<Estudiante> estudiantes;
    private LinkedList<Tema> temas;

    /*
    public Clase(int id, Tutor tutor, Date fecha, String direccion, String estado) {
        this.id = id;
        this.tutor = tutor;
        this.fecha = fecha;
        this.direccion = direccion;
        this.estado = estado;
        this.estudiantes = new LinkedList <>();
        this.temas = new LinkedList<>();
    }
	*/
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Tutor getTutor() { return tutor; }
    public void setTutor(Tutor tutor) { this.tutor = tutor; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
        
    public int getCupo() { return cupo; }
	public void setCupo(int cupo) { this.cupo = cupo; }

	public LinkedList<Estudiante> getEstudiantes() { return estudiantes; }
    public void setEstudiantes(LinkedList<Estudiante> estudiantes) { this.estudiantes = estudiantes; }
    
    public LinkedList<Tema> getTemas() { return temas; }
	public void setTemas(LinkedList<Tema> temas) { this.temas = temas; }

	
	// Métodos para añadir y eliminar temas
    public void agregarTema(Tema tema) {
        if (!this.temas.contains(tema)) {
            this.temas.add(tema);
        }
    }

    public void eliminarTema(Tema tema) {
        if (this.temas.contains(tema)) {
            this.temas.remove(tema);
        }
    }
	
	// Métodos para añadir y eliminar estudiantes
    public void agregarEstudiante(Estudiante estudiante) {
        if (!this.estudiantes.contains(estudiante)) {
            this.estudiantes.add(estudiante);
        }
    }

    public void eliminarEstudiante(Estudiante estudiante) {
        if (this.estudiantes.contains(estudiante)) {
            this.estudiantes.remove(estudiante);
        }
    }
    
}

