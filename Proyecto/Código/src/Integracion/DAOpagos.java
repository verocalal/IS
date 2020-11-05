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

public class DAOpagos extends Conexion implements InterfaceDao {

	@Override
	public List<Pagos> listar() {
		List<Pagos> lista_pagos = null;
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("SELECT * FROM Pago");
			lista_pagos = new ArrayList<Pagos>();
			ResultSet rs = st.executeQuery();
			while(rs.next() ) {
				Pagos pg = new Pagos();
				pg.setIdAlumno(rs.getString("idAlumno"));
				pg.setIdPago(rs.getString("idPago"));
				pg.setIdProfesor(rs.getString("idProfesores"));
				pg.setIdClase(rs.getString("idClase"));
				pg.setPagoAlumno(Integer.parseInt(rs.getString("cantidad")));
				pg.setSueldoProfesor(Integer.parseInt(rs.getString("cantToProf")));
				if (!lista_pagos.contains(pg)) {
					lista_pagos.add(pg);
				}
			}
			rs.close();
			this.cerrar();
		}
		catch (Exception e) {
			e.getMessage();
		}
		return lista_pagos;
	}

	@Override
	public void eliminar(String id) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("DELETE FROM Pago WHERE idPago = ?");
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
			PreparedStatement st = this.con.prepareStatement("UPDATE Pago SET ? = ? WHERE ? = ?");
			st.setString(1, a_modificar);
			st.setString(2, valor);
			st.setString(3, "idPago");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aniadir(Pagos pg) {
		try {
			this.conectar();
			PreparedStatement st = this.con.prepareStatement("INSERT INTO Pago (idPago, idProfesores, idClase, idAlumno, cantidad, cantToProf) VALUES ( ?, ?, ?, ?, ?, ?)");
			st.setString(1, pg.getIdPago());
			st.setString(2, pg.getIdProfesor());
			st.setString(3, pg.getIdClase());
			st.setString(4, pg.getIdAlumno());
			st.setString(5, Integer.toString(pg.getPagoAlumno()));
			st.setString(6, Integer.toString(pg.getSueldoProfesor()));
			st.executeUpdate();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	

}
