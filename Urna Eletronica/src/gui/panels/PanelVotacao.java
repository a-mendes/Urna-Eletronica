package gui.panels;

import static util.Util.lerChapas;

import javax.swing.JPanel;

import main.Main;
import urna.gui.Urna;

public class PanelVotacao extends JPanel {

	private static final long serialVersionUID = 1L;

	private Urna urna;
	public PanelVotacao(Main main) {
		urna = new Urna(lerChapas());
		add(urna);
	}
}
