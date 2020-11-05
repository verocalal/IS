package Negocio;

import java.util.ArrayList;
import java.util.List;

public class Profesor extends Persona
{
	private List<Asignaturas> _asignaturas; 
	private int _ss;
	private int _cuentaBancaria;
	private String Pr_Contrasenia;
	private String Inicio_Jornada;
	private String Final_Jornada;
	private int Sueldo = 0;

	public Profesor(String dni, String contrasenia, String nombre, String apellidos, int SS, int banco, 
			String inicio, String fin)
	{
		super(dni, nombre, apellidos);
		this._ss = SS;
		this._cuentaBancaria = banco;
		this.Pr_Contrasenia = contrasenia;
		this.Inicio_Jornada = inicio;
		this.Final_Jornada = fin;
		this._asignaturas = new ArrayList<Asignaturas>();
	}
	
	public Profesor () {}

	public boolean imparteAsignatura(Asignaturas a)
	{
		return _asignaturas.contains(a);
	}
	
	public String toStringLista()
	{
		String lista= "";
		Asignaturas a = null;
		int max = _asignaturas.size();
		for(int i = 0; i < max; i++)
		{
			a = _asignaturas.get(i);
			if(i < max -1)
			{
				lista += a.getNombre() + ", ";
			}
			else
			{
				lista += a.getNombre();
			}
		}
		return lista;
	}

	@Override
	public String mostrar()
	{
		return DNI +";" +Nombre + ";" + Apellido + ";" + mostrar_asignaturas()
		+ ";" + Inicio_Jornada + "-" + Final_Jornada; 
	}
	
	public String mostrar_asignaturas() {
		String r = "";
		for (Asignaturas a: _asignaturas) {
			r += (a.getNombre() + " ");
		}
		return r;
	}

	public List<Asignaturas> get_asignaturas() {
		return _asignaturas;
	}

	public void set_asignaturas(List<Asignaturas> _asignaturas) {
		this._asignaturas = _asignaturas;
	}

	public int get_ss() {
		return _ss;
	}

	public void set_ss(int _ss) {
		this._ss = _ss;
	}

	public int get_cuentaBancaria() {
		return _cuentaBancaria;
	}

	public void set_cuentaBancaria(int _cuentaBancaria) {
		this._cuentaBancaria = _cuentaBancaria;
	}

	public String getPr_Contrasenia() {
		return Pr_Contrasenia;
	}

	public void setPr_Contrasenia(String pr_Contrasenia) {
		Pr_Contrasenia = pr_Contrasenia;
	}

	public String getInicio_Jornada() {
		return Inicio_Jornada;
	}

	public void setInicio_Jornada(String inicio_Jornada) {
		Inicio_Jornada = inicio_Jornada;
	}

	public String getFinal_Jornada() {
		return Final_Jornada;
	}

	public void setFinal_Jornada(String final_Jornada) {
		Final_Jornada = final_Jornada;
	}

	public int getSueldo() {
		return Sueldo;
	}

	public void setSueldo(int sueldo) {
		Sueldo = sueldo;
	}

	public String generarId(String numSerie) 
	{
		return "Profe_" + Nombre.charAt(0) + Apellido.charAt(0) + numSerie;
	}
	
	public void addAsignatura(Asignaturas a)
	{
		this._asignaturas.add(a);
	}

}
