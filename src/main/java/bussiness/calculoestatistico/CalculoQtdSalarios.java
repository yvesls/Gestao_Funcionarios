package bussiness.calculoestatistico;

import java.util.List;

import model.CalculoEstatistico;
import model.Salario;

public class CalculoQtdSalarios extends CalculoEstatisticoAbstract {

	public CalculoQtdSalarios(List<Salario> salarios) {
		super(salarios);
	}

	@Override
	public void calcular() {
		int qtd = salarios.size();
		CalculoEstatistico calculo = new CalculoEstatistico("Quantidade de salários", Double.valueOf(qtd));
		registrar(calculo);
	}
}
