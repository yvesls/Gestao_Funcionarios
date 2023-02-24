package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.conexao.DAOSQLiteConexao;
import model.Bonus;
import model.CalculoEstatistico;

public class CalculoEstatisticoSQLiteDAO extends DAOSQLiteConexao {
	
	public boolean salvar(CalculoEstatistico calculo) {
		PreparedStatement stmt = null;
		try {
			conectar();
			String sql = "INSERT INTO tb_calculo_estatistico (tipo, valor, mes_ano) VALUES (?, ?, ?);";

			stmt = criarStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, calculo.getTipo());
			stmt.setDouble(2, calculo.getValor());
			stmt.setString(3, calculo.getData());
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
	
	public void ifIsCriarTb() {
		conectar();
		String sql = "CREATE TABLE IF NOT EXISTS tb_calculo_estatistico ("
				+ "  id               INTEGER      PRIMARY KEY AUTOINCREMENT UNIQUE,"
				+ "  tipo VARCHAR (20) NOT NULL," 
				+ "	 mes_ano            VARCHAR (20) NOT NULL,"
				+ "	 valor DOUBLE (10)  NOT NULL "
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
					Logger.getLogger(FuncionarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}
	}
	
	public int getCalculoMesTipo(CalculoEstatistico calculo) {
		conectar();
		int ce = -1;
		ResultSet result = null;
		PreparedStatement stmt = null;
		String sql = "SELECT id FROM tb_calculo_estatistico WHERE mes_ano = '" + calculo.getData()
				+ "' AND tipo ='" + calculo.getTipo() + "';";

		stmt = this.criarStatement(sql);
		try {
			result = stmt.executeQuery();
			while (result.next()) {
				ce = result.getInt("id");
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
		return ce;
	}
	
	public boolean alterarCalculoMesTipo(CalculoEstatistico calculo) {
		conectar();
		String sql = "UPDATE tb_calculo_estatistico SET valor=? WHERE mes_ano = '"
				+ calculo.getData() + "' AND tipo = '" + calculo.getTipo() + "';";
		PreparedStatement stmt = criarStatement(sql);
		try {
			stmt.setDouble(1, calculo.getValor());
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
	
	public ArrayList<CalculoEstatistico> getListCalculoEstatistico() {
		conectar();
		ArrayList<CalculoEstatistico> listaCalc = new ArrayList<>();
		CalculoEstatistico calc = new CalculoEstatistico();
		ResultSet result = null;
		PreparedStatement stmt = null;
		String sql = "SELECT DISTINCT mes_ano, id, tipo, valor FROM tb_calculo_estatistico;";

		stmt = this.criarStatement(sql);
		try {
			result = stmt.executeQuery();
			while (result.next()) {
				calc = new CalculoEstatistico(result.getInt("id"), result.getString("tipo"), result.getDouble("valor"), result.getString("mes_ano"));
				listaCalc.add(calc);
			}
			fechar();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					Logger.getLogger(CalculoEstatisticoSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}
		return listaCalc;
	}
}
