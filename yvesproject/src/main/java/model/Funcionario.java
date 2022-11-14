package model;

import java.util.List;

public class Funcionario {
	private String nome;
	private String cargo;
	private String admissao;
	private int idade;
	private Double salario;
	private int distTrab;
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

	public Funcionario(String nome, String cargo, int idade, Double salario, int distTrab, String admissao, Double tempoServico) {
		this.nome = nome;
		this.cargo = cargo;
		this.idade = idade;
		this.salario = salario;
		this.distTrab = distTrab; 
	    this.admissao = admissao;
	    this.salarioTotal = salario;
	    this.funcMes = false;
	    this.tempoServico = tempoServico;
	    if(salarioTotal == null) {
	    	this.salarioTotal = this.salario;
	    }
	}
	
	public Funcionario(int funcId, String nome, String cargo, int idade, Double salario, int distTrab, String admissao, Double tempoServico) {
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
	
	public Funcionario(int funcId, String nome, String cargo, int idade, Double salarioTotal, Double salario, int distTrab, String admissao, boolean funcionarioMes, Double tempoServico) {
		this.FuncId = funcId;
		this.nome = nome;
		this.cargo = cargo;
		this.idade = idade;
		this.salario = salario;
		this.distTrab = distTrab;
	    this.admissao = admissao;
	    this.funcMes = funcionarioMes;
	    this.tempoServico = tempoServico;
	    if(salarioTotal == null) {
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
	
	public int getDistTrab() {
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



	@Override
	public String toString() {
		return "Funcionario [nome=" + nome + ", cargo=" + cargo + ", admissao=" + admissao + ", idade=" + idade
				+ ", salario=" + salario + ", distTrab=" + distTrab + ", FuncId=" + FuncId + ", salarioTotal="
				+ salarioTotal + ", funcMes=" + funcMes + ", tempoServico=" + tempoServico + "]";
	}

	public boolean isFuncMes() {
		return funcMes;
	}

	public Double getTempoServico() {
		return tempoServico;
	}
}
