package model;

import java.time.LocalDate;

import javax.swing.JOptionPane;

public class CalculaBonusFuncionarioMes extends CalculaBonusAbstract {

	public CalculaBonusFuncionarioMes(Funcionario funcionario) {
		super(funcionario);
	}

	@Override
	public void calcular() {
		System.out.println("Funcionario do mês.");
		
		if(func.getIsFuncionarioDoMes()) {
			LocalDate date = LocalDate.now();
			String data = ConverterData(date);
			this.bonus = new Bonus(func.getFuncId(), "Funcionario do mês", data, 200.0);
			this.salario = new Salario(func.getFuncId(), func.getSalario(),  Double.valueOf(func.getSalarioTotal()) + 200, data);
			func = new Funcionario(func.getFuncId(), func.getNome(), func.getCargo(), func.getIdade(),
					Double.valueOf( func.getSalarioTotal() )+ 200, func.getSalario(), func.getDistTrab(), func.getAdmissao(), func.getIsFuncionarioDoMes(), func.getTempoServico());
			if(registrar(this.bonus, func, this.salario)) {
				JOptionPane.showMessageDialog(null, "Salário calculado com sucesso!", "Atenção",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
