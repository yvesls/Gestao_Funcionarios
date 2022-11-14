package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ViewVerBonus;

public class VerBonusPresenter {
	private ViewVerBonus view;

	public VerBonusPresenter(ViewVerBonus viewVerBonus) {
		this.view = viewVerBonus;
		this.getAcoesDaView();
	}

	public void getAcoesDaView() {
		this.view.getBtnFecharVerBonus().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getFrame().setVisible(false);
			}
		});
	}
}
