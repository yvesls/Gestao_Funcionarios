package presenter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import DAO.conexao.DAOSingleton;
import model.CalculoEstatistico;
import view.ViewCalculoEstatistico;

public class CalculoEstatisticoPresenter {
	private ViewCalculoEstatistico view;
	private ArrayList<CalculoEstatistico> listCalc;

	public CalculoEstatisticoPresenter(ViewCalculoEstatistico view) {
		this.view = view;
		this.exibirCalculoEstatistico();
		this.getAcoesDaView();
	}

	public void getAcoesDaView() {
		this.view.getBtnFecharCalcEst().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getFrame().setVisible(false);
			}
		});
	}

	public void exibirCalculoEstatistico() {
		listCalc = DAOSingleton.getInstance().getCalculoEstatisticoSQLiteDAO().getListCalculoEstatistico();
		int tam = 1;
		String[] colunas;
		Object[][] dados;
		for (CalculoEstatistico ce : listCalc) {
			for (int i = 0; i < listCalc.size(); i++) {
				if (!ce.getData().equals(listCalc.get(i).getData())) {
					tam++;
					System.out.println(tam);
				}
			}
		}
		colunas = new String[tam + 1];
		colunas[0] = "Resultado";
		for (int i = 1; i < tam + 1; i++) {
			colunas[i] = listCalc.get(i).getData();
		}
		dados = new Object[6][tam+1];
		JTable tabela = new JTable(dados, colunas);
		tabela.setFont(new Font("Tahoma", Font.PLAIN, 18));
		for (int i = 0; i < 6; i++) {
			dados[i][0] = listCalc.get(i).getTipo();
			for (int j = 1; j < tam+1; j++) {
				dados[i][j++] = listCalc.get(i).getValor();
				tabela.setRowHeight(i, 40);
			}
		}
		view.getScrollPane().setViewportView(tabela);
		TableColumn firstColumn = tabela.getColumnModel().getColumn(0); 
		firstColumn.setResizable(false);
	}
}
