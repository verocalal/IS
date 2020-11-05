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

public class DAOclase extends Conexion implements InterfaceDao {

	@Override
	public  List<Clase> listar() {
		List<Clase> lista_clases = null;
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("SELECT * FROM Clase");
			lista_clases = new ArrayList<Clase>();
			ResultSet rs = st.executeQuery();
			while(rs.next() ) {
				Clase as = new Clase();
				as.setId_Clase(rs.getString("idClase"));
				as.setId_Alumno(rs.getString("idAlumno"));
				as.setId_Asignatura(rs.getString("idAsignatura"));
				as.setId_Profesor(rs.getString("idProfesor"));
				as.setId_Pago(rs.getString("Pago_idPago"));
				as.setId_horario(rs.getString("Horario"));
				if (!lista_clases.contains(as)) {
					lista_clases.add(as);
				}
			}
			rs.close();
			this.cerrar();
		}
		catch (Exception e) {
			e.getMessage();
		}
		return lista_clases;
	}

	@Override
	public void eliminar(String id) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("DELETE FROM Clase WHERE idClase = ?");
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
			PreparedStatement st = this.con.prepareStatement("UPDATE Clase SET ? = ? WHERE ? = ?");
			st.setString(1, a_modificar);
			st.setString(2, valor);
			st.setString(3, "idClase");
			st.setString(4, id);
			st.executeUpdate();
		}
		catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void aniadir(Clase cl) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("INSERT INTO Clase (idClase, idProfesor, idAlumno, idAsignatura, Horario, Pago_idPago) VALUES ( ?, ?, ?, ?, ?, ?)");
			st.setString(1, cl.getId_Clase());
			st.setString(2, cl.getId_Profesor());
			st.setString(3, cl.getId_Alumno());
			st.setString(4, cl.getId_Asignatura());
			st.setString(5, cl.getId_horario());
			st.setString(6, cl.getId_Pago());
			st.executeUpdate();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
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
