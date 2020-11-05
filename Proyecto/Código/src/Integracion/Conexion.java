package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	protected Connection con = null;
	
	protected String password = "";
	protected String usuario = "root";
	protected String url = "jdbc:mysql://localhost:3306/DonPreguntonbbdd?user=" + usuario
			+ "&password=" + password;
	
	public void conectar() {

		try {
			con = DriverManager.getConnection(url);
			if (con != null) {
				System.out.println("Conectado");
			}
		} catch (SQLException e) {
			System.out.println("No se pudo conectar a la base de datos");
			e.printStackTrace();
		}
	}
	
	public void cerrar() throws SQLException {
		if (con != null) {
			if (!con.isClosed()) {
				con.close();
			}
		}
	}
}
