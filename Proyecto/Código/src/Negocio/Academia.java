package Negocio;

import java.util.ArrayList;
import java.util.List;

import Integracion.DAOalumno;
import Integracion.DAOasignaturas;
import Integracion.DAOprofesores;
import Presentacion.Observer;


public class Academia 
{
	private List<Alumno> alumnos;
	private List<Profesor> profesores;
	private List<Clase> clases;
	private List<Asignaturas> asignaturas;
	private List<Horario> horarios;
	private List<Pagos> pagos;
	
	private List<Observer> observadores;
	
	
	public Academia()
	{
		this.alumnos = new ArrayList<Alumno>();
		this.profesores = new ArrayList<Profesor>();
		this.clases = new ArrayList<Clase>();
		this.asignaturas = new ArrayList<Asignaturas>();
		this.observadores = new ArrayList<Observer>();
	}
	
	public void logInAlumno(String id, String password)
	{
		int cont = 0;
		int maxAl = alumnos.size();
		boolean encontrado = false;
		Alumno a= null;
		
		if(maxAl == 0)
		{
			throw new IllegalArgumentException("No existe el alumno");
		}
		
		while(!encontrado && cont < maxAl)
		{
			a = alumnos.get(cont);
			if(a.get_id().equals(id))
			{
				encontrado = true;
			}
			cont++;
		}
		
		if(cont > maxAl)
		{
			throw new IllegalArgumentException("No existe el alumno");
		}
		if(!a.getAl_Contrasenia().equals(password))
		{
			throw new IllegalArgumentException("Password incorrecto");
		}
		
		for(Observer o : observadores)
		{
			o.onMain(true, id);
		}
		
	}
	
	public void logInProfesor(String id, String password)
	{
		int cont = 0;
		int maxPrf = profesores.size();
		boolean encontrado = false;
		Profesor p = null;
		
		if(maxPrf == 0)
		{
			throw new IllegalArgumentException("No existe el profesor");
		}
		
		while(!encontrado && cont < maxPrf)
		{
			p = profesores.get(cont);
			if(p.get_id().equals(id))
			{
				encontrado = true;
			}
			cont++;
		}
		
		if(cont > maxPrf)
		{
			throw new IllegalArgumentException("No existe el alumno");
		}
		if(!p.getPr_Contrasenia().equals(password))
		{
			throw new IllegalArgumentException("Password incorrecto");
		}
		
		for(Observer o : observadores)
		{
			o.onMain(false, id);
		}
	}
	
	public void addAsigProfe(Profesor p, List<Asignaturas> asig)
	{
		List<Asignaturas> aux = new ArrayList<Asignaturas>();
		for(Asignaturas a : asig)
		{
			if(!existeAsignatura(a))
			{
				String numSerie = Integer.toString(asignaturas.size());
				String id = a.generarId(numSerie);
				while(existeIdAsig(id))
				{
					numSerie = Integer.toString(asignaturas.size() + 1);
					id = a.generarId(numSerie);
					
				}
				a.setIdAsignatura(id);
				asignaturas.add(a);
				DAOasignaturas dao = new DAOasignaturas();
				dao.aniadir(a);
			}
			else
			{
				a = existeAsignatura(a.getNombre());
			}
			p.addAsignatura(a);
			a.addProfesor(p);
			aux.add(a);
		}
		asig = aux;
		//Observer
		for(Observer o: observadores)
		{
			o.onAniadirProfesor(p, aux);
		}
	}
	
	public boolean existeAsignatura(Asignaturas a)
	{
		int i = 0;
		int max = asignaturas.size();
		boolean existe = false;
		Asignaturas aux = null;
		
		while(i < max && !existe)
		{
			aux = asignaturas.get(i);
			if(aux.getNombre().equals(a.getNombre()))
			{
				existe = true;
			}
			i++;
		}
		
		return existe;
	}
	
	public void generarIdPago(Pagos p)
	{
		String numSerie = Integer.toString(pagos.size());
		String id = p.generarId(numSerie);
		while(existeidPago(id))
		{
			numSerie = Integer.toString(pagos.size() + 1);
			id = p.generarId(numSerie);
		}
		
		p.setIdPago(id);
	}
	
	public void reservarClase(Clase c, String idAlumno, String profesor, Asignaturas asg, String fecha, String hora)
	{
		//Buscamos al alumno y al profesor
		Alumno al = existeAlumno(idAlumno);
		Profesor p = existeProfesor(profesor);
		
		if(al == null || p == null || asg == null)
		{
			throw new IllegalArgumentException("Datos incorrector: Profesor no encontrados");
		}
		
		/*//comprobamos que el profesor imparte esa asignatura
		if(!puedeReservarse(c.getId_Alumno(), c.getId_Profesor(), fecha, hora))
		{
			throw new IllegalArgumentException("Hora ya reservada");
		}
		*/
		registrarClase(c);
		
	}
	
	private Alumno existeAlumno(String idAlumno)
	{
		int cont = 0;
		int maxAl = alumnos.size();
		Alumno a = null;
		boolean encontrado = false;
		
		while(!encontrado && cont < maxAl)
		{
			a = alumnos.get(cont);
			if(a.get_id().equals(idAlumno))
			{
				encontrado = true;
			}
			cont++;
		}
		
		return a;
	}

	private Profesor existeProfesor(String profesor)
	{
		int cont = 0;
		int maxPrf = profesores.size();

		Profesor p = null;
		boolean encontrado = false;
		
		while(!encontrado && cont < maxPrf)
		{
			p = profesores.get(cont);
			if(p.get_id().equals(profesor))
			{
				encontrado = true;
			}
			cont++;
		}
		
		return p;
	}

	private boolean existeHorario(Horario h)
	{
		int cont = 0;
		int maxAl = horarios.size();
		boolean encontrado = false;
		Horario aux = null;
		while(cont < maxAl && !encontrado)
		{
			aux = horarios.get(cont);
			if(aux.getId_Clase().equals(h.getId_Clase()))
			{
				encontrado = true;
			}
			cont++;
		}
		return encontrado;
	}
	
	private boolean buenHorario (String idHorario, String fecha, String hora)
	{
		boolean encontrado = true;
		int cont = 0;
		int maxHorario = horarios.size();
		Horario h = null;
		
		while(!encontrado && cont < maxHorario)
		{
			h = horarios.get(cont);
			if(h.getId_Horario().equals(idHorario))
			{
				encontrado = true;
			}
			cont++;
		}
		
		if(h.getFecha().equals(fecha))
		{
			String horaPosible[] = hora.split(":");
			String horaHorario[] = h.getHora().split(":");
			int horas = Integer.parseInt(horaPosible[0]);
			int min = Integer.parseInt(horaPosible[1]);
			int horasH = Integer.parseInt(horaHorario[0]);
			int minH = Integer.parseInt(horaHorario[1]);
			
			if((horas >= horasH +1 && min >= minH) || (horas <= horasH -1 && min<=minH))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		else
		{
			return true;
		}
	
	}
	
	public boolean puedeReservarse(String al, String pr, String fecha, String hora)
	{
		boolean ok = true;
		int cont = 0;
		int maxClases = clases.size();
		Clase c = null;
		while(cont < maxClases && ok)
		{
			c = clases.get(cont);
			if(c.getId_Alumno().equals(al) || c.getId_Profesor().equals(pr))
			{
				ok = buenHorario(c.getId_horario(), fecha, hora);
			}
			cont++;
		}
		return ok;
		
	}
	
	public void buscarAsignatura(String asig)
	{
		int i = 0;
		int maxAs = asignaturas.size();
		boolean encontrado = false;
		Asignaturas a = null;
		
		while(i < maxAs && !encontrado)
		{
			a = asignaturas.get(i);
			if(a.getNombre().equals(asig))
			{
				encontrado = true;
			}
			i++;
		}
		
		for(Observer o : observadores)
		{
			o.onAsignaturaProfesor(a);
		}
		
	}
	
	public void registrarClase(Clase c)
	{
			clases.add(c);
	}
	
	public void cambioHorario(Clase c)
	{
		//buscamos horario
		Horario h = buscarHorario(c.getId_horario());
		//observer
		for(Observer o : observadores)
		{
			o.onReservar();
			o.onAddClase(c, h);
		}
	}
	
	public Horario buscarHorario(String id)
	{
		int cont = 0;
		
		int maxAl = horarios.size();
		boolean encontrado = false;
		Horario h = null;
		while(cont < maxAl && !encontrado)
		{
			h = horarios.get(cont);
			if(h.getId_Horario().equals(id))
			{
				encontrado = true;
			}
			cont++;		
		}
		return h;
	}
	
	public void registrarHorario(Horario h)
	{
		if(!existeHorario(h))
		{
			horarios.add(h);
		}
	}
	
	public void registrarPago(Pagos p)
	{
		if(!pagos.contains(p))
		{
			pagos.add(p);
			
			for(Observer o : observadores)
			{
				o.onAddPago(p);
			}
			
		}
	}
	
	public void mostrar(boolean _student, String id)
	{
		boolean encontrado = false;
		int i = 0;
		String datos = null;
		
		if(_student)
		{
			Alumno a = null; 
			while(!encontrado && i < alumnos.size())
			{
				a = alumnos.get(i);
				if(a.get_id().equals(id))
				{
					encontrado = true;
				}
				i++;
				
			}
			datos = a.mostrar();
			
		}
		else
		{
			Profesor p = null;
			while(!encontrado && i < profesores.size())
			{
				p = profesores.get(i);
				if(p.get_id().equals(id))
				{
					encontrado = true;
				}
				i++;
			}
			datos = p.mostrar();
		}
		
		for(Observer o: observadores)
		{
			o.onMostrar(_student, datos);
		}
		
	}
	
	public void addAlumno(Alumno a)
	{
		//List
		if(!existePersona(true, a))
		{
			//Generar id;
			String numSerie = Integer.toString(alumnos.size());
			String id = a.generarId(numSerie);
			while(existeId(true, id))
			{
				numSerie = Integer.toString(alumnos.size() + 1);
				id = a.generarId(numSerie);
			}
			
			a.set_id(id);
			alumnos.add(a);
			
			//Observer
			for(Observer o: observadores)
			{
				o.onAniadirAlumno(a);
			}
		}
		else
		{
			throw new IllegalArgumentException("El alumno ya existe");
		}
		
	}
	
	public void addProfesor(Profesor p)
	{
		if(!existePersona(false, p))
		{
			//Generar id;
			String numSerie = Integer.toString(profesores.size());
			String id = p.generarId(numSerie);
			while(existeId(false, id))
			{
				numSerie = Integer.toString(profesores.size() + 1);
				id = p.generarId(numSerie);
			}
			
			p.set_id(id);
			profesores.add(p);
			
		}
		else
		{
			throw new IllegalArgumentException("El Profesor ya existe");
		}
		
	}

	private boolean existePersona(boolean _stundent, Persona per)
	{
		int cont = 0;
		if(_stundent)
		{
			int maxAl = alumnos.size();
			boolean encontrado = false;
			Alumno a = null;
			while(cont < maxAl && !encontrado)
			{
				a = alumnos.get(cont);
				if(a.get_dni().equals(per.get_dni()))
				{
					encontrado = true;
				}
				cont++;		
			}
			return encontrado;
		}
		else
		{
			int maxPrf = profesores.size();
			boolean encontrado = false;
			Profesor p = null;
			while(cont < maxPrf && !encontrado)
			{
				p = profesores.get(cont);
				if(p.get_dni().equals(per.get_dni()))
				{
					encontrado = true;
				}
				cont++;		
			}
			return encontrado;
		}
	}
	
	private boolean existeClase(Clase c)
	{
		int cont = 0;
		int maxAl = clases.size();
		boolean encontrado = false;
		Clase cl = null;
		while(cont < maxAl && !encontrado)
		{
			cl = clases.get(cont);
			if(cl.getId_Alumno().equals(c.getId_Alumno()) && cl.getId_Profesor().equals(c.getId_Profesor())
					&& cl.getId_Pago().equals(c.getId_Pago()))
			{
				encontrado = true;
			}
			cont++;
					
		}
		
		return encontrado;
		
	}
	
	private boolean existeIdAsig(String id)
	{
		int cont = 0;
		int maxAl = asignaturas.size();
		boolean encontrado = false;
		Asignaturas a = null;
		while(cont < maxAl && !encontrado)
		{
			a = asignaturas.get(cont);
			if(a.getIdAsignatura().equals(id))
			{
				encontrado = true;
			}
			cont++;		
		}
		return encontrado;
	}
	private boolean existeId(boolean _stundent, String id)
	{
		int cont = 0;
		if(_stundent)
		{
			int maxAl = alumnos.size();
			boolean encontrado = false;
			Alumno a = null;
			while(cont < maxAl && !encontrado)
			{
				a = alumnos.get(cont);
				if(a.get_id().equals(id))
				{
					encontrado = true;
				}
				cont++;		
			}
			return encontrado;
		}
		else
		{
			int maxPrf = profesores.size();
			boolean encontrado = false;
			Profesor p = null;
			while(cont < maxPrf && !encontrado)
			{
				p = profesores.get(cont);
				if(p.get_id().equals(id))
				{
					encontrado = true;
				}
				cont++;		
			}
			return encontrado;
		}
	}
	
	public void cargarHorario(String _idPersona, boolean _student) 
	{
		List<Clase> clasesPersona = new ArrayList<Clase>();
		
		if(_student)
		{
			for(Clase c : clases)
			{
				if(c.getId_Alumno().equals(_idPersona))
				{
					clasesPersona.add(c);
				}
			}
		}
		else
		{
			for(Clase c : clases)
			{
				if(c.getId_Profesor().equals(_idPersona))
				{
					clasesPersona.add(c);
				}
			}
		}
		
		for(Observer o: observadores)
		{
			o.onHorario(clasesPersona, horarios);
		}
		
	}
	
	public void cargarPagos(String _idPersona, boolean _student) 
	{
		List<Pagos> pagosPersona = new ArrayList<Pagos>();
		
		if(_student)
		{
			for(Pagos p : pagos)
			{
				if(p.getIdAlumno().equals(_idPersona))
				{
					pagosPersona.add(p);
				}
			}
		}
		else
		{
			for(Pagos p : pagos)
			{
				if(p.getIdProfesor().equals(_idPersona))
				{
					pagosPersona.add(p);
				}
			}
		}
		
		for(Observer o: observadores)
		{
			o.onPagos(pagosPersona);
		}
		
	}
	
	public void cargarAlumno(String _idAlumno)
	{
		boolean encontrado = false;
		int cont = 0, max = alumnos.size();
		Alumno a = null;
		//buscar alumno
		while(!encontrado && cont < max)
		{
			a = alumnos.get(cont);
			if(a.get_id().equals(_idAlumno))
			{
				encontrado = true;
			}
			cont++;
		}
		//cargar datos en GUI
		for(Observer o : observadores)
		{
			o.onCargarAlumno(a);
		}
	}

	public void cargarProfesor(String _idProfesor)
	{
		boolean encontrado = false;
		int cont = 0, max = profesores.size();
		Profesor p = null;
		//buscar alumno
		while(!encontrado && cont < max)
		{
			p = profesores.get(cont);
			if(p.get_id().equals(_idProfesor))
			{
				encontrado = true;
			}
			cont++;
		}
		//cargar datos en GUI
		for(Observer o : observadores)
		{
			o.onCargarProfesor(p);
		}
	}

	public void cargarAsignaturas()
	{
		String[] asig = new String[asignaturas.size()];
		int i = 0;
		for(Asignaturas a : asignaturas)
		{
			asig[i] = a.getNombre();
			i++;
		}
		
		for(Observer o : observadores)
		{
			o.onCargarAsignaturas(asig);
		}
	}
	
	public Asignaturas existeAsignatura(String asig)
	{
		int i = 0;
		int max = asignaturas.size();
		boolean existe = false;
		Asignaturas a = null;
		
		while(i < max && !existe)
		{
			a = asignaturas.get(i);
			if(a.getNombre().equals(asig))
			{
				existe = true;
			}
			i++;
		}
		
		return a;
	}
	
	public void addAsignatura(List<Asignaturas>_asigNuevas)
	{
		for(Observer o : observadores)
		{
			o.onNuevaAsignatura(_asigNuevas);
		}
	}
	
	public void modificarAlumno(String _idPersona,String dni,String nombre,
			String apellido,String password, int tarjeta)
	{
		//modificar
		Alumno a = existeAlumno(_idPersona);
		DAOalumno dao = new DAOalumno();
		if(!dni.equals(a.get_dni()))
		{
			dao.modificar(a.get_id(), "DNI", dni);
			a.set_dni(dni);
		}
		if(!nombre.equals(a.get_nombre()))
		{
			dao.modificar(a.get_id(), "Nombre", nombre);
			a.set_nombre(nombre);
		}
		if(!apellido.equals(a.get_apellidos()))
		{
			dao.modificar(a.get_id(), "Apellido", apellido);
			a.set_apellidos(apellido);
		}
		if(!password.equals(a.getAl_Contrasenia()))
		{
			dao.modificar(a.get_id(), "Al_Contrasenia", password);
			a.setAl_Contrasenia(password);
		}
		if(tarjeta != a.get_numTarjeta())
		{
			dao.modificar(a.get_id(), "tarjeta_bancaria", Integer.toString(tarjeta));
			a.set_numTarjeta(tarjeta);
		}
		
		//Observer
		for(Observer o : observadores)
		{
			o.onModificarAlumno(a);
		}
	}
	
	public void modificarProfesor(String _idPersona,String dni,String nombre,String apellido,
			String password, String jornada,int tarjeta,List<Asignaturas> asignaturas)
	{
		//modificar
		DAOprofesores dao = new DAOprofesores();
		Profesor p = existeProfesor(_idPersona);
		if(!dni.equals(p.get_dni()))
		{
			dao.modificar(p.get_id(), "DNI", dni);
			p.set_dni(dni);
		}
		if(!nombre.equals(p.get_nombre()))
		{
			dao.modificar(p.get_id(), "Nombre", nombre);
			p.set_nombre(nombre);
		}
		if(!apellido.equals(p.get_apellidos()))
		{
			dao.modificar(p.get_id(), "Apellido", apellido);
			p.set_apellidos(apellido);
		}
		if(!password.equals(p.getPr_Contrasenia()))
		{
			dao.modificar(p.get_id(), "Pr_Contrasenia", password);
			p.setPr_Contrasenia(password);
		}
		if(!(jornada.equals(p.getInicio_Jornada() + "-" + p.getFinal_Jornada())))
		{
			String[] jornadaNueva = jornada.split("-");
			p.setInicio_Jornada(jornadaNueva[0]);
			dao.modificar(p.get_id(), "Inicio_Jornada", jornadaNueva[0]);
			p.setFinal_Jornada(jornadaNueva[1]);
			dao.modificar(p.get_id(), "Final_Jornada", jornadaNueva[1]);
		}
		if(tarjeta != p.get_cuentaBancaria())
		{
			dao.modificar(p.get_id(), "Num_cuenta", Integer.toString(tarjeta));
			p.set_cuentaBancaria(tarjeta);
		}
		//asignaturas
		modificarAsignaturas(p, asignaturas);
	}
	
	public void modificarAsignaturas(Profesor p, List<Asignaturas> asig)
	{
		List<Asignaturas> nuevas = new ArrayList<Asignaturas>();
		
		for(Asignaturas a : asig)
		{
			if(!existeAsignatura(a))
			{
				String numSerie = Integer.toString(asignaturas.size());
				String id = a.generarId(numSerie);
				while(existeIdAsig(id))
				{
					numSerie = Integer.toString(asignaturas.size() + 1);
					id = a.generarId(numSerie);
				}
				a.setIdAsignatura(id);
				asignaturas.add(a);
			}
			else
			{
				a = existeAsignatura(a.getNombre());
			}
			a.addProfesor(p);
			nuevas.add(a);
		}
		p.set_asignaturas(nuevas);
		
		for(Observer o : observadores)
		{
			o.onModificarProfesor(p, nuevas);
		}
	}

	public void cerrarMostrar()
	{
		for(Observer o : observadores)
		{
			o.onCerrarMostrar();
		}
	}
	
	public void cerrarPagos()
	{
		for(Observer o : observadores)
		{
			o.onCerrarPagos();
		}
	}

	public void cerrarHorario()
	{
		for(Observer o : observadores)
		{
			o.onCerrarHorario();
		}
	}
	
	public void removeObserver(Observer o)
	{
		boolean encontrado = false;
		int cont = 0;
		int max = observadores.size();
		Observer observador = null;
		//buscamos
		while(!encontrado && cont < max)
		{
			observadores.get(cont);
			if(observador == o)
			{
				encontrado = true;
				observadores.remove(cont);
			}
			cont++;
		}
	}
	
	public void addObserver(Observer o)
	{
		observadores.add(o);
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public List<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}

	public List<Asignaturas> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<Asignaturas> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public List<Pagos> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pagos> pagos) {
		this.pagos = pagos;
	}

	public List<Observer> getObservadores() {
		return observadores;
	}

	public void setObservadores(List<Observer> observadores) {
		this.observadores = observadores;
	}

	public void generarIdHorario(Horario h) {
		String numSerie = Integer.toString(horarios.size());
		String id = h.generarId(numSerie);
		while(existeidHorario(id))
		{
			numSerie = Integer.toString(horarios.size() + 1);
			id = h.generarId(numSerie);
		}
		
		h.setId_Horario(id);
	}
	
	public boolean existeidPago(String id) {
		int cont = 0;
		int maxAl = pagos.size();
		boolean encontrado = false;
		Pagos p = null;
		while(cont < maxAl && !encontrado)
		{
			p = pagos.get(cont);
			if(p.getIdPago().equalsIgnoreCase(id))
			{
				encontrado = true;
			}
			cont++;		
		}
		return encontrado;
	}
	
	public boolean existeidHorario(String id) {
		int cont = 0;
		int maxAl = horarios.size();
		boolean encontrado = false;
		Horario h = null;
		while(cont < maxAl && !encontrado)
		{
			h = horarios.get(cont);
			if(h.getId_Horario().equalsIgnoreCase(id))
			{
				encontrado = true;
			}
			cont++;		
		}
		return encontrado;
	}

	public void generarIdClase(Clase c) 
	{
		if(!existeClase(c))
		{
			//Generar id;
			String numSerie = Integer.toString(clases.size());
			String id = c.generarId(numSerie);
			while(existeIdClase(id))
			{
				numSerie = Integer.toString(alumnos.size() + 1);
				id = c.generarId(numSerie);
			}
			
			c.setId_Clase(id);
			
		}
		else
		{
			throw new IllegalArgumentException("Ya existe esta clase");
		}
		
	}

	private boolean existeIdClase(String id) 
	{
		int cont = 0;
		int maxAl = clases.size();
		boolean encontrado = false;
		Clase c = null;
		while(cont < maxAl && !encontrado)
		{
			c = clases.get(cont);
			if(c.getId_Clase().equals(id))
			{
				encontrado = true;
			}
			cont++;		
		}
		return encontrado;
	}
}
