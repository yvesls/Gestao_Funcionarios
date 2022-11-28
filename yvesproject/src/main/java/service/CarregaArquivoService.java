package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import DAO.DAOSQLiteFactory;
import DAO.DAOSingleton;
import bussiness.ConversorData;
import model.Funcionario;

public class CarregaArquivoService {

	public CarregaArquivoService() {
	}

	public List<Funcionario> lerArquivo() throws IOException {
		
		List<Funcionario> funcs = new ArrayList<>();
		try {
			Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\yvesl\\OneDrive\\Documentos\\GitHub\\funcionarios.csv"));
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
			List<String[]> funcionarios = csvReader.readAll();
			
			for (String[] funcss : funcionarios) {
				int contador = 1;
				Funcionario func = new Funcionario();
				for (String funcsss : funcss) {
					
					if (contador == 2) {
						func.setNome(funcsss);
					}
					else if (contador == 3) {
						func.setCargo(funcsss);
						func.setIdade(0);
					}
					else if (contador == 4) {
						func.setSalario(Double.valueOf(funcsss));
					}
					else if (contador == 5) {
						func.setDistTrab(Double.valueOf(funcsss));
					}
					else if (contador == 6) {
						func.setTempoServico(Double.valueOf(funcsss));
					}
					else if (contador == 7) {
						contador = 1;
						funcs.add(func);
						ConversorData.instanciar();
						funcsss = ConversorData.converter(funcsss);
						func.setAdmissao(funcsss);
						System.out.println(func.toString());
					
					}
					contador++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return funcs;
	}
}
