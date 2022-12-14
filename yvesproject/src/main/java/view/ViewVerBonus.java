package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewVerBonus {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnFecharVerBonus;

	public ViewVerBonus() {
		this.frame = new JFrame();
		initialize();
	}

	private void initialize() {
		frame.setBounds(100, 100, 636, 371);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 600, 266);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		model = new DefaultTableModel();
		Object[] column = {"Data do cálculo", "Cargo", "Tipo de bônus", "Valor do bônus"};
		//Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		btnFecharVerBonus = new JButton("Fechar");
		btnFecharVerBonus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFecharVerBonus.setBounds(10, 298, 99, 23);
		frame.getContentPane().add(btnFecharVerBonus);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JTable getTable() {
		return table;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public JButton getBtnFecharVerBonus() {
		return btnFecharVerBonus;
	}
}
