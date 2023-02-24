package model;

public class CalculoBonusDados {
	private String funcionario;
	private String dataSalario;
	private Double salarioBase;
	private Double bonusAcumulado;
	private Double salarioTotal;
	private int IdFunc;
	
	public String getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}
	public String getDataSalario() {
		return dataSalario;
	}
	public void setDataSalario(String dataSalario) {
		this.dataSalario = dataSalario;
	}
	public Double getSalarioBase() {
		return salarioBase;
	}
	public void setSalarioBase(Double salarioBase) {
		this.salarioBase = salarioBase;
	}
	public Double getBonusAcumulado() {
		return bonusAcumulado;
	}
	public void setBonusAcumulado(Double bonusAcumulado) {
		this.bonusAcumulado = bonusAcumulado;
	}
	public Double getSalarioTotal() {
		return salarioTotal;
	}
	public void setSalarioTotal(Double salarioTotal) {
		this.salarioTotal = salarioTotal;
	}
	public int getIdFunc() {
		return IdFunc;
	}
	public void setIdFunc(int idFunc) {
		IdFunc = idFunc;
	}
}
