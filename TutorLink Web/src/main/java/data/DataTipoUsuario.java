package data;

import entities.*;
import java.sql.*;
import java.util.LinkedList;

public class DataTipoUsuario {
	
	public LinkedList<TipoUsuario> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<TipoUsuario> tipos= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from tipo_usuario");
			if(rs!=null) {
				while(rs.next()) {
					TipoUsuario t=new TipoUsuario();
					t.setId(rs.getInt("id"));
					t.setNombre(rs.getString("nombre"));
					tipos.add(t);
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
		
		
		return tipos;
	}
	
	public TipoUsuario getById(TipoUsuario typeToSearch) {
		TipoUsuario t=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from tipo_usuario where id=?"
					);
			stmt.setInt(1, typeToSearch.getId());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				t=new TipoUsuario();
				t.setId(rs.getInt("id"));
				t.setNombre(rs.getString("nombre"));
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
	
	public TipoUsuario getByDesc(TipoUsuario typeToSearch) {
		TipoUsuario t=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from tipo_usuario where nombre=?"
					);
			stmt.setString(1, typeToSearch.getNombre());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				t=new TipoUsuario();
				t.setId(rs.getInt("id"));
				t.setNombre(rs.getString("nombre"));
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

}
