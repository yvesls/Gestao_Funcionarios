package DAO.interfaces;

import DAO.BonusSQLiteDAO;
import DAO.CalculoEstatisticoSQLiteDAO;
import DAO.FaltasSQLiteDAO;
import DAO.FuncionarioSQLiteDAO;
import DAO.SalarioSQLiteDAO;

public interface IDAOSQLiteFactory {
	public FuncionarioSQLiteDAO getFuncionarioDAO();
	public SalarioSQLiteDAO getSalarioDAO();
	public BonusSQLiteDAO getBonusDAO();
	public FaltasSQLiteDAO getFaltasDAO();
	public CalculoEstatisticoSQLiteDAO getCalculoEstatisticoDAO();
}
