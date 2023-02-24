package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import model.Funcionario;

public class CarregaArquivoFuncionariosService {

    private ArrayList<Funcionario> funcs;
    private int quantidadeRegistro;

    public CarregaArquivoFuncionariosService() {
        this.funcs = new ArrayList<>();
        this.quantidadeRegistro = 0;
    }

    public ArrayList<Funcionario> lerArquivo(File caminhoArquivo) {
        Scanner scanner;
        try {
        	int cont = 0;
            scanner = new Scanner(caminhoArquivo);
            String isCsv = caminhoArquivo.getName().substring(caminhoArquivo.getName().length() - 3);
            if (isCsv.equals("csv")) {
                scanner.next();
            } 
            scanner.useDelimiter(",|\\n"); // delimita por vírgula ou nova linha
            while (cont < 1000) {
            	String id = scanner.next();
            	String nome = scanner.next();
            	String cargo = scanner.next();
                int idade = gerarIdadeAleatoria();
                String salario = scanner.next();
                String distanciaTrab = scanner.next();
                String tempoServ = scanner.next();
                String dataContr = converterData(scanner.next());
                Funcionario funcionario = new Funcionario(nome, cargo, idade, Double.valueOf(salario), Double.valueOf(distanciaTrab), dataContr, Double.valueOf(tempoServ)); // nome, cargo, idade, salario_base, distancia, admissao, tempoempresa
                this.funcs.add(funcionario);
                this.quantidadeRegistro++;
                cont++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
           
        }
        
        return funcs;
    }
    
    public static String converterData(String data) {
        try {
            // cria um objeto SimpleDateFormat para fazer a conversão
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");

            // converte a data de entrada para um objeto Date
            Date dataObj = formatoEntrada.parse(data);

            // converte o objeto Date para a data de saída no formato desejado
            String dataFormatada = formatoSaida.format(dataObj);

            return dataFormatada;
        } catch (ParseException e) {
            System.out.println("Erro ao converter data: " + e.getMessage());
            return null;
        }
    }
    
    public static int gerarIdadeAleatoria() {
        Random random = new Random();
        int idade = random.nextInt(46) + 20; // gera um número entre 0 e 45 e soma 20
        return idade;
    }

	public ArrayList<Funcionario> getFuncs() {
		return funcs;
	}

	public int getQuantidadeRegistro() {
		return quantidadeRegistro;
	}
}
