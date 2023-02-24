package bussiness.calculobonus;

import java.util.Calendar;

public class CalculadoraDiferencaEntreDatas {
private static CalculadoraDiferencaEntreDatas conversorData;
	
	public CalculadoraDiferencaEntreDatas() {
		
	}
	
	public static void instanciar() {
		if(conversorData == null) {
			conversorData = new CalculadoraDiferencaEntreDatas();
		}
	}
	
	public static int calcularDiferencaEmAnos(String dataString) {
        String[] partes = dataString.split("/");
        int mes = Integer.parseInt(partes[0]);
        int ano = Integer.parseInt(partes[1]);
        Calendar data = Calendar.getInstance();
        int anoAtual = data.get(Calendar.YEAR);
        int mesAtual = data.get(Calendar.MONTH) + 1;
        int diferenca = (anoAtual - ano) * 12 + (mesAtual - mes);
        int anos = diferenca / 12;
        return anos;
    }
}
