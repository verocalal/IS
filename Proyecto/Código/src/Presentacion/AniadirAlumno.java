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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Window;

import Controller.Controller;
import Negocio.Alumno;
import Negocio.Asignaturas;
import Negocio.Clase;
import Negocio.Horario;
import Negocio.Pagos;
import Negocio.Profesor;

public class AniadirAlumno extends JFrame implements Observer
{	
	private Controller _ctrl;
	
	private JLabel Logo = new JLabel(" DON PREGUNTON ");
	private JLabel Header = new JLabel("  Introducir datos:                                     ");
	
	JTextField DNI = new JTextField();
	JTextField Contrasenia = new JTextField();
	JTextField Nombre = new JTextField();
	JTextField Apellidos = new JTextField();
	JTextField Tarjeta = new JTextField();
	JButton aniadir = new JButton("Darse de alta");

	
	public AniadirAlumno(Controller controller) 
	{
		super("Log Up");
		this._ctrl = controller;
		_ctrl.addObserver(this);
		initGUI();
	}
	
	private void initGUI()
	{
		this.setMinimumSize(new Dimension(900, 600));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.BLACK);
		this.setContentPane(mainPanel);
		this.setMinimumSize(new Dimension(900, 600));
		setLocationRelativeTo(null);
		
		//Logo
		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		textFieldHeader(Logo, Color.RED, 30);
		mainPanel.add(Logo);
		//Header
		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		textFieldHeader(Header, Color.RED, 40);
		mainPanel.add(Header);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		//Panel de datos
		JPanel data = new JPanel();
		data.setMaximumSize(new Dimension(650,250));
		data.setBackground(Color.BLACK);
		data.setLayout(new BoxLayout(data, BoxLayout.X_AXIS));
		
		data.add(addHeaders());
		data.add(Box.createRigidArea(new Dimension(10, 0)));
		data.add(addTextField());
		mainPanel.add(data);
		
		//Boton de aniadir
		aniadir.setAlignmentX(JButton.CENTER_ALIGNMENT);
		aniadir.setBackground(Color.CYAN);
		aniadir.setMaximumSize(new Dimension(300, 50));
		aniadir.setMinimumSize(new Dimension(300,50));
		aniadir.addActionListener(new ActionListener()
			{
	
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						String Dni = DNI.getText();
						String Password = Contrasenia.getText();
						String Name = Nombre.getText();
						String Surname = Apellidos.getText();
						int CC = (int) Integer.parseInt(Tarjeta.getText());
						
						//llamar al Controller para aniadir al alumno
						_ctrl.addAlumno(Dni, Password, Name, Surname, CC);
						//excepciones, etc
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Aniadir Alumno",
								JOptionPane.ERROR_MESSAGE);
					}
				}
		
			});
		mainPanel.add(aniadir);	
		
		pack();
		this.setVisible(true);
	}
	
	private JPanel addHeaders()
	{
		JPanel data = new JPanel();
		data.setBackground(Color.BLACK);
		data.setLayout(new BoxLayout(data, BoxLayout.Y_AXIS));
		Font box = new Font("Cabin",Font.ITALIC,30);
		data.setAlignmentX(BoxLayout.PAGE_AXIS);
		
		//DNI
		JLabel _dni = new JLabel(" DNI                      ");
		_dni.setFont(box);
		_dni.setBackground(Color.RED);
		_dni.setOpaque(true);
		
		
		//Name
		JLabel _name = new JLabel(" Nombre               ");
		_name.setFont(box);
		_name.setBackground(Color.RED);
		_name.setOpaque(true);
		
		//Apellidos
		JLabel _surname = new JLabel(" Apellidos             ");
		_surname.setFont(box);
		_surname.setBackground(Color.RED);
		_surname.setOpaque(true);
		
		//Contrasenia
		JLabel _password = new JLabel(" Password            ");
		_password.setFont(box);
		_password.setBackground(Color.RED);
		_password.setOpaque(true);
		
		//Tarjeta
		JLabel _creditCard = new JLabel(" Tarjeta Bancaria ");
		_creditCard.setFont(box);
		_creditCard.setBackground(Color.RED);
		_creditCard.setOpaque(true);
		
		data.add(_dni);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(_password);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(_name);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(_surname);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(_creditCard);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		
		return data;
	}
	
	private JPanel addTextField()
	{
		JPanel data = new JPanel();
		data.setBackground(Color.BLACK);
		data.setLayout(new BoxLayout(data, BoxLayout.Y_AXIS));
	
		data.add(DNI);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(Contrasenia);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(Nombre);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(Apellidos);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(Tarjeta);
		data.add(Box.createRigidArea(new Dimension(0, 14)));
		
		return data;
	}
	
	private void textFieldHeader(JLabel logo2, Color c, int tam)
	{
		Font box = new Font("Cabin",Font.ITALIC,tam);
		logo2.setFont(box);
		logo2.setBackground(c);
		logo2.setOpaque(true);
		logo2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
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
	public void onReservar() {}

	@Override
	public void onCerrarHorario() {}

	@Override
	public void onCerrarMostrar() {}

	@Override
	public void onMain(boolean _student, String id) {}

	@Override
	public void onNuevaAsignatura(List<Asignaturas> asig) {}

	@Override
	public void onAniadirAlumno(Alumno a)
	{
		JOptionPane.showMessageDialog(null, "ID: " + a.get_id(), "ID", JOptionPane.INFORMATION_MESSAGE);
		_ctrl.removeObserver(this);
		dispose();
	}

	@Override
	public void onModificarAlumno(Alumno a) {}

	@Override
	public void onModificarProfesor(Profesor p, List<Asignaturas> asig) {}

	@Override
	public void onCargarAsignaturas(String[] asig) {}

	@Override
	public void onAsignaturaProfesor(Asignaturas a) {}

	@Override
	public void onAniadirProfesor(Profesor p, List<Asignaturas> asig) {}

	@Override
	public void onPagos(List<Pagos> pagos) {}

	@Override
	public void onCerrarPagos() {}

	@Override
	public void onAddPago(Pagos p) {}

}
