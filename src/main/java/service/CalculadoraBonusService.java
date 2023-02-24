package service;

import java.text.ParseException;

import bussiness.calculobonus.CalculaBonusFuncionarioMes;
import bussiness.calculobonus.CalculaBonusPorDistancia;
import bussiness.calculobonus.CalculaBonusPorFalta;
import bussiness.calculobonus.CalculaBonusPorTempoServico;
import bussiness.calculobonus.ProcessosCalculoBonusChain;
import model.Funcionario;

public class CalculadoraBonusService {
	private ProcessosCalculoBonusChain chain;

	public CalculadoraBonusService(Funcionario funcionario) {
		chain = new ProcessosCalculoBonusChain(new CalculaBonusFuncionarioMes(funcionario),
				new ProcessosCalculoBonusChain(new CalculaBonusPorDistancia(funcionario),
						new ProcessosCalculoBonusChain(new CalculaBonusPorFalta(funcionario),
								new ProcessosCalculoBonusChain(new CalculaBonusPorTempoServico(funcionario), null))));
	}

	public void calcular() throws ParseException {
		chain.execute();
	}
}
