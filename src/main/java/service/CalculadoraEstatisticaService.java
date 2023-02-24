package service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import bussiness.calculoestatistico.CalculoCoeficienteVariacao;
import bussiness.calculoestatistico.CalculoDesvioPadrao;
import bussiness.calculoestatistico.CalculoMaiorSalario;
import bussiness.calculoestatistico.CalculoMedia;
import bussiness.calculoestatistico.CalculoMenorSalario;
import bussiness.calculoestatistico.CalculoSomatorio;
import bussiness.calculoestatistico.ProcessosCalculoEstatisticoChain;
import model.Salario;

public class CalculadoraEstatisticaService {
	private ProcessosCalculoEstatisticoChain chain;

	public CalculadoraEstatisticaService(List<Salario> salarios) {
		chain = new ProcessosCalculoEstatisticoChain(new CalculoSomatorio(salarios),
					new ProcessosCalculoEstatisticoChain(new CalculoMedia(salarios),
						new ProcessosCalculoEstatisticoChain(new CalculoCoeficienteVariacao(salarios),
							new ProcessosCalculoEstatisticoChain(new CalculoMaiorSalario(salarios),
								new ProcessosCalculoEstatisticoChain(new CalculoMenorSalario(salarios),
									new ProcessosCalculoEstatisticoChain(new CalculoDesvioPadrao(salarios), null))))));
	}

	public void calcular() throws ParseException {
		chain.execute();
	}
}
