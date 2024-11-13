package data;

import entities.*;
import java.sql.*;
import java.util.LinkedList;

public class DataUsuario {
	
	public LinkedList<Usuario> getAll() {
		DataTipoUsuario dtu = new DataTipoUsuario();
	    LinkedList<Usuario> usuarios = new LinkedList<>();
	    Statement stmt = null;
	    ResultSet rs = null;

	    try {
	    	
	    	stmt= DbConnector.getInstancia().getConn().createStatement();
	        rs = stmt.executeQuery("SELECT * FROM usuario");

	        while (rs!=null && rs.next()) {
	            Usuario usuario = new Usuario();
	            usuario.setId(rs.getInt("id"));
	            usuario.setEmail(rs.getString("email"));
	            usuario.setPassword(rs.getString("password"));
	            usuario.setNombre(rs.getString("nombre"));
	            usuario.setApellido(rs.getString("apellido"));
	            usuario.setDni(rs.getInt("dni"));
	            usuario.setTelefono(rs.getString("telefono"));

	            // Obtenemos el tipo de usuario - Podria hacerse todo en un método setTipoUsuaro(Usuario usuario) de DataTipoUsuario
	            TipoUsuario tipo = new TipoUsuario();
	            tipo.setId(rs.getInt("tipoUsuario"));
	            usuario.setTipoUsuario(dtu.getById(tipo));

	            // usuario agregado a LinkedList
	            usuarios.add(usuario);
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

	    return usuarios;
	}
	
	public Tutor getTutorById(Tutor tutorToSearch) {
		DataTipoUsuario dtu = new DataTipoUsuario();
	    Tutor tutor = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        Connection conn = DbConnector.getInstancia().getConn();
	        stmt = conn.prepareStatement("SELECT * FROM tutor WHERE id = ?");
	        stmt.setInt(1, tutorToSearch.getId());
	        rs = stmt.executeQuery();

	        if (rs!=null && rs.next()) {
	        	tutor = new Tutor();
	        	tutor.setId(rs.getInt("id"));
	        	tutor.setEmail(rs.getString("email"));
	        	tutor.setPassword(rs.getString("password"));
	        	tutor.setNombre(rs.getString("nombre"));
	        	tutor.setApellido(rs.getString("apellido"));
	        	tutor.setDni(rs.getInt("dni"));
	        	tutor.setTelefono(rs.getString("telefono"));

	            // Obtenemos el tipo de usuario - Podria hacerse todo en un método setTipoUsuaro(Usuario usuario) de DataTipoUsuario
	            TipoUsuario tipo = new TipoUsuario();
	            tipo.setId(rs.getInt("tipoUsuario"));
	            tutor.setTipoUsuario(dtu.getById(tipo));
	            
	            //Debo desarrollar para asignar los temas, las asignaturas y las clases
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

	    return tutor;
	}
	
	
	public Estudiante getEstudianteById(Estudiante estudianteToSearch) {
		DataTipoUsuario dtu = new DataTipoUsuario();
	    Estudiante estudiante = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        Connection conn = DbConnector.getInstancia().getConn();
	        stmt = conn.prepareStatement("SELECT * FROM estudiante WHERE id = ?");
	        stmt.setInt(1, estudianteToSearch.getId());
	        rs = stmt.executeQuery();

	        if (rs!=null && rs.next()) {
	        	estudiante = new Estudiante();
	        	estudiante.setId(rs.getInt("id"));
	        	estudiante.setEmail(rs.getString("email"));
	        	estudiante.setPassword(rs.getString("password"));
	        	estudiante.setNombre(rs.getString("nombre"));
	        	estudiante.setApellido(rs.getString("apellido"));
	        	estudiante.setDni(rs.getInt("dni"));
	        	estudiante.setTelefono(rs.getString("telefono"));

	            // Obtenemos el tipo de usuario - Podria hacerse todo en un método setTipoUsuaro(Usuario usuario) de DataTipoUsuario
	            TipoUsuario tipo = new TipoUsuario();
	            tipo.setId(rs.getInt("tipoUsuario"));
	            estudiante.setTipoUsuario(dtu.getById(tipo));
	            
	            //Debo desarrollar para asignar los temas, las asignaturas y las clases
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

	    return estudiante;
	}
	
	
	public LinkedList<Estudiante> getEstudiantesByClase(Clase clase) {
		DataTipoUsuario dtu = new DataTipoUsuario();
	    Estudiante estudiante = null;
	    LinkedList<Estudiante> estudiantes = new LinkedList<>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        Connection conn = DbConnector.getInstancia().getConn();
	        stmt = conn.prepareStatement("SELECT * FROM estudiante INNER JOIN estudiante_clase on estudiante.id = estudiante_clase.id_estudiante WHERE estudiante_clase.id_clase= ?");
	        stmt.setInt(1, clase.getId());
       
	        rs = stmt.executeQuery();

	        while(rs!=null && rs.next()) {
	        	estudiante = new Estudiante();
	        	estudiante.setId(rs.getInt("id"));
	        	estudiante.setEmail(rs.getString("email"));
	        	estudiante.setPassword(rs.getString("password"));
	        	estudiante.setNombre(rs.getString("nombre"));
	        	estudiante.setApellido(rs.getString("apellido"));
	        	estudiante.setDni(rs.getInt("dni"));
	        	estudiante.setTelefono(rs.getString("telefono"));

	            // Obtenemos el tipo de usuario - Podria hacerse todo en un método setTipoUsuaro(Usuario usuario) de DataTipoUsuario
	            TipoUsuario tipo = new TipoUsuario();
	            tipo.setId(rs.getInt("tipoUsuario"));
	            estudiante.setTipoUsuario(dtu.getById(tipo));   
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

	    return estudiantes;
	}
	
	public Usuario getById(Usuario usuarioToSearch) {
		DataTipoUsuario dtu = new DataTipoUsuario();
	    Usuario usuario = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        Connection conn = DbConnector.getInstancia().getConn();
	        stmt = conn.prepareStatement("SELECT * FROM usuario WHERE id = ?");
	        stmt.setInt(1, usuarioToSearch.getId());
	        rs = stmt.executeQuery();
	        
	        while (rs!=null && rs.next()) {
	            usuario = new Usuario();
	            usuario.setId(rs.getInt("id"));
	            usuario.setEmail(rs.getString("email"));
	            usuario.setPassword(rs.getString("password"));
	            usuario.setNombre(rs.getString("nombre"));
	            usuario.setApellido(rs.getString("apellido"));
	            usuario.setDni(rs.getInt("dni"));
	            usuario.setTelefono(rs.getString("telefono"));
	
	            // Obtenemos el tipo de usuario - Podria hacerse todo en un método setTipoUsuaro(Usuario usuario) de DataTipoUsuario
	            TipoUsuario tipo = new TipoUsuario();
	            tipo.setId(rs.getInt("tipoUsuario"));
	            usuario.setTipoUsuario(dtu.getById(tipo));
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

	    return usuario;
	}
	
	
	public Usuario getByEmailPassword(Usuario usuarioToSearch) {
		DataTipoUsuario dtu = new DataTipoUsuario();
	    Usuario usuario = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        Connection conn = DbConnector.getInstancia().getConn();
	        stmt = conn.prepareStatement("SELECT * FROM usuario WHERE email = ? AND password = ?");
	        stmt.setString(1, usuarioToSearch.getEmail());
	        stmt.setString(2, usuarioToSearch.getPassword());
	        rs = stmt.executeQuery();

	        if (rs!=null && rs.next()) {
	            usuario = new Usuario();
	            usuario.setId(rs.getInt("id"));
	            usuario.setEmail(rs.getString("email"));
	            usuario.setPassword(rs.getString("password"));
	            usuario.setNombre(rs.getString("nombre"));
	            usuario.setApellido(rs.getString("apellido"));
	            usuario.setDni(rs.getInt("dni"));
	            usuario.setTelefono(rs.getString("telefono"));

	            // Obtenemos el tipo de usuario - Podria hacerse todo en un método setTipoUsuaro(Usuario usuario) de DataTipoUsuario
	            TipoUsuario tipo = new TipoUsuario();
	            tipo.setId(rs.getInt("tipoUsuario"));
	            usuario.setTipoUsuario(dtu.getById(tipo));
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

	    return usuario;
	}
	
	
	public void add(Usuario usuario, TipoUsuario tipo) {//el tipo tendra solamente el id con el tipo de usuario
		DataTipoUsuario dtu = new DataTipoUsuario();
		PreparedStatement stmt= null;
		PreparedStatement stmtRol= null;
		ResultSet keyResultSet=null;
		try {
			TipoUsuario tu = new TipoUsuario();
			tu = dtu.getById(tipo);
			
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into usuario(email, password, nombre, apellido, dni, telefono, tipoUsuario) values(?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getPassword());
			stmt.setString(3, usuario.getNombre());
			stmt.setString(4, usuario.getApellido());
			stmt.setInt(5, usuario.getDni());
			stmt.setString(6, usuario.getTelefono());
			stmt.setInt(7, tu.getId());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                usuario.setId(keyResultSet.getInt(1));
            }
            
            if(tu.getId() == 1) {
            	// Registro Administrador, No hay tabla administrador
            	//System.out.println("Admin registrado correctamente");
            }
            
            if(tu.getId() == 2) {
            	// TipoUsuario.getId() = 2 - Registro Tutor
            	stmtRol=DbConnector.getInstancia().getConn().
    					prepareStatement(
    							"insert into tutor(id) values(?)"
    							);
            	stmtRol.setInt(1, tu.getId());
            	stmt.executeUpdate();
            	//System.out.println("Tutor registrado correctamente");
            }
            
            if(tu.getId() == 3) {
            	// TipoUsuario.getId() = 3 - Registro Estudiante
            	stmtRol=DbConnector.getInstancia().getConn().
    					prepareStatement(
    							"insert into estudiante(id) values(?)"
    							);
            	stmtRol.setInt(1, tu.getId());
            	stmt.executeUpdate();
            }
            			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                if(stmtRol!=null)stmtRol.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
    }
	
	
	public void update(Usuario usuario) {
	    PreparedStatement stmt = null;

	    try {
	        Connection conn = DbConnector.getInstancia().getConn();
	        stmt = conn.prepareStatement(
	            "UPDATE usuario SET email = ?, password = ?, nombre = ?, apellido = ?, dni = ?, telefono = ? WHERE id = ?"
	        );
	        stmt.setString(1, usuario.getEmail());
	        stmt.setString(2, usuario.getPassword());
	        stmt.setString(3, usuario.getNombre());
	        stmt.setString(4, usuario.getApellido());
	        stmt.setInt(5, usuario.getDni());
	        stmt.setString(6, usuario.getTelefono());
	        stmt.setInt(7, usuario.getId());

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
	
	
	public void remove(Usuario usuario) { //habria que ver como es el tema de el eliminado en cascada
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from usuario where id=?");
			stmt.setInt(1, usuario.getId());
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
