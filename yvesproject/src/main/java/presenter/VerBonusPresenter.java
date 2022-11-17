package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import DAO.DAOSQLiteFactory;
import DAO.DAOSingleton;
import model.Bonus;
import model.Funcionario;
import view.ViewVerBonus;

public class VerBonusPresenter {
	private ViewVerBonus view;
	private Funcionario func;
	private List<Bonus> bonus;

	public VerBonusPresenter(ViewVerBonus viewVerBonus, Funcionario func) {
		this.view = viewVerBonus;
		this.func = func;
		this.getAcoesDaView();
		this.carregarHistoricoBonus(func);
		this.listarHistoricoBonus();
	}

	public void getAcoesDaView() {
		this.view.getBtnFecharVerBonus().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getFrame().setVisible(false);
			}
		});
	}

	public void carregarHistoricoBonus(Funcionario func) {
		DAOSingleton.configurarSingleton(new DAOSQLiteFactory());
		this.bonus = DAOSingleton.getInstance().getBonusSqliteDAO().getListTodosBonusFunc(func.getFuncId());
	}

	public void listarHistoricoBonus() {
		if (!this.bonus.isEmpty()) {
			DefaultTableModel modelo = (DefaultTableModel) view.getTable().getModel();
			modelo.setNumRows(0);
			for (Bonus bonus : this.bonus) {
				modelo.addRow(
						new Object[] { bonus.getData(), this.func.getCargo(), bonus.getTipo(), bonus.getValor() });
			}
		}else {
			JOptionPane.showMessageDialog(null, "Funcionário não possui bônus cadastrados.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			view.getFrame().setVisible(false);
		}

	}
}
