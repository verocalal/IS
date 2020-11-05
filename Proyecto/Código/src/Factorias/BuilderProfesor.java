package Factorias;

import Negocio.Profesor;

public class BuilderProfesor extends Builder<Profesor>
{

	public BuilderProfesor() 
	{
		super("Profesor");
	}

	@Override
	public Profesor createTheInstance(String[] info)
	{
		try
		{
			String dni = info[1];
			String password = info[2];
			String nombre = info[3];
			String apellido = info[4];
			int SS = Integer.parseInt(info[5]);
			int CC = Integer.parseInt(info[6]);
			String iniJornada = info[7];
			String finJornada = info[8];
			
			Profesor p = new Profesor(dni, password, nombre, apellido, SS, CC, iniJornada, finJornada);
			return p;
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
