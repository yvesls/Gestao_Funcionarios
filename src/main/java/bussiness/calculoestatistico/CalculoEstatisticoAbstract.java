package bussiness.calculoestatistico;

import java.util.List;

import DAO.conexao.DAOSingleton;
import model.CalculoEstatistico;
import model.Salario;

public abstract class CalculoEstatisticoAbstract {
	protected List<Salario> salarios;
	
	public CalculoEstatisticoAbstract(List<Salario> salarios) {
		this.salarios = salarios;
	}
	
	protected abstract void calcular();
	
	protected void registrar(CalculoEstatistico calculo) {
		int isCalculo = DAOSingleton.getInstance().getCalculoEstatisticoSQLiteDAO().getCalculoMesTipo(calculo);
		if(isCalculo == -1) {
			DAOSingleton.getInstance().getCalculoEstatisticoSQLiteDAO().salvar(calculo);
		}else {
			DAOSingleton.getInstance().getCalculoEstatisticoSQLiteDAO().alterarCalculoMesTipo(calculo);
		}
	}
}
