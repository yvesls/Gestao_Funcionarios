package presenter;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import DAO.conexao.DAOSQLiteFactory;
import DAO.conexao.DAOSingleton;
import bussiness.calculobonus.CalculadoraDiferencaEntreDatas;
import bussiness.calculobonus.ConversorData;
import view.ViewCalculadoraBonus;
import view.ViewCalculoEstatistico;
import view.ViewFuncionario;
import view.ViewPrincipal;

public class PrincipalPresenter {
	private ViewPrincipal view;

	public PrincipalPresenter(ViewPrincipal viewPrincipal) {
		this.view = viewPrincipal;
		ConversorData.instanciar();
		CalculadoraDiferencaEntreDatas.instanciar();
		DAOSingleton.configurarSingleton(new DAOSQLiteFactory());
		this.gerarBotoesView();
		this.exibirBarraSistema();
		try {
			this.criarBancoDados();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void gerarBotoesView() {
		view.getBtnFuncionarios().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ViewFuncionario window = new ViewFuncionario();
							window.getFrame().setVisible(true);
							new FuncionarioPresenter(window);
						} catch (Exception e) {
							e.printStackTrace();
						}
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

		view.getBtnEstatisticas().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						ViewCalculoEstatistico window = new ViewCalculoEstatistico();
						window.getFrame().setVisible(true);
						new CalculoEstatisticoPresenter(window);
					}
				});
			}
		});
	}

	private void criarBancoDados() throws SQLException {
		DAOSingleton.configurarSingleton(new DAOSQLiteFactory());
		DAOSingleton.getInstance().getFuncionarioSqliteDAO().ifIsCriarBd();
		DAOSingleton.getInstance().getFaltaSqliteDAO().ifIsCriarTbFaltas();
		DAOSingleton.getInstance().getSalarioSqliteDAO().ifIsCriarTb();
		DAOSingleton.getInstance().getBonusSqliteDAO().ifIsCriarTb();
		DAOSingleton.getInstance().getCalculoEstatisticoSQLiteDAO().ifIsCriarTb();
	}
	
	public void exibirBarraSistema() {
		DAOSingleton.configurarSingleton(new DAOSQLiteFactory());
		DatabaseMetaData metaData = null;
		String version = ViewPrincipal.class.getPackage().getImplementationVersion();
		metaData = DAOSingleton.getInstance().getFuncionarioSqliteDAO().getMetaData();
		if (metaData == null) {
			view.getLblPersistenciaInserir().setText("Não conectado com o banco de dados");
		} else {
			try {
				view.getLblBuildInserir().setText("1.0-SNAPSHOT");
				view.getLblPersistenciaInserir().setText(metaData.getDriverName() + " " + metaData.getDriverVersion());
				view.getLblTotalFuncionriosInserir().setText(String.valueOf(DAOSingleton.getInstance().getFuncionarioSqliteDAO().getTotalFuncs()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
