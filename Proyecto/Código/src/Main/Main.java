package Main;

import java.awt.EventQueue;

import Controller.Controller;
import Factorias.BuilderAlumno;
import Factorias.BuilderAsignatura;
import Factorias.BuilderBasedFactory;
import Factorias.BuilderClase;
import Factorias.BuilderHorario;
import Factorias.BuilderPago;
import Factorias.BuilderProfesor;
import Factorias.Factoria;
import Negocio.Academia;
import Negocio.Alumno;
import Negocio.Asignaturas;
import Negocio.Clase;
import Negocio.Horario;
import Negocio.Pagos;
import Negocio.Profesor;
import Presentacion.MainWindow;

public class Main
{
	//Factorias Negocio
	private static Factoria<Alumno> factoriaAlumn;;
	private static Factoria<Profesor> factoriaProf;
	private static Factoria<Asignaturas> factoriaAsig;
	private static Factoria<Clase> factoriaClase;
	private static Factoria<Pagos> factoriaPagos;
	private static Factoria<Horario> factoriaHorario;
	
	//Factorias DAO
	private static void initFactoriaNegocio()
	{
		factoriaAlumn = new BuilderBasedFactory<Alumno>(new BuilderAlumno());
		factoriaProf = new BuilderBasedFactory<Profesor>(new BuilderProfesor());
		factoriaAsig = new BuilderBasedFactory<Asignaturas>(new BuilderAsignatura());
		factoriaClase = new BuilderBasedFactory<Clase>(new BuilderClase());
		factoriaPagos = new BuilderBasedFactory<Pagos>(new BuilderPago());
		factoriaHorario = new BuilderBasedFactory<Horario>(new BuilderHorario());
	}
	

	public static void main(String[] args) {
		
		//inicio factorias negocio
		initFactoriaNegocio();
		//ComunicacionDAOTransfer modelo = new ComunicacionDAOTransfer();
		
		
		
		final Academia modelo = new Academia();
		final Controller controller = new Controller(modelo,factoriaAlumn, factoriaProf, factoriaAsig,
		factoriaClase, 	factoriaPagos , factoriaHorario );//(modelo, Fasig, FPersona)
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainWindow(controller);
				
			}});
	}
}
