package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bonus;
import model.Funcionario;

public class BonusSQLiteDAO extends ConexaoFactory {

	public boolean salvar(Bonus bonus) {
		PreparedStatement stmt = null;
		try {
			conectar();

			String sql = "INSERT INTO tb_bonus (id_funcionario, tipo_bonus, data_bonus, valor_bonus) VALUES (?, ?, ?, ?);";

			stmt = criarStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, bonus.getIdFunc());
			stmt.setString(2, bonus.getTipo());
			stmt.setString(3, bonus.getData());
			stmt.setDouble(4, bonus.getValor());
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
					Logger.getLogger(BonusSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}

		fechar();
		return true;
	}

	public List<Bonus> getListTodosBonusFUncionarioMes() {
		List<Bonus> listaBonus = new ArrayList<>();
		LocalDate data = LocalDate.now();
		String mes = data.getMonth().toString();
		Bonus bonus = new Bonus();

		conectar();
		ResultSet result = null;
		PreparedStatement stmt = null;
		String sql = ""
				+ "SELECT id_funcionario, tipo_bonus, data_bonus, valor_bonus FROM tb_bonus WHERE data_bonus LIKE '%"
				+ mes + "';";

		stmt = criarStatement(sql);
		try {
			result = stmt.executeQuery();
			while (result.next()) {
				bonus = new Bonus(result.getInt("id_funcionario"), result.getString("tipo_bonus"),
						result.getString("data_bonus"), result.getDouble("valor_bonus"));
				listaBonus.add(bonus);
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
					Logger.getLogger(BonusSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}

		return listaBonus;
	}

	public List<Bonus> getListTodosBonusFuncMesId(String data, int idFunc) {
		conectar();
		List<Bonus> listaBonus = new ArrayList<>();
		Bonus bonus = new Bonus();
		ResultSet result = null;
		PreparedStatement stmt = null;
		String sql = "SELECT id_funcionario, valor_bonus FROM tb_bonus WHERE data_bonus = '" + data
				+ "' AND id_funcionario ='" + idFunc + "';";

		stmt = this.criarStatement(sql);
		try {
			result = stmt.executeQuery();
			while (result.next()) {
				bonus = new Bonus(result.getInt("id_funcionario"), result.getDouble("valor_bonus"));
				listaBonus.add(bonus);
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
					Logger.getLogger(BonusSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}
		return listaBonus;
	}

	public Bonus getListBonusFuncMesIdTipo(String data, int idFunc, String tipo) {
		conectar();
		Bonus bonus = null;
		ResultSet result = null;
		PreparedStatement stmt = null;
		String sql = "SELECT id_funcionario, valor_bonus FROM tb_bonus WHERE data_bonus = '" + data
				+ "' AND id_funcionario ='" + idFunc + "' AND tipo_bonus = '" + tipo + "';";

		stmt = this.criarStatement(sql);
		try {
			result = stmt.executeQuery();
			if(result != null) {
				bonus = new Bonus(result.getInt("id_funcionario"), result.getDouble("valor_bonus"));
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
					Logger.getLogger(BonusSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}

		return bonus;
	}

	public void ifIsCriarTb() {
		conectar();
		String sql = " CREATE TABLE IF NOT EXISTS tb_bonus("
				+ "    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," + "    id_funcionario INTEGER NOT NULL,"
				+ "    tipo_bonus TEXT NOT NULL," + "    data_bonus TEXT NOT NULL UNIQUE,"
				+ "    valor_bonus DOUBLE NOT NULL," + "    FOREIGN KEY (id_funcionario) REFERENCES tb_funcionario (id)"
				+ ");";
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
					Logger.getLogger(BonusSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}

	}

	public boolean excluir(int idFunc) {
		conectar();
		String sql = "DELETE FROM tb_bonus WHERE id_funcionario = ?";
		PreparedStatement stmt = this.criarStatement(sql);
		try {
			stmt.setInt(1, idFunc);
			stmt.executeUpdate();
			fechar();
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger(BonusSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
			return false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					Logger.getLogger(BonusSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}
		return true;
	}

	public boolean alterarBonusMesTipo(int idFunc, String data, Bonus bonus, String tipo) {
		conectar();
		String sql = "UPDATE tb_bonus SET valor_bonus=? WHERE id_funcionario = '" + idFunc + "' AND data_bonus = '"
				+ data + "' AND tipo_bonus = '" + tipo + "';";
		PreparedStatement stmt = criarStatement(sql);
		try {
			stmt.setDouble(1, bonus.getValor());
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
}
