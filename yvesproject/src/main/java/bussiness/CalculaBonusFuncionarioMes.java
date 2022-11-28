package bussiness;

import java.time.LocalDate;
import model.Bonus;
import model.Funcionario;
import model.Salario;

public class CalculaBonusFuncionarioMes extends CalculaBonusAbstract {

	public CalculaBonusFuncionarioMes(Funcionario funcionario) {
		super(funcionario);
	}

	@Override
	public void calcular() {
		if(func.getIsFuncionarioDoMes()) {
			
			LocalDate date = LocalDate.now();ConversorData.instanciar();
			String data = ConversorData.converter(date);
			this.bonus = new Bonus(func.getFuncId(), "Funcionario do mês", data, 200.0);
			this.salario = new Salario(func.getFuncId(), func.getSalario(),  Double.valueOf(func.getSalarioTotal()) + 200, data);
			func = new Funcionario(func.getFuncId(), func.getNome(), func.getCargo(), func.getIdade(),
					Double.valueOf(func.getSalarioTotal()) + 200, func.getSalario(), Double.valueOf(func.getDistTrab()),
					func.getAdmissao(), func.getIsFuncionarioDoMes(), func.getTempoServico());
			registrarCalculos(this.bonus, func, this.salario);
		}
	}
}
