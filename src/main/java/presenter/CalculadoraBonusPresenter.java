package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import DAO.conexao.DAOSQLiteFactory;
import DAO.conexao.DAOSingleton;
import bussiness.calculobonus.CalculadoraDiferencaEntreDatas;
import bussiness.calculobonus.ConversorData;
import model.Bonus;
import model.CalculoBonusDados;
import model.Funcionario;
import model.Salario;
import service.CalculadoraBonusService;
import service.CalculadoraEstatisticaService;
import view.ViewCalculadoraBonus;

public class CalculadoraBonusPresenter {
	private ViewCalculadoraBonus view;
	private List<Salario> salarios;
	private List<Funcionario> funcs;
	private List<Bonus> bonus;
	private String dataDaProcura;
	private List<Double> valorBonus;
	private List<CalculoBonusDados> objetoCalculoBonus;
	private CalculadoraBonusService calculaBonus;
	private CalculadoraEstatisticaService calculaEstatisticas;

	public CalculadoraBonusPresenter(ViewCalculadoraBonus view) {
		this.view = view;
		this.getAcoesDaView();
	}

	public void getAcoesDaView() {
		view.getBtnCalcular().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (objetoCalculoBonus != null) {
					try {
						for(Funcionario func : funcs) {
							int tempoEmpresa = CalculadoraDiferencaEntreDatas.calcularDiferencaEmAnos(ConversorData.converter(func.getAdmissao()));
							func.setTempoServico(Double.valueOf(tempoEmpresa));
							DAOSingleton.getInstance().getFuncionarioSqliteDAO().alterarFuncionario(func);
						}
						
						for (int i = 0; i < salarios.size(); i++) {
							funcs.get(i).setSalarioTotal(funcs.get(i).getSalario());
							calculaBonus = new CalculadoraBonusService(funcs.get(i));
							calculaBonus.calcular();
						}
						JOptionPane.showMessageDialog(null, "Salário calculado com sucesso!", "Atenção",
								JOptionPane.INFORMATION_MESSAGE);
						LocalDate date = LocalDate.now();
						salarios = DAOSingleton.getInstance().getSalarioSqliteDAO().getTodosSalariosMes(ConversorData.converter(date));
						calculaEstatisticas = new CalculadoraEstatisticaService(salarios);
						calculaEstatisticas.calcular();
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Erro ao executar a operação!", "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					}
					
				}
			}
		});

		view.getBtnBuscar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!view.getTextDataDosFuncionarios().getText().equals("")) {
					dataDaProcura = view.getTextDataDosFuncionarios().getText();
					view.getLblDataCalculo().setText(dataDaProcura);
					if (!carregarSalariosData(view.getTextDataDosFuncionarios().getText())) {

						JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum registro nessa data inserida.",
								"Erro", JOptionPane.ERROR_MESSAGE);
					} else {
						listarFuncionarios();
						gerarObjetoCalculoBonus();
						gerarTabelaCalculoBonus();
					}
				}else {
					JOptionPane.showMessageDialog(null, "É preciso inserir uma data.",
							"Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		view.getBtnListarTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate data = LocalDate.now();
				String date = ConversorData.converter(data);
				view.getLblDataCalculo().setText(date);
				if (!carregarTodosSalarios()) {
					JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum registro.",
							"Erro", JOptionPane.ERROR_MESSAGE);
				} else {
					listarFuncionarios();
					gerarObjetoCalculoBonus();
					gerarTabelaCalculoBonus();
				}
				
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
	
	public boolean carregarTodosSalarios() {
		this.salarios = DAOSingleton.getInstance().getSalarioSqliteDAO().getTodosSalarios();
		if (!this.salarios.isEmpty()) {
			return true;
		}
		return false;
	}

	public boolean carregarSalariosData(String data) {
		this.salarios = DAOSingleton.getInstance().getSalarioSqliteDAO().getTodosSalariosMes(data);
		if (!this.salarios.isEmpty()) {
			return true;
		}
		return false;
	}

	public Funcionario carregarFuncionariosPorId(int idFunc) {
		return DAOSingleton.getInstance().getFuncionarioSqliteDAO().getFuncionariosPorId(idFunc);
	}

	public void listarFuncionarios() {
		this.funcs = new ArrayList<>();
		this.valorBonus = new ArrayList<>();
		for (Salario salario : this.salarios) {
			this.funcs.add(this.carregarFuncionariosPorId(salario.getIdFunc()));
			this.valorBonus.add(carregarBonusPorDataIdFunc(salario.getData(), salario.getIdFunc()));
		}
	}

	public Double carregarBonusPorDataIdFunc(String data, int idFunc) {
		Double valor = 0.0;
		this.bonus = DAOSingleton.getInstance().getBonusSqliteDAO().getListTodosBonusFuncMesId(data, idFunc);
		for (Bonus bonus : this.bonus) {
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
