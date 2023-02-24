package bussiness.calculoestatistico;

import java.util.List;

import model.CalculoEstatistico;
import model.Salario;

public class CalculoMedia extends CalculoEstatisticoAbstract {

	public CalculoMedia(List<Salario> salarios) {
		super(salarios);
	}

	@Override
	public void calcular() {
		double media = 0.0;
        for (Salario salario : salarios) {
            media += salario.getSalarioTotal();
        }
        media /= salarios.size();
		CalculoEstatistico calculo = new CalculoEstatistico("Média", media);
		registrar(calculo);
	}
}
