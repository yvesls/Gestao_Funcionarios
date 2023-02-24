package bussiness.calculoestatistico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.CalculoEstatistico;
import model.Salario;

public class CalculoMenorSalario extends CalculoEstatisticoAbstract {

	public CalculoMenorSalario(List<Salario> salarios) {
		super(salarios);
	}

	@Override
	public void calcular() {
		List<Double> valorSals = new ArrayList<Double>();
		for(Salario sal : salarios) {
			valorSals.add(sal.getSalarioTotal());
		}
		CalculoEstatistico calculo = new CalculoEstatistico("Menor salário", String.valueOf(Collections.min(valorSals)));
		registrar(calculo);
	}
}
