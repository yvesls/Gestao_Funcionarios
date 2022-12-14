package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Funcionario;
import model.Salario;

public class SalarioSQLiteDAO extends DAOSQLiteConexao {

	public boolean salvar(Salario salario, Funcionario func) {
		PreparedStatement stmt = null;
		try {
			conectar();

			String sql = "INSERT INTO tb_salario (id_funcionario, salario, salario_total, "
					+ "data_salario) VALUES (?, ?, ?, ?);";

			stmt = criarStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, func.getFuncId());
			stmt.setDouble(2, salario.getSalario());
			stmt.setDouble(3, salario.getSalarioTotal());
			stmt.setString(4, salario.getData());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					Logger.getLogger(SalarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}

		fechar();
		return true;
	}

	public List<Salario> getListTodosSalariosAtribuidos() {
		List<Salario> listaSal = new ArrayList<>();
		Salario sal = new Salario();

		conectar();
		ResultSet result = null;
		PreparedStatement stmt = null;
		String sql = "" + "SELECT id_funcionario, salario, salario_total, data_salario FROM tb_salario";

		stmt = criarStatement(sql);
		try {
			result = stmt.executeQuery();
			while (result.next()) {
				sal = new Salario(result.getInt("id_funcionario"), result.getDouble("salario"),
						result.getDouble("salario_total"), result.getString("data_salario"));
				listaSal.add(sal);
			}
			fechar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					Logger.getLogger(SalarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}

		return listaSal;
	}

	public void ifIsCriarTb() {
		conectar();
		String sql = "" + "CREATE TABLE IF NOT EXISTS tb_salario("
				+ "    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," + "    id_funcionario INTEGER NOT NULL,"
				+ "    salario DOUBLE NOT NULL," + "    salario_total DOUBLE NOT NULL,"
				+ "    data_salario TEXT NOT NULL UNIQUE,"
				+ "    FOREIGN KEY (id_funcionario) REFERENCES tb_funcionario (id)" + ");";
		PreparedStatement stmt = criarStatement(sql);
		try {
			stmt.executeUpdate();
			fechar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					Logger.getLogger(SalarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}

	}

	public boolean excluir(int idFunc) {
		conectar();
		String sql = "DELETE FROM tb_salario WHERE id_funcionario = ?";
		PreparedStatement stmt = this.criarStatement(sql);
		try {
			stmt.setInt(1, idFunc);
			stmt.executeUpdate();
			fechar();
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger(SalarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
			return false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					Logger.getLogger(SalarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}
		return true;
	}
	
	public List<Salario> getTodosSalarios() {
		conectar();
		List<Salario> listaSal = new ArrayList<>();
		ResultSet result = null;
		PreparedStatement stmt = null;
		Salario sal = new Salario();

		String sql = "SELECT id_funcionario, salario, salario_total, data_salario "
				+ "FROM tb_salario;";
		stmt = this.criarStatement(sql);

		try {
			stmt.executeQuery();
			result = stmt.executeQuery();
			while (result.next()) {
				sal = new Salario(result.getInt("id_funcionario"), result.getDouble("salario"),
						result.getDouble("salario_total"), result.getString("data_salario"));

				listaSal.add(sal);
			}
			fechar();
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger(SalarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
			return null;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					Logger.getLogger(SalarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}
		return listaSal;
	}

	public List<Salario> getTodosSalariosMes(String data) {
		conectar();
		List<Salario> listaSal = new ArrayList<>();
		ResultSet result = null;
		PreparedStatement stmt = null;
		Salario sal = new Salario();

		String sql = "SELECT id_funcionario, salario, salario_total, data_salario "
				+ "FROM tb_salario WHERE data_salario LIKE '%" + data + "';";
		stmt = this.criarStatement(sql);

		try {
			stmt.executeQuery();
			result = stmt.executeQuery();
			while (result.next()) {
				sal = new Salario(result.getInt("id_funcionario"), result.getDouble("salario"),
						result.getDouble("salario_total"), result.getString("data_salario"));

				listaSal.add(sal);
			}
			fechar();
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger(SalarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
			return null;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					Logger.getLogger(SalarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}
		return listaSal;
	}

	public Salario getSalarioMesFuncionario(String data, int idFunc) {
		try {
			for (Salario salario : getTodosSalariosMes(data)) {
				if (salario.getIdFunc() == idFunc) {
					return salario;
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean alterarSalario(int idFunc, String data, Salario sal) {
		conectar();
		String sql = "UPDATE tb_salario SET salario_total=? WHERE id_funcionario = '" + idFunc
				+ "' AND data_salario = '" + data + "'";
		PreparedStatement stmt = criarStatement(sql);
		try {

			stmt.setDouble(1, sal.getSalarioTotal());

			stmt.executeUpdate();
			fechar();
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger(SalarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
			return false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					Logger.getLogger(SalarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}
		return true;
	}
}
