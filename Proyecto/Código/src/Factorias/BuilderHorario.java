package Factorias;

import Negocio.Horario;

public class BuilderHorario extends Builder<Horario>
{

	public BuilderHorario() 
	{
		super("Horario");
	}

	@Override
	public Horario createTheInstance(String[] info) 
	{
		try
		{
			String clase = info[1];
			String dia = info[2];
			String hora = info[3];
			
			Horario h = new Horario(clase, dia, hora);
			return h;
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException(e.getMessage()); 
		}
	}

}
