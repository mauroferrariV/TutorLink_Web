package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.*;

public class DataAsignatura {
	
	public LinkedList<Asignatura> getAll(){
		DataTema dt = new DataTema();
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Asignatura> asignaturas= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from asignatura");
			if(rs!=null) {
				while(rs.next()) {
					Asignatura a=new Asignatura();
					a.setId(rs.getInt("id"));
					a.setNombre(rs.getString("nombre"));
					asignaturas.add(a);
					//agregar asignaturas
					dt.setTemas(a);
					
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return asignaturas;
	}
	
	//Debe devolver una asignatura con todos sus temas
	public Asignatura getById(Asignatura asignaturaToSearch) {
		DataTema dt = new DataTema();
		Asignatura a=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from asignatura where id=?"
					);
			stmt.setInt(1, asignaturaToSearch.getId());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				a=new Asignatura();
				a.setId(rs.getInt("id"));
				a.setNombre(rs.getString("nombre"));
				//agregar asignaturas
				dt.setTemas(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return a;
	}
	
	
	public AsignaturaFacultad getAsigFacuById(Facultad facultad, Asignatura asignatura) {
		AsignaturaFacultad af=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from asignatura_facultad where id_facultad=? and id_asignatura=?"
					);
			stmt.setInt(1, facultad.getId());
			stmt.setInt(2, asignatura.getId());
			rs=stmt.executeQuery();
			// Si se encuentra un resultado, crea un objeto AsignaturaFacultad
			if(rs!=null && rs.next()) {
				af=new AsignaturaFacultad();
				af.setFacultad(facultad);//estas inea van a tener que cambiar probablemente.
				af.setAsignatura(asignatura); //esta tambien
				af.setAño(rs.getInt("año"));
				af.setCatedra(rs.getString("catedra"));
				af.setCarrera(rs.getString("carrera"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return af;
	} 
	
	public LinkedList<Asignatura> getAsigsByFacu(Facultad facultad) {
		DataTema dt = new DataTema();
		Asignatura a=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Asignatura> asignaturas= new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select asignatura.* from asignatura inner join asignatura_facultad on asignatura.id = asignatura_facultad.id_asignatura where asignatura_facultad.id_facultad=?"
					);
			stmt.setInt(1, facultad.getId());
			rs=stmt.executeQuery();
			// Si se encuentra un resultado, crea un objeto AsignaturaFacultad
			if(rs!=null) {
				while(rs.next()) {
					a=new Asignatura();
					a.setId(rs.getInt("id"));
					a.setNombre(rs.getString("nombre"));
					asignaturas.add(a);
					//Agregar los temas de la asignatura
					dt.setTemas(a);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return asignaturas;
	}
/*
	public void setRoles(Persona per) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select rol.* "
					+ "from rol "
					+ "inner join rol_persona "
					+ "on rol.id=rol_persona.id_rol "
					+ "where id_persona=?"
					);
			stmt.setInt(1, per.getId());
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Rol r=new Rol();
					r.setId(rs.getInt("id"));
					r.setDescripcion(rs.getString("descripcion"));
					per.addRol(r);;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
*/
	
	//hay que validar la entrada dela facultad y la de el año de asigFacu
	public void add(Facultad facultad, Asignatura asignatura, AsignaturaFacultad asigFacu) {
		PreparedStatement stmtAsignatura = null;
        PreparedStatement stmtRelacion = null;
		ResultSet keyResultSet=null;
		try {
			// Inserta la asignatura en la tabla 'asignatura'
			stmtAsignatura=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into asignatura(nombre) values(?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmtAsignatura.setString(1, facultad.getNombre());
			stmtAsignatura.executeUpdate();
			
			// Obtener la clave generada para la asignatura
			keyResultSet=stmtAsignatura.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	facultad.setId(keyResultSet.getInt(1));
            }

            // Inserta la relación en la tabla intermedia 'asignatura_facultad'
            stmtRelacion=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into asignatura_facultad(id_facultad, id_asignatura, año, catedra, carrera) values(?, ?, ?, ?, ?)"
							);
            stmtRelacion.setInt(1, facultad.getId());
            stmtRelacion.setInt(2, asignatura.getId());
            stmtRelacion.setInt(3, asigFacu.getAño());
            stmtRelacion.setString(4, asigFacu.getCatedra());
            stmtRelacion.setString(5, asigFacu.getCarrera());
            stmtRelacion.executeUpdate();
			
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmtAsignatura!=null)stmtAsignatura.close();
                if(stmtRelacion!=null)stmtRelacion.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}

	}
	
	public void update(Asignatura asignatura) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update asignatura set nombre=? where id=?");
			stmt.setInt(1, asignatura.getId());
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
	
	public void remove(Asignatura asignatura) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from asignatura where id=?");
			stmt.setInt(1, asignatura.getId());
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
