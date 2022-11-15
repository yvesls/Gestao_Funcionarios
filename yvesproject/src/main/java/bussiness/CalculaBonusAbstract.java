package bussiness;

import java.text.ParseException;
import java.time.LocalDate;
import DAO.DAOSQLiteFactory;
import DAO.DAOSingleton;
import model.Bonus;
import model.Funcionario;
import model.Salario;

public abstract class CalculaBonusAbstract {
	protected Bonus bonus;
	protected Salario salario;
	protected static Funcionario func;
	
	public CalculaBonusAbstract(Funcionario funcionario) {
		func = funcionario;
	}
	
	public void calcular() throws ParseException {
		
	}

	public boolean registrarCalculos(Bonus bon, Funcionario func, Salario sal) {
		boolean funcSalvo = false, salarioSalvo = false, bonusSalvo = false;
		LocalDate date = LocalDate.now();
		ConversorData.instanciar();
		String data = ConversorData.converter(date);
		DAOSingleton.configurarSingleton(new DAOSQLiteFactory());

		Salario isSalario = DAOSingleton.getDaoSingleton().getSalarioSqliteDAO().getSalarioMesFuncionario(data, func.getFuncId());
		Bonus isBonus =  DAOSingleton.getDaoSingleton().getBonusSqliteDAO().getListBonusFuncMesIdTipo(data, func.getFuncId(), bon.getTipo());

		if (isSalario == null) {
			salarioSalvo =  DAOSingleton.getDaoSingleton().getSalarioSqliteDAO().salvar(sal, func);
		} else {
			salarioSalvo =  DAOSingleton.getDaoSingleton().getSalarioSqliteDAO().alterarSalario(func.getFuncId(), data, sal);
		}

		if (isBonus == null) {
			bonusSalvo =  DAOSingleton.getDaoSingleton().getBonusSqliteDAO().salvar(bon);
		} else {
			bonusSalvo =  DAOSingleton.getDaoSingleton().getBonusSqliteDAO().alterarBonusMesTipo(func.getFuncId(), data, bon, bon.getTipo());
		}

		funcSalvo =  DAOSingleton.getDaoSingleton().getFuncionarioSqliteDAO().alterarFuncionario(func);

		if (funcSalvo && salarioSalvo && bonusSalvo) {
			return true;
		}
		return false;
	}
}
