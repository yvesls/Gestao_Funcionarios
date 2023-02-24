package DAO.interfaces;

import java.util.List;
import model.Funcionario;

public interface IFuncionarioSQLiteDAO {
	
	public int salvar(Funcionario funcionario);

	public List<Funcionario> getListFuncDAO();

	public List<Funcionario> getListFuncDAOMes(String data);

	public void ifIsCriarBd();

	public boolean excluirFuncionario(int pCodigo);

	public List<Funcionario> getFuncionarioPorNome(String nome);

	public Funcionario getFuncionariosPorId(int idFunc);

	public boolean alterarFuncionario(Funcionario func);
}
