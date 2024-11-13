package entities;

public class Tema {
	private int id;
    private Asignatura asignatura;
    private String nombre;

    /*
    public Tema(int id, Asignatura asignatura, String nombre) {
        this.id = id;
        this.asignatura = asignatura;
        this.nombre = nombre;
    }
	*/
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Asignatura getAsignatura() { return asignatura; }
    public void setAsignatura(Asignatura asignatura) { this.asignatura = asignatura; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
