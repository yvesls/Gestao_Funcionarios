package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class ViewManterFuncionario {

	private JFrame frame;
	private JButton btnFecharManterFuniconario;
	private JButton btnSalvarModificacaoFuncionario;
	private JButton btnExcluirFuncionario;
	private JButton btnEditarFuncionario;
	private JLabel lblNome;
	private JLabel lblCargo;
	private JLabel lblIdade;
	private JLabel lblAdmissao;
	private JLabel lblSalario;
	private JLabel lblDistanciaTrab;
	private JLabel lblIdFunc;
	private JTextField textNome;
	private JTextField textSalario;
	private JTextField textDistTrab;
	private JTextField textCargo;
	private JTextField textIdade;
	private JCheckBox chckbxFuncMes;
	private JLabel lblNumFaltas;
	private JTextField textFaltas;
	private JTextField textTempoServico;
	private JLabel lblTempoServico;
	/**
	 * Create the application.
	 */
	public ViewManterFuncionario() {
		this.frame = new JFrame();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setBounds(100, 100, 636, 270);
		frame.getContentPane().setLayout(null);
		
		btnFecharManterFuniconario = new JButton("Fechar");
		btnFecharManterFuniconario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFecharManterFuniconario.setBounds(7, 197, 89, 23);
		frame.getContentPane().add(btnFecharManterFuniconario);
		
		btnSalvarModificacaoFuncionario = new JButton("Salvar");
		btnSalvarModificacaoFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalvarModificacaoFuncionario.setBounds(520, 197, 89, 23);
		frame.getContentPane().add(btnSalvarModificacaoFuncionario);
		
		btnExcluirFuncionario = new JButton("Excluir");
		btnExcluirFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcluirFuncionario.setBounds(322, 197, 89, 23);
		frame.getContentPane().add(btnExcluirFuncionario);
		
		btnEditarFuncionario = new JButton("Editar");
		btnEditarFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditarFuncionario.setBounds(421, 197, 89, 23);
		frame.getContentPane().add(btnEditarFuncionario);
		
		lblNome = new JLabel("");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(66, 11, 334, 23);
		frame.getContentPane().add(lblNome);
		
		lblCargo = new JLabel("");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCargo.setBounds(381, 54, 228, 23);
		frame.getContentPane().add(lblCargo);
		
		lblIdade = new JLabel("");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIdade.setBounds(385, 102, 47, 23);
		frame.getContentPane().add(lblIdade);
		
		lblAdmissao = new JLabel("");
		lblAdmissao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAdmissao.setBounds(207, 54, 98, 23);
		frame.getContentPane().add(lblAdmissao);
		
		lblSalario = new JLabel("");
		lblSalario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSalario.setBounds(472, 11, 127, 23);
		frame.getContentPane().add(lblSalario);
		
		lblDistanciaTrab = new JLabel("");
		lblDistanciaTrab.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDistanciaTrab.setBounds(180, 102, 104, 23);
		frame.getContentPane().add(lblDistanciaTrab);
		
		JLabel lblNome_2 = new JLabel("Nome:");
		lblNome_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome_2.setBounds(10, 11, 53, 23);
		frame.getContentPane().add(lblNome_2);
		
		JLabel lblCargo_2 = new JLabel("Cargo:");
		lblCargo_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCargo_2.setBounds(318, 54, 53, 23);
		frame.getContentPane().add(lblCargo_2);
		
		JLabel lblAdmissao_1 = new JLabel("Admissão:");
		lblAdmissao_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAdmissao_1.setBounds(122, 54, 75, 23);
		frame.getContentPane().add(lblAdmissao_1);
		
		JLabel lblDistnciaDoTrabalho = new JLabel("Distância do Trabalho:");
		lblDistnciaDoTrabalho.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDistnciaDoTrabalho.setBounds(10, 102, 160, 23);
		frame.getContentPane().add(lblDistnciaDoTrabalho);
		
		JLabel lblIdade_2 = new JLabel("Idade:");
		lblIdade_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIdade_2.setBounds(322, 102, 47, 23);
		frame.getContentPane().add(lblIdade_2);
		
		JLabel lblSalrio = new JLabel("Salário:");
		lblSalrio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSalrio.setBounds(410, 11, 60, 23);
		frame.getContentPane().add(lblSalrio);
		
		lblIdFunc = new JLabel("");
		lblIdFunc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIdFunc.setBounds(41, 54, 75, 23);
		frame.getContentPane().add(lblIdFunc);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId.setBounds(10, 54, 31, 23);
		frame.getContentPane().add(lblId);
		
		textNome = new JTextField();
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNome.setBounds(66, 14, 334, 20);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);
		
		textSalario = new JTextField();
		textSalario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textSalario.setBounds(472, 14, 137, 20);
		frame.getContentPane().add(textSalario);
		textSalario.setColumns(10);
		
		textDistTrab = new JTextField();
		textDistTrab.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textDistTrab.setBounds(178, 105, 127, 20);
		frame.getContentPane().add(textDistTrab);
		textDistTrab.setColumns(10);
		
		textCargo = new JTextField();
		textCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textCargo.setBounds(381, 57, 229, 20);
		frame.getContentPane().add(textCargo);
		textCargo.setColumns(10);
		
		textIdade = new JTextField();
		textIdade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textIdade.setBounds(384, 102, 226, 20);
		frame.getContentPane().add(textIdade);
		textIdade.setColumns(10);
		
		JLabel lblFaltas = new JLabel("Faltas no mês:");
		lblFaltas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFaltas.setBounds(10, 158, 106, 14);
		frame.getContentPane().add(lblFaltas);
		
		chckbxFuncMes = new JCheckBox("Funcionário do mês");
		chckbxFuncMes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxFuncMes.setBounds(207, 154, 183, 23);
		frame.getContentPane().add(chckbxFuncMes);
		
		lblNumFaltas = new JLabel("0");
		lblNumFaltas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumFaltas.setBounds(126, 157, 38, 16);
		frame.getContentPane().add(lblNumFaltas);
		
		textFaltas = new JTextField();
		textFaltas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFaltas.setBounds(126, 155, 50, 20);
		frame.getContentPane().add(textFaltas);
		textFaltas.setColumns(10);
		
		lblTempoServico = new JLabel("Tempo de serviço:");
		lblTempoServico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTempoServico.setBounds(381, 160, 129, 14);
		frame.getContentPane().add(lblTempoServico);
		
		textTempoServico = new JTextField();
		textTempoServico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textTempoServico.setBounds(523, 157, 86, 20);
		frame.getContentPane().add(textTempoServico);
		textTempoServico.setColumns(10);
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public JButton getBtnFecharManterFuniconario() {
		return btnFecharManterFuniconario;
	}

	public JButton getBtnSalvarModificacaoFuncionario() {
		return btnSalvarModificacaoFuncionario;
	}

	public JButton getBtnExcluirFuncionario() {
		return btnExcluirFuncionario;
	}

	public JButton getBtnEditarFuncionario() {
		return btnEditarFuncionario;
	}

	public JLabel getLblNome() {
		return lblNome;
	}

	public JLabel getLblCargo() {
		return lblCargo;
	}

	public JLabel getLblIdade() {
		return lblIdade;
	}

	public JLabel getLblAdmissao() {
		return lblAdmissao;
	}

	public JLabel getLblSalario() {
		return lblSalario;
	}

	public JLabel getLblDistanciaTrab() {
		return lblDistanciaTrab;
	}

	public JLabel getLblIdFunc() {
		return lblIdFunc;
	}

	public JTextField getTextNome() {
		return textNome;
	}

	public JTextField getTextSalario() {
		return textSalario;
	}

	public JTextField getTextDistTrab() {
		return textDistTrab;
	}

	public JTextField getTextCargo() {
		return textCargo;
	}

	public JTextField getTextIdade() {
		return textIdade;
	}

	public JCheckBox getChckbxFuncMes() {
		return chckbxFuncMes;
	}

	public JTextField getTextFaltas() {
		return textFaltas;
	}

	public JLabel getLblNumFaltas() {
		return lblNumFaltas;
	}

	public JTextField getTextTempoServico() {
		return textTempoServico;
	}

	public JLabel getLblTempoServico() {
		return lblTempoServico;
	}
}
