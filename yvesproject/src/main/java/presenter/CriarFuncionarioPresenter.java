package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import DAO.BonusSQLiteDAO;
import DAO.FuncionarioSQLiteDAO;
import DAO.SalarioSQLiteDAO;
import model.Bonus;
import model.Funcionario;
import model.Salario;
import view.ViewCriarFuncionario;

public class CriarFuncionarioPresenter {
	private ViewCriarFuncionario view;
	private Funcionario func;
	private String erro;

	public CriarFuncionarioPresenter(final ViewCriarFuncionario viewCriarFuncionario) throws Exception {
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
				this.erro = "Existem campos vazios!";
				JOptionPane.showConfirmDialog(null, erro);
			} else {
				try {
					this.func = new Funcionario(view.getTextNome().getText(), view.getTextCargo().getText(),
							Integer.valueOf(view.getTextIdade().getText()),
							Double.valueOf(view.getTextSalario().getText()),
							Integer.valueOf(view.getTextDistTrab().getText()), view.getTextAdmissao().getText(), 0.0);
					
					try {
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
							JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário!", "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					this.erro = "Digite valores válidos!";
					JOptionPane.showConfirmDialog(null, erro);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
	}

	public boolean criarNovoFuncionario() throws Exception {
		FuncionarioSQLiteDAO dao = new FuncionarioSQLiteDAO();
		return dao.salvar(this.func);
	}
	
	public Funcionario getFuncionarioCadastrado(String nome) {
		FuncionarioSQLiteDAO dao = new FuncionarioSQLiteDAO();
		this.func = dao.getFuncionario(nome).get(0);
		return this.func;
	}

	public boolean registrarSalarioFuncionario(Salario salario) {
		SalarioSQLiteDAO daoSalario = new SalarioSQLiteDAO();
		return daoSalario.salvar(salario, this.func);
	}

	public boolean registrarBonusFuncionario(Bonus bonus) {
		BonusSQLiteDAO daoBonus = new BonusSQLiteDAO();
		return daoBonus.salvar(bonus);
	}
}
