package model;

import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import DAO.FaltasSQLiteDAO;
import DAO.SalarioSQLiteDAO;
import bussiness.ConversorData;

public class CalculaBonusPorFalta extends CalculaBonusAbstract {

	public CalculaBonusPorFalta(Funcionario funcionario) {
		super(funcionario);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void calcular() throws ParseException {
		System.out.println("Por falta.");
		FaltaAoTrabalho falta = new FaltaAoTrabalho();
		LocalDate date = LocalDate.now();
		Double valorBonus = 0.0;
		
		FaltasSQLiteDAO daoFalta = new FaltasSQLiteDAO();
		falta = daoFalta.getFaltasFuncionarioEsseMes(func.getFuncId(), date);
		
		if(falta.getQuantidadeFaltas() < 6 && falta != null) {
			if(falta.getQuantidadeFaltas() == 0) {
				valorBonus = 0.05;
			}else if(falta.getQuantidadeFaltas() > 0 && falta.getQuantidadeFaltas() < 4) {
				valorBonus = 0.03;
			}else if(falta.getQuantidadeFaltas() > 3) {
				valorBonus = 0.01;
			}
                        ConversorData.instanciar();
			String data = ConversorData.converter(date);
			this.bonus = new Bonus(func.getFuncId(), "Distância do trabalho", data, Double.valueOf(func.getSalario())*valorBonus);
			this.salario = new Salario(func.getFuncId(), func.getSalario(),  Double.valueOf(func.getSalarioTotal()) + Double.valueOf(func.getSalario())*valorBonus, data);
			func = new Funcionario(func.getFuncId(), func.getNome(), func.getCargo(), func.getIdade(),
					Double.valueOf(func.getSalarioTotal()) + Double.valueOf(func.getSalario())*valorBonus, func.getSalario(), func.getDistTrab(), func.getAdmissao(), func.getIsFuncionarioDoMes(), func.getTempoServico());
			if(registrar(this.bonus, func, this.salario)) {
				JOptionPane.showMessageDialog(null, "Salário calculado com sucesso!", "Atenção",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}

}
