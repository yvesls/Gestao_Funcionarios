package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ViewCalculoEstatistico {
	private JFrame frame;
	private JTable table;
	private JButton btnFecharCalcEst;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	
	public ViewCalculoEstatistico() {
		this.frame = new JFrame();
		initialize();
	}

	private void initialize() {
		frame.setBounds(100, 100, 981, 412);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		btnFecharCalcEst = new JButton("Fechar");
		btnFecharCalcEst.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFecharCalcEst.setBounds(10, 337, 126, 25);
		frame.getContentPane().add(btnFecharCalcEst);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 945, 269);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		model = new DefaultTableModel();
		Object[] column = {"Tipo", "Mês"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JLabel lblEstSistema = new JLabel("Estatísticas do sistema");
		lblEstSistema.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblEstSistema.setBounds(10, 11, 547, 32);
		frame.getContentPane().add(lblEstSistema);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtnFecharCalcEst() {
		return btnFecharCalcEst;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}
}
