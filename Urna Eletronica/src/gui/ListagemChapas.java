package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Chapa;
import util.Util;

public class ListagemChapas extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnNextPage;
	private JButton btnPrevPage;
	
	private JLabel lblCurrentPage;
	
	private JPanel pnlNavegacao;
	private JPanel pnlElementos;
	
	private ArrayList<JPanel> listElementos;
	
	private int currentPage = 1;
	private int maxPage;
	
	private int qtdItensPagina;
	private HashMap<Integer, Chapa> hashChapas;
	

	public ListagemChapas(int qtdItensPagina, HashMap<Integer, Chapa> hashChapas) {

		this.qtdItensPagina = (qtdItensPagina <= 0) ? (1) : (qtdItensPagina);
		this.hashChapas = hashChapas;
		
		setLayout(new FlowLayout()); 
		setPreferredSize(new Dimension(700, 500));
		
		initPnlElementos();
	}
	
	private void initPnlElementos() {
		pnlElementos = new JPanel(new GridLayout(qtdItensPagina, 1));
		pnlElementos.setPreferredSize(new Dimension(700, 400));
		
		listElementos = new ArrayList<>();
		
		atualizarElementos();
	}
	
	private void atualizarElementos() {
		pnlElementos.removeAll();
		
		int paginaIncompleta = (hashChapas.size() % qtdItensPagina >= 1) ? (1) : (0);
		this.maxPage = hashChapas.size() / qtdItensPagina + paginaIncompleta;
		
		if(hashChapas.size() == 0) {
			pnlElementos.add(new Label("Não há chapas cadastradas", SwingConstants.CENTER));
			return;
		}
		
		ArrayList<Chapa> listChapas = Util.HashToArray(hashChapas);
		
		/**
		 * Recupera os chapas a serem exibidos de acordo com a paginação
		 */
		for (int i = 0; i < qtdItensPagina; i++) {
			int indexChapa = i + (qtdItensPagina * (currentPage - 1));
			if(indexChapa >= listChapas.size())
				break;
			
			Chapa chapa = listChapas.
					get(indexChapa);
			
			JPanel pnlElemento = new JPanel(new FlowLayout());
			
			String infoChapa = "<HTML><BODY>"
								+ " <h2>"+ chapa.getNumeroEleitoral() +"</h2>"
								+ " <b>Presidente: </b> " + chapa.getNomePresidente() + "<br/>"
								+ " <b>Vice: </b> " + chapa.getNomeVice() + "<br/>"
							 + "</BODY></HTML>";
			
			JLabel lblInfo = new JLabel(infoChapa);
			lblInfo.setPreferredSize(new Dimension(400, 150));
			pnlElemento.add(lblInfo);
			
			JButton btnExcluirElemento = new JButton("Excluir Chapa");
			//TODO tamanho
			btnExcluirElemento.setPreferredSize((new Dimension(150, 30)));
			btnExcluirElemento.setForeground(Color.RED);
			btnExcluirElemento.addActionListener((ActionEvent e) -> excluirChapa(chapa.getNumeroEleitoral()));
			pnlElemento.add(btnExcluirElemento);
			
			listElementos.add(pnlElemento);
			pnlElementos.add(pnlElemento);
		}
		
		add(pnlElementos);
		if(pnlNavegacao != null)
			remove(pnlNavegacao);
		initPnlNavegacao();
		
		validate();
		repaint();
	}
	
	private void initPnlNavegacao() {
		pnlNavegacao = new JPanel(new GridLayout(1, 3));
		pnlNavegacao.setPreferredSize(new Dimension(200, 40));
		
		btnPrevPage = new JButton("<");
		btnPrevPage.addActionListener((ActionEvent e) -> prevPage());
		pnlNavegacao.add(btnPrevPage);
		
		lblCurrentPage = new JLabel(String.valueOf(currentPage), SwingConstants.CENTER);
		pnlNavegacao.add(lblCurrentPage);
		
		btnNextPage = new JButton(">");
		btnNextPage.addActionListener((ActionEvent e) -> nextPage());
		pnlNavegacao.add(btnNextPage);
		
		/**
		 * Desativa o botao de prévia caso esteja na primeira pagina
		 */
		if(currentPage == 1)
			btnPrevPage.setEnabled(false);
		
		/**
		 * Desativa o botao de next caso esteja na ultima pagina
		 */
		if(currentPage == maxPage)
			btnNextPage.setEnabled(false);
		
		
		add(pnlNavegacao);
	}
	
	private void prevPage() {
		
		/**
		 * Remove os elementos da página atual
		 */
		remove(pnlElementos);
		
		/**
		 * Diminui valor da pagina atual
		 */
		
		currentPage--;
		lblCurrentPage.setText(String.valueOf(currentPage));
	
		/**
		 * Atualiza os componentes redesenhando o panel
		 */
		atualizarElementos();
		validate();
		repaint();
	}

	private void nextPage() {
		
		/**
		 * Remove os itens da página atual
		 */
		remove(pnlElementos);
		/**
		 * Aumenta valor da pagina atual
		 */
		currentPage++;
		lblCurrentPage.setText(String.valueOf(currentPage));
		
		/**
		 * Atualiza os componentes redesenhando o panel
		 */
		atualizarElementos();
		validate();
		repaint();
	}

	public void setHashChapas(HashMap<Integer, Chapa> hashChapas) {
		this.hashChapas = hashChapas;
		int paginaIncompleta = (hashChapas.size() % qtdItensPagina >= 1) ? (1) : (0);
		this.maxPage = hashChapas.size() / qtdItensPagina + paginaIncompleta;
		
		atualizarElementos();
	}
	
	private void excluirChapa(int numeroEleitoral) {
		int opt = JOptionPane.showConfirmDialog(null, "Deseja excluir a chapa \""+ numeroEleitoral +"\"?");
		/*
		 * Não e Cancelar
		 */
		if(opt != 0) 
			return;
		
		/**
		 * Sim
		 */
		
		hashChapas.remove(numeroEleitoral);
		Util.atualizarChapas(hashChapas);
		
		currentPage = 1;
		atualizarElementos();
		validate();
		repaint();
	}
}
