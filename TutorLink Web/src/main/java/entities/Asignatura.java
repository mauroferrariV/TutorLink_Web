package entities;

import java.util.LinkedList;

public class Asignatura {
		private int id;
	    private String nombre;
	    private LinkedList<Tema> temas;

	    /*
	    public Asignatura(int id, String nombre) {
	        this.id = id;
	        this.nombre = nombre;
	        this.temas = new LinkedList<>();
	    }
		*/
	    
	    // Getters y Setters
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
	    
	    public LinkedList<Tema> getTemas() {
	        return temas;
	    }

	    public void setTemas(LinkedList<Tema> temas) {
	        this.temas = temas;
	    }

	    // Métodos para añadir y eliminar temas
	    public void addTema(Tema tema) {
	        if (!this.temas.contains(tema)) {
	            this.temas.add(tema);
	        }
	    }

	    public void removeTema(Tema tema) {
	        if (this.temas.contains(tema)) {
	            this.temas.remove(tema);
	        }
	    }
}
