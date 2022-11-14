package service;

import java.text.ParseException;

import bussiness.ProcessosCalculoBonusChain;
import model.CalculaBonusFuncionarioMes;
import model.CalculaBonusPorDistancia;
import model.CalculaBonusPorFalta;
import model.CalculaBonusPorTempoServico;
import model.CalculoBonusDados;
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
