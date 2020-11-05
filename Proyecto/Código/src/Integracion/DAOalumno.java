package Integracion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Negocio.Alumno;
import Negocio.Asignaturas;
import Negocio.Clase;
import Negocio.Horario;
import Negocio.Pagos;
import Negocio.Profesor;

public class DAOalumno extends Conexion implements InterfaceDao {

	@Override
	public void eliminar(String id) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("DELETE FROM Alumno WHERE idAlumno = ?");
			st.setString(1, id);
			st.executeUpdate();
		}
		catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void aniadir(Alumno al) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("INSERT INTO alumno (idAlumno, Nombre, Apellido, DNI, tarjeta_bancaria, Al_Contrasenia) VALUES ( ?, ?, ?, ?, ?, ?)");
			st.setString(1, al.get_id());
			st.setString(2, al.get_nombre());
			st.setString(3, al.get_apellidos());
			st.setString(4, al.get_dni());
			st.setString(5, Integer.toString(al.get_numTarjeta()));
			st.setString(6, al.getAl_Contrasenia());
			st.executeUpdate();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public List<Alumno> listar() {
		List<Alumno> lista_alumnos = null;
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("SELECT * FROM Alumno");
			
			lista_alumnos = new ArrayList<Alumno>();
			ResultSet rs = st.executeQuery();
			while(rs.next() ) {
				Alumno al = new Alumno();
				al.set_id(rs.getString("idAlumno"));
				al.set_nombre(rs.getString("Nombre"));
				al.set_apellidos(rs.getString("Apellido"));
				al.set_dni(rs.getString("DNI"));
				al.set_numTarjeta(rs.getInt("tarjeta_bancaria"));
				al.setAl_Contrasenia(rs.getString("Al_Contrasenia"));
				if (!lista_alumnos.contains(al)) {
					lista_alumnos.add(al);
				}
			}
			rs.close();
			this.cerrar();
		}
		catch (Exception e) {
			e.getMessage();
		}
		return lista_alumnos;
	}

	@Override
	public void modificar(String id, String a_modificar, String valor) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("UPDATE Alumno SET " + a_modificar + " = ? WHERE idAlumno = ?");
			st.setString(1, valor);
			st.setString(2, id);
			st.executeUpdate();
		}
		catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void aniadir(Profesor pr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aniadir(Asignaturas as) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aniadir(Clase cl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aniadir(Horario hr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aniadir(Pagos pg) {
		// TODO Auto-generated method stub
		
	}

}
