package Controller;

import java.util.ArrayList;
import java.util.List;

import Factorias.Factoria;
import Integracion.DAOalumno;
import Integracion.DAOasignaturas;
import Integracion.DAOclase;
import Integracion.DAOhorario;
import Integracion.DAOpagos;
import Integracion.DAOprofesores;
import Negocio.Academia;
import Negocio.Alumno;
import Negocio.Asignaturas;
import Negocio.Clase;
import Negocio.Horario;
import Negocio.Pagos;
import Negocio.Profesor;
import Presentacion.Observer;

public class Controller 
{
	private Academia _modelo;
	private Factoria<Alumno> factoriaAlumn;
	private Factoria<Profesor> factoriaProf;
	private Factoria<Asignaturas> factoriaAsig;
	private Factoria<Clase> factoriaClase;
	private Factoria<Pagos> factoriaPagos; 
	private Factoria<Horario> factoriaHorario;
	
	public Controller(Academia modelo, Factoria<Alumno> factoriaAlumn,
			Factoria<Profesor> factoriaProf, Factoria<Asignaturas> factoriaAsig,
			Factoria<Clase> factoriaClase, Factoria<Pagos> factoriaPagos, 
			Factoria<Horario> factoriaHorario)
	{
		this._modelo = modelo;
		this.factoriaAlumn = factoriaAlumn;
		this.factoriaProf = factoriaProf;
		this.factoriaAsig = factoriaAsig;
		this.factoriaClase = factoriaClase;
		this.factoriaPagos = factoriaPagos;
		this.factoriaHorario = factoriaHorario;
		cargarListasFromBd();
	}
	
	public void existeAlumno(String id, String password)
	{
		_modelo.logInAlumno(id, password);
	}
	
	public void existeProfesor(String id, String password)
	{
		_modelo.logInProfesor(id, password);
	}
	
	public void addAsignatura(String asig, List<Asignaturas> _asigNuevas)
	{
		//Comprueba si existe la Asign
		Asignaturas a = _modelo.existeAsignatura(asig);
		if(a == null)
		{
			//llamamos a la factoria
			//a =...
		}
		_asigNuevas.add(a);
		_modelo.addAsignatura(_asigNuevas);
	}
	
	private Pagos addPago(String idProfesor, String idAlumno, int pagoAl, int sueldoPrf)
	{
		String infoPago[] = new String[5];
		infoPago[0] = "Pago";
		infoPago[1] = idProfesor;
		infoPago[2] = idAlumno;
		infoPago[3] = Integer.toString(pagoAl);
		infoPago[4] = Integer.toString(sueldoPrf);
		Pagos p = factoriaPagos.createInstance(infoPago);
		
		if(p != null)
		{
			_modelo.generarIdPago(p);
		}
		return p;
	}
	
	public void reservarClase(String idAlumno, String profesor, Asignaturas asignatura, String fecha, String hora)
	{
		//buscamos al profesor
		List<Profesor> profesores = asignatura.getProfesores();
		String idProfesor = profesorDeClase(profesores, profesor);
		int pagoAlumno = 30;
		int sueldoProf = 20;
		Pagos pago = addPago(idProfesor, idAlumno, pagoAlumno, sueldoProf);
		//generar Pago 
		
		String[] info = new String[5];
		info[0] = "Clase";
		info[1] = idProfesor;
		info[2] = idAlumno;
		info[3] = asignatura.getIdAsignatura();
		info[4] = pago.getIdPago();
		Clase c = factoriaClase.createInstance(info);
		
		
		if(c!=null)
		{
			_modelo.generarIdClase(c);
			pago.setIdClase(c.getId_Clase());
			
			Horario h = addHorario(c, fecha, hora, c.getId_Clase());
			//generar Horario
			
			if(h != null)
			{
				//id
				_modelo.generarIdHorario(h);
				c.setId_horario(h.getId_Horario());
				c.setId_Pago(pago.getIdPago());
				
				_modelo.reservarClase(c, idAlumno, idProfesor, asignatura, fecha, hora);
				
				_modelo.registrarPago(pago);
				_modelo.registrarHorario(h);
				_modelo.cambioHorario(c);
				DAOpagos d = new DAOpagos();
				d.aniadir(pago);
				DAOclase dC = new DAOclase();
				dC.aniadir(c);
				DAOhorario dao = new DAOhorario();
				dao.aniadir(h);
			}
			
		}
		else
		{
			throw new IllegalArgumentException("Datos de la clase no validos");
		}
	}
	
	private Horario addHorario(Clase c, String fecha, String hora, String idClase)
	{
		String[] info = new String[4];
		info[0] = "Horario";
		info[1] = c.getId_Clase();
		info[2] = fecha;
		info[3] = hora;
		Horario h = factoriaHorario.createInstance(info);
		if (h != null)
		{
			h.setId_Clase(idClase);
			_modelo.generarIdHorario(h);
		}
		return h;
	}
	
	public String profesorDeClase(List<Profesor> profesores, String nombre)
	{
		int i = 0;
		int max = profesores.size();
		boolean encontrado = false;
		Profesor p = null;
		
		while(i < max && !encontrado)
		{
			p = profesores.get(i);
			if(p.get_nombre().equals(nombre))
			{
				encontrado = true;
			}
			i++;
		}
		if(p != null)
		{
			return p.get_id();
		}
		else
		{
			throw new IllegalArgumentException("Nombre de profesor incorrecto");
		}
		
	}
	
	public void mostrar(boolean _student, String id)
	{
		_modelo.mostrar(_student, id);
	}

	public void addAlumno(String dni, String Password, String name, String surname, int cC) 
	{
		String[] info = new String[6];
		info[0] = "Alumno";
		info[1] = dni;
		info[2] = Password;
		info[3] = name;
		info[4] = surname;
		info[5] = Integer.toString(cC);
		DAOalumno d = new DAOalumno();
		
		//factoria persona
		Alumno a = factoriaAlumn.createInstance(info);
		//aniadir

		if(a != null)
		{
			_modelo.addAlumno(a);
			d.aniadir(a);
		}
		else
		{
			throw new IllegalArgumentException("Datos del alumno no validos");
		}
		
		
	}

	public void addProfesor(String dni, String password, String n, String s, int ss, int c,
			String jornada, String asig) 
	{
		
		String[] info = new String[9];
		String[] jornadaTime = jornada.split("-");
		info[0] = "Profesor";
		info[1] = dni;
		info[2] = password;
		info[3] = n;
		info[4] = s;
		info[5] = Integer.toString(ss);
		info[6] = Integer.toString(c);
		info[7] = jornadaTime[0];
		info[8] = jornadaTime[1];
		
		//factoria persona
		Profesor p = factoriaProf.createInstance(info);
		DAOprofesores dao = new DAOprofesores();
		//aniadir
		if(p != null)
		{
			List<Asignaturas> listAsig = controlAsignaturas(asig);
			_modelo.addProfesor(p);
			_modelo.addAsigProfe(p, listAsig);
			dao.aniadir(p);
			dao.aniadir_asignaturas(p.get_id(), p.get_asignaturas());
		}
		else
		{
			throw new IllegalArgumentException("Datos del profesor no validos");
		}
		
		
	}
	
	private List<Asignaturas> controlAsignaturas(String asig)
	{
		String as[] = asig.split(",");
		String info[] = new String[2];
		List<Asignaturas> asignaturas = new ArrayList<Asignaturas>();
		Asignaturas a = null;
		for(String nombreAs : as)
		{
			info[0] = "Asignaturas";
			info[1] = nombreAs;
			a = factoriaAsig.createInstance(info);
			asignaturas.add(a);
		}
		
		return asignaturas;
	}
	
	public void cargarHorario(String _idPersona, boolean _student) 
	{
		_modelo.cargarHorario(_idPersona, _student);
	}
	
	public void cargarPagos(String _idPersona, boolean _student) 
	{
		_modelo.cargarPagos(_idPersona, _student);
	}
	
	public void cargarAlumno(String _idAlumno)
	{
		_modelo.cargarAlumno(_idAlumno);
	}
	
	public void cargarProfesor(String _idProfesor)
	{
		_modelo.cargarProfesor(_idProfesor);
	}

	public void modificarAlumno(String _idPersona,String dni,String nombre,String apellido,
			String password, int tarjeta)
	{
		_modelo.modificarAlumno(_idPersona, dni, nombre, apellido,password, tarjeta);
	}
	
	public void modificarProfesor(String _idPersona,String dni,String nombre,String apellido,
			String password, String jornada,int tarjeta, String asignaturas)
	{
		//generar lista de asignaturas sin Id
		List<Asignaturas> asig = controlAsignaturas(asignaturas);
		
		_modelo.modificarProfesor(_idPersona, dni, nombre, apellido, password,
				jornada, tarjeta, asig);
	}
	
	 public void buscarAsignatura(String asig)
	 {
		 _modelo.buscarAsignatura(asig);
	 }
	
	public void addObserver(Observer o)
	{
		 _modelo.addObserver(o);
	}
	
	public void cerrarMostrar()
	{
		_modelo.cerrarMostrar();
	}
	
	public void cerrarPagos()
	{
		_modelo.cerrarPagos();
	}
	
	public void cerrarHorario()
	{
		_modelo.cerrarHorario();
	}
	
	public void cargarAsignaturas()
	{
		_modelo.cargarAsignaturas();
	}
	
	public void removeObserver(Observer o)
	{
		_modelo.removeObserver(o);
	}
	
	public void cargarListasFromBd()
	{
		DAOprofesores pr = new DAOprofesores();
		DAOalumno al = new DAOalumno();
		DAOasignaturas as = new DAOasignaturas();
		DAOclase cl = new DAOclase();
		DAOhorario hr = new DAOhorario();
		DAOpagos pg = new DAOpagos();
		_modelo.setAlumnos(al.listar());
		_modelo.setAsignaturas(as.listar());
		_modelo.setClases(cl.listar());
		_modelo.setHorarios(hr.listar());
		_modelo.setPagos(pg.listar());
		_modelo.setProfesores(pr.listar());
		for (Asignaturas a : _modelo.getAsignaturas()) {
			a.setProfesores(as.profesor_asignatura(a.getIdAsignatura()));
		}
		for (Profesor p : _modelo.getProfesores()) {
			p.set_asignaturas(pr.asignaturas_profesor(p.get_id()));
		}
	}
	
	public void removeAlumnoToBd(String id)
	{
		DAOalumno dao = new DAOalumno();
		dao.eliminar(id);
	}
	
	public void removeProfesorToBd(String id)
	{
		DAOprofesores dao = new DAOprofesores();
		dao.eliminar(id);
	}
	
}
