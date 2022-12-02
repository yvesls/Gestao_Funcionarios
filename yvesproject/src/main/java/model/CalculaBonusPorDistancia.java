package model;

import bussiness.ConversorData;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class CalculaBonusPorDistancia extends CalculaBonusAbstract {

	public CalculaBonusPorDistancia(Funcionario funcionario) {
		super(funcionario);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void calcular() {
		System.out.println("Por distância.");
		Double valorBonus = 0.0;
		if (!(func.getDistTrab() < 50)) {
			if (func.getDistTrab() >= 50 && func.getDistTrab() < 100) {
				valorBonus = func.getSalario() * 0.02;
			} else if (func.getDistTrab() > 100 && func.getDistTrab() < 150) {
				valorBonus = func.getSalario() * 0.035;
			} else if (func.getDistTrab() > 150 && func.getDistTrab() < 200) {
				valorBonus = func.getSalario() * 0.05;
			} else if (func.getDistTrab() > 250) {
				valorBonus = func.getSalario() * 0.07;
			}
			LocalDate date = LocalDate.now();
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
