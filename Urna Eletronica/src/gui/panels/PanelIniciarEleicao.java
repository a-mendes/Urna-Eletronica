package gui.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.Main;
import util.Util;

public class PanelIniciarEleicao extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton btnCadastrarChapas;
	private JButton btnIniciarVotacao;

	public PanelIniciarEleicao(Main main) {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new FlowLayout());

		Icon iconCadastro = new ImageIcon("res/icones/cadastro.png");
		btnCadastrarChapas = new JButton("Cadastrar Chapas", iconCadastro);
		estilizarButton(btnCadastrarChapas);
		btnCadastrarChapas
			.addActionListener((ActionEvent e) -> main.setPanel(new PanelCadastrarChapas(main)));
		add(btnCadastrarChapas);

		Icon iconVotacao = new ImageIcon("res/icones/votar.png");
		btnIniciarVotacao = new JButton("Iniciar Votação", iconVotacao);
		btnIniciarVotacao.setEnabled(Util.hasChapas());
		estilizarButton(btnIniciarVotacao);
		//TODO ActionListener
		add(btnIniciarVotacao);
	}

	/**
	 * Aplica a estilização padrão em um botão
	 * @param JButton - botão a ser aplicado o estilo
	 */
	private void estilizarButton(JButton button) {
		button.setIconTextGap(50);
		button.setPreferredSize(new Dimension(500, 300));
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
	}
}
