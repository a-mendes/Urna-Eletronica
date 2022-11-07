package urna.gui;

import static javax.swing.JOptionPane.showMessageDialog;
import static urna.gui.BotoesUrna.BRANCO;
import static urna.gui.BotoesUrna.CONFIRMA;
import static urna.gui.BotoesUrna.CORRIGE;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Chapa;

public class MonitorUrna extends JPanel {

	private static final long serialVersionUID = 1L;

	private HashMap<Integer, Chapa> chapas;
	private JLabel lblCargo;
	private JLabel lblNumero1, lblNumero2;
	private JLabel lblChapa;
	
	public MonitorUrna(HashMap<Integer, Chapa> chapas) {
		this.chapas = chapas;
		setPreferredSize(new Dimension(600, 400));
		setMinimumSize(new Dimension(600, 400));
		monitorVotando();
		
	}
	
	public void atualizarMonitor(BotoesUrna btnUrna) {
		if(btnUrna == BRANCO)
			monitorBranco();
		
		else if(btnUrna == CORRIGE)
			monitorCorrige();
		
		else if(btnUrna == CONFIRMA) {
			if(lblNumero1.getText().isEmpty() || lblNumero2.getText().isEmpty())
				showMessageDialog(null, "Preencha corretamente os números ou vote em BRANCO antes de CONFIRMAR");
			
			else 
				monitorConfirma();
		}
		
		else if(lblNumero1.getText().isEmpty()) 
			lblNumero1.setText(btnUrna.getCodigo());
		
		else if(lblNumero2.getText().isEmpty()) {
			lblNumero2.setText(btnUrna.getCodigo());
			
			int numeroEleitoral = Integer.parseInt(lblNumero1.getText() + lblNumero2.getText()); 
			showCandidato(numeroEleitoral);
		}
		
		validate();
		repaint();
	}
	
	private void monitorVotando() {
		//TODO DRY
		lblCargo = new JLabel("Presidente\n", SwingConstants.CENTER);
		lblCargo.setSize(new Dimension(500, 100));
		if(getFont() != null)
			lblCargo.setFont(getFont().deriveFont((float) 20));
		add(lblCargo);
		
		lblNumero1 = new JLabel("", SwingConstants.CENTER);
		lblNumero1.setPreferredSize(new Dimension(100, 100));
		lblNumero1.setBorder(BorderFactory.createLineBorder(Color.black));
		add(lblNumero1);
		
		lblNumero2 = new JLabel("", SwingConstants.CENTER);
		lblNumero2.setPreferredSize(new Dimension(100, 100));
		lblNumero2.setBorder(BorderFactory.createLineBorder(Color.black));
		add(lblNumero2);
		
		lblChapa = new JLabel();
		add(lblChapa);
	}
	
	private void monitorBranco() {
		removeAll();
		
		//TODO DRY
		lblCargo = new JLabel("Presidente\n", SwingConstants.CENTER);
		lblCargo.setMinimumSize(new Dimension(500, 100));
		if(getFont() != null)
			lblCargo.setFont(getFont().deriveFont((float) 20));
		add(lblCargo);
		
		JLabel lblBranco = new JLabel("VOTO EM BRANCO", SwingConstants.CENTER);
		lblBranco.setSize(new Dimension(500, 300));
		if(getFont() != null)
			lblBranco.setFont(getFont().deriveFont((float) 30));
		add(lblBranco);
	}
	
	private void monitorCorrige() {
		removeAll();
		monitorVotando();
	}
	
	private void monitorConfirma() {
		//TODO barulinho e fim
	}
	
	private void showCandidato(int numeroEleitoral) {
		Chapa chapa = chapas.get(numeroEleitoral);
		
		if(chapa == null) {
			//TODO voto nulo
			return;
		}
		
		String infoChapa = "<HTML><BODY>"
				+ " <h2>"+ chapa.getNumeroEleitoral() +"</h2>"
				+ " <b>Presidente: </b> " + chapa.getNomePresidente() + "<br/>"
				+ " <b>Vice: </b> " + chapa.getNomeVice() + "<br/>"
			 + "</BODY></HTML>";

		lblChapa.setText(infoChapa);
		lblChapa.setPreferredSize(new Dimension(400, 150));
	}
}
