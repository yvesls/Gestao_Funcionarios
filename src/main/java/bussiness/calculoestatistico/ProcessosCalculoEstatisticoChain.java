package bussiness.calculoestatistico;

import java.text.ParseException;

public class ProcessosCalculoEstatisticoChain {
	private CalculoEstatisticoAbstract metodo;
	private ProcessosCalculoEstatisticoChain next;

	public ProcessosCalculoEstatisticoChain(CalculoEstatisticoAbstract metodo) {
		this.metodo = metodo;
	}

	public ProcessosCalculoEstatisticoChain(CalculoEstatisticoAbstract metodo, ProcessosCalculoEstatisticoChain next) {
		this.metodo = metodo;
		this.next = next;
	}

	public void setNext(ProcessosCalculoEstatisticoChain next) {
		this.next = next;
	}

	public void execute() throws ParseException {
		metodo.calcular();
		if (metodo == null)
			return;
		if (next != null)
			next.execute();
	}
}
