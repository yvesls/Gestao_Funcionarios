package bussiness.calculobonus;

import java.text.ParseException;
import java.time.LocalDate;
import DAO.conexao.DAOSingleton;
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

	protected void calcular() throws ParseException {

	}

	protected boolean registrarCalculos(Bonus bon, Funcionario func, Salario sal) {
		boolean funcSalvo = false, salarioSalvo = false, bonusSalvo = false;
		LocalDate date = LocalDate.now();
		String data = ConversorData.converter(date);
		Salario isSalario = DAOSingleton.getInstance().getSalarioSqliteDAO().getSalarioMesFuncionario(data,
				func.getFuncId());
		Bonus isBonus = DAOSingleton.getInstance().getBonusSqliteDAO().getListBonusFuncMesIdTipo(data, func.getFuncId(),
				bon.getTipo());
		if (isSalario == null) {
			salarioSalvo = DAOSingleton.getInstance().getSalarioSqliteDAO().salvar(sal, func);
		} else {
			salarioSalvo = DAOSingleton.getInstance().getSalarioSqliteDAO().alterarSalario(func.getFuncId(), data, sal);
		}
		if (isBonus == null) {
			bonusSalvo = DAOSingleton.getInstance().getBonusSqliteDAO().salvar(bon);
		} else {
			bonusSalvo = DAOSingleton.getInstance().getBonusSqliteDAO().alterarBonusMesTipo(func.getFuncId(), data, bon,
					bon.getTipo());
		}
		funcSalvo = DAOSingleton.getInstance().getFuncionarioSqliteDAO().alterarFuncionario(func);
		if (funcSalvo && salarioSalvo && bonusSalvo) {
			return true;
		}
		return false;
	}

	protected boolean registrarCalculos(Bonus bon) {
		Bonus isBonus = DAOSingleton.getInstance().getBonusSqliteDAO().getListBonusFuncMesIdTipo(bon.getData(), bon.getIdFunc(), bon.getTipo());
		if(isBonus != null) {
			DAOSingleton.getInstance().getBonusSqliteDAO().excluirPorTipoMes(bon);
		}
		return true;
	}
}
