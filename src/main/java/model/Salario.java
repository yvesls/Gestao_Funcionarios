package model;

import java.time.LocalDate;

import bussiness.calculobonus.ConversorData;

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
	
	public Salario(int idFunc, Double salario, Double salarioTotal) {
		this.idFunc = idFunc;
		this.salario = salario;
		this.salarioTotal = salarioTotal;
		LocalDate data = LocalDate.now();
		ConversorData.instanciar();
		this.data = ConversorData.converter(data);
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
}
