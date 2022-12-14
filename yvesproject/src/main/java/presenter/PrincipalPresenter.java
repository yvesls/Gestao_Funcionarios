package presenter;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.BonusSQLiteDAO;
import DAO.FaltasSQLiteDAO;
import DAO.FuncionarioSQLiteDAO;
import DAO.SalarioSQLiteDAO;
import view.ViewCalculadoraBonus;
import view.ViewFuncionario;
import view.ViewPrincipal;

public class PrincipalPresenter {
	private ViewPrincipal view;

	public PrincipalPresenter(ViewPrincipal viewPrincipal) {
		this.view = viewPrincipal;
		this.gerarBotoesView();
	}

	public void gerarBotoesView() {
		view.getBtnFuncionarios().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							this.criarBancoDados();
							ViewFuncionario window = new ViewFuncionario();
							window.getFrame().setVisible(true);
							new FuncionarioPresenter(window);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					private void criarBancoDados() throws SQLException {
						new FuncionarioSQLiteDAO().ifIsCriarBd();
						new FaltasSQLiteDAO().ifIsCriarTbFaltas();
						new SalarioSQLiteDAO().ifIsCriarTb();
						new BonusSQLiteDAO().ifIsCriarTb();
					}
				});
			}
		});

		view.getBtnCalcularSalarios().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ViewCalculadoraBonus window = new ViewCalculadoraBonus();
							window.getFrame().setVisible(true);
							new CalculadoraBonusPresenter(window);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		view.getBtnInfoSistema().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						JOptionPane.showMessageDialog(null,
								"Requisito funcional não criado.",
								"Atenção", JOptionPane.INFORMATION_MESSAGE);
					}
				});
			}
		});
		
		view.getBtnEstatisticas().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						JOptionPane.showMessageDialog(null,
								"Requisito funcional não criado.",
								"Atenção", JOptionPane.INFORMATION_MESSAGE);
					}
				});
			}
		});
	}
}
