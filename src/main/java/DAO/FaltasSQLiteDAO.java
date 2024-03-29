package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.conexao.DAOSQLiteConexao;
import model.FaltaAoTrabalho;
import model.Funcionario;

public class FaltasSQLiteDAO extends DAOSQLiteConexao{
	
	public void ifIsCriarTbFaltas() {
		conectar();
		String sql = ""
				+ "CREATE TABLE IF NOT EXISTS tb_faltas("
				+ "    id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"
				+ "    id_funcionario INTEGER NOT NULL,"
				+ "    qtd INTEGER NOT NULL,"
				+ "    data_falta TEXT NOT NULL,"
				+ "    FOREIGN KEY (id_funcionario) REFERENCES tb_funcionario (id)"
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
	
	public boolean salvar(FaltaAoTrabalho falta) {
		PreparedStatement stmt = null;
		try {
			conectar();
			String sql = ""
					+ "INSERT INTO tb_faltas (id_funcionario, qtd, data_falta) "
					+ "VALUES (?, ?, ?);";
			
			stmt = criarStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, falta.getIdFunc());
			stmt.setInt(2, falta.getQuantidadeFaltas());
			stmt.setString(3, falta.getDataFalta());
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
					Logger.getLogger(FuncionarioSQLiteDAO.class.getName(), null).log(Level.SEVERE, null, e);
				}
			}
		}

		fechar();
		return true;
	}
	
	public List<FaltaAoTrabalho> getFaltasFuncionario(int pCodigo) throws ParseException {
		conectar();
		FaltaAoTrabalho falta = new FaltaAoTrabalho();
		List<FaltaAoTrabalho> listaFaltas = new ArrayList<>();
		ResultSet result = null;
		
		String sql = "SELECT id_funcionario, qtd, data_falta FROM tb_faltas WHERE id_funcionario = ?";
		PreparedStatement stmt = this.criarStatement(sql);
		try {
			stmt.setInt(1, pCodigo);
			result = stmt.executeQuery();
				while (result.next()) {
					falta = new FaltaAoTrabalho(result.getInt("id_funcionario"), result.getInt("qtd"), result.getString("data_falta"));
					listaFaltas.add(falta);
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
		
		return listaFaltas;
	}
	
	public FaltaAoTrabalho getFaltasFuncionarioEsseMes(int idFunc, LocalDate data) {
		try {
			for(FaltaAoTrabalho falta : getFaltasFuncionario(idFunc)) {
				if(Integer.valueOf(falta.getDataFalta().substring(0,2)) == (data.getMonth().getValue()) &&
					Integer.valueOf(falta.getDataFalta().substring(3,7)) == data.getYear()) {
					return falta;
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deletarFaltasFunc(int idFunc) {
		conectar();
		String sql = "DELETE FROM tb_faltas WHERE id_funcionario = ?";
		PreparedStatement stmt = criarStatement(sql);
		try {
			stmt.setInt(1, idFunc);
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
	
	public boolean alterarFaltaFuncionario(Funcionario func) {
		conectar();
		System.out.println(func.getFaltaTrabalho().getQuantidadeFaltas() +" "+ func.getFaltaTrabalho().getDataFalta());
		String sql = "UPDATE tb_faltas SET qtd=?, "
				+ "data_falta=? WHERE id_funcionario = ?";
		PreparedStatement stmt = criarStatement(sql);
		try {
			stmt.setInt(1, func.getFaltaTrabalho().getQuantidadeFaltas());
			stmt.setString(2, func.getFaltaTrabalho().getDataFalta());
			stmt.setInt(3, func.getFuncId());
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
