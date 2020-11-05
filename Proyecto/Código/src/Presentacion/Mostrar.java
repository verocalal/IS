package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Controller;
import Negocio.Alumno;
import Negocio.Asignaturas;
import Negocio.Clase;
import Negocio.Horario;
import Negocio.Pagos;
import Negocio.Profesor;

public class Mostrar extends JFrame implements Observer
{
	private Controller _ctrl;
	private String _idPersona;
	private boolean _student;
	
	private JLabel Logo = new JLabel(" DON PREGUNTON ");
	private JLabel Header = new JLabel("  Datos:                         ");
	
	JTextField ID = new JTextField();
	JButton exit = new JButton ("Salir");
	
	JLabel Dni = new JLabel(" Dni:");
	JLabel Nombre = new JLabel(" Nombre:");
	JLabel Apellidos = new JLabel(" Apellidos:");
	JLabel Jornada = new JLabel("Jornada: ");
	JLabel Asignaturas = new JLabel ("Asignaturas: ");
	
	public Mostrar(Controller controller, String Id, boolean student) 
	{
		super("Mostrar datos");
		this._ctrl = controller;
		this._idPersona = Id;
		this._student = student;
		_ctrl.addObserver(this);
		initGUI();
	}
	
	private void initGUI()
	{
		JPanel show = new JPanel();
		show.setLayout(new BoxLayout(show, BoxLayout.Y_AXIS));
		this.setMinimumSize(new Dimension(900, 600));
		this.setLocationRelativeTo(null);
		show.setBackground(Color.BLACK);
		this.setContentPane(show);
		
		
		//Prepare JLabel
		Dni.setBackground(Color.RED);
		Nombre.setBackground(Color.RED);
		Apellidos.setBackground(Color.RED);
		Jornada.setBackground(Color.RED);
		Asignaturas.setBackground(Color.RED);
		
		//Logo
		show.add(Box.createRigidArea(new Dimension(0, 40)));
		Font box = new Font("Cabin",Font.ITALIC,30);
		Logo.setFont(box);
		Logo.setBackground(Color.RED);
		Logo.setOpaque(true);
		Logo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		show.add(Logo);
		//Header
		show.add(Box.createRigidArea(new Dimension(0, 40)));
		Font box2 = new Font("Cabin",Font.ITALIC,40);
		Header.setFont(box2);
		Header.setBackground(Color.RED);
		Header.setOpaque(true);
		Header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		show.add(Header);
		show.add(Box.createRigidArea(new Dimension(0, 10)));
		
		//Panel de datos
		show.add(tablaDatos());
		cargarDatos();
		
		//Exit Button
		exit.setAlignmentX(JButton.CENTER_ALIGNMENT);
		exit.setBackground(Color.CYAN);
		exit.setMaximumSize(new Dimension(300, 50));
		exit.setMinimumSize(new Dimension(300,50));
		exit.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					_ctrl.cerrarMostrar();
				}
		
			});
		
		show.add(Box.createRigidArea(new Dimension(0, 10)));
		show.add(exit);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		this.setVisible(true);
	}
	
	private JPanel tablaDatos()	
	{
		JPanel data = new JPanel();
		data.setMaximumSize(new Dimension(600,200));
		data.setBackground(Color.RED);
		data.setLayout(new BoxLayout(data, BoxLayout.Y_AXIS));
		Font box = new Font("Cabin",Font.ITALIC,30);
		data.setAlignmentX(CENTER_ALIGNMENT);
		
		//JLabel
		Dni.setFont(box);
		Dni.setOpaque(true);
		Nombre.setFont(box);
		Nombre.setOpaque(true);
		Apellidos.setFont(box);
		Apellidos.setOpaque(true);
		
		//add
		data.add(Dni);
		data.add(Nombre);
		data.add(Apellidos);
		
		if(!_student)
		{
			data.setBackground(Color.RED);
			Jornada.setFont(box);
			Jornada.setOpaque(true);
			Asignaturas.setFont(box);
			Asignaturas.setOpaque(true);
			
			data.add(Asignaturas);
			data.add(Jornada);
		}
		
		return data;
	}
	
	private void cargarDatos()
	{
		_ctrl.mostrar(_student , _idPersona);
	}

	@Override
	public void onMostrar(boolean _student, String datos) 
	{
		String[] cambios = datos.split(";");
		Dni.setText(Dni.getText() + cambios[0]);
		Nombre.setText(Nombre.getText() + cambios[1]);
		Apellidos.setText(Apellidos.getText() + cambios[2]);
		
		if(!_student)
		{
			Asignaturas.setText(Asignaturas.getText() + cambios[3]);
			Jornada.setText(Jornada.getText() + cambios[4]);
		}
	}

	@Override
	public void onAddClase(Clase clases, Horario h) {}

	@Override
	public void onHorario(List<Clase> clases, List<Horario> horarios) {}

	@Override
	public void onCargarAlumno(Alumno a) {}

	@Override
	public void onCargarProfesor(Profesor p) {}

	@Override
	public void onReservar() {}

	@Override
	public void onCerrarHorario() {}

	@Override
	public void onCerrarMostrar() 
	{
		_ctrl.removeObserver(this);
		dispose();
	}

	@Override
	public void onMain(boolean _student, String id) {}

	@Override
	public void onNuevaAsignatura(List<Negocio.Asignaturas> asig) {}

	@Override
	public void onAniadirAlumno(Alumno a) {}

	@Override
	public void onModificarAlumno(Alumno a) {}

	@Override
	public void onModificarProfesor(Profesor p, List<Asignaturas> asig) {}

	@Override
	public void onCargarAsignaturas(String[] asig) {}

	@Override
	public void onAsignaturaProfesor(Negocio.Asignaturas a) {}

	@Override
	public void onAniadirProfesor(Profesor p, List<Negocio.Asignaturas> asig) {}

	@Override
	public void onPagos(List<Pagos> pagos) {}

	@Override
	public void onCerrarPagos() {}

	@Override
	public void onAddPago(Pagos p) {}
	
}
