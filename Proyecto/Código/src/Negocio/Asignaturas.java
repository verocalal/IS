package Negocio;

import java.util.ArrayList;
import java.util.List;

public class Asignaturas 
{
	private String idAsignatura;
	private String Nombre;

	private List<Profesor> profesores;
	
	public Asignaturas(String n)
	{
		this.Nombre = n;
		this.profesores = new ArrayList<Profesor>();
	}
	
	public Asignaturas() {}
	
	public String generarId(String numSerie)
	{
		String id = "Asig_" + String.valueOf(Nombre.charAt(0)) + numSerie;
		return id;
	}
	public String getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(String idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	public List<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}
	
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) 
	{
		Nombre = nombre;
	}
	
	public void addProfesor(Profesor p)
	{
		if (profesores == null) {
			profesores = new ArrayList<Profesor>();
		}
		this.profesores.add(p);
	}
	
}
