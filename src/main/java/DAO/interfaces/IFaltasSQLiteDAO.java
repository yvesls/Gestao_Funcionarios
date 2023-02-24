package DAO.interfaces;

import java.time.LocalDate;
import java.util.List;
import model.FaltaAoTrabalho;
import model.Funcionario;

public interface IFaltasSQLiteDAO {
	
	public void ifIsCriarTbFaltas();
	
	public boolean salvar(FaltaAoTrabalho falta, int idFunc);
	
	public List<FaltaAoTrabalho> getFaltasFuncionario(int pCodigo);
	
	public FaltaAoTrabalho getFaltasFuncionarioEsseMes(int idFunc, LocalDate data);
	
	public boolean deletarFaltasFunc(int idFunc);
	
	public boolean alterarFaltaFuncionario(Funcionario func);
}
