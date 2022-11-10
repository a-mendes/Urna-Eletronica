package gui.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import gui.ListagemChapas;
import main.Main;
import model.Chapa;
import util.Util;

public class PanelCadastrarChapas extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private JTextField txtNomePresidente;
	private JTextField txtNomeVice;
	private JTextField txtNumeroEleitoral;
	
	private JButton btnSalvar;
	private JButton btnVoltar;
	
	private ListagemChapas listagemChapas;
	
	private HashMap<Integer, Chapa> chapas;
	
	//TODO estilização
	public PanelCadastrarChapas(Main main) {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new FlowLayout());

		txtNomePresidente = new JTextField();
		JPanel pnlNomePresidente = getLabelxTextFieldPanel("Nome Presidente", txtNomePresidente);
		add(pnlNomePresidente);
		
		txtNomeVice = new JTextField();
		JPanel pnlNomeVice = getLabelxTextFieldPanel("Nome Vice", txtNomeVice);
		add(pnlNomeVice);
		
		txtNumeroEleitoral = new JFormattedTextField(getFormatter());
		JPanel pnlNumeroEleitoral = getLabelxTextFieldPanel("Numero Eleitoral", txtNumeroEleitoral);
		add(pnlNumeroEleitoral);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener((ActionEvent e) -> salvarChapa());
		add(btnSalvar);
		
		chapas = Util.lerChapas();
		listagemChapas = new ListagemChapas(3, chapas);
		add(listagemChapas);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener((ActionEvent e) -> main.setPanel(new PanelIniciarEleicao(main)));	
		add(btnVoltar);
	}
	
	/**
	 * Cria uma formatação de JLabel x JTextField para campos
	 * @param JTextField txtField
	 * @param String nomeCampo
	 * @return JPanel
	 */
	private JPanel getLabelxTextFieldPanel(String nomeCampo, JTextField txtField) {
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new FlowLayout());
		jpanel.setPreferredSize(new Dimension(350, 50));
		
		JLabel lbl = new JLabel(nomeCampo, SwingConstants.RIGHT);
		lbl.setPreferredSize(new Dimension(130, 30));
		jpanel.add(lbl);
		
		txtField.setPreferredSize(new Dimension(200, 30));
		jpanel.add(txtField);
		
		return jpanel;
	}
	
	private AbstractFormatter getFormatter() {
		NumberFormat format = NumberFormat.getInstance();
		format.setGroupingUsed(false);
		NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(1);
	    formatter.setCommitsOnValidEdit(true);
	
	    return formatter;
	}
	
	private void salvarChapa() {
		String nomePresidente = txtNomePresidente.getText();
		String nomeVice = txtNomeVice.getText();
		int numeroEleitoral;
			try {numeroEleitoral = Integer.parseInt(txtNumeroEleitoral.getText());}
			catch(Exception e) {numeroEleitoral = 0;}
		
		if(nomePresidente == null || nomePresidente.isEmpty()) {
			JOptionPane.showMessageDialog(null, "\"Nome Presidente\" não pode ser nulo");
			return;
		}
		
		if(nomeVice == null || nomeVice.isEmpty()) {
			JOptionPane.showMessageDialog(null, "\"Nome Vice\" não pode ser nulo");
			return;
		}
		
		if(numeroEleitoral < 1) {
			JOptionPane.showMessageDialog(null, "\"Número eleitoral\" deve ser um valor maior que \"1\"");
			return;
		}
		
		if(chapas.containsKey(numeroEleitoral)) {
			JOptionPane.showMessageDialog(null, "O número eleitoral \""+ numeroEleitoral +"\" já está cadastrado");
			return;
		}
		
		Chapa chapa = null;
		
		//TODO refact
		if(numeroEleitoral == 11)
			chapa = new Chapa(numeroEleitoral, nomePresidente, "res/candidatos/jordan.jpg", 
												nomeVice, "res/candidatos/nicolas.jpg");
		
		else if(numeroEleitoral == 69)
			chapa = new Chapa(numeroEleitoral, nomePresidente, "res/candidatos/manoa.jpg", 
												nomeVice, "res/candidatos/pedro.jpg");
		
		Util.armazenarChapa(chapa);
		
		chapas = Util.lerChapas();
		listagemChapas.setHashChapas(chapas);
		validate();
		repaint();
	}
}
