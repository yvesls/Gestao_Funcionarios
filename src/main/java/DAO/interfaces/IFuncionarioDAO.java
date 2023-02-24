package DAO.interfaces;

import model.Funcionario;

public interface IFuncionarioDAO {
	public boolean salvar(Funcionario func);
	public void remover();
	public boolean atualizar();
}
