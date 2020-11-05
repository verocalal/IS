package Presentacion;

import java.util.List;

import Negocio.Alumno;
import Negocio.Asignaturas;
import Negocio.Clase;
import Negocio.Horario;
import Negocio.Pagos;
import Negocio.Profesor;

public interface Observer 
{
	public void onMain(boolean _student , String id);
	public void onMostrar(boolean _student, String datos);
	public void onAniadirAlumno(Alumno a);
	public void onAniadirProfesor(Profesor p, List<Asignaturas> asig);
	public void onAddClase(Clase clases, Horario h);
	public void onHorario(List<Clase> clases, List<Horario> horarios);
	public void onPagos(List<Pagos> pagos);
	public void onCargarAlumno(Alumno a);
	public void onCargarProfesor(Profesor p);
	public void onModificarAlumno(Alumno a);
	public void onModificarProfesor(Profesor p, List<Asignaturas> asig);
	public void onReservar();
	public void onCerrarHorario();
	public void onCerrarMostrar();
	public void onCerrarPagos();
	public void onAddPago(Pagos p);
	public void onNuevaAsignatura(List<Asignaturas> asig);
	public void onCargarAsignaturas(String[] asig);
	public void onAsignaturaProfesor(Asignaturas a);
}
