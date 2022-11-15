package DAO;

public class DAOSingleton {
	private static DAOSingleton daoSingleton;
	
	private FuncionarioSQLiteDAO funcionarioSqliteDAO;
	private FaltasSQLiteDAO faltaSqliteDAO;
	private BonusSQLiteDAO bonusSqliteDAO;
	private SalarioSQLiteDAO salarioSqliteDAO;

	private DAOSingleton() {
	}

	private void configurar(IDAOSQLiteFactory daoSqliteFactory) {
		this.salarioSqliteDAO = daoSqliteFactory.getSalarioDAO();
		this.bonusSqliteDAO = daoSqliteFactory.getBonusDAO();
		this.faltaSqliteDAO = daoSqliteFactory.getFaltasDAO();
		this.funcionarioSqliteDAO = daoSqliteFactory.getFuncionarioDAO();
	}

	public static void configurarSingleton(IDAOSQLiteFactory daoSqliteFactory) {
		if (daoSingleton == null) {
			daoSingleton = new DAOSingleton();
		}

		daoSingleton.configurar(daoSqliteFactory);
	}

	public static DAOSingleton getInstance() {
		return daoSingleton;
	}

	public static DAOSingleton getDaoSingleton() {
		return daoSingleton;
	}

	public FuncionarioSQLiteDAO getFuncionarioSqliteDAO() {
		return funcionarioSqliteDAO;
	}

	public FaltasSQLiteDAO getFaltaSqliteDAO() {
		return faltaSqliteDAO;
	}

	public BonusSQLiteDAO getBonusSqliteDAO() {
		return bonusSqliteDAO;
	}

	public SalarioSQLiteDAO getSalarioSqliteDAO() {
		return salarioSqliteDAO;
	}
}
