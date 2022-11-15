package DAO;

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

}
