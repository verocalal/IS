package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import Controller.Controller;

public class HorarioTable extends JFrame
{
	private String _idPersona;
	private Controller _ctrl;
	private HorarioTableModel tableModel;
	private boolean _student;
	
	public HorarioTable(Controller ctrl, String id, boolean student) 
	{
		super("Horario de clases");
		this._ctrl = ctrl;
		this._student = student;
		_idPersona = id;
		initGUI();
	}

	public void initGUI() 
	{
		JPanel horario = new JPanel();
		horario.setLayout(new BorderLayout());
		horario.setBackground(Color.BLACK);
		this.setContentPane(horario);
		this.setMinimumSize(new Dimension(900, 600));
		setLocationRelativeTo(null);
		
		
		horario.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),
				"Horario", TitledBorder.LEFT, TitledBorder.TOP));

		horario.setPreferredSize(new Dimension(200, 200));

		//Table
		tableModel = new HorarioTableModel(_ctrl, _idPersona, _student);
		JTable table = new JTable(tableModel);
		table.setGridColor(Color.WHITE);
		horario.add(new JScrollPane(table));
		pack();
		this.setVisible(true);

	}
}
