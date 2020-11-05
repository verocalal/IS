package Negocio;

public class Horario 
{
	private String id_Horario;
	private String id_Clase;
	private String fecha;
	private String hora;
	
	
	public Horario(String clase, String dia, String hms) 
	{
		this.id_Clase = clase;
		this.fecha = dia;
		this.hora = hms;
	}


	public Horario() {}


	public String getId_Clase() 
	{
		return id_Clase;
	}


	public void setId_Clase(String id_Clase) 
	{
		this.id_Clase = id_Clase;
	}


	public String getFecha() 
	{
		return fecha;
	}


	public void setFecha(String fecha) 
	{
		this.fecha = fecha;
	}


	public String getHora() 
	{
		return hora;
	}


	public void setHora(String hora) 
	{
		this.hora = hora;
	}


	public String getId_Horario() {
		return id_Horario;
	}


	public void setId_Horario(String id_Horario) {
		this.id_Horario = id_Horario;
	}


	public String generarId(String numSerie) {
		return "Hor_"  + Character.toString(id_Clase.charAt(6)) + Character.toString(id_Clase.charAt(7)) +numSerie;
	}
	
}
