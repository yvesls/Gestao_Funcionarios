package presenter;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import DAO.FuncionarioSQLiteDAO;
import model.Funcionario;
import view.ViewCriarFuncionario;
import view.ViewFuncionario;
import view.ViewManterFuncionario;

public class FuncionarioPresenter {
	private List<Funcionario> funcs;
	private Funcionario funcSelecionado;
	private ViewFuncionario view;
	/**
	 * @wbp.parser.entryPoint
	 */
	public FuncionarioPresenter(final ViewFuncionario viewFuncionario) throws SQLException {
		this.view = viewFuncionario;
		this.getAcoesDaView();
	}

	public void getAcoesDaView() {
		this.view.getBtnFecharFuncionarios().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getFrame().setVisible(false);
			}
		});

		this.view.getBtnNovoFuncionario().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ViewCriarFuncionario window = new ViewCriarFuncionario();
							window.getFrame().setVisible(true);
							new CriarFuncionarioPresenter(window);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		this.view.getTable().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				DefaultTableModel modelo = (DefaultTableModel) view.getTableSelecionado().getModel();
				modelo.setNumRows(0);
				for (Funcionario funcionario : funcs) {
					if (funcionario.getFuncId() == Integer
							.valueOf(String.valueOf(view.getTable().getValueAt(view.getTable().getSelectedRow(), 0)))) {
						funcSelecionado = new Funcionario();
						funcSelecionado = funcionario;
						modelo.addRow(new Object[] { funcSelecionado.getFuncId(), funcSelecionado.getNome(),
								funcSelecionado.getIdade(), funcSelecionado.getCargo(), funcSelecionado.getAdmissao(),
								funcSelecionado.getSalario() });
					}
				}

			}
		});

		this.view.getBtnVerFuncionario().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							if (funcSelecionado != null) {
								
								ViewManterFuncionario window = new ViewManterFuncionario();
								window.getFrame().setVisible(true);
								new ManterFuncionarioPresenter(window, funcSelecionado);
							} else {
								JOptionPane.showMessageDialog(null, "É preciso selecionar um funcionário primeiro!");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		this.view.getBtnBuscarFuncionarios().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						String nome = view.getTextFieldCampoBuscar().getText();
						if (nome != "") {
							funcs = new FuncionarioSQLiteDAO().getFuncionario(nome);
							if (!funcs.isEmpty()) {
								DefaultTableModel modelo = (DefaultTableModel) view.getTable().getModel();
								modelo.setNumRows(0);
								for (Funcionario funcionario : funcs) {
									modelo.addRow(new Object[] { funcionario.getFuncId(), funcionario.getNome(),
											funcionario.getIdade(), funcionario.getCargo(), funcionario.getAdmissao(),
											funcionario.getSalario() });
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"O funcionario buscado não se encontra na nossa base de dados!");
							}
						} else {
							JOptionPane.showMessageDialog(null, "É preciso digitar um nome primeiro!");
						}
					}
				});
			}
		});

		this.view.getBtnAtualizarLista().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					carregarFuncionarios(view);
					view.getTextFieldCampoBuscar().setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public void carregarFuncionarios(ViewFuncionario viewFuncionario) throws SQLException {
		funcs = new FuncionarioSQLiteDAO().getListFuncDAO();
		DefaultTableModel modelo = (DefaultTableModel) viewFuncionario.getTable().getModel();
		modelo.setNumRows(0);
		for (Funcionario funcionario : funcs) {
			modelo.addRow(new Object[] { funcionario.getFuncId(), funcionario.getNome(), funcionario.getIdade(),
					funcionario.getCargo(), funcionario.getAdmissao(), funcionario.getSalario() });
		}
	}
}
