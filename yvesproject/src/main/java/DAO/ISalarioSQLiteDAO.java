package DAO;

import java.util.List;
import model.Funcionario;
import model.Salario;

public interface ISalarioSQLiteDAO {
	
	public boolean salvar(Salario salario, Funcionario func);

	public List<Salario> getListTodosSalariosAtribuidos();

	public void ifIsCriarTb();

	public boolean excluir(int idFunc);

	public List<Salario> getTodosSalariosMes(String data);

	public Salario getSalarioMesFuncionario(String data, int idFunc);
	
	public boolean alterarSalario(int idFunc, String data, Salario sal);
}
