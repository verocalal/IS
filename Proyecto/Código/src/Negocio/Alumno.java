package Negocio;

public class Alumno extends Persona
{
	private int _numTarjeta;
	private String Al_Contrasenia;

	public Alumno(String DNI, String contrasenia, String Nombre, String Apellidos, int Tarjeta) 
	{
		super(DNI, Nombre, Apellidos);
		this._numTarjeta = Tarjeta;
		this.Al_Contrasenia = contrasenia;
	}
	
	public Alumno() {}

	@Override
	public String mostrar() 
	{
		return DNI +";" +Nombre + ";" + Apellido ; 
	}



	public int get_numTarjeta() {
		return _numTarjeta;
	}



	public void set_numTarjeta(int n) {
		this._numTarjeta = n;
	}
	
	public String getAl_Contrasenia() 
	{
		return Al_Contrasenia;
	}
	
	public void setAl_Contrasenia(String al_Contrasenia) {
		Al_Contrasenia = al_Contrasenia;
	}


	public String generarId(String numSerie) 
	{
		return "Alumn_" + Nombre.charAt(0) + Apellido.charAt(0) + numSerie;
	}

}
