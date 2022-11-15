package bussiness;

import java.text.ParseException;

public class ProcessosCalculoBonusChain {
	private CalculaBonusAbstract metodo;
	private ProcessosCalculoBonusChain next;

	public ProcessosCalculoBonusChain(CalculaBonusAbstract metodo) {
		this.metodo = metodo;
	}

	public ProcessosCalculoBonusChain(CalculaBonusAbstract metodo, ProcessosCalculoBonusChain next) {
		this.metodo = metodo;
		this.next = next;
	}

	public void setNext(ProcessosCalculoBonusChain next) {
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
