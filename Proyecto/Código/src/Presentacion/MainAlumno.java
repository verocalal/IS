package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.Controller;

public class MainAlumno extends JFrame
{
	private Controller _ctrl;
	private String idAlumno; 

	private JLabel Logo = new JLabel(" DON PREGUNTON ");
	private JButton eliminar = new JButton("Dar de baja");
	private JButton mostrar = new JButton("Mostrar datos");
	private JButton modificar = new JButton("Modificar datos");
	private JButton clase = new JButton("Reservar clase");
	private JButton horario = new JButton ("Horario de clases");
	private JButton pagos = new JButton ("Sistema de Pagos");

	public MainAlumno(Controller ctrl, String ID) 
	{
		super("Don Pregunton - Alumno");
		_ctrl = ctrl;
		idAlumno = ID;
		initGUI();
	}

	private void initGUI() 
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setMinimumSize(new Dimension(900, 600));
		setLocationRelativeTo(null);
		mainPanel.setBackground(Color.BLACK);
		this.setContentPane(mainPanel);
		
		//Logo
		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		Font box = new Font("Cabin",Font.ITALIC,30);
		Logo.setFont(box);
		Logo.setBackground(Color.RED);
		Logo.setOpaque(true);
		Logo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		mainPanel.add(Logo);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 70)));
		
		//Button
		addButton(clase, mainPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		addButton(horario, mainPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		addButton(mostrar, mainPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		addButton(pagos, mainPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		addButton(modificar, mainPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		addButton(eliminar, mainPanel);
		
		//Listener
		
		eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Object[] options = { "Si", "No" };
				int n = JOptionPane.showOptionDialog(null, "¿Estas seguro de que quieres darte de baja?", "Darse de baja", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options, options[0]/* resalta la casilla 0 */);

				if (n == 0) 
				{
					_ctrl.removeAlumnoToBd(idAlumno);
					System.exit(0);
				}
			}
		});
		
		modificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new ModificarAlumno(_ctrl, idAlumno);
			}
		});
		
		mostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				new Mostrar(_ctrl, idAlumno, true);			
			}
		});
		
		pagos.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				new PagosTable(_ctrl, idAlumno, true);	
			}
			
		});
			
		
		clase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new Reservar(_ctrl, idAlumno);
				
			}
		});
		
		horario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new HorarioTable(_ctrl, idAlumno, true);		
			}
		});
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private void addButton(JButton button, JPanel container) 
	{
		button.setAlignmentX(JButton.CENTER_ALIGNMENT);
		// fija el tamaño de la componente (500 de ancho por 100 de largo)
		button.setPreferredSize(new Dimension(300, 50));
		button.setMaximumSize(new Dimension(300, 50));
		// permite disminuir proporcionalmente hasta 200 de ancho por 30 de alto
		button.setMinimumSize(new Dimension(300,50));
		button.setBackground(Color.CYAN);
		container.add(button);
	}
}
