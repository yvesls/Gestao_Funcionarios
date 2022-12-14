package model;

import java.text.ParseException;
import java.time.LocalDate;

public class FaltaAoTrabalho {
	private int quantidade;
	private String data;
	private Integer IdFunc;
	
	public FaltaAoTrabalho(int idFunc, int qtdFaltas, String data) {
		this.quantidade = qtdFaltas;
		this.IdFunc = idFunc;
		this.data = data;
	}
	
	public FaltaAoTrabalho(int quantidade) {
		this.quantidade = quantidade;
		LocalDate data = LocalDate.now();
		this.data = this.ConverterData(data);
		this.IdFunc = null;
	}
	
	public FaltaAoTrabalho() throws ParseException {
		this.quantidade = 0;
		LocalDate date = LocalDate.now();
		this.data = this.ConverterData(date);
		this.IdFunc = null;
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
