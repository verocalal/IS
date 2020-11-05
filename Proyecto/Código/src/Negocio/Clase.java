package Negocio;

public class Clase 
{

	private String id_Clase;
	private String id_Profesor;
	private String id_Alumno;
	private String id_Asignatura;
	private String id_horario;
	private String id_Pago;
	
	
	public Clase(String profesor, String alumno, String asignatura, String horario, String pago)
	{
		this.id_Profesor = profesor;
		this.id_Alumno = alumno;
		this.id_Asignatura = asignatura;
		this.id_horario = horario;
		this.id_Pago = pago;
	}
	
	public Clase() {}

	public String generarId(String numSerie)
	{
		return "Clase_" + id_Alumno.charAt(6) + id_Profesor.charAt(6) + numSerie;
	}
	
	public String getId_Clase() {
		return id_Clase;
	}


	public void setId_Clase(String id_Clase) {
		this.id_Clase = id_Clase;
	}


	public String getId_Profesor() {
		return id_Profesor;
	}


	public void setId_Profesor(String id_Profesor) {
		this.id_Profesor = id_Profesor;
	}


	public String getId_Alumno() {
		return id_Alumno;
	}


	public void setId_Alumno(String id_Alumno) {
		this.id_Alumno = id_Alumno;
	}


	public String getId_Asignatura() {
		return id_Asignatura;
	}


	public void setId_Asignatura(String id_Asignatura) {
		this.id_Asignatura = id_Asignatura;
	}

	public String getId_horario() {
		return id_horario;
	}

	public void setId_horario(String id_horario) {
		this.id_horario = id_horario;
	}

	public String getId_Pago() {
		return id_Pago;
	}

	public void setId_Pago(String id_Pago) {
		this.id_Pago = id_Pago;
	}
	
	
}
