package Factorias;

import Negocio.Alumno;

public class BuilderAlumno extends Builder<Alumno>
{

	public BuilderAlumno() 
	{
		super("Alumno");
	}

	@Override
	public Alumno createTheInstance(String[] info) 
	{
		try
		{
			String dni = info[1];
			String password = info[2];
			String nombre = info[3];
			String apellido = info[4];
			int CC = Integer.parseInt(info[5]);
			
			Alumno a = new Alumno(dni, password, nombre, apellido, CC);
			return a;
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException(e.getMessage()); 
		}
	}

}
