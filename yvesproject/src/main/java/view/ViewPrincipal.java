package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import presenter.PrincipalPresenter;

import java.awt.Font;

public class ViewPrincipal {

	private JFrame frame;
	private JButton btnInfoSistema;
	private JButton btnFuncionarios;
	private JButton btnEstatisticas;
	private JButton btnCalcularSalarios;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPrincipal window = new ViewPrincipal();
					new PrincipalPresenter(window);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewPrincipal() {
		initialize();
		
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 870, 493);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		btnInfoSistema = new JButton("Informações do Sistema");
		btnInfoSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInfoSistema.setBounds(673, 63, 171, 23);
		frame.getContentPane().add(btnInfoSistema);
		
		btnFuncionarios = new JButton("Funcionários");
		btnFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFuncionarios.setBounds(23, 63, 171, 23);
		frame.getContentPane().add(btnFuncionarios);
		
		btnEstatisticas = new JButton("Visualizar Estatísticas");
		btnEstatisticas.setBounds(447, 63, 171, 23);
		frame.getContentPane().add(btnEstatisticas);
		
		JLabel lblNewLabel = new JLabel("Gestão de Funcionários");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.ITALIC, 24));
		lblNewLabel.setBounds(260, 11, 330, 22);
		frame.getContentPane().add(lblNewLabel);
		
		ImageIcon imagem = new ImageIcon(getClass().getResource("func.png"));
		JLabel labelImg = new JLabel(imagem);
		labelImg.setVerticalAlignment(SwingConstants.BOTTOM);
		labelImg.setBounds(66, 93, 711, 361);
		frame.getContentPane().add(labelImg);
		
		btnCalcularSalarios = new JButton("Salários");
		btnCalcularSalarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCalcularSalarios.setBounds(225, 63, 172, 23);
		frame.getContentPane().add(btnCalcularSalarios);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JButton getBtnInfoSistema() {
		return btnInfoSistema;
	}

	public JButton getBtnFuncionarios() {
		return btnFuncionarios;
	}

	public JButton getBtnEstatisticas() {
		return btnEstatisticas;
	}

	public JButton getBtnCalcularSalarios() {
		return btnCalcularSalarios;
	}

}
