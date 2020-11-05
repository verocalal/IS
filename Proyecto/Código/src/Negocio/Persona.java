package Negocio;

public abstract class Persona 
{
	protected String _id;
	protected String DNI;
	protected String Nombre;
	protected String Apellido;
	
	public Persona(String DNI, String Nombre, String Apellidos)
	{
		this.DNI = DNI;
		this.Nombre = Nombre;
		this.Apellido = Apellidos;
	}

	
	public Persona() {}
	
	public abstract String mostrar();

	public abstract String generarId(String numSerie);
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_dni() {
		return DNI;
	}

	public void set_dni(String _dni) {
		this.DNI = _dni;
	}

	public String get_nombre() {
		return Nombre;
	}

	public void set_nombre(String _nombre) {
		this.Nombre = _nombre;
	}

	public String get_apellidos() {
		return Apellido;
	}

	public void set_apellidos(String _apellidos) {
		this.Apellido = _apellidos;
	}
	
}
