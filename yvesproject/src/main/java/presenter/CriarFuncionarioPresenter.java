package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import DAO.DAOSQLiteFactory;
import DAO.DAOSingleton;
import model.Bonus;
import model.Funcionario;
import model.Salario;
import view.ViewCriarFuncionario;

public class CriarFuncionarioPresenter {
	private ViewCriarFuncionario view;
	private Funcionario func;

	public CriarFuncionarioPresenter(final ViewCriarFuncionario viewCriarFuncionario) {
		this.view = viewCriarFuncionario;
		this.getAcoesDaView();
	}

	public void getAcoesDaView() {
		this.view.getBtnFecharCriarFuncionario().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getFrame().setVisible(false);
			}
		});

		this.view.getBtnSalvarCriarFuncionario().addActionListener((ActionEvent e) -> {
			if ((view.getTextCargo().getText() == null || view.getTextCargo().getText().trim().equals(""))
					|| (view.getTextNome().getText() == null || view.getTextNome().getText().trim().equals(""))
					|| (view.getTextSalario().getText() == null || view.getTextSalario().getText().trim().equals(""))
					|| (view.getTextIdade().getText() == null || view.getTextIdade().getText().trim().equals(""))
					|| (view.getTextAdmissao().getText() == null || view.getTextAdmissao().getText().trim().equals(""))
					|| (view.getTextDistTrab().getText() == null
							|| view.getTextDistTrab().getText().trim().equals(""))) {
				JOptionPane.showConfirmDialog(null, "Existem campos vazios!");
			} else {
				this.func = new Funcionario(view.getTextNome().getText(), view.getTextCargo().getText(),
						Integer.valueOf(view.getTextIdade().getText()), Double.valueOf(view.getTextSalario().getText()),
						Double.valueOf(view.getTextDistTrab().getText()), view.getTextAdmissao().getText(), 0.0);

				if (this.criarNovoFuncionario()) {
					getFuncionarioCadastrado(this.func.getNome());
					Salario sal = new Salario(this.func.getSalario(), this.func.getSalario());
					Bonus bonus = new Bonus(this.func.getFuncId());
					if (this.registrarSalarioFuncionario(sal) && registrarBonusFuncionario(bonus)) {
						JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!", "Atenção",
								JOptionPane.INFORMATION_MESSAGE);
						view.getFrame().setVisible(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário, tente novamente.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		this.view.getBtnCarregarArquivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}

	public boolean criarNovoFuncionario() {
		DAOSingleton.configurarSingleton(new DAOSQLiteFactory());
		return DAOSingleton.getInstance().getFuncionarioSqliteDAO().salvar(this.func);
	}

	public Funcionario getFuncionarioCadastrado(String nome) {
		DAOSingleton.configurarSingleton(new DAOSQLiteFactory());
		this.func = DAOSingleton.getInstance().getFuncionarioSqliteDAO().getFuncionarioPorNome(nome).get(0);
		return this.func;
	}

	public boolean registrarSalarioFuncionario(Salario salario) {
		DAOSingleton.configurarSingleton(new DAOSQLiteFactory());
		return DAOSingleton.getInstance().getSalarioSqliteDAO().salvar(salario, this.func);
	}

	public boolean registrarBonusFuncionario(Bonus bonus) {
		DAOSingleton.configurarSingleton(new DAOSQLiteFactory());
		return DAOSingleton.getInstance().getBonusSqliteDAO().salvar(bonus);
	}
}
