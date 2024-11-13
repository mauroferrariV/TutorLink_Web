package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.*;

public class DataTema {
	
	public LinkedList<Tema> getAll(){
		DataAsignatura da = new DataAsignatura();
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Tema> temas= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from tema");
			if(rs!=null) {
				while(rs.next()) {
					Tema t = new Tema();
					//creamos un objeto Asignatura
					Asignatura a = new Asignatura();
					//Le asignamos el valor que nos devuelve de id_asignatura
					a.setId(rs.getInt("id_asignatura"));
					t.setId(rs.getInt("id"));
					t.setNombre(rs.getString("nombre"));
					t.setAsignatura(da.getById(a)); //recuperamos la asignatura con su id
					temas.add(t);
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
		
		
		return temas;
	}
	
	public Tema getById(Tema temaToSearch) {
		DataAsignatura da = new DataAsignatura();
		Tema t=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from tema where id=?"
					);
			stmt.setInt(1, temaToSearch.getId());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				t=new Tema();
				Asignatura a = new Asignatura();
				//Le asignamos el valor que nos devuelve de id_asignatura
				a.setId(rs.getInt("id_asignatura"));
				t.setId(rs.getInt("id"));
				t.setNombre(rs.getString("nombre"));
				t.setAsignatura(da.getById(a)); //recuperamos la asignatura con su id
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
		
		return t;
	}
	
	public LinkedList<Tema> getTemasByClase(Clase clase) {
		DataAsignatura da = new DataAsignatura();
		Tema t=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Tema> temas = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select tema.* from tema inner join clase_tema on tema.id = clase_tema.id_tema where clase_tema.id_clase=?"
					);
			stmt.setInt(1, clase.getId());
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					t=new Tema();
					t.setId(rs.getInt("id"));
					t.setNombre(rs.getString("nombre"));
					
					Asignatura a = new Asignatura();
					t.setAsignatura(da.getById(a)); //recuperamos la asignatura con su id
					temas.add(t);
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
		
		return temas;
	}
	
	public void setTemas(Asignatura asignatura) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select tema.* "
					+ "from tema "
					+ "inner join asignatura "
					+ "on tema.id_asignatura=asignatura.id "
					+ "where asignatura.id=?"
					);
			stmt.setInt(1, asignatura.getId());
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Tema t=new Tema();
					t.setId(rs.getInt("id"));
					t.setNombre(rs.getString("nombre"));
					t.setAsignatura(asignatura);
					asignatura.addTema(t);
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
	
	/*
	public Facultad getByDesc(Facultad facultadToSearch) {
		Facultad f=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from facultades where nombre=?"
					);
			stmt.setString(1, facultadToSearch.getNombre());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				f=new Facultad();
				f.setId(rs.getInt("id"));
				f.setNombre(rs.getString("nombre"));
				f.setCiudad(rs.getString("ciudad"));
				f.setPais(rs.getString("pais"));
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
		
		return f;
	} 
	
	*/
	public void add(Tema tema, Asignatura asignatura) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into tema(id_asignatura, nombre) values(?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, asignatura.getId());
			stmt.setString(2, tema.getNombre());
			stmt.executeUpdate();
			
			// Obtener la clave generada para la asignatura
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	tema.setId(keyResultSet.getInt(1));
            }

			
		} catch (SQLException e) {
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
	
	public void update(Tema tema) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update tema set nombre=? where id=? and id_asignatura=?");
			stmt.setString(1, tema.getNombre());
			stmt.setInt(2, tema.getId());
			stmt.setInt(3, tema.getAsignatura().getId()); //PODRIA hacerse esto omandar un objeto Asignatura como parametro que contenga solo el id
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
	
	public void remove(Tema tema) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from tema where id=? and id_asignatura=?");
			stmt.setInt(1, tema.getId());
			stmt.setInt(2, tema.getAsignatura().getId());
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
