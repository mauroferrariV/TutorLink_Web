package entities;

public class Usuario {
    private int id;
    private String email;
    private String password;
    private TipoUsuario tipoUsuario;
    private String nombre;
    private String apellido;
    private int dni;
    private String telefono;
	
    /*
    public Usuario(int id, String email, String password, TipoUsuario tipoUsuario, String nombre, String apellido) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    */
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
	
	public int getDni() { return dni; }
	public void setDni(int dni) { this.dni = dni; }
	
	public String getTelefono() { return telefono; }
	public void setTelefono(String telefono) { this.telefono = telefono; }
	
	
	@Override
	public String toString() {
		return "\nUsuario [id=" + id + ", email=" + email + ", password=" + password + ", tipoUsuario=" + tipoUsuario.getNombre()
				+ ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
	

}