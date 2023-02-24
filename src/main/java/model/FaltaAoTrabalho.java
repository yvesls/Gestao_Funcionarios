package model;

import java.text.ParseException;
import java.time.LocalDate;

import bussiness.calculobonus.ConversorData;

public class FaltaAoTrabalho {
	private int quantidade;
	private String data;
	private int IdFunc;
	
	public FaltaAoTrabalho(int idFunc, int quantidade, String data) {
		this.quantidade = quantidade;
		this.IdFunc = idFunc;
		this.data = data;
	}
	
	public FaltaAoTrabalho(int idFunc, int quantidade) {
		this.IdFunc = idFunc;
		this.quantidade = quantidade;
		LocalDate data = LocalDate.now();
		ConversorData.instanciar();
		this.data = ConversorData.converter(data);
	}
	
	public FaltaAoTrabalho() throws ParseException {
	}

	public void alteraQuantidade(int quantidade, LocalDate mes) {
		
	}
	
	public int getQuantidadeFaltas() {
		return quantidade;
	}
	
	public String getDataFalta() {
		
		return data;
	}
	
	public Integer getIdFunc() {
		return IdFunc;
	}

	@Override
	public String toString() {
		return "FaltaAoTrabalho [quantidade=" + quantidade + ", data=" + data + ", IdFunc=" + IdFunc + "]";
	}
}
