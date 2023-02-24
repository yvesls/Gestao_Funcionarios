package DAO.conexao;

import DAO.BonusSQLiteDAO;
import DAO.CalculoEstatisticoSQLiteDAO;
import DAO.FaltasSQLiteDAO;
import DAO.FuncionarioSQLiteDAO;
import DAO.SalarioSQLiteDAO;
import DAO.interfaces.IDAOSQLiteFactory;

public class DAOSQLiteFactory implements IDAOSQLiteFactory {

	@Override
	public FuncionarioSQLiteDAO getFuncionarioDAO() {
		return new FuncionarioSQLiteDAO();
	}

	@Override
	public SalarioSQLiteDAO getSalarioDAO() {
		return new SalarioSQLiteDAO();
	}

	@Override
	public FaltasSQLiteDAO getFaltasDAO() {
		return new FaltasSQLiteDAO();
	}

	@Override
	public BonusSQLiteDAO getBonusDAO() {
		return new BonusSQLiteDAO();
	}

	@Override
	public CalculoEstatisticoSQLiteDAO getCalculoEstatisticoDAO() {
		return new CalculoEstatisticoSQLiteDAO();
	}

}
