package model;

import java.time.LocalDate;

public class Salario {
	private int idFunc;
	private Double salario;
	private Double salarioTotal;
	private String data;
	
	public Salario(int idFunc, Double salario, Double salarioTotal, String data) {
		this.idFunc = idFunc;
		this.salario = salario;
		this.salarioTotal = salarioTotal;
		this.data = data;
	}
	
	public Salario(Double salario, Double salarioTotal) {
		this.salario = salario;
		this.salarioTotal = salarioTotal;
		LocalDate data = LocalDate.now();
		this.data = this.ConverterData(data);
	}
	
	public Salario() {
		
	}

	public int getIdFunc() {
		return idFunc;
	}

	public Double getSalario() {
		return salario;
	}

	public Double getSalarioTotal() {
		return salarioTotal;
	}

	public String getData() {
		return data;
	}
	
	public String ConverterData(LocalDate date) {
		String dataFormatada = "";
		String[] split = date.toString().split("-");
		for(int i = split.length-1; i>=0; i--) {
			dataFormatada += split[i] + "/";
		}
		dataFormatada = dataFormatada.substring(0, dataFormatada.length()-1);
		dataFormatada = dataFormatada.substring(3, dataFormatada.length());
		return dataFormatada;
	}
}
