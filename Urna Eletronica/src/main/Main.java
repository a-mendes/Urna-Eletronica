package main;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.panels.PanelVotacao;

/**
 * TODO
 * Se houver uma eleicao em andamento
 * 		Finalizar eleicao
 * 		Continuar eleicao
 */ 

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 700;
	
	private JPanel currentPanel;
	
	public Main() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setTitle("Urna Eletronica");
		ImageIcon icon = new ImageIcon("res/icones/urna.png");
		setIconImage(icon.getImage());
		
		setPanel(new PanelVotacao(this));
		
		add(currentPanel);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String Args[]) {
		new Main();
	}
	
	public void setPanel(JPanel panel) {
		if(currentPanel !=  null)
			remove(currentPanel);
		this.currentPanel = panel;
		add(currentPanel);
		validate();
		repaint();
	}
}
