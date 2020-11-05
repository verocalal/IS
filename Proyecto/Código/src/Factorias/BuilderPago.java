package Factorias;

import Negocio.Pagos;

public class BuilderPago extends Builder<Pagos>
{

	public BuilderPago() 
	{
		super("Pago");
	}

	@Override
	public Pagos createTheInstance(String[] info)
	{
		
		try
		{
			String profesor = info[1];
			String alumno = info[2];
			int pagoAl = Integer.parseInt(info[3]);
			int sueldoPrf = Integer.parseInt(info[4]);
			
			Pagos p = new Pagos(profesor, alumno, pagoAl, sueldoPrf);
			return p;
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException(e.getMessage()); 
		}
	}

}
