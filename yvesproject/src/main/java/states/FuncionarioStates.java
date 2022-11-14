package states;

import presenter.ManterFuncionarioPresenter;

public class FuncionarioStates {
	protected ManterFuncionarioPresenter funcManterPresenter;

	public FuncionarioStates(ManterFuncionarioPresenter funcPresenter) {
		this.funcManterPresenter = funcPresenter;
	}

	public void editar() throws Exception {
		throw new Exception("Botão não pode ser acionado!");
	}

	public void salvar() throws Exception {
		throw new Exception("Botão não pode ser acionado!");
	}

	public void desabilitarVisualizacoesAnteriores() throws Exception {

	}
}
