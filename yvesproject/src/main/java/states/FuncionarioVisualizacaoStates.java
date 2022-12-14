package states;

import presenter.ManterFuncionarioPresenter;

public class FuncionarioVisualizacaoStates extends FuncionarioStates {

	public FuncionarioVisualizacaoStates(ManterFuncionarioPresenter funcManterPresenter) throws Exception {
		super(funcManterPresenter);
		this.desabilitarVisualizacoesAnteriores();
		
		funcManterPresenter.getViewManterFuncionario().getBtnExcluirFuncionario().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getBtnEditarFuncionario().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getLblNome().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getLblCargo().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getLblIdade().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getLblSalario().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getLblDistanciaTrab().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getLblNumFaltas().setVisible(true);
	}

	@Override
	public void editar() {
		this.funcManterPresenter.setFuncionarioState(new FuncionarioEditacaoState(this.funcManterPresenter));
	}
	
	@Override
	public void desabilitarVisualizacoesAnteriores() throws Exception {
		funcManterPresenter.setaCamposFunc();
		funcManterPresenter.getViewManterFuncionario().getBtnSalvarModificacaoFuncionario().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getTextNome().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getTextCargo().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getTextIdade().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getTextSalario().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getTextDistTrab().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getTextFaltas().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getChckbxFuncMes().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getTextTempoServico().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getLblTempoServico().setVisible(false);
	}
}
