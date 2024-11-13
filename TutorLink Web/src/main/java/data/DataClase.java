package data;

import entities.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class DataClase {
	
	public LinkedList<Clase> getAll() {
		DataUsuario du = new DataUsuario();
		DataTema dt = new DataTema();
	    LinkedList<Clase> clases = new LinkedList<>();
	    Statement stmt = null;
	    ResultSet rs = null;

	    try {
	    	
	    	stmt= DbConnector.getInstancia().getConn().createStatement();
	        rs = stmt.executeQuery("SELECT * FROM clase");

	        while (rs!=null && rs.next()) {
	            Clase clase = new Clase();
	            clase.setId(rs.getInt("id"));
	            clase.setDireccion(rs.getString("direccion"));
	            clase.setEstado(rs.getString("estado"));
	            clase.setCupo(rs.getInt("cupo"));
	            
	            clase.setFechaHora(rs.getObject("fecha", LocalDateTime.class));
	            
	            //asigno Tutor a cada clase
	            Tutor t = new Tutor();
	            t.setId(rs.getInt("tutor"));
	            clase.setTutor(du.getTutorById(t));
	            
	            //Asigno a los estudiantes de cada clase
	            clase.setEstudiantes(du.getEstudiantesByClase(clase));
	            
	            //Asigno los temas a cada clase
	            clase.setTemas(dt.getTemasByClase(clase));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            DbConnector.getInstancia().releaseConn();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return clases;
	}
	
	public Clase getById(Clase claseToSearch) {
		DataUsuario du = new DataUsuario();
		DataTema dt = new DataTema();
		Clase clase = new Clase();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	    	
	    	Connection conn = DbConnector.getInstancia().getConn();
	        stmt = conn.prepareStatement("SELECT * FROM clase WHERE id = ?");
	        stmt.setInt(1, claseToSearch.getId());
	        rs = stmt.executeQuery();

	        if(rs!=null && rs.next()) {
	            clase.setId(rs.getInt("id"));
	            clase.setDireccion(rs.getString("direccion"));
	            clase.setEstado(rs.getString("estado"));
	            clase.setCupo(rs.getInt("cupo"));
	            
	            clase.setFechaHora(rs.getObject("fecha", LocalDateTime.class));
	            
	            //asigno Tutor a clase
	            Tutor t = new Tutor();
	            t.setId(rs.getInt("tutor"));
	            clase.setTutor(du.getTutorById(t));
	            
	            //Asigno a los estudiantes de la clase
	            clase.setEstudiantes(du.getEstudiantesByClase(clase));
	            
	            //Asigno los temas a la clase
	            clase.setTemas(dt.getTemasByClase(clase));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            DbConnector.getInstancia().releaseConn();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return clase;
	}
	
	
	public LinkedList<Clase> getClasesByEstudiante(Estudiante estudiante) {
		DataUsuario du = new DataUsuario();
		DataTema dt = new DataTema();
	    LinkedList<Clase> clases = new LinkedList<>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	    	
	    	Connection conn = DbConnector.getInstancia().getConn();
	        stmt = conn.prepareStatement("SELECT clase.* FROM clase INNER JOIN estudiante_clase on WHERE estudiante_clase.id_estudiante = ?");
	        stmt.setInt(1, estudiante.getId());
	        rs = stmt.executeQuery();
	       
	        while (rs!=null && rs.next()) {
	            Clase clase = new Clase();
	            clase.setId(rs.getInt("id"));
	            clase.setDireccion(rs.getString("direccion"));
	            clase.setEstado(rs.getString("estado"));
	            clase.setCupo(rs.getInt("cupo"));
	            
	            clase.setFechaHora(rs.getObject("fecha", LocalDateTime.class));
	            
	            //asigno Tutor a cada clase
	            Tutor t = new Tutor();
	            t.setId(rs.getInt("tutor"));
	            clase.setTutor(du.getTutorById(t));
	            
	            //Asigno a los estudiantes de cada clase
	            clase.setEstudiantes(du.getEstudiantesByClase(clase));
	            
	            //Asigno los temas a cada clase
	            clase.setTemas(dt.getTemasByClase(clase));
	            
	            clases.add(clase);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            DbConnector.getInstancia().releaseConn();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return clases;
	}
	
	
	public EstudianteClase getEstudianteClase(Clase claseToSearch, Estudiante estudianteToSearch) {
		DataUsuario du = new DataUsuario();
		EstudianteClase ec = new EstudianteClase();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	    	
	    	Connection conn = DbConnector.getInstancia().getConn();
	        stmt = conn.prepareStatement("SELECT * FROM estudiante_clase WHERE id_estudiante = ? and id_clase = ?");
	        stmt.setInt(1, claseToSearch.getId());
	        stmt.setInt(2, estudianteToSearch.getId());
	        rs = stmt.executeQuery();

	        if(rs!=null && rs.next()) {
	        	//Busco estudiante por id
	        	Estudiante e = new Estudiante();
	        	e.setId(rs.getInt("id_estudiante"));
	        	ec.setEstudiante(du.getEstudianteById(e));
	        	
	        	//Busco clase por id
	        	Clase c = new Clase();
	        	c.setId(rs.getInt("id_clase"));
	        	ec.setClase(getById(c));
	        	
	        	//Busco Tutor por id
	        	Tutor t = new Tutor();
	        	t.setId(rs.getInt("id_tutor"));
	        	ec.setTutor(du.getTutorById(t));
	        	
	        	
	        	ec.setEstado(rs.getString("estado"));
	            ec.setFechaInscripcion(rs.getObject("fecha_inscripcion", LocalDateTime.class));
	            
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            DbConnector.getInstancia().releaseConn();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return ec;
	}
	
	public void addTemaToClase(Clase clase, Tema tema) { 
		DataTema dt = new DataTema();
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			//Valido que exista el tema que me enviaron
			Tema t = new Tema();
			t = dt.getById(tema);
						
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into clase_tema(id_clase, id_tema, id_asignatura) values(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, clase.getId());
			stmt.setInt(2, t.getId());
			stmt.setInt(3, t.getAsignatura().getId());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	clase.setId(keyResultSet.getInt(1));
            }          
            			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
    }
	
	public void removeTemaToClase(Clase clase, Tema tema) { 
		DataTema dt = new DataTema();
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			//Valido que exista el tema que me enviaron
			Tema t = new Tema();
			t = dt.getById(tema);
						
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from clase_tema where id_clase = ? and id_tema = ? and id_asignatura = ?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, clase.getId());
			stmt.setInt(2, t.getId());
			stmt.setInt(3, t.getAsignatura().getId());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	clase.setId(keyResultSet.getInt(1));
            }          
            			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
    }
	
	public void addEstudianteToClase(Clase clase, Estudiante estudiante) { 
		DataUsuario du = new DataUsuario();
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			//Valido que exista el estudiante que me enviaron
			Estudiante e = new Estudiante();
			e = du.getEstudianteById(estudiante);
			
			//Podriamos hacer lo mismo con el tutor, valido que exista de clase.getTutor().getId();
			
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into estudiante_clase(id_estudiante, id_clase, id_tutor, fechaInscripcion, estado) values(?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, e.getId());
			stmt.setInt(2, clase.getId());
			stmt.setInt(3, clase.getTutor().getId());
			stmt.setObject(4, LocalDateTime.now()); //Esto debe enviar el tiempo del NOW, es decir el momento en donde se inscribe
			stmt.setString(5, "Inscripto"); //los estados son Inscripto, Cancelado, Finalizado
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	clase.setId(keyResultSet.getInt(1));
            }          
            			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
    }
	
	public void cancelEstudianteToClase(Clase clase, Estudiante estudiante) { 
		DataUsuario du = new DataUsuario();
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			//Valido que exista el estudiante que me enviaron
			Estudiante e = new Estudiante();
			e = du.getEstudianteById(estudiante);
			
			//Podriamos hacer lo mismo con el tutor, valido que exista de clase.getTutor().getId();
			
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update estudiante_clase set estado = ? where id_estudiante = ? and id_clase = ? and id_tutor = ?"
							);
			stmt.setString(1, "Cancelado"); //los estados son Inscripto, Cancelado, Finalizado
			stmt.setInt(2, e.getId());
			stmt.setInt(3, clase.getId());
			stmt.setInt(4, clase.getTutor().getId());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	clase.setId(keyResultSet.getInt(1));
            }          
            			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
    }
	
	public void add(Clase clase, Tutor tutor) { //en vez de usar tutor puedo mandarle el id dentro del campo clase.getTutor.getId()
		DataUsuario du = new DataUsuario();
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			//en vez de usar tutor puedo mandarle el id dentro del campo clase.getTutor.getId()
			Tutor t = new Tutor();
			t = du.getTutorById(tutor);
			
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into clase(id_tutor, fecha, direccion, estado, cupo) values(?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, t.getId());
			stmt.setObject(2, clase.getFechaHora());
			stmt.setString(3, clase.getDireccion());
			stmt.setString(4, clase.getEstado());
			stmt.setInt(5, clase.getCupo());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	clase.setId(keyResultSet.getInt(1));
            }          
            			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
    }
	
	
	public void update(Clase clase) { //debo mandar un objeto que previamente tenga el objeto tutor cargado
	    PreparedStatement stmt = null;

	    try {
	        Connection conn = DbConnector.getInstancia().getConn();
	        stmt = conn.prepareStatement(
	            "UPDATE clase SET fecha = ?, direccion = ?, estado = ?, cupo = ? WHERE id = ? and id_tutor = ?"
	        );
	        stmt.setObject(1, clase.getFechaHora());
	        stmt.setString(2, clase.getDireccion());
	        stmt.setString(3, clase.getEstado());
	        stmt.setInt(4, clase.getCupo());
	        stmt.setInt(5, clase.getId());
	        stmt.setInt(6, clase.getTutor().getId());


	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	            DbConnector.getInstancia().releaseConn();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	
	public void remove(Clase clase) { //habria que ver como es el tema de el eliminado en cascada
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from clase where id=?");
			stmt.setInt(1, clase.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
}