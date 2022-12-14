package urna.gui;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import static urna.gui.BotoesUrna.getBotaoUrna;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Chapa;
import model.Eleicao;
import util.Util;

public class Urna extends JPanel {

	private static final long serialVersionUID = 1L;

	private MonitorUrna monitor;
	private JPanel pnlBotoes;
	private HashMap<Integer, Chapa> chapas;
	
	private BufferedImage background;
	private String pathBackground = "/urna.png";
	
	private Eleicao eleicao;
	
	public Urna(HashMap<Integer, Chapa> chapas) {
		try {
			background = ImageIO.read(getClass().getResource(pathBackground));
			paintComponent(background.getGraphics());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.chapas = chapas;
		this.eleicao = Util.lerEleicao();
		
		setLayout(null);
		setPreferredSize(new Dimension(1029, 600));
		setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		initMonitor();
		initPanelBotoes();
		add(btnFinalizarEleicao());
	}
	
	private void initMonitor() {
		monitor = new MonitorUrna(chapas);
		monitor.setBounds(50, 215, 575, 325);
		add(monitor);
	}
	
	private void initPanelBotoes() {
		pnlBotoes = new JPanel(new GridLayout(5, 3));
		pnlBotoes.setOpaque(false);
		pnlBotoes.setBounds(680, 275, 270, 280);
		
		/**
		 * Botoes de 1 a 9
		 */
		for (int i = 1; i <= 9; i++) {
			String strI = ""+i;
			
			JButton btn = new JButton();
			btn.addActionListener((ActionEvent e) -> monitor.atualizarMonitor(getBotaoUrna(strI)));
			configureBotao(btn);
			pnlBotoes.add(btn);
		}
		
		/**
		 * Botao 0 
		 */
		
		JLabel lblVazia = new JLabel();
		lblVazia.setOpaque(false);
		pnlBotoes.add(lblVazia); // Espa?o em branco
		
		JButton btn0 = new JButton();
		btn0.addActionListener((ActionEvent e) -> monitor.atualizarMonitor(getBotaoUrna("0")));
		configureBotao(btn0);
		pnlBotoes.add(btn0);
		
		JLabel lblVazia2 = new JLabel();
		lblVazia.setOpaque(false);
		pnlBotoes.add(lblVazia2); // Espa?o em branco
		
		//TODO espa?o entre op??es e n?meros
		
		/**
		 * Botoes BRANCO, CORRIGE e CONFIRMA
		 */
		JButton btnBranco = new JButton();
		btnBranco.addActionListener((ActionEvent e) -> monitor.atualizarMonitor(getBotaoUrna("BRANCO")));
		configureBotao(btnBranco);
		pnlBotoes.add(btnBranco);
		
		JButton btnCorrige = new JButton();
		btnCorrige.addActionListener((ActionEvent e) -> monitor.atualizarMonitor(getBotaoUrna("CORRIGE")));
		configureBotao(btnCorrige);
		pnlBotoes.add(btnCorrige);
		
		JButton btnConfirma = new JButton();
		btnConfirma.addActionListener((ActionEvent e) -> {
			monitor.atualizarMonitor(getBotaoUrna("CONFIRMA"));
			eleicao.computarVoto(monitor.numeroEleitoral);
			monitor.numeroEleitoral = -2;
		});
		configureBotao(btnConfirma);
		pnlBotoes.add(btnConfirma);

		add(pnlBotoes);
	}
	
	private JButton btnFinalizarEleicao() {
		JButton btnFinalizar = new JButton();
		configureBotao(btnFinalizar);
		btnFinalizar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnFinalizar.setBounds(663, 172, 300, 80);
		
		btnFinalizar.addActionListener((ActionEvent arg0) -> {
			String senha = showInputDialog(null, "Informe a senha para finalizar a ELEICAO");
			
			if("2504".equals(senha)) {
				Util.finalizarEleicao();
				showMessageDialog(null, "O resultado foi impresso no arquivo \"resultado.txt\"");
				System.exit(0);
			}
			
			else
				showMessageDialog(null, "Senha incorreta");

		});
		
		return btnFinalizar;
	}
	
	private void configureBotao(JButton btn) {
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	        g.drawImage(background, 0, 0, null);
	}
}
