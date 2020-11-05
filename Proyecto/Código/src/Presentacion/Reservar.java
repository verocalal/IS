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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Controller;
import Negocio.Alumno;
import Negocio.Asignaturas;
import Negocio.Clase;
import Negocio.Horario;
import Negocio.Pagos;
import Negocio.Profesor;

public class Reservar extends JFrame implements Observer
{
	private Controller _ctrl;
	private String _idAlumno;
	
	private JLabel Logo = new JLabel(" DON PREGUNTON ");
	private JLabel Header = new JLabel("  Reservar Clase: Asignatura, Profesor, fecha, hora  ");
	
	private String asignaturas[] = {};
	private JLabel Profesores = new JLabel("Profesores:                                    ");
	private Asignaturas asignatura;
	
	private JComboBox<String> opcionAsignatura;
	private JTextField profesor = new JTextField();
	private JTextField hora = new JTextField();
	private JTextField fecha = new JTextField();
	
	private JButton reservar = new JButton("Reservar");
	
	public Reservar(Controller controller, String id)
	{
		super("Reservar Clase");
		this._ctrl = controller;
		this._idAlumno = id;
		_ctrl.addObserver(this);
		_ctrl.cargarAsignaturas();
		initGUI();
	}
	
	private void initGUI()
	{
		JPanel data = new JPanel();
		data.setLayout(new BoxLayout(data, BoxLayout.Y_AXIS));
		this.setMinimumSize(new Dimension(900, 600));
		this.setLocationRelativeTo(null);
		data.setBackground(Color.BLACK);
		this.setContentPane(data);
		
		//Logo
		data.add(Box.createRigidArea(new Dimension(0, 40)));
		textFieldHeader(Logo, Color.RED, 30);
		data.add(Logo);
		//Header
		data.add(Box.createRigidArea(new Dimension(0, 40)));
		textFieldHeader(Header, Color.RED, 30);
		data.add(Header);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		//Reserva
		if (asignaturas.length == 0) {
			JOptionPane.showMessageDialog(null, "No hay asignaturas", "Reservar",
					JOptionPane.ERROR_MESSAGE);
			dispose();
		}
		else {
			data.add(datosReserva());
			data.add(Box.createRigidArea(new Dimension(0, 40)));
			textFieldHeader(Profesores, Color.RED, 20);
			data.add(Profesores);
			this.pack();
			this.setVisible(true);
		}
	}
	
	private JPanel datosReserva()
	{
		JPanel reserva = new JPanel();
		reserva.setLayout(new BoxLayout(reserva, BoxLayout.X_AXIS));
		reserva.setMaximumSize(new Dimension(500, 50));
		reserva.setBackground(Color.BLACK);
		
		//JComboBox - Asignaturas

		opcionAsignatura = new JComboBox<String>(asignaturas);
		opcionAsignatura.setSelectedIndex(0);
		opcionAsignatura.setEditable(false);
		Listener();
		
		//add
		reserva.add(opcionAsignatura);
		reserva.add(Box.createRigidArea(new Dimension(10, 0)));
		reserva.add(profesor);
		reserva.add(Box.createRigidArea(new Dimension(10, 0)));
		reserva.add(fecha);
		reserva.add(Box.createRigidArea(new Dimension(10, 0)));
		reserva.add(hora);
		reserva.add(Box.createRigidArea(new Dimension(10, 0)));
		addButton(reservar, reserva);
		
		
		return reserva;
		
	}
	
	private void Listener()
	{
		opcionAsignatura.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String asig = opcionAsignatura.getSelectedItem().toString();
				_ctrl.buscarAsignatura(asig);
			}
	
		});
		
		reservar.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String Asignatura = opcionAsignatura.getSelectedItem().toString();
					String Profesor = profesor.getText();
					String Fecha = fecha.getText();
					String Hora  = hora.getText();
					
					_ctrl.reservarClase(_idAlumno, Profesor, asignatura, Fecha, Hora);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Aniadir Reserva",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
	}
	
	private void textFieldHeader(JLabel logo2, Color c, int tam)
	{
		Font box = new Font("Cabin",Font.ITALIC,tam);
		logo2.setFont(box);
		logo2.setBackground(c);
		logo2.setOpaque(true);
		logo2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	}
	
	private void addButton(JButton button, JPanel container) 
	{
		button.setAlignmentX(JButton.CENTER_ALIGNMENT);
		button.setPreferredSize(new Dimension(150, 50));
		button.setMaximumSize(new Dimension(150, 50));
		button.setMinimumSize(new Dimension(150,50));
		button.setBackground(Color.CYAN);
		container.add(button);
	}

	@Override
	public void onMostrar(boolean _student, String datos) {}

	@Override
	public void onAddClase(Clase clases, Horario h) {}

	@Override
	public void onHorario(List<Clase> clases, List<Horario> horarios) {}

	@Override
	public void onCargarAlumno(Alumno a) {}

	@Override
	public void onCargarProfesor(Profesor p) {}

	@Override
	public void onReservar() 
	{
		JOptionPane.showMessageDialog(null, "La clase ha sido reservada con exito",
				"Reservar Clase", JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}

	@Override
	public void onCerrarHorario() {}

	@Override
	public void onCerrarMostrar() {}

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
	public void onCargarAsignaturas(String[] asig) 
	{
		this.asignaturas = asig;
		
	}

	@Override
	public void onAsignaturaProfesor(Asignaturas a) 
	{
		if (a.getProfesores() == null || a.getProfesores().size() == 0) {
			JOptionPane.showMessageDialog(null, "No hay profesores para esa asignatura",
					"Reservar Clase", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			this.asignatura = a;
			String profes = "";
			List<Profesor> profesores = a.getProfesores();
			int maxProf = profesores.size();
			int i = 0;
			for(Profesor p : profesores)
			{
				if(i < maxProf - 1)
				{
					profes += p.get_nombre() + ", ";
				}
				else
				{
					profes += p.get_nombre();
				}
				i++;
			}
			
			this.Profesores.setText("Profesores: " + profes);
		}
	}

	@Override
	public void onAniadirProfesor(Profesor p, List<Asignaturas> asig) {}

	@Override
	public void onPagos(List<Pagos> pagos) {}

	@Override
	public void onCerrarPagos() {}

	@Override
	public void onAddPago(Pagos p) {}
	
}
