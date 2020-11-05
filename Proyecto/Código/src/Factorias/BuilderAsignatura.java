package Factorias;

import Negocio.Asignaturas;

public class BuilderAsignatura extends Builder<Asignaturas>
{

	public BuilderAsignatura() 
	{
		super("Asignaturas");
	}

	@Override
	public Asignaturas createTheInstance(String[] info) 
	{
		
		try
		{
			String nombre = info[1];
			
			Asignaturas a = new Asignaturas(nombre);
			return a;
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException(e.getMessage()); 
		}
	}

}
