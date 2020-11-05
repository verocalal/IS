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

public class DAOprofesores extends Conexion implements InterfaceDao {

	@Override
	public List<Profesor> listar() {
		List<Profesor> lista_alumnos = null;
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("SELECT * FROM Profesor");
			
			lista_alumnos = new ArrayList<Profesor>();
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
				if (!lista_alumnos.contains(pr)) {
					lista_alumnos.add(pr);
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
	public void eliminar(String id) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("DELETE FROM Profesor WHERE idProfesor = ?");
			st.setString(1, id);
			PreparedStatement std = this.con.prepareStatement("DELETE FROM asignatura_has_profesor WHERE Profesor_idProfesor = ?");
			std.setString(1, id);
			std.executeUpdate();
			st.executeUpdate();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void aniadir(Profesor pr) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("INSERT INTO profesor (idProfesor, Nombre, Apellido, DNI, Inicio_Jornada, Final_Jornada, NumSS, Sueldo, Pr_Contrasenia, Num_cuenta) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			st.setString(1, pr.get_id());
			st.setString(2, pr.get_nombre());
			st.setString(3, pr.get_apellidos());
			st.setString(4, pr.get_dni());
			st.setString(5, pr.getInicio_Jornada());
			st.setString(6, pr.getFinal_Jornada());
			st.setString(7, Integer.toString(pr.get_ss()));
			st.setString(8, Integer.toString(pr.getSueldo()));
			st.setString(9, pr.getPr_Contrasenia());
			st.setString(10, Integer.toString(pr.get_cuentaBancaria()));
			st.executeUpdate();
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void aniadir_asignaturas(String id, List<Asignaturas> l) {
		try {
			this.conectar();
			for (Asignaturas a : l) {
				PreparedStatement stp = this.con.prepareStatement("INSERT INTO asignatura_has_profesor (Asignatura_idAsignatura, Profesor_idProfesor) VALUES (?, ?)");
				stp.setString(1, a.getIdAsignatura());
				stp.setString(2, id);
				stp.executeUpdate();
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void aniadir_asignatura(String id, Asignaturas a) {
		try {
			this.conectar();
			PreparedStatement stp = this.con.prepareStatement("INSERT INTO asignatura_has_profesor (Asignatura_idAsignatura, Profesor_idProfesor) VALUES (?, ?)");
			stp.setString(1, a.getIdAsignatura());
			stp.setString(2, id);
			stp.executeUpdate();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void modificar(String id, String a_modificar, String valor) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("UPDATE Profesor SET " + a_modificar + " = ? WHERE idProfesor = ?");
			st.setString(1, valor);
			st.setString(2, id);
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
	
	public List<Asignaturas> asignaturas_profesor(String id) {
		List<Asignaturas> lista_asignaturas = null;
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("SELECT Asignatura.idAsignatura, Asignatura.Nombre "
					+ "FROM Asignatura JOIN asignatura_has_profesor ON Asignatura.idAsignatura = asignatura_has_profesor.Asignatura_idAsignatura "
					+ "JOIN Profesor ON asignatura_has_profesor.Profesor_idProfesor = Profesor.idProfesor WHERE Profesor.idProfesor = ?");
			st.setString(1, id);
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
