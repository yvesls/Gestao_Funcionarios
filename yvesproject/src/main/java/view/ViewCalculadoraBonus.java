package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class ViewCalculadoraBonus {
	private JFrame frame;
	private JTextField textDataDosFuncionarios;
	private JButton btnCalcular;
	private JButton btnListarTodos;
	private JButton btnBuscar;
	private JTable tableSalariosCalculados;
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private JButton btnFechar;
	private JLabel lblMostraDataCalculo;
	private JLabel lblDataCalculo;
	private JScrollPane scrollPane2;
	
	public ViewCalculadoraBonus() {
		frame = new JFrame();
		initialize();
	}

	private void initialize() {
		frame.setBounds(100, 100, 941, 459);
		frame.getContentPane().setLayout(null);
		
		lblMostraDataCalculo = new JLabel("Data do cálculo:");
		lblMostraDataCalculo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMostraDataCalculo.setBounds(611, 387, 99, 21);
		frame.getContentPane().add(lblMostraDataCalculo);
		
		lblDataCalculo = new JLabel("");
		lblDataCalculo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataCalculo.setBounds(720, 387, 82, 21);
		frame.getContentPane().add(lblDataCalculo);
		
		textDataDosFuncionarios = new JTextField();
		textDataDosFuncionarios.setBounds(12, 11, 104, 23);
		frame.getContentPane().add(textDataDosFuncionarios);
		textDataDosFuncionarios.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscar.setBounds(126, 11, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
		btnListarTodos = new JButton("Listar todos");
		btnListarTodos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnListarTodos.setBounds(762, 9, 153, 23);
		frame.getContentPane().add(btnListarTodos);
		
		btnCalcular = new JButton("Calcular");
		btnCalcular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCalcular.setBounds(826, 386, 89, 23);
		frame.getContentPane().add(btnCalcular);
		
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 41, 905, 335);
		frame.getContentPane().add(scrollPane2);
		
		tableSalariosCalculados = new JTable();
		tableSalariosCalculados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		model2 = new DefaultTableModel();
		Object[] column2 = {"Funcionário", "Data", "Salário base (R$)", "Bônus (R$)", "Salário (R$)"};
		model2.setColumnIdentifiers(column2);
		tableSalariosCalculados.setModel(model2);
		scrollPane2.setViewportView(tableSalariosCalculados);
		
		btnFechar = new JButton("Fechar");
		btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFechar.setBounds(12, 386, 99, 23);
		frame.getContentPane().add(btnFechar);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JTextField getTextDataDosFuncionarios() {
		return textDataDosFuncionarios;
	}

	public JButton getBtnCalcular() {
		return btnCalcular;
	}

	public JButton getBtnListarTodos() {
		return btnListarTodos;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JTable getTableSalariosCalculados() {
		return tableSalariosCalculados;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public DefaultTableModel getModel2() {
		return model2;
	}

	public JButton getBtnFechar() {
		return btnFechar;
	}

	public JLabel getLblMostraDataCalculo() {
		return lblMostraDataCalculo;
	}

	public JLabel getLblDataCalculo() {
		return lblDataCalculo;
	}
}
