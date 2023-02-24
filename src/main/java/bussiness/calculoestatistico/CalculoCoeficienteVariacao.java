package bussiness.calculoestatistico;

import java.util.List;

import model.CalculoEstatistico;
import model.Salario;

public class CalculoCoeficienteVariacao extends CalculoEstatisticoAbstract {

	public CalculoCoeficienteVariacao(List<Salario> salarios) {
		super(salarios);
	}

	@Override
	public void calcular() {
		Double media = 0.0;
        for (Salario salario : salarios) {
            media += salario.getSalarioTotal();
        }
        media /= salarios.size();
        Double somaQuadrados = 0.0;
        for (Salario salario : salarios) {
            somaQuadrados += Math.pow(salario.getSalarioTotal() - media, 2);
        }
        Double desvioPadrao = Math.sqrt(somaQuadrados / salarios.size());
        Double coeficienteVariacao = (desvioPadrao / media) * 100;
		CalculoEstatistico calculo = new CalculoEstatistico("Coeficiente de variação", coeficienteVariacao);
		registrar(calculo);
	}
}
