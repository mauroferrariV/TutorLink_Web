package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.*;

public class DataFacultad {
	
	public LinkedList<Facultad> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Facultad> facultades= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from facultades");
			if(rs!=null) {
				while(rs.next()) {
					Facultad f=new Facultad();
					f.setId(rs.getInt("id"));
					f.setNombre(rs.getString("nombre"));
					f.setCiudad(rs.getString("ciudad"));
					f.setPais(rs.getString("pais"));
					facultades.add(f);
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
		
		
		return facultades;
	}
	
	public Facultad getById(Facultad facultadToSearch) {
		Facultad f=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from facultades where id=?"
					);
			stmt.setInt(1, facultadToSearch.getId());
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
	
	
	public Facultad getByNombre(Facultad facultadToSearch) {
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
	
	
	public void add(Facultad facultad) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into facultades(nombre, ciudad, pais) values(?, ?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, facultad.getNombre());
			stmt.setString(2, facultad.getCiudad());
			stmt.setString(3, facultad.getPais());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	facultad.setId(keyResultSet.getInt(1));
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
	
	public void update(Facultad facultad) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update facultades set nombre=? , ciudad=?, pais=? where id=?");
			stmt.setString(1, facultad.getNombre());
			stmt.setString(2, facultad.getCiudad());
			stmt.setString(3, facultad.getPais());
			stmt.setInt(4, facultad.getId());
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
	
	public void remove(Facultad facultad) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from facultades where id=?");
			stmt.setInt(1, facultad.getId());
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
