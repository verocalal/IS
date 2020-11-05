package Presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Controller.Controller;
import Negocio.Asignaturas;
import Negocio.Clase;
import Negocio.Horario;
import Negocio.Pagos;

public class PagosTableModel extends AbstractTableModel implements Observer
{
	private String[] Alumno = { "Pagado por la clase", "Profesor", "Ingresos Profesor" };
	private String[] Profesor = { "Ingresos Profesor", "Alumno", "Pagado por la clase"};
	
	private String _idPersona;
	private boolean _student;
	private List<Pagos> _pagos;
	private Controller _ctrl;
	
	public PagosTableModel(Controller ctrl, String ID,  boolean student)
	{
		this._ctrl = ctrl;
		_ctrl.addObserver(this);
		this._student = student;
		this._idPersona = ID;
		this._pagos = new ArrayList<Pagos>();
		_ctrl.cargarPagos(_idPersona, _student);
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
		return _pagos.size(); 
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		
		 Pagos p = _pagos.get(rowIndex);

		if (columnIndex == 0)
		{
			if(_student)
			{
				return p.getPagoAlumno();
			}
			else
			{
				return p.getSueldoProfesor();
			}
		}
		if (columnIndex == 1)
		{
			if(_student)
			{
				return p.getIdProfesor();
			}
			else
			{
				return p.getIdAlumno();
			}
		}
		if (columnIndex == 2)
		{
			if(_student)
			{
				return p.getSueldoProfesor();
			}
			else
			{
				return p.getPagoAlumno();
			}
		}
		
		return null;
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
	public void onAddClase(Clase clases, Horario h) {}

	@Override
	public void onHorario(List<Clase> clases, List<Horario> horarios) {}

	@Override
	public void onCargarAlumno(Negocio.Alumno a) {}

	@Override
	public void onCargarProfesor(Negocio.Profesor p) {}

	@Override
	public void onReservar() {}

	@Override
	public void onCerrarMostrar() {}

	@Override
	public void onCerrarHorario()	{}

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
	public void onPagos(List<Pagos> pagos) 
	{
		this._pagos = pagos;
	}

	@Override
	public void onCerrarPagos() 
	{
		_ctrl.removeObserver(this);
	}

	@Override
	public void onAddPago(Pagos p) 
	{
		if((p.getIdAlumno() == _idPersona)  || (p.getIdProfesor() == _idPersona) )
		{
			_pagos.add(p);
			fireTableStructureChanged();
		}	
		
	}
	
}