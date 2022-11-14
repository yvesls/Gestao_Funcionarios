package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import DAO.BonusSQLiteDAO;
import DAO.FuncionarioSQLiteDAO;
import DAO.SalarioSQLiteDAO;
import model.Bonus;
import model.CalculoBonusDados;
import model.Funcionario;
import model.Salario;
import service.CalculadoraBonusService;
import view.ViewCalculadoraBonus;

public class CalculadoraBonusPresenter {
	private ViewCalculadoraBonus view;
	private List<Salario> salarios;
	private List<Funcionario> funcs;
	private String dataDaProcura;
	private List<Double> valorBonus;
	private List<CalculoBonusDados> objetoCalculoBonus;
	private CalculadoraBonusService calculaBonus;

	public CalculadoraBonusPresenter(ViewCalculadoraBonus view) {
		this.view = view;
		this.getAcoesDaView();
	}

	public void getAcoesDaView() {
		view.getBtnCalcular().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (objetoCalculoBonus != null) {
					
					for (int i = 0; i < salarios.size()-1; i++) {
						try {
							calculaBonus = new CalculadoraBonusService(funcs.get(i));
							calculaBonus.calcular();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});

		view.getBtnBuscar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (view.getTextDataDosFuncionarios().getText() != "") {
					dataDaProcura = view.getTextDataDosFuncionarios().getText();
					view.getLblMostraDataCalculo().setText(dataDaProcura);
					if (!carregarSalariosData(view.getTextDataDosFuncionarios().getText())) {

						JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum registro nessa data inserida.",
								"Erro", JOptionPane.ERROR_MESSAGE);
					} else {
						listarFuncionarios();
						gerarObjetoCalculoBonus();
						gerarTabelaCalculoBonus();
					}
				}
			}
		});

		view.getBtnListarTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		view.getBtnBuscar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		view.getBtnFechar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getFrame().setVisible(false);
			}
		});
	}

	public ViewCalculadoraBonus getViewCalculadoraBonus() {
		return view;
	}

	public boolean carregarSalariosData(String data) {
		SalarioSQLiteDAO daoSalario = new SalarioSQLiteDAO();
		this.salarios = daoSalario.getTodosSalariosMes(data);
		if (this.salarios != null) {
			return true;
		}
		return false;
	}

	public Funcionario carregarFuncionariosPorId(int idFunc) {
		FuncionarioSQLiteDAO dao = new FuncionarioSQLiteDAO();
		return dao.getFuncionariosPorId(idFunc);
	}

	public void listarFuncionarios() {
		this.funcs = new ArrayList<>();
		this.valorBonus = new ArrayList<>();
		for (Salario salario : this.salarios) {
			this.funcs.add(this.carregarFuncionariosPorId(salario.getIdFunc()));
			this.valorBonus.add(carregarBonusPorDataIdFunc(dataDaProcura, salario.getIdFunc()));
		}
	}

	public Double carregarBonusPorDataIdFunc(String data, int idFunc) {
		Double valor = 0.0;
		BonusSQLiteDAO dao = new BonusSQLiteDAO();
		for (Bonus bonus : dao.getListTodosBonusFuncMesId(data, idFunc)) {
			valor += bonus.getValor();
		}
		return valor;
	}

	public void gerarObjetoCalculoBonus() {
		this.objetoCalculoBonus = new ArrayList<>();
		for (int i = 0; i < this.salarios.size(); i++) {
			CalculoBonusDados obj = new CalculoBonusDados();
			obj.setIdFunc(this.funcs.get(i).getFuncId());
			obj.setBonusAcumulado(this.valorBonus.get(i));
			obj.setFuncionario(this.funcs.get(i).getNome());
			obj.setSalarioBase(this.salarios.get(i).getSalario());
			obj.setSalarioTotal(this.salarios.get(i).getSalarioTotal());
			obj.setDataSalario(this.salarios.get(i).getData());
			this.objetoCalculoBonus.add(obj);
		}
	}

	public void gerarTabelaCalculoBonus() {
		DefaultTableModel modelo = (DefaultTableModel) view.getTableSalariosCalculados().getModel();
		modelo.setNumRows(0);
		for (CalculoBonusDados obj : this.objetoCalculoBonus) {
			modelo.addRow(new Object[] { obj.getFuncionario(), obj.getDataSalario(), obj.getSalarioBase(),
					obj.getBonusAcumulado(), obj.getSalarioTotal() });
		}
	}
}
