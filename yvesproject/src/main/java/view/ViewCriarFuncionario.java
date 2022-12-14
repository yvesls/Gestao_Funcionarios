package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class ViewCriarFuncionario {

	private JFrame frame;
	private JTextField textCargo;
	private JTextField textNome;
	private JTextField textIdade;
	private JTextField textSalario;
	private JTextField textAdmissao;
	private JButton btnFecharCriarFuncionario;
	private JButton btnSalvarCriarFuncionario;
	private JTextField TextDistTrab;
	private JButton btnCarregarArquivo;
	
	public ViewCriarFuncionario() {
		this.frame = new JFrame();
		initialize();
	}

	private void initialize() {
		frame.setBounds(100, 100, 569, 285);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCargo.setBounds(10, 28, 42, 24);
		frame.getContentPane().add(lblCargo);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 84, 42, 24);
		frame.getContentPane().add(lblNome);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdade.setBounds(10, 135, 42, 24);
		frame.getContentPane().add(lblIdade);
		
		JLabel lblSalario = new JLabel("Salário:");
		lblSalario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSalario.setBounds(153, 135, 44, 24);
		frame.getContentPane().add(lblSalario);
		
		JLabel lblAdmissao = new JLabel("Admissão:");
		lblAdmissao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAdmissao.setBounds(351, 135, 62, 24);
		frame.getContentPane().add(lblAdmissao);
		
		textCargo = new JTextField();
		textCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textCargo.setBounds(62, 28, 218, 24);
		frame.getContentPane().add(textCargo);
		textCargo.setColumns(10);
		
		textNome = new JTextField();
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNome.setColumns(10);
		textNome.setBounds(62, 85, 476, 24);
		frame.getContentPane().add(textNome);
		
		textIdade = new JTextField();
		textIdade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textIdade.setColumns(10);
		textIdade.setBounds(62, 136, 62, 25);
		frame.getContentPane().add(textIdade);
		
		textSalario = new JTextField();
		textSalario.setBounds(205, 135, 115, 26);
		frame.getContentPane().add(textSalario);
		textSalario.setColumns(10);
		
		textAdmissao = new JTextField();
		textAdmissao.setToolTipText("exemplo: 22/09/2010");
		textAdmissao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAdmissao.setBounds(423, 135, 115, 24);
		frame.getContentPane().add(textAdmissao);
		textAdmissao.setColumns(10);
		
		btnFecharCriarFuncionario = new JButton("Fechar");
		btnFecharCriarFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFecharCriarFuncionario.setBounds(10, 211, 89, 23);
		frame.getContentPane().add(btnFecharCriarFuncionario);
		
		btnSalvarCriarFuncionario = new JButton("Salvar");
		btnSalvarCriarFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalvarCriarFuncionario.setBounds(449, 211, 89, 23);
		frame.getContentPane().add(btnSalvarCriarFuncionario);
		
		btnCarregarArquivo = new JButton("Carregar Arquivo");
		btnCarregarArquivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCarregarArquivo.setBounds(256, 211, 183, 23);
		frame.getContentPane().add(btnCarregarArquivo);
		
		JLabel lblDistTrab = new JLabel("Distância do trabalho (Km):");
		lblDistTrab.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDistTrab.setBounds(290, 32, 172, 17);
		frame.getContentPane().add(lblDistTrab);
		
		TextDistTrab = new JTextField();
		TextDistTrab.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TextDistTrab.setBounds(472, 28, 66, 24);
		frame.getContentPane().add(TextDistTrab);
		TextDistTrab.setColumns(10);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public JTextField getTextCargo() {
		return textCargo;
	}

	public JTextField getTextNome() {
		return textNome;
	}

	public JTextField getTextIdade() {
		return textIdade;
	}

	public JTextField getTextSalario() {
		return textSalario;
	}

	public JTextField getTextAdmissao() {
		return textAdmissao;
	}

	public JButton getBtnFecharCriarFuncionario() {
		return btnFecharCriarFuncionario;
	}

	public JButton getBtnSalvarCriarFuncionario() {
		return btnSalvarCriarFuncionario;
	}

	public JTextField getTextDistTrab() {
		return TextDistTrab;
	}

	public JButton getBtnCarregarArquivo() {
		return btnCarregarArquivo;
	}
	
	
}
