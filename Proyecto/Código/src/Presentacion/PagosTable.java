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

public class PagosTable extends JFrame
{
	private String _idPersona;
	private Controller _ctrl;
	private PagosTableModel tableModel;
	private boolean _student;
	
	public PagosTable(Controller ctrl, String id, boolean student) 
	{
		super("Pagos");
		this._ctrl = ctrl;
		this._student = student;
		_idPersona = id;
		initGUI();
	}

	public void initGUI() 
	{
		JPanel pagos = new JPanel();
		pagos.setLayout(new BorderLayout());
		pagos.setBackground(Color.BLACK);
		this.setContentPane(pagos);
		this.setMinimumSize(new Dimension(900, 600));
		setLocationRelativeTo(null);
		
		
		pagos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),
				"Pagos", TitledBorder.LEFT, TitledBorder.TOP));

		pagos.setPreferredSize(new Dimension(200, 200));

		//Table
		tableModel = new PagosTableModel(_ctrl, _idPersona, _student);
		JTable table = new JTable(tableModel);
		table.setGridColor(Color.WHITE);
		pagos.add(new JScrollPane(table));
			
		pack();
		this.setVisible(true);

	}
}
