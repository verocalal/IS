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

public class DAOhorario extends Conexion implements InterfaceDao{

	@Override
	public List<Horario> listar() {
		List<Horario> lista_horario = null;
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("SELECT * FROM Horario");
			lista_horario = new ArrayList<Horario>();
			ResultSet rs = st.executeQuery();
			while(rs.next() ) {
				Horario hr = new Horario();
				hr.setFecha(rs.getString("fecha"));
				hr.setHora(rs.getString("hora"));
				hr.setId_Clase("Clase_idClase");
				hr.setId_Horario(rs.getString("idHorario"));
				if (!lista_horario.contains(hr)) {
					lista_horario.add(hr);
				}
			}
			rs.close();
			this.cerrar();
		}
		catch (Exception e) {
			e.getMessage();
		}
		return lista_horario;
	}

	@Override
	public void eliminar(String id) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("DELETE FROM Horario WHERE idHorario = ?");
			st.setString(1, id);
			st.executeUpdate();
		}
		catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void aniadir(Alumno al) {
		// TODO Auto-generated method stub
		
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
	public void modificar(String id, String a_modificar, String valor) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("UPDATE Horario SET ? = ? WHERE ? = ?");
			st.setString(1, a_modificar);
			st.setString(2, valor);
			st.setString(3, "idHorario");
			st.setString(4, id);
			st.executeUpdate();
		}
		catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void aniadir(Clase cl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aniadir(Horario hr) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("INSERT INTO Horario (idHorario, fecha, hora, Clase_idClase) VALUES ( ?, ?, ?, ?)");
			st.setString(1, hr.getId_Horario());
			st.setString(2, hr.getFecha());
			st.setString(3, hr.getHora());
			st.setString(4, hr.getId_Clase());
			st.executeUpdate();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void aniadir(Pagos pg) {
		// TODO Auto-generated method stub
		
	}

	
}
