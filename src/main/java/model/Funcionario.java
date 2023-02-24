package model;

import java.util.List;

import DAO.conexao.DAOSingleton;

public class Funcionario {
	private String nome;
	private String cargo;
	private String admissao;
	private int idade;
	private Double salario;
	private Double distTrab;
	private int FuncId;
	private Double salarioTotal;
	private List<Bonus> bonusRecebidos;
	private FaltaAoTrabalho faltaTrabalho;
	private boolean funcMes;
	private Double tempoServico;
	
	public Funcionario() {
		
	}
	
	public Funcionario(int idFunc, String nome) {
		this.nome = nome;
	    this.FuncId = idFunc;
	}

	public Funcionario(String nome, String cargo, int idade, Double salario, Double distTrab, String admissao, Double tempoServico) {
		this.nome = nome;
		this.cargo = cargo;
		this.idade = idade;
		this.salario = salario;
		this.distTrab = distTrab; 
	    this.admissao = admissao;
	    this.salarioTotal = salario;
	    this.funcMes = false;
	    this.tempoServico = tempoServico;
	}
	
	public Funcionario(int funcId, String nome, String cargo, int idade, Double salario, Double distTrab, String admissao, Double tempoServico) {
		this.FuncId = funcId;
		this.nome = nome;
		this.cargo = cargo;
		this.idade = idade;
		this.salario = salario;
		this.distTrab = distTrab;
	    this.admissao = admissao;
	    this.funcMes = false;
	    this.tempoServico = tempoServico;
	    if(salarioTotal == null) {
	    	this.salarioTotal = this.salario;
	    }
	}
	
	public Funcionario(int funcId, String nome, String cargo, int idade, Double salarioTotal, Double salario, Double distTrab, String admissao, boolean funcionarioMes, Double tempoServico) {
		this.FuncId = funcId;
		this.nome = nome;
		this.cargo = cargo;
		this.idade = idade;
		this.salario = salario;
		this.distTrab = distTrab;
	    this.admissao = admissao;
	    this.funcMes = funcionarioMes;
	    this.tempoServico = tempoServico;
	    if(salarioTotal == null || salarioTotal == 0) {
	    	this.salarioTotal = this.salario;
	    }else {
	    	this.salarioTotal = salarioTotal;
	    }
	}
	
	public void addBonus(Bonus bonus) {
		this.bonusRecebidos.add(bonus);
	}
	
	public void removeBonus(Bonus bonus) {
		
	}
	
	public void addFalta(FaltaAoTrabalho falta) throws Exception {
		this.faltaTrabalho = falta;	
	}
	
	public void removeFalta(FaltaAoTrabalho falta) {
		
	}
	
	public void calculaSalario() {
		for(Bonus bonus: this.bonusRecebidos) {
			this.salarioTotal += bonus.getValor();
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCargo() {
		return cargo;
	}
	
	public String getAdmissao() {
		return admissao;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public Double getSalario() {
		return salario;
	}
	
	public Double getDistTrab() {
		return distTrab;
	}
	
	public int getFuncId() {
		return FuncId;
	}
	
	public Double getSalarioTotal() {
		return salarioTotal;
	}

	public FaltaAoTrabalho getFaltaTrabalho() {
		return faltaTrabalho;
	}
	
	public boolean getIsFuncionarioDoMes() {
		return funcMes;
	}


	public boolean isFuncMes() {
		return funcMes;
	}

	public Double getTempoServico() {
		return tempoServico;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public void setAdmissao(String admissao) {
		this.admissao = admissao;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public void setDistTrab(Double distTrab) {
		this.distTrab = distTrab;
	}

	public void setFuncId(int funcId) {
		FuncId = funcId;
	}

	public void setSalarioTotal(Double salarioTotal) {
		this.salarioTotal = salarioTotal;
	}

	public void setBonusRecebidos(List<Bonus> bonusRecebidos) {
		this.bonusRecebidos = bonusRecebidos;
	}

	public void setFaltaTrabalho(FaltaAoTrabalho faltaTrabalho) {
		this.faltaTrabalho = faltaTrabalho;
	}

	public void setFuncMes(boolean funcMes) {
		this.funcMes = funcMes;
	}

	public void setTempoServico(Double tempoServico) {
		this.tempoServico = tempoServico;
	}

	@Override
	public String toString() {
		return "Funcionario [nome=" + nome + ", cargo=" + cargo + ", admissao=" + admissao + ", idade=" + idade
				+ ", salario=" + salario + ", distTrab=" + distTrab + ", FuncId=" + FuncId + ", salarioTotal="
				+ salarioTotal + ", funcMes=" + funcMes + ", tempoServico=" + tempoServico + "]";
	}
}
