package DAO.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAOSQLiteConexao {
	protected Connection conexao;
	protected boolean conectar() {
		String url = "jdbc:sqlite:db/bdfuncionariosdb.db";
		try {
			setConexao(DriverManager.getConnection(url));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	protected boolean fechar() {
		try {
			if(this.getConexao().isClosed() == false) {
				this.getConexao().close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	} 
	
	protected Statement criarStatement() {
		try {
			return this.getConexao().createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected PreparedStatement criarStatement(String pSQL, int RETURN_GENERATE_KEYS) {
		try {
			return this.getConexao().prepareStatement(pSQL, RETURN_GENERATE_KEYS);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected PreparedStatement criarStatement(String pSQL) {
		try {
			return this.getConexao().prepareStatement(pSQL);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected Connection getConexao() {
		return conexao;
	}

	protected void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
}
