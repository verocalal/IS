package Presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Controller.Controller;
import Negocio.Asignaturas;
import Negocio.Clase;
import Negocio.Horario;
import Negocio.Pagos;

public class HorarioTableModel extends AbstractTableModel implements Observer
{
	private String[] Alumno = { "Asignatura", "Fecha", "Hora", "Profesor" };
	private String[] Profesor = { "Asignatura", "Fecha", "Hora", "Alumno" };
	
	private String _idPersona;
	private boolean _student;
	private List<Clase> _clases;
	private List<Horario> _horario;
	private Controller _ctrl;
	
	public HorarioTableModel(Controller ctrl, String ID,  boolean student)
	{
		this._ctrl = ctrl;
		_ctrl.addObserver(this);
		this._student = student;
		this._idPersona = ID;
		this._clases = new ArrayList<Clase>();
		this._horario = new ArrayList<Horario>();
		_ctrl.cargarHorario(_idPersona, _student);
	}
	
	@Override
	public int getColumnCount() 
	{
		if(_student)
		{
			return Alumno.length;
		}
		else
		{
			return Profesor.length;
		}
	}

	@Override
	public int getRowCount() 
	{
		return _clases.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		 Clase c = _clases.get(rowIndex);
		 Horario h = buscarHorario(c.getId_horario());

		if (columnIndex == 0)
		{
			return c.getId_Asignatura();
		}
		if (columnIndex == 1)
		{
			return h.getFecha();
		}
		if (columnIndex == 2)
			return h.getHora();
		if (columnIndex == 3)
		{
			if(_student)
			{
				return c.getId_Profesor();
			}
			else
			{
				return c.getId_Alumno();
			}
		}
		return null;
	}
	
	private Horario buscarHorario(String id)
	{
		int cont = 0;
		
		int maxAl = _horario.size();
		boolean encontrado = false;
		Horario h = null;
		while(cont < maxAl && !encontrado)
		{
			h = _horario.get(cont);
			if(h.getId_Horario() == id)
			{
				encontrado = true;
			}
			cont++;		
		}
		return h;
	}
	
	@Override
	public String getColumnName(int columnIndex) 
	{
		if(_student)
		{
			return Alumno[columnIndex];
		}
		else
		{
			return Profesor[columnIndex];
		}
	}

	@Override
	public void onMostrar(boolean _student, String datos) {}

	@Override
	public void onAddClase(Clase clase, Horario h) {
		
		if((clase.getId_Alumno() == _idPersona)  || (clase.getId_Profesor() == _idPersona) )
		{
			this._clases.add(clase);
			this._horario.add(h);
			fireTableStructureChanged();
		}	
		
	}

	@Override
	public void onHorario(List<Clase> clases, List<Horario> horario) 
	{
		this._clases = clases;
		this._horario = horario;
		fireTableStructureChanged();
	}

	@Override
	public void onCargarAlumno(Negocio.Alumno a) {}

	@Override
	public void onCargarProfesor(Negocio.Profesor p) {}

	@Override
	public void onReservar() {}

	@Override
	public void onCerrarMostrar() {}

	@Override
	public void onCerrarHorario() 
	{
		_ctrl.removeObserver(this);
	}

	@Override
	public void onMain(boolean _student, String id) {}

	@Override
	public void onNuevaAsignatura(List<Asignaturas> asig) {}

	@Override
	public void onAniadirAlumno(Negocio.Alumno a) {}

	@Override
	public void onModificarAlumno(Negocio.Alumno a) {}

	@Override
	public void onModificarProfesor(Negocio.Profesor p, List<Asignaturas> asig) {}

	@Override
	public void onCargarAsignaturas(String[] asig) {}

	@Override
	public void onAsignaturaProfesor(Asignaturas a) {}

	@Override
	public void onAniadirProfesor(Negocio.Profesor p, List<Asignaturas> asig) {}

	@Override
	public void onPagos(List<Pagos> pagos) {}

	@Override
	public void onCerrarPagos() {}

	@Override
	public void onAddPago(Pagos p) {}
	
}
