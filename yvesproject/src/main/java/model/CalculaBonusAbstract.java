package model;

import java.text.ParseException;
import java.time.LocalDate;

import DAO.BonusSQLiteDAO;
import DAO.FuncionarioSQLiteDAO;
import DAO.SalarioSQLiteDAO;

public abstract class CalculaBonusAbstract {
	protected Bonus bonus;
	protected Salario salario;
	protected static Funcionario func;
	
	public CalculaBonusAbstract(Funcionario funcionario) {
		func = funcionario;
	}
	
	public void calcular() throws ParseException {
		
	}

	public boolean registrar(Bonus bon, Funcionario func, Salario sal) {
		boolean funcSalvo = false, salarioSalvo = false, bonusSalvo = false;
		LocalDate date = LocalDate.now();
		String data = sal.ConverterData(date);
		
		SalarioSQLiteDAO daoSalario = new SalarioSQLiteDAO();
		FuncionarioSQLiteDAO daoFunc = new FuncionarioSQLiteDAO();
		BonusSQLiteDAO daoBonus = new BonusSQLiteDAO();

		Salario isSalario = daoSalario.getSalarioMesFuncionario(data, func.getFuncId());
		Bonus isBonus = daoBonus.getListBonusFuncMesIdTipo(data, func.getFuncId(), bon.getTipo());
		
		if (isSalario == null) {
			salarioSalvo = daoSalario.salvar(sal, func);
		} else {
			salarioSalvo = daoSalario.alterarSalario(func.getFuncId(), data, sal);
		}

		if (isBonus == null) {
			bonusSalvo = daoBonus.salvar(bon);
		} else {
			bonusSalvo = daoBonus.alterarBonusMesTipo(func.getFuncId(), data, bon, bon.getTipo());
		}

		funcSalvo = daoFunc.alterarFuncionario(func);

		if (funcSalvo && salarioSalvo && bonusSalvo) {
			return true;
		}
		return false;
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
