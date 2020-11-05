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

import Controller.Controller;
import Negocio.Alumno;
import Negocio.Asignaturas;
import Negocio.Clase;
import Negocio.Horario;
import Negocio.Pagos;
import Negocio.Profesor;

public class AniadirProfesor extends JFrame implements Observer
{
	private Controller _ctrl;
	
	private JLabel Logo = new JLabel(" DON PREGUNTON ");
	private JLabel Header = new JLabel("  Introducir datos:                                     ");
	
	JTextField DNI = new JTextField();
	JTextField Nombre = new JTextField();
	JTextField Apellidos = new JTextField();
	JTextField Contrasenia = new JTextField();
	JTextField Jornada = new JTextField();
	JTextField SS = new JTextField();
	JTextField Cuenta = new JTextField();
	JTextField Asignaturas = new JTextField();
	
	JButton aniadir = new JButton("Darse de alta");

	
	public AniadirProfesor(Controller controller) 
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
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		//Header
		
		textFieldHeader(Header, Color.RED, 40);
		mainPanel.add(Header);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		//Panel de datos
		JPanel data = new JPanel();
		data.setMaximumSize(new Dimension(650,310));
		data.setBackground(Color.BLACK);
		data.setLayout(new BoxLayout(data, BoxLayout.X_AXIS));
		
		data.add(addHeaders());
		data.add(Box.createRigidArea(new Dimension(10, 0)));
		data.add(addTextField());
		mainPanel.add(data);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
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
						String Name = Nombre.getText();
						String Surname = Apellidos.getText();
						String password = Contrasenia.getText();
						String jornada = Jornada.getText();
						int SegSoc = (int) Integer.parseInt(SS.getText());
						int cuenta = (int) Integer.parseInt(Cuenta.getText());
						String asignaturas = Asignaturas.getText();
					
						
						//llamar al Controller para aniadir al alumno
						_ctrl.addProfesor(Dni, password, Name, Surname, SegSoc, cuenta, jornada, asignaturas);
						//excepciones, etc
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Aniadir Profesor",
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
		Font box = new Font("Cabin",Font.ITALIC,23);
		data.setAlignmentX(BoxLayout.PAGE_AXIS);
		
		//DNI
		JLabel _dni = new JLabel(" DNI                                   ");
		_dni.setFont(box);
		_dni.setBackground(Color.RED);
		_dni.setOpaque(true);
		
		
		//Name
		JLabel _name = new JLabel(" Nombre                            ");
		_name.setFont(box);
		_name.setBackground(Color.RED);
		_name.setOpaque(true);
		
		//Apellidos
		JLabel _surname = new JLabel(" Apellidos                          ");
		_surname.setFont(box);
		_surname.setBackground(Color.RED);
		_surname.setOpaque(true);
		
		//Constraseña
		JLabel _password = new JLabel(" Contraseña                      ");
		_password.setFont(box);
		_password.setBackground(Color.RED);
		_password.setOpaque(true);
		
		//Jornada
		JLabel _jornada = new JLabel(" Jornada (INI-FIN)            ");
		_jornada.setFont(box);
		_jornada.setBackground(Color.RED);
		_jornada.setOpaque(true);
		
		//Asignaturas
		JLabel _asignaturas = new JLabel(" Asignaturas                      ");
		_asignaturas.setFont(box);
		_asignaturas.setBackground(Color.RED);
		_asignaturas.setOpaque(true);
		
		//SS
		JLabel _ssNumber = new JLabel(" Numero de la Seg. Soc.  ");
		_ssNumber.setFont(box);
		_ssNumber.setBackground(Color.RED);
		_ssNumber.setOpaque(true);
		
		//Cuenta 
		JLabel _cuenta = new JLabel (" Cuenta Bancaria              ");
		_cuenta.setFont(box);
		_cuenta.setBackground(Color.RED);
		_cuenta.setOpaque(true);
		
		
		data.add(_dni);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(_name);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(_surname);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(_password);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(_jornada);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(_asignaturas);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(_ssNumber);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(_cuenta);
		
		return data;
	}
	
	private JPanel addTextField()
	{
		JPanel data = new JPanel();
		data.setBackground(Color.BLACK);
		data.setLayout(new BoxLayout(data, BoxLayout.Y_AXIS));
	
		Jornada.setText("00:00-00:00");
		
		data.add(DNI);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(Nombre);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(Apellidos);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(Contrasenia);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(Jornada);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(Asignaturas);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(SS);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(Cuenta);
		
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
	public void onAniadirProfesor(Profesor p, List<Negocio.Asignaturas> asig) 
	{
		JOptionPane.showMessageDialog(null, "ID: " + p.get_id(), "ID", JOptionPane.INFORMATION_MESSAGE);
		_ctrl.removeObserver(this);
		dispose();	
	}

	@Override
	public void onPagos(List<Pagos> pagos) {}

	@Override
	public void onCerrarPagos() {}

	@Override
	public void onAddPago(Pagos p) {}
	
}
