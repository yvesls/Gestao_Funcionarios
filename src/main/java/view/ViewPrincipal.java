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
import javax.swing.JPanel;
import java.awt.Color;

public class ViewPrincipal {

	private JFrame frame;
	private JButton btnFuncionarios;
	private JButton btnEstatisticas;
	private JButton btnCalcularSalarios;
	private JLabel lblBuildInserir;
	private JLabel lblPersistenciaInserir;
	private JLabel lblTotalFuncionriosInserir;
	
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
		
		btnFuncionarios = new JButton("Funcionários");
		btnFuncionarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFuncionarios.setBounds(23, 63, 211, 23);
		frame.getContentPane().add(btnFuncionarios);
		
		btnEstatisticas = new JButton("Visualizar Estatísticas");
		btnEstatisticas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEstatisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEstatisticas.setBounds(626, 63, 206, 23);
		frame.getContentPane().add(btnEstatisticas);
		
		JLabel lblNewLabel = new JLabel("Gestão de Funcionários");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.ITALIC, 24));
		lblNewLabel.setBounds(260, 11, 330, 22);
		frame.getContentPane().add(lblNewLabel);
		
		btnCalcularSalarios = new JButton("Salários");
		btnCalcularSalarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCalcularSalarios.setBounds(336, 61, 184, 23);
		frame.getContentPane().add(btnCalcularSalarios);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 413, 854, 41);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblBuild = new JLabel("Build do sistema: ");
		lblBuild.setBounds(10, 11, 116, 19);
		panel.add(lblBuild);
		
		lblBuildInserir = new JLabel("");
		lblBuildInserir.setBounds(118, 11, 128, 19);
		panel.add(lblBuildInserir);
		
		JLabel lblPersistencia = new JLabel("Persistência:");
		lblPersistencia.setBounds(305, 11, 78, 19);
		panel.add(lblPersistencia);
		
		lblPersistenciaInserir = new JLabel("");
		lblPersistenciaInserir.setBounds(388, 11, 183, 19);
		panel.add(lblPersistenciaInserir);
		
		JLabel lblTotalFuncionrios = new JLabel("Total funcionários:");
		lblTotalFuncionrios.setBounds(638, 11, 110, 19);
		panel.add(lblTotalFuncionrios);
		
		lblTotalFuncionriosInserir = new JLabel("");
		lblTotalFuncionriosInserir.setBounds(760, 11, 62, 19);
		panel.add(lblTotalFuncionriosInserir);
		
		ImageIcon imagem = new ImageIcon(getClass().getResource("func.png"));
		JLabel labelImg = new JLabel(imagem);
		labelImg.setVerticalAlignment(SwingConstants.BOTTOM);
		labelImg.setBounds(84, 82, 711, 361);
		frame.getContentPane().add(labelImg);
		
	}

	public JFrame getFrame() {
		return frame;
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

	public JLabel getLblBuildInserir() {
		return lblBuildInserir;
	}

	public JLabel getLblPersistenciaInserir() {
		return lblPersistenciaInserir;
	}

	public JLabel getLblTotalFuncionriosInserir() {
		return lblTotalFuncionriosInserir;
	}
}
