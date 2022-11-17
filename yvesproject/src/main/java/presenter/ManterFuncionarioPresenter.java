package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import DAO.DAOSQLiteFactory;
import DAO.DAOSingleton;
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

	public ManterFuncionarioPresenter(final ViewManterFuncionario viewManterFuncionario, Funcionario func) {
		this.func = func;
		this.viewManterFuncionario = viewManterFuncionario;
		try {
			this.setFuncionarioState(new FuncionarioVisualizacaoStates(this));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Ocorreu um erro inesperado ao definir o estado da tela 'manter funcionario', entre em contato com o suporte.",
					"Atenção", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		try {
			this.setaCamposFunc();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um problema ao resgatar um dos campos de visualização de funcionário!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			this.viewManterFuncionario.getFrame().setVisible(false);
			e.printStackTrace();
		}
		this.getAcoesDaView();
		
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
					JOptionPane.showMessageDialog(null, "Erro ao atualizar dados do funcionário!", "Erro",
							JOptionPane.ERROR_MESSAGE);
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
		getViewManterFuncionario().getTextTempoServico().setText(String.valueOf(func.getTempoServico()));
		getViewManterFuncionario().getChckbxFuncMes().setSelected(func.getIsFuncionarioDoMes());
		getViewManterFuncionario().getLblSalarioTotal().setText(String.valueOf(func.getSalarioTotal()));
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

	public boolean salvarFunc() throws NumberFormatException {
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
						Double.valueOf(getViewManterFuncionario().getLblSalarioTotal().getText()),
						Double.valueOf(getViewManterFuncionario().getTextSalario().getText()),
						Integer.valueOf(getViewManterFuncionario().getTextDistTrab().getText()),
						String.valueOf(getViewManterFuncionario().getLblAdmissao().getText()),
						getViewManterFuncionario().getChckbxFuncMes().isEnabled(),
						Double.valueOf(getViewManterFuncionario().getTextTempoServico().getText()));
				this.func.addFalta(new FaltaAoTrabalho(Integer.valueOf(getViewManterFuncionario().getTextFaltas().getText())));
				FuncionarioSQLiteDAO dao = new FuncionarioSQLiteDAO();
				try {
					if (dao.alterarFuncionario(this.func) && alterarFaltasFunc()) {
						return true;
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao atualizar dados do funcionário!", "Erro",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showConfirmDialog(null, "Digite valores válidos!");
			}
		}

		return false;
	}

	public void visualizarFunc() throws Exception {
		state.salvar();
	}

	public Funcionario getFunc() {
		return func;
	}

	public ViewManterFuncionario getViewManterFuncionario() {
		return viewManterFuncionario;
	}

	public int carregarFaltaFuncionarioSelecionado(Funcionario func) throws ParseException, Exception {
		LocalDate data = LocalDate.now();
		DAOSingleton.configurarSingleton(new DAOSQLiteFactory());
		
		FaltaAoTrabalho falta = DAOSingleton.getInstance().getFaltaSqliteDAO().getFaltasFuncionarioEsseMes(func.getFuncId(), data);
		if (falta != null) {
			qtdFalta = falta.getQuantidadeFaltas();
		} else {
			func.addFalta(new FaltaAoTrabalho());
			DAOSingleton.getInstance().getFaltaSqliteDAO().salvar(func.getFaltaTrabalho(), func.getFuncId());
			falta = DAOSingleton.getInstance().getFaltaSqliteDAO().getFaltasFuncionarioEsseMes(func.getFuncId(), data);
			qtdFalta = falta.getQuantidadeFaltas();
		}
		return qtdFalta;
	}
}
