package bussiness.calculoestatistico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.CalculoEstatistico;
import model.Salario;

public class CalculoMaiorSalario extends CalculoEstatisticoAbstract {

	public CalculoMaiorSalario(List<Salario> salarios) {
		super(salarios);
	}

	@Override
	public void calcular() {
		List<Double> valorSals = new ArrayList<Double>();
		for(Salario sal : salarios) {
			valorSals.add(sal.getSalarioTotal());
		}
		CalculoEstatistico calculo = new CalculoEstatistico("Maior salário", Collections.max(valorSals));
		registrar(calculo);
	}
}
