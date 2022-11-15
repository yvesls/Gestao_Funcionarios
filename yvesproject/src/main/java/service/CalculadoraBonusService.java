package service;

import java.text.ParseException;

import bussiness.CalculaBonusFuncionarioMes;
import bussiness.CalculaBonusPorDistancia;
import bussiness.CalculaBonusPorFalta;
import bussiness.CalculaBonusPorTempoServico;
import bussiness.ProcessosCalculoBonusChain;
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
