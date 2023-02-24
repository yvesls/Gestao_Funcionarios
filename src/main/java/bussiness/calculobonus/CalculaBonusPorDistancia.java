package bussiness.calculobonus;

import java.time.LocalDate;

import model.Bonus;
import model.Funcionario;
import model.Salario;

public class CalculaBonusPorDistancia extends CalculaBonusAbstract {

	public CalculaBonusPorDistancia(Funcionario funcionario) {
		super(funcionario);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void calcular() {
		Double valorBonus = 0.0;
		LocalDate date = LocalDate.now();
		String data = ConversorData.converter(date);
		System.out.println("distancia");
		if (func.getDistTrab() >= 50.0) {
			if (func.getDistTrab() <= 100) {
				valorBonus = 0.02;
			} else if (func.getDistTrab() > 100 && func.getDistTrab() < 150) {
				valorBonus = 0.035;
			} else if (func.getDistTrab() >= 150 && func.getDistTrab() < 200) {
				valorBonus = 0.05;
			} else if (func.getDistTrab() >= 250) {
				valorBonus = 0.07;
			}
			this.bonus = new Bonus(func.getFuncId(), "Distância do trabalho", data,
					(Double.valueOf(func.getSalario()) * valorBonus));
			this.salario = new Salario(func.getFuncId(), func.getSalario(),
					(Double.valueOf(func.getSalarioTotal()) + (Double.valueOf(func.getSalario()) * valorBonus)), data);
			func = new Funcionario(func.getFuncId(), func.getNome(), func.getCargo(), func.getIdade(),
					(Double.valueOf(func.getSalarioTotal()) + (Double.valueOf(func.getSalario()) * valorBonus)),
					func.getSalario(), Double.valueOf(func.getDistTrab()), func.getAdmissao(), func.getIsFuncionarioDoMes(),
					func.getTempoServico());
			registrarCalculos(this.bonus, func, this.salario);
		}else {
			this.bonus = new Bonus(func.getFuncId(), "Distância do trabalho", data, 0.0);
			registrarCalculos(this.bonus);
		}
	}
}
