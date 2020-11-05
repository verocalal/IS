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

public class ModificarAlumno extends JFrame implements Observer
{
	private Controller _ctrl;
	private String _idPersona;
	
	private JLabel Logo = new JLabel(" DON PREGUNTON ");
	private JLabel Header = new JLabel("  Modificar alumno                                ");
	
	JTextField DNI = new JTextField();
	JTextField Nombre = new JTextField();
	JTextField Apellidos = new JTextField();
	JTextField Contrasenia = new JTextField();
	JTextField Tarjeta = new JTextField();
	
	JButton Modificar = new JButton("Modificar datos");

	
	public ModificarAlumno(Controller controller, String Id) 
	{
		super("Modificar");
		this._ctrl = controller;
		_ctrl.addObserver(this);
		this._idPersona = Id;
		initGUI();
	}
	
	private void initGUI()
	{
		this.setMinimumSize(new Dimension(900, 600));
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		main.setBackground(Color.BLACK);
		this.setContentPane(main);
		setLocationRelativeTo(null);
		
		//Logo
		main.add(Box.createRigidArea(new Dimension(0, 40)));
		Font box = new Font("Cabin",Font.ITALIC,30);
		Logo.setFont(box);
		Logo.setBackground(Color.RED);
		Logo.setOpaque(true);
		Logo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		main.add(Logo);
		
		//Header
		main.add(Box.createRigidArea(new Dimension(0, 40)));
		Font box2 = new Font("Cabin",Font.ITALIC,40);
		Header.setFont(box2);
		Header.setBackground(Color.RED);
		Header.setOpaque(true);
		Header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		main.add(Header);
		main.add(Box.createRigidArea(new Dimension(0, 10)));
		
		//Panel de datos
		JPanel data = new JPanel();
		data.setMaximumSize(new Dimension(600,250));
		data.setBackground(Color.BLACK);
		data.setLayout(new BoxLayout(data, BoxLayout.X_AXIS));
		
		data.add(addHeaders());
		data.add(Box.createRigidArea(new Dimension(10, 0)));
		data.add(addTextField());
		_ctrl.cargarAlumno(_idPersona);
		main.add(data);
		
		//Guardar cambios
		ModifButton(Modificar);
		Modificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					//intenta guardar los datos modificados usando la id
					String dni = DNI.getText();
					String nombre = Nombre.getText();
					String apellido = Apellidos.getText();
					String password = Contrasenia.getText();
					int tarjeta = Integer.parseInt(Tarjeta.getText());
					
					//llama al controler
					_ctrl.modificarAlumno(_idPersona, dni, nombre, apellido, password, tarjeta);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error al modificar",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		main.add(Modificar);	
		
		pack();
		this.setVisible(true);
	}
	
	private void ModifButton(JButton button)
	{
		button.setAlignmentX(JButton.CENTER_ALIGNMENT);
		button.setBackground(Color.CYAN);
		button.setMaximumSize(new Dimension(300, 50));
		button.setMinimumSize(new Dimension(300,50));
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
		
		//add
		data.add(_dni);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(_name);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(_surname);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(_password);
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
		data.add(Nombre);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(Apellidos);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(Contrasenia);
		data.add(Box.createRigidArea(new Dimension(0, 10)));
		data.add(Tarjeta);
		data.add(Box.createRigidArea(new Dimension(0, 14)));
		
		
		return data;
	}

	@Override
	public void onMostrar(boolean _student, String datos) {}

	@Override
	public void onAddClase(Clase clases, Horario h) {}

	@Override
	public void onHorario(List<Clase> clases, List<Horario> horarios) {}

	@Override
	public void onCargarAlumno(Alumno a) 
	{
		DNI.setText(a.get_dni());
		Nombre.setText(a.get_nombre());
		Apellidos.setText(a.get_apellidos());
		Contrasenia.setText(a.getAl_Contrasenia());
		Tarjeta.setText(Integer.toString(a.get_numTarjeta()));
	}

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
	public void onAniadirAlumno(Alumno a) {}

	@Override
	public void onModificarAlumno(Alumno a) 
	{
		JOptionPane.showMessageDialog(null, "Alumno Modificado", "Modificar", JOptionPane.INFORMATION_MESSAGE);
		_ctrl.removeObserver(this);
		dispose();
	}

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
