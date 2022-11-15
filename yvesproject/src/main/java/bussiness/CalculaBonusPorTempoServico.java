package bussiness;

import java.time.LocalDate;
import model.Bonus;
import model.Funcionario;
import model.Salario;

public class CalculaBonusPorTempoServico extends CalculaBonusAbstract {

	public CalculaBonusPorTempoServico(Funcionario funcionario) {
		super(funcionario);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void calcular() {
		Double valorBonus = 0.0;
		
		if(func.getTempoServico() != 0) {
			if(func.getTempoServico() > 0 && func.getTempoServico() <= 5) {
				valorBonus = 0.02;
			}else if(func.getTempoServico() > 5 && func.getTempoServico() <= 10) {
				valorBonus = 0.03;
			}else if(func.getTempoServico() > 10 && func.getTempoServico() <= 15) {
				valorBonus = 0.08;
			}else if(func.getTempoServico() > 15 && func.getTempoServico() <= 20) {
				valorBonus = 0.1;
			}else if(func.getTempoServico() > 20) {
				valorBonus = 0.15;
			}
			LocalDate date = LocalDate.now(); 
			ConversorData.instanciar();
			String data = ConversorData.converter(date);
			this.bonus = new Bonus(func.getFuncId(), "Tempo de serviço", data, Double.valueOf(func.getSalario())*valorBonus);
			this.salario = new Salario(func.getFuncId(), func.getSalario(),  Double.valueOf(func.getSalarioTotal()) + Double.valueOf(func.getSalario())*valorBonus, data);
			func = new Funcionario(func.getFuncId(), func.getNome(), func.getCargo(), func.getIdade(),
					(Double.valueOf(func.getSalarioTotal()) + (Double.valueOf(func.getSalario())*valorBonus)),
					func.getSalario(), func.getDistTrab(), func.getAdmissao(), func.getIsFuncionarioDoMes(), func.getTempoServico());
			registrarCalculos(this.bonus, func, this.salario);
		}
	}

}
