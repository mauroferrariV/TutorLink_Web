package data;

import java.sql.*;

public class DbConnector {

	private static DbConnector instancia; //variable de clase o static
	
	//Estas variables despues estaran en un archivo de configuracion
	private String driver="com.mysql.cj.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="java";
	private String password="123";
	private String db="tutorlink";
	private int conectados=0;
	private Connection conn=null;
	
	//Patrón Singleton
	private DbConnector() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static DbConnector getInstancia() {
		if (instancia == null) {
			instancia = new DbConnector();
		}
		return instancia;
	}
	//
	public Connection getConn() {
		try {
			if(conn==null || conn.isClosed()) {
				System.out.println("Estableciendo nueva conexión con la base de datos...");
				conn=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db, user, password);
				conectados=0;
				if (conn != null && !conn.isClosed()) {
	                System.out.println("Conexión establecida exitosamente.");
				} else {
	                System.err.println("No se pudo establecer una conexión con la base de datos.");
	            }
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error al conectar con la base de datos: " + e.getMessage());
		}
		conectados++;
		return conn;
	}
	
	public void releaseConn() {
		conectados--;
		try {
			 // Verificar si conn no es null y cerrar sólo si ya no hay conexiones activas
			if (conectados<=0 && conn != null) {
				conn.close();
				conn = null; // Resetear conn a null después de cerrarla
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
