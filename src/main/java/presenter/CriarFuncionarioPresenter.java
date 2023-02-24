package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import DAO.conexao.DAOSingleton;
import bussiness.calculobonus.CalculadoraDiferencaEntreDatas;
import bussiness.calculobonus.ConversorData;
import model.Bonus;
import model.FaltaAoTrabalho;
import model.Funcionario;
import model.Salario;
import service.CarregaArquivoFuncionariosService;
import view.ViewCriarFuncionario;

public class CriarFuncionarioPresenter {
	private ViewCriarFuncionario view;
	private Funcionario func;
    private CarregaArquivoFuncionariosService l;
    private ArrayList<Funcionario> funcs;

	public CriarFuncionarioPresenter(final ViewCriarFuncionario viewCriarFuncionario) {
		this.view = viewCriarFuncionario;
		this.funcs = null;
		this.getAcoesDaView();
	}

	public void getAcoesDaView() {
		this.view.getBtnFecharCriarFuncionario().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getFrame().setVisible(false);
			}
		});
		
		this.view.getBtnCarregarArquivo().addActionListener((ActionEvent e) ->{
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.showOpenDialog(this.view.getFrame());
			File f = chooser.getSelectedFile();
			this.lerArquivo(f);
		});

		this.view.getBtnSalvarCriarFuncionario().addActionListener((ActionEvent e) -> {
			if(funcs != null) {
				processarLista();
			}else {
				if ((view.getTextCargo().getText() == null || view.getTextCargo().getText().trim().equals(""))
						|| (view.getTextNome().getText() == null || view.getTextNome().getText().trim().equals(""))
						|| (view.getTextSalario().getText() == null || view.getTextSalario().getText().trim().equals(""))
						|| (view.getTextIdade().getText() == null || view.getTextIdade().getText().trim().equals(""))
						|| (view.getTextAdmissao().getText() == null || view.getTextAdmissao().getText().trim().equals(""))
						|| (view.getTextDistTrab().getText() == null
								|| view.getTextDistTrab().getText().trim().equals(""))) {
					JOptionPane.showConfirmDialog(null, "Existem campos vazios!");
				} else {
					if (view.getTextAdmissao().getText().length() == 10) {
						String tempoEmpresa = view.getTextAdmissao().getText();
						tempoEmpresa = ConversorData.converter(view.getTextAdmissao().getText());
						this.func = new Funcionario(view.getTextNome().getText(), view.getTextCargo().getText(),
								Integer.valueOf(view.getTextIdade().getText()),
								Double.valueOf(view.getTextSalario().getText()),
								Double.valueOf(view.getTextDistTrab().getText()), view.getTextAdmissao().getText(),
								Double.valueOf(CalculadoraDiferencaEntreDatas.calcularDiferencaEmAnos(tempoEmpresa)));
						if(processarFuncionario(func)) {
							JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!", "Atenção",
									JOptionPane.INFORMATION_MESSAGE);
							view.getFrame().setVisible(false);
						}else {
							JOptionPane.showMessageDialog(null, "A data de adesão não possui o formato válido. Por favor, insira conforme o exemplo: 20/05/2019.", "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário, tente novamente.", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
	public boolean processarFuncionario(Funcionario fun) {
		int id = this.criarNovoFuncionario(fun);
		if (id != -1) {
			fun.setFuncId(id);
			getFuncionarioCadastrado(fun.getNome());
			Salario sal = new Salario(fun.getFuncId(), fun.getSalario(),
					fun.getSalario());
			FaltaAoTrabalho falta = new FaltaAoTrabalho(fun.getFuncId(), 0);
			if (this.registrarSalarioFuncionario(sal) && registrarFaltaFuncionario(falta)) {
				return true;
			}
		}
		return false;
	}

	public int criarNovoFuncionario(Funcionario fun) {
		return DAOSingleton.getInstance().getFuncionarioSqliteDAO().salvar(fun);
	}

	public Funcionario getFuncionarioCadastrado(String nome) {
		this.func = DAOSingleton.getInstance().getFuncionarioSqliteDAO().getFuncionarioPorNome(nome).get(0);
		return this.func;
	}

	public boolean registrarSalarioFuncionario(Salario salario) {
		return DAOSingleton.getInstance().getSalarioSqliteDAO().salvar(salario, this.func);
	}

	public boolean registrarBonusFuncionario(Bonus bonus) {
		return DAOSingleton.getInstance().getBonusSqliteDAO().salvar(bonus);
	}

	public boolean registrarFaltaFuncionario(FaltaAoTrabalho falta) {
		return DAOSingleton.getInstance().getFaltaSqliteDAO().salvar(falta);
	}
	
	public void lerArquivo(File caminhoArquivo) {
        l = new CarregaArquivoFuncionariosService();
        try {
            this.funcs = l.lerArquivo(caminhoArquivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void processarLista() {
		int cont = 0;
        for(Funcionario fun : funcs) {
        	if(processarFuncionario(fun)) {
        		cont++;
        	}
        }
        if(cont == funcs.size()) {
        	JOptionPane.showMessageDialog(null, "Funcionários cadastrados com sucesso! Total: " + l.getQuantidadeRegistro() + "", "Atenção",
					JOptionPane.INFORMATION_MESSAGE);
        	view.getFrame().setVisible(false);
		}else {
			JOptionPane.showMessageDialog(null, "A data de adesão não possui o formato válido. Por favor, insira conforme o exemplo: 20/05/2019.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
