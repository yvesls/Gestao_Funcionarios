package bussiness.calculoestatistico;

import java.util.List;

import model.CalculoEstatistico;
import model.Salario;

public class CalculoSomatorio extends CalculoEstatisticoAbstract {

	public CalculoSomatorio(List<Salario> salarios) {
		super(salarios);
	}

	@Override
	public void calcular() {
		Double soma = 0.0;
		for(Salario sal : salarios) {
			soma += sal.getSalarioTotal();
		}
		CalculoEstatistico calculo = new CalculoEstatistico("Somatório", String.valueOf(soma));
		registrar(calculo);
	}
}
