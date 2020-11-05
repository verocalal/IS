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


public class MainWindow extends JFrame implements Observer
{
	private Controller _ctrl;
	
	private String person[] = {"Alumno","Profesor"}; //factoria persona ---------
	private JComboBox<String> options;

	private JLabel Logo = new JLabel(" DON PREGUNTON ");
	
	private JButton logUpT = new JButton("Registrar Profesor");
	private JButton logUpS = new JButton("Registrar Alumno");
	private JButton logIn = new JButton("Iniciar sesión");
	
	private JTextField id = new JTextField();
	private JTextField password = new JTextField();
	
	
	public MainWindow(Controller ctrl)
	{
		super("Don Pregunton");
		this._ctrl = ctrl;
		_ctrl.addObserver(this);
		initGUI();
	}
	
	public void initGUI()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setMinimumSize(new Dimension(900, 600));
		setLocationRelativeTo(null);
		mainPanel.setBackground(Color.BLACK);
		this.setContentPane(mainPanel);
		
		//Listener
		ButtonListener();
		
		//Logo
		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		Font box = new Font("Cabin",Font.ITALIC,30);
		Logo.setFont(box);
		Logo.setBackground(Color.RED);
		Logo.setOpaque(true);
		Logo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		mainPanel.add(Logo);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 70)));
		
		//Log
		
		mainPanel.add(logIn());
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(logUp());
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		this.setVisible(true);
		
		
	}
	
	private JPanel logIn()
	{
		JPanel logPanel = new JPanel();
		logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.X_AXIS));
		logPanel.setMaximumSize(new Dimension(500, 50));
		logPanel.setBackground(Color.BLACK);
		
		//JComboBox
		options = new JComboBox<String>(person);
		options.setSelectedIndex(0);
		options.setEditable(false);
		
		//JButton
		logIn.setAlignmentX(JButton.CENTER_ALIGNMENT);
		logIn.setMaximumSize(new Dimension(100, 50));
		logIn.setBackground(Color.CYAN);
		
		//add
		logPanel.add(options);
		logPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		logPanel.add(id);
		logPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		logPanel.add(password);
		logPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		logPanel.add(logIn);
		
		return logPanel;
	}
	
	private JPanel logUp()
	{
		JPanel logPanel = new JPanel();
		logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.X_AXIS));
		logPanel.setMaximumSize(new Dimension(300, 50));
		logPanel.setBackground(Color.BLACK);
		logPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		//JButton
		logUpT.setAlignmentX(JButton.CENTER_ALIGNMENT);
		logUpT.setMaximumSize(new Dimension(300, 50));
		logUpT.setBackground(Color.CYAN);
		logUpS.setAlignmentX(JButton.CENTER_ALIGNMENT);
		logUpS.setMaximumSize(new Dimension(300, 50));
		logUpS.setBackground(Color.CYAN);
				
		logPanel.add(logUpT);
		logPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		logPanel.add(logUpS);
		
		return logPanel;
	}
	
	private void ButtonListener()
	{
		logIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					String Id = id.getText();
					String Password = password.getText();
					String Person = options.getSelectedItem().toString();
					
					//La excepcion salta al no encontrarlo en su BD correspondiente
					
					if(Person == person[0])
					{
						//buscar Alumno
						_ctrl.existeAlumno(Id, Password);
					}
					else if(Person == person[1])
					{
						//Buscar Profesor
						_ctrl.existeProfesor(Id, Password);
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		
		logUpS.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new AniadirAlumno(_ctrl);
				
			}
			
		});
		
		logUpT.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new AniadirProfesor(_ctrl);
				
			}
			
		});
	}

	@Override
	public void onMain(boolean _student, String id) 
	{
		if(_student)
		{
			new MainAlumno(_ctrl, id);
		}
		else
		{
			new MainProfesor(_ctrl, id);
		}
		_ctrl.removeObserver(this);
		dispose();
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
	public void onNuevaAsignatura(List<Asignaturas> asig) {}

	@Override
	public void onAniadirAlumno(Alumno a) {}

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
