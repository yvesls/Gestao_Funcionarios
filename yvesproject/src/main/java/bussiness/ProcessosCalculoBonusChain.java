package bussiness;

import java.text.ParseException;

import model.CalculaBonusAbstract;
import model.CalculoBonusDados;
import model.Funcionario;

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

	public void execute() {
		try {
			System.out.println(String.format("Calculando por:" + metodo.toString()));
			metodo.calcular();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		if (metodo == null)
			return;
		if (next != null)
			next.execute();
	}
}
