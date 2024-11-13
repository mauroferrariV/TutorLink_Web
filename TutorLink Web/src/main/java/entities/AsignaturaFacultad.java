package entities;

public class AsignaturaFacultad {
    private Facultad facultad;
    private Asignatura asignatura;
    private int año;
    private String catedra;
    private String carrera;

    /*
    public AsignaturaFacultad(Facultad facultad, Asignatura asignatura, int año, String catedra, String carrera) {
        this.facultad = facultad;
        this.asignatura = asignatura;
        this.año = año;
        this.catedra = catedra;
        this.carrera = carrera;
    }
	*/
    // Getters y Setters
    public Facultad getFacultad() { return facultad; }
    public void setFacultad(Facultad facultad) { this.facultad = facultad; }

    public Asignatura getAsignatura() { return asignatura; }
    public void setAsignatura(Asignatura asignatura) { this.asignatura = asignatura; }

    public int getAño() { return año; }
    public void setAño(int año) { this.año = año; }

    public String getCatedra() { return catedra; }
    public void setCatedra(String catedra) { this.catedra = catedra; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }
}