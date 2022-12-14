package states;

import presenter.ManterFuncionarioPresenter;

public class FuncionarioEditacaoState extends FuncionarioStates {

	public FuncionarioEditacaoState(ManterFuncionarioPresenter funcPresenter) {
		super(funcPresenter);
		this.desabilitarVisualizacoesAnteriores();
		
		funcManterPresenter.getViewManterFuncionario().getBtnSalvarModificacaoFuncionario().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getTextNome().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getTextCargo().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getTextIdade().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getTextSalario().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getTextDistTrab().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getTextFaltas().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getChckbxFuncMes().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getTextTempoServico().setVisible(true);
		funcManterPresenter.getViewManterFuncionario().getLblTempoServico().setVisible(true);
		
		funcManterPresenter.getViewManterFuncionario().getTextNome().setText(funcManterPresenter.getFunc().getNome());
		funcManterPresenter.getViewManterFuncionario().getTextCargo().setText(funcManterPresenter.getFunc().getCargo());
		funcManterPresenter.getViewManterFuncionario().getTextIdade().setText(String.valueOf(funcManterPresenter.getFunc().getIdade()));
		funcManterPresenter.getViewManterFuncionario().getTextSalario().setText(String.valueOf(funcManterPresenter.getFunc().getSalario()));
		funcManterPresenter.getViewManterFuncionario().getTextDistTrab().setText(String.valueOf(funcManterPresenter.getFunc().getDistTrab()));
	}

	@Override
	public void salvar() throws Exception {
		this.funcManterPresenter.setFuncionarioState(new FuncionarioVisualizacaoStates(this.funcManterPresenter));
	}

	@Override
	public void desabilitarVisualizacoesAnteriores() {
		funcManterPresenter.getViewManterFuncionario().getBtnExcluirFuncionario().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getBtnEditarFuncionario().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getLblNome().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getLblCargo().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getLblIdade().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getLblSalario().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getLblDistanciaTrab().setVisible(false);
		funcManterPresenter.getViewManterFuncionario().getLblNumFaltas().setVisible(false);
	}
}
