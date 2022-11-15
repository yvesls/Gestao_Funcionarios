package model;

import java.time.LocalDate;

import bussiness.ConversorData;

public class Bonus {
	private Double valor;
	private String data;
	private String tipo;
	private int idFunc;
	
	public Bonus(int idFunc) {
		this.idFunc = idFunc;
		this.valor = 0.0;
		this.tipo = "Sem bônus";
		LocalDate date = LocalDate.now();
		ConversorData.instanciar();
		this.data = ConversorData.converter(date);
	}
	
	public Bonus(Double valor, String tipo) {
		this.valor = valor;
		this.tipo = tipo;
		LocalDate date = LocalDate.now();
		ConversorData.instanciar();
		this.data = ConversorData.converter(date);
	}
	
	public Bonus(int idFunc, String tipo, String data, Double valor) {
		this.valor = valor;
		this.tipo = tipo;
        this.data = data;
        this.idFunc = idFunc;
	}
	
	public Bonus(int idFunc, Double valor) {
		this.valor = valor;
        this.idFunc = idFunc;
	}
	
	public Bonus() {
		
	}

	public Double getValor() {
		return valor;
	}

	public String getData() {
		return data;
	}

	public String getTipo() {
		return tipo;
	}
	
	public int getIdFunc() {
		return idFunc;
	}

	@Override
	public String toString() {
		return "Bonus [valor=" + valor + ", data=" + data + ", tipo=" + tipo + ", idFunc=" + idFunc + "]";
	}
}
