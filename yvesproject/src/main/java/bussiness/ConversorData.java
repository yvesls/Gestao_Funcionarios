package bussiness;

import java.time.LocalDate;

public class ConversorData {
	private static ConversorData conversorData;
	
	public ConversorData() {
		
	}
	
	public static void instanciar() {
		if(conversorData == null) {
			conversorData = new ConversorData();
		}
	}
	
	public static String converter(LocalDate date) {
		String dataFormatada = "";
		String[] split = date.toString().split("-");
		for(int i = split.length-1; i>=0; i--) {
			dataFormatada += split[i] + "/";
		}
		dataFormatada = dataFormatada.substring(0, dataFormatada.length()-1);
		dataFormatada = dataFormatada.substring(3, dataFormatada.length());
		return dataFormatada;
	}
	
	public static String converter(String date) {
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
