package model;

import java.time.LocalDate;

import bussiness.calculobonus.ConversorData;

public class CalculoEstatistico {
	private Double valor;
	private String data;
	private String tipo;
	private int id;
	
	public CalculoEstatistico() {
	}
	
	public CalculoEstatistico(int id, String tipo, Double valor, String data) {
		this.id = id;
		this.tipo = tipo;
		this.valor = valor;
		this.data = data;
	}
	
	public CalculoEstatistico(String tipo, Double valor) {
		this.tipo = tipo;
		this.valor = valor;
		LocalDate date = LocalDate.now();
		ConversorData.instanciar();
		this.data = ConversorData.converter(date);
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

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "CalculoEstatistico [valor=" + valor + ", data=" + data + ", tipo=" + tipo + ", id=" + id + "]";
	}
}