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

public class DAOasignaturas extends Conexion implements InterfaceDao {

	@Override
	public List<Asignaturas> listar() {
		List<Asignaturas> lista_asignaturas = null;
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("SELECT * FROM Asignatura");
			lista_asignaturas = new ArrayList<Asignaturas>();
			ResultSet rs = st.executeQuery();
			while(rs.next() ) {
				Asignaturas as = new Asignaturas();
				as.setIdAsignatura(rs.getString("idAsignatura"));
				as.setNombre(rs.getString("Nombre"));
				if (!lista_asignaturas.contains(as)) {
					lista_asignaturas.add(as);
				}
			}
			rs.close();
			this.cerrar();
		}
		catch (Exception e) {
			e.getMessage();
		}
		return lista_asignaturas;
	}

	@Override
	public void eliminar(String id) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("DELETE FROM Asignatura WHERE idAsignatura = ?");
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
	public void modificar(String id, String a_modificar, String valor) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("UPDATE Asignatura SET ? = ? WHERE ? = ?");
			st.setString(1, a_modificar);
			st.setString(2, valor);
			st.setString(3, "idAsignatura");
			st.setString(4, id);
			st.executeUpdate();
		}
		catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void aniadir(Asignaturas as) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("INSERT INTO Asignatura (idAsignatura, Nombre) VALUES ( ?, ?)");
			st.setString(1, as.getIdAsignatura());
			st.setString(2, as.getNombre());
			st.executeUpdate();
		}
		catch (Exception e) {
			e.getMessage();
		}
	}
	
	public List<Profesor> profesor_asignatura(String id) {
		List<Profesor> lista_profesores = null;
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("SELECT Profesor.idProfesor, Profesor.Nombre, Profesor.Apellido, Profesor.DNI, Profesor.Inicio_Jornada, Profesor.Final_Jornada, Profesor.NumSS, Profesor.Sueldo, Profesor.Pr_Contrasenia, Profesor.Num_cuenta "
					+ "FROM Asignatura JOIN asignatura_has_profesor ON Asignatura.idAsignatura = asignatura_has_profesor.Asignatura_idAsignatura "
					+ "JOIN Profesor ON asignatura_has_profesor.Profesor_idProfesor = Profesor.idProfesor WHERE Asignatura_idAsignatura = ?");
			st.setString(1, id);
			lista_profesores = new ArrayList<Profesor>();
			ResultSet rs = st.executeQuery();
			while(rs.next() ) {
				Profesor pr = new Profesor();
				pr.set_id(rs.getString("idProfesor"));
				pr.set_nombre(rs.getString("Nombre"));
				pr.set_apellidos(rs.getString("Apellido"));
				pr.set_dni(rs.getString("DNI"));
				pr.set_ss(Integer.parseInt(rs.getString("NumSS")));
				pr.set_cuentaBancaria(Integer.parseInt(rs.getString("Num_cuenta")));
				pr.setInicio_Jornada(rs.getString("Inicio_Jornada"));
				pr.setFinal_Jornada(rs.getString("Final_Jornada"));
				pr.setSueldo(Integer.parseInt(rs.getString("Sueldo")));
				pr.setPr_Contrasenia(rs.getString("Pr_Contrasenia"));
				if (!lista_profesores.contains(pr)) {
					lista_profesores.add(pr);
				}
			}
			rs.close();
			this.cerrar();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista_profesores;
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
