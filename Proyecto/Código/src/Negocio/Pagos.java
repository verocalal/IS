package Negocio;

public class Pagos 
{
	private String idPago;
	private String idAlumno;
	private String idProfesor;
	private String idClase;
	private int pagoAlumno;
	private int sueldoProfesor;
	
	public Pagos(String profe, String alum, int pago, int sueldo)
	{
		this.idAlumno = alum;
		this.idProfesor = profe;
		this.pagoAlumno = pago;
		this.sueldoProfesor = sueldo;
	}
	
	public Pagos() {}

	public String generarId(String numSer)
	{
		return "Pago_" + idAlumno.charAt(6) + idProfesor.charAt(6) + numSer;
	}
	
	public String getIdPago() {
		return idPago;
	}

	public void setIdPago(String idPago) {
		this.idPago = idPago;
	}

	public String getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(String idAlumno) {
		this.idAlumno = idAlumno;
	}

	public String getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(String idProfesor) {
		this.idProfesor = idProfesor;
	}

	public int getPagoAlumno() {
		return pagoAlumno;
	}

	public void setPagoAlumno(int pagoAlumno) {
		this.pagoAlumno = pagoAlumno;
	}

	public int getSueldoProfesor() {
		return sueldoProfesor;
	}

	public void setSueldoProfesor(int sueldoProfesor) {
		this.sueldoProfesor = sueldoProfesor;
	}

	public String getIdClase() {
		return idClase;
	}

	public void setIdClase(String idClase) {
		this.idClase = idClase;
	}
	
}
