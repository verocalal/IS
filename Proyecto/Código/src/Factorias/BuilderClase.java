package Factorias;

import Negocio.Clase;

public class BuilderClase extends Builder<Clase>
{

	public BuilderClase() 
	{
		super("Clase");
	}

	@Override
	public Clase createTheInstance(String[] info) 
	{
		try
		{
			String profesor = info[1];
			String alumno = info[2];
			String asignatura = info[3];
			String id_pago = info[4];
			
			Clase a = new Clase(profesor, alumno, asignatura, null, id_pago);
			return a;
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException(e.getMessage()); 
		}
	}

}
