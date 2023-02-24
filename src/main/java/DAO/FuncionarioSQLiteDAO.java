package DAO;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.conexao.DAOSQLiteConexao;
import DAO.interfaces.IFuncionarioSQLiteDAO;
import model.Funcionario;

public class FuncionarioSQLiteDAO extends DAOSQLiteConexao implements IFuncionarioSQLiteDAO {

	public int salvar(Funcionario funcionario) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		int idGerado = -1;
		try {
			conectar();

			String sql = "INSERT INTO tb_funcionario (" + "nome_funcionario, " + "dist_trabalho, " + "idade, "
					+ "cargo, " + "admissao," + "salario, " + "funcionario_mes, " + "salario_total, "
					+ "tempo_servico) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

			stmt = criarStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, funcionario.getNome());
			stmt.setDouble(2, funcionario.getDistTrab());
			stmt.setInt(3, funcionario.getIdade());
			stmt.setString(4, funcionario.getCargo());
			stmt.setString(5, String.valueOf(funcionario.getAdmissao()));
			stmt.setDouble(6, funcionario.getSalario());
			stmt.setBoolean(7, false);
			stmt.setDouble(8, funcionario.getSalario());
			stmt.setDouble(9, funcionario.getTempoServico());

			stmt.executeUpdate();
			result = stmt.getGeneratedKeys();
			if (result.next()) {
				idGerado = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					Logger.getLogger(FuncionarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}

		fechar();
		return idGerado;
	}

	public List<Funcionario> getListFuncDAO() {
		List<Funcionario> listaFunc = new ArrayList<>();
		Funcionario func = new Funcionario();

		conectar();
		ResultSet result = null;
		PreparedStatement stmt = null;
		String sql = "" + "SELECT id, " + "nome_funcionario, " + "dist_trabalho, " + "idade, " + "cargo, " + "admissao,"
				+ "salario, funcionario_mes, tempo_servico, ROUND(salario_total, 2) salario_total " + "FROM tb_funcionario";

		stmt = criarStatement(sql);
		try {
			result = stmt.executeQuery();
			while (result.next()) {
				func = new Funcionario(result.getInt("id"), result.getString("nome_funcionario").replaceAll("\"", ""),
						result.getString("cargo").replaceAll("\"", ""), result.getInt("idade"), result.getDouble("salario_total"),
						result.getDouble("salario"), result.getDouble("dist_trabalho"), result.getString("admissao"),
						result.getBoolean("funcionario_mes"), result.getDouble("tempo_servico"));
				listaFunc.add(func);
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
					Logger.getLogger(FuncionarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}
		return listaFunc;
	}

	public int getTotalFuncs() {
		conectar();
		ResultSet result = null;
		int qtd = -1;
		PreparedStatement stmt = null;
		String sql = "" + "SELECT COUNT(*) FROM tb_funcionario";

		stmt = criarStatement(sql);
		try {
			result = stmt.executeQuery();
			qtd = result.getInt(1);
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
					Logger.getLogger(FuncionarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}
		return qtd;
	}

	public List<Funcionario> getListFuncDAOMes(String data) {
		List<Funcionario> listaFunc = new ArrayList<>();
		Funcionario func = new Funcionario();

		conectar();
		ResultSet result = null;
		PreparedStatement stmt = null;
		String sql = "" + "SELECT id, " + "nome_funcionario, " + "dist_trabalho, " + "cargo, " + "admissao,"
				+ "salario, tempo_servico, ROUND(salario_total, 2) salario_total, funcionario_mes "
				+ "FROM tb_funcionario WHERE data_salario LIKE '%" + data + "';";

		stmt = criarStatement(sql);
		try {
			result = stmt.executeQuery();
			while (result.next()) {
				func = new Funcionario(result.getInt("id"), result.getString("nome_funcionario").replaceAll("\"", ""),
						result.getString("cargo").replaceAll("\"", ""), result.getInt("idade"), result.getDouble("salario_total"),
						result.getDouble("salario"), result.getDouble("dist_trabalho"), result.getString("admissao"),
						result.getBoolean("funcionario_mes"), result.getDouble("tempo_servico"));
				listaFunc.add(func);
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
					Logger.getLogger(FuncionarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}

		return listaFunc;
	}

	public void ifIsCriarBd() {
		conectar();
		String sql = "CREATE TABLE IF NOT EXISTS tb_funcionario ("
				+ "  id               INTEGER      PRIMARY KEY AUTOINCREMENT UNIQUE,"
				+ "  nome_funcionario VARCHAR (20) NOT NULL," + "	 dist_trabalho    INT (5)      NOT NULL,"
				+ "	 idade            INT (2)      NOT NULL," + "	 cargo            VARCHAR (20) NOT NULL,"
				+ "	 admissao         TEXT NOT NULL," + "salario DOUBLE (10)  NOT NULL, "
				+ "salario_total DOUBLE (10)  NOT NULL, " + "tempo_servico DOUBLE (5) NOT NULL,"
				+ "funcionario_mes BOOLEAN NOT NULL" + "" + ");";
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
					Logger.getLogger(FuncionarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}

	}

	public boolean excluirFuncionario(int pCodigo) {
		conectar();
		String sql = "DELETE FROM tb_funcionario WHERE id = '" + pCodigo + "'";
		PreparedStatement stmt = this.criarStatement(sql);
		try {
			stmt.executeUpdate();
			fechar();
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger(FuncionarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
			return false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					Logger.getLogger(FuncionarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}
		return true;
	}

	public List<Funcionario> getFuncionarioPorNome(String nome) {
		conectar();
		List<Funcionario> listaFunc = new ArrayList<>();
		ResultSet result = null;
		PreparedStatement stmt = null;
		Funcionario func = new Funcionario();

		String sql = "SELECT id, nome_funcionario, dist_trabalho, idade, cargo, admissao,"
				+ "salario, ROUND(salario_total, 2) salario_total, tempo_servico, funcionario_mes FROM tb_funcionario WHERE nome_funcionario LIKE '%"
				+ nome + "%';";
		stmt = this.criarStatement(sql);

		try {
			stmt.executeQuery();
			result = stmt.executeQuery();
			while (result.next()) {
				func = new Funcionario(result.getInt("id"), result.getString("nome_funcionario").replaceAll("\"", ""),
						result.getString("cargo").replaceAll("\"", ""), result.getInt("idade"), result.getDouble("salario_total"),
						result.getDouble("salario"), result.getDouble("dist_trabalho"), result.getString("admissao"),
						result.getBoolean("funcionario_mes"), result.getDouble("tempo_servico"));

				listaFunc.add(func);
			}
			fechar();
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger(FuncionarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
			return null;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					Logger.getLogger(FuncionarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}
		return listaFunc;
	}

	public Funcionario getFuncionariosPorId(int idFunc) {
		conectar();
		ResultSet result = null;
		PreparedStatement stmt = null;
		Funcionario func = new Funcionario();

		String sql = "SELECT id, nome_funcionario, idade, cargo, dist_trabalho, admissao, salario, ROUND(salario_total, 2) salario_total, funcionario_mes, tempo_servico FROM tb_funcionario WHERE id = '"
				+ idFunc + "';";
		stmt = this.criarStatement(sql);

		try {
			stmt.executeQuery();
			result = stmt.executeQuery();
			System.out.println(result.toString());
			if (result != null) {
				func = new Funcionario(result.getInt("id"), result.getString("nome_funcionario").replaceAll("\"", ""),
						result.getString("cargo").replaceAll("\"", ""), result.getInt("idade"), result.getDouble("salario_total"),
						result.getDouble("salario"), result.getDouble("dist_trabalho"), result.getString("admissao"),
						result.getBoolean("funcionario_mes"), result.getDouble("tempo_servico"));
			}

			fechar();
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger(FuncionarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
			return null;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					Logger.getLogger(FuncionarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}
		return func;
	}

	public boolean alterarFuncionario(Funcionario func) {
		conectar();
		String sql = "UPDATE tb_funcionario SET nome_funcionario=?, "
				+ "dist_trabalho=?, idade=?, cargo=?, admissao=?, salario=?, funcionario_mes=?, salario_total=?, tempo_servico = ? WHERE id = '"
				+ func.getFuncId() + "'";
		PreparedStatement stmt = criarStatement(sql);
		try {
			stmt.setString(1, func.getNome());
			stmt.setDouble(2, func.getDistTrab());
			stmt.setInt(3, func.getIdade());
			stmt.setString(4, func.getCargo());
			stmt.setString(5, String.valueOf(func.getAdmissao()));
			stmt.setDouble(6, func.getSalario());
			stmt.setBoolean(7, func.getIsFuncionarioDoMes());
			stmt.setDouble(8, func.getSalarioTotal());
			stmt.setDouble(9, func.getTempoServico());
			stmt.executeUpdate();
			fechar();
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger(FuncionarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
			return false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					Logger.getLogger(FuncionarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}
		return true;
	}

	public DatabaseMetaData getMetaData() {
		conectar();
		try {
			return this.getConexao().getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		fechar();
		return null;
	}
}
