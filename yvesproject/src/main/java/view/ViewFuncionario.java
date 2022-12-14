package view;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ViewFuncionario{
	private JFrame frame;
	private JTextField textFieldCampoBuscar;
	private JTable table;
	private JButton btnBuscarFuncionarios;
	private JButton btnFecharFuncionarios;
	private JButton btnNovoFuncionario;
	private JButton btnVerBonus;
	private JButton btnVerFuncionario;
	private DefaultTableModel model;
	private JTable tableSelecionado;
	private DefaultTableModel modelSelecionado;
	private JButton btnAtualizarLista;
	
	public ViewFuncionario() {
		this.frame = new JFrame();
		initialize();
		
	}

	private void initialize() {
		
		frame.setBounds(100, 100, 981, 523);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 46, 20);
		frame.getContentPane().add(lblNewLabel);
		
		textFieldCampoBuscar = new JTextField();
		textFieldCampoBuscar.setBounds(54, 8, 380, 23);
		frame.getContentPane().add(textFieldCampoBuscar);
		textFieldCampoBuscar.setColumns(10);
		
		btnBuscarFuncionarios = new JButton("Buscar");
		btnBuscarFuncionarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscarFuncionarios.setBounds(444, 11, 101, 21);
		frame.getContentPane().add(btnBuscarFuncionarios);
		
		btnFecharFuncionarios = new JButton("Fechar");
		btnFecharFuncionarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFecharFuncionarios.setBounds(10, 448, 126, 25);
		frame.getContentPane().add(btnFecharFuncionarios);
		
		btnNovoFuncionario = new JButton("Novo");
		btnNovoFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNovoFuncionario.setBounds(537, 448, 126, 24);
		frame.getContentPane().add(btnNovoFuncionario);
		
		btnVerBonus = new JButton("Ver Bonus");
		btnVerBonus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVerBonus.setBounds(687, 449, 126, 23);
		frame.getContentPane().add(btnVerBonus);
		
		btnVerFuncionario = new JButton("Visualizar");
		btnVerFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVerFuncionario.setBounds(834, 449, 121, 23);
		frame.getContentPane().add(btnVerFuncionario);
		frame.setResizable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 945, 363);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		model = new DefaultTableModel();
		Object[] column = {"ID", "Nome", "Idade", "Fun????o", "Admiss??o", "Sal??rio Base (R$)"};
		//Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPaneFuncSelecionado = new JScrollPane();
		tableSelecionado = new JTable();
		tableSelecionado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modelSelecionado = new DefaultTableModel();
		Object[] column1 = {"", "", "", "", "", ""};
		modelSelecionado.setColumnIdentifiers(column1);
		tableSelecionado.setModel(modelSelecionado);
		scrollPaneFuncSelecionado.setViewportView(tableSelecionado);
		scrollPaneFuncSelecionado.setBounds(10, 410, 945, 27);
		frame.getContentPane().add(scrollPaneFuncSelecionado);
		
		btnAtualizarLista = new JButton("Atualizar");
		btnAtualizarLista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtualizarLista.setBounds(866, 10, 89, 23);
		frame.getContentPane().add(btnAtualizarLista);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JTextField getTextFieldCampoBuscar() {
		return textFieldCampoBuscar;
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtnBuscarFuncionarios() {
		return btnBuscarFuncionarios;
	}

	public JButton getBtnFecharFuncionarios() {
		return btnFecharFuncionarios;
	}

	public JButton getBtnNovoFuncionario() {
		return btnNovoFuncionario;
	}

	public JButton getBtnVerBonus() {
		return btnVerBonus;
	}

	public JButton getBtnVerFuncionario() {
		return btnVerFuncionario;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public JTable getTableSelecionado() {
		return tableSelecionado;
	}

	public DefaultTableModel getModelSelecionado() {
		return modelSelecionado;
	}

	public JButton getBtnAtualizarLista() {
		return btnAtualizarLista;
	}
}
