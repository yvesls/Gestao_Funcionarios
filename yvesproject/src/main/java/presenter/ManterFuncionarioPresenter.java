package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import DAO.FaltasSQLiteDAO;
import DAO.FuncionarioSQLiteDAO;
import DAO.SalarioSQLiteDAO;
import model.FaltaAoTrabalho;
import model.Funcionario;
import states.FuncionarioStates;
import states.FuncionarioVisualizacaoStates;
import view.ViewManterFuncionario;

public class ManterFuncionarioPresenter {
	private Funcionario func;
	private ViewManterFuncionario viewManterFuncionario;
	private FuncionarioStates state;
	private int qtdFalta;

	public ManterFuncionarioPresenter(final ViewManterFuncionario viewManterFuncionario, Funcionario func)
			throws Exception {
		this.func = func;
		this.viewManterFuncionario = viewManterFuncionario;
		this.setFuncionarioState(new FuncionarioVisualizacaoStates(this));
		this.getAcoesDaView();
		this.setaCamposFunc();
	}

	public void getAcoesDaView() {
		viewManterFuncionario.getBtnEditarFuncionario().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarFunc();
			}
		});

		viewManterFuncionario.getBtnSalvarModificacaoFuncionario().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (salvarFunc()) {

						JOptionPane.showMessageDialog(null, "Modificação salva com sucesso!", "Atenção",
								JOptionPane.INFORMATION_MESSAGE);
						viewManterFuncionario.getFrame().setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao atualizar dados do funcionário!", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		viewManterFuncionario.getBtnFecharManterFuniconario().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewManterFuncionario.getFrame().setVisible(false);
			}
		});

		viewManterFuncionario.getBtnExcluirFuncionario().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (excluirFunc() && excluirFaltasFunc()) {
					excluirSalariosFunc();
					JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso!", "Atenção",
							JOptionPane.INFORMATION_MESSAGE);
					viewManterFuncionario.getFrame().setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao excluir funcionário!", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void setFuncionarioState(FuncionarioStates funcState) {
		this.state = funcState;
	}

	public void setaCamposFunc() throws Exception {
		getViewManterFuncionario().getLblNome().setText(func.getNome());
		getViewManterFuncionario().getLblCargo().setText(func.getCargo());
		getViewManterFuncionario().getLblIdade().setText(String.valueOf(func.getIdade()));
		getViewManterFuncionario().getLblAdmissao().setText(String.valueOf(func.getAdmissao()));
		getViewManterFuncionario().getLblSalario().setText(String.valueOf(func.getSalario()));
		getViewManterFuncionario().getLblDistanciaTrab().setText(String.valueOf(func.getDistTrab()));
		getViewManterFuncionario().getLblIdFunc().setText(String.valueOf(func.getFuncId()));
		getViewManterFuncionario().getTextFaltas().setText(String.valueOf(carregarFaltaFuncionarioSelecionado(func)));
		getViewManterFuncionario().getLblNumFaltas().setText(String.valueOf(carregarFaltaFuncionarioSelecionado(func)));
	}

	private boolean excluirFunc() {
		FuncionarioSQLiteDAO dao = new FuncionarioSQLiteDAO();
		return dao.excluirFuncionario(this.func.getFuncId());
	}

	public boolean excluirFaltasFunc() {
		FaltasSQLiteDAO daoFalta = new FaltasSQLiteDAO();
		return daoFalta.deletarFaltasFunc(this.func.getFuncId());
	}
	
	public boolean excluirSalariosFunc() {
		SalarioSQLiteDAO daoSalario = new SalarioSQLiteDAO();
		return daoSalario.excluir(this.func.getFuncId());
	}

	public boolean alterarFaltasFunc() {
		FaltasSQLiteDAO daoFalta = new FaltasSQLiteDAO();
		return daoFalta.alterarFaltaFuncionario(this.func);
	}

	public void editarFunc() {
		try {
			this.state.editar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean salvarFunc() throws NumberFormatException, Exception {
		if ((getViewManterFuncionario().getTextCargo().getText() == null
				|| getViewManterFuncionario().getTextCargo().getText().trim().equals(""))
				|| (getViewManterFuncionario().getTextNome().getText() == null
						|| getViewManterFuncionario().getTextNome().getText().trim().equals(""))
				|| (getViewManterFuncionario().getTextSalario().getText() == null
						|| getViewManterFuncionario().getTextSalario().getText().trim().equals(""))
				|| (getViewManterFuncionario().getTextIdade().getText() == null
						|| getViewManterFuncionario().getTextIdade().getText().trim().equals(""))
				|| (getViewManterFuncionario().getTextDistTrab().getText() == null
						|| getViewManterFuncionario().getTextDistTrab().getText().trim().equals(""))) {
			JOptionPane.showConfirmDialog(null, "Há campos vazios!");
		} else {
			try {
				this.func = new Funcionario(Integer.valueOf(getViewManterFuncionario().getLblIdFunc().getText()),
						getViewManterFuncionario().getTextNome().getText(),
						getViewManterFuncionario().getTextCargo().getText(),
						Integer.valueOf(getViewManterFuncionario().getTextIdade().getText()),
						Double.valueOf(getViewManterFuncionario().getTextSalario().getText()),
						Integer.valueOf(getViewManterFuncionario().getTextDistTrab().getText()),
						getViewManterFuncionario().getLblAdmissao().getText(), 0.0);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showConfirmDialog(null, "Digite valores válidos!");
			}
			this.func.addFalta(
					new FaltaAoTrabalho(Integer.valueOf(getViewManterFuncionario().getTextFaltas().getText())));
			FuncionarioSQLiteDAO dao = new FuncionarioSQLiteDAO();

			try {
				if (dao.alterarFuncionario(this.func) && alterarFaltasFunc()) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public void visualizarFunc() {
		try {
			state.salvar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Funcionario getFunc() {
		return func;
	}

	public ViewManterFuncionario getViewManterFuncionario() {
		return viewManterFuncionario;
	}

	public int carregarFaltaFuncionarioSelecionado(Funcionario func) throws Exception {
		LocalDate data = LocalDate.now();
		FaltaAoTrabalho falta = new FaltasSQLiteDAO().getFaltasFuncionarioEsseMes(func.getFuncId(), data);
		if (falta != null) {
			qtdFalta = falta.getQuantidadeFaltas();
		} else {
			func.addFalta(new FaltaAoTrabalho());
			new FaltasSQLiteDAO().salvar(func.getFaltaTrabalho(), func.getFuncId());
			falta = new FaltasSQLiteDAO().getFaltasFuncionarioEsseMes(func.getFuncId(), data);
			qtdFalta = falta.getQuantidadeFaltas();
		}
		return qtdFalta;
	}
}