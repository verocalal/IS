package Integracion;

import java.util.List;

import Negocio.Alumno;
import Negocio.Asignaturas;
import Negocio.Clase;
import Negocio.Horario;
import Negocio.Pagos;
import Negocio.Profesor;

public interface InterfaceDao {
	
	public <T> List<T> listar();
	public void eliminar(String id);
	public void aniadir(Alumno al);
	public void aniadir(Profesor pr);
	public void aniadir(Asignaturas as);
	public void aniadir(Clase cl);
	public void aniadir(Horario hr);
	public void aniadir(Pagos pg);
	public void modificar(String id, String a_modificar, String valor);
	
}
