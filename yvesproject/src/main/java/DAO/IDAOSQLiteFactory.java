package DAO;

public interface IDAOSQLiteFactory {
	public FuncionarioSQLiteDAO getFuncionarioDAO();
	public SalarioSQLiteDAO getSalarioDAO();
	public BonusSQLiteDAO getBonusDAO();
	public FaltasSQLiteDAO getFaltasDAO();
}
