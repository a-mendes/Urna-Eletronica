package urna.gui;

import static urna.gui.BotoesUrna.getBotaoUrna;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Chapa;

public class Urna extends JPanel {

	private static final long serialVersionUID = 1L;

	private MonitorUrna monitor;
	private JPanel pnlBotoes;
	private HashMap<Integer, Chapa> chapas;
	
	public Urna(HashMap<Integer, Chapa> chapas) {
		this.chapas = chapas;
		setBorder(BorderFactory.createLineBorder(Color.black, 5));
		
		initMonitor();
		initPanelBotoes();
	}
	
	private void initMonitor() {
		monitor = new MonitorUrna(chapas);
		add(monitor);
	}
	
	private void initPanelBotoes() {
		pnlBotoes = new JPanel(new GridLayout(5, 3));
		pnlBotoes.setPreferredSize(new Dimension(350, 500));
		
		/**
		 * Botoes de 1 a 9
		 */
		for (int i = 1; i <= 9; i++) {
			JButton btn = new JButton(""+i);
			btn.addActionListener((ActionEvent e) -> monitor.atualizarMonitor(getBotaoUrna(btn.getText())));
			
			pnlBotoes.add(btn);
		}
		
		/**
		 * Botao 0 
		 */
		
		pnlBotoes.add(new Label()); // Espaço em branco
		
		JButton btn0 = new JButton("0");
		btn0.addActionListener((ActionEvent e) -> monitor.atualizarMonitor(getBotaoUrna(btn0.getText())));
		pnlBotoes.add(btn0);
		
		pnlBotoes.add(new Label()); // Espaço em branco
		
		//TODO espaço entre opções e números
		
		/**
		 * Botoes BRANCO, CORRIGE e CONFIRMA
		 */
		JButton btnBranco = new JButton("BRANCO");
		btnBranco.addActionListener((ActionEvent e) -> monitor.atualizarMonitor(getBotaoUrna(btnBranco.getText())));
		pnlBotoes.add(btnBranco);
		
		JButton btnCorrige = new JButton("CORRIGE");
		btnCorrige.addActionListener((ActionEvent e) -> monitor.atualizarMonitor(getBotaoUrna(btnCorrige.getText())));
		pnlBotoes.add(btnCorrige);
		
		JButton btnConfirma = new JButton("CONFIRMA");
		btnConfirma.addActionListener((ActionEvent e) -> monitor.atualizarMonitor(getBotaoUrna(btnConfirma.getText())));
		pnlBotoes.add(btnConfirma);

		add(pnlBotoes);
	}
}
