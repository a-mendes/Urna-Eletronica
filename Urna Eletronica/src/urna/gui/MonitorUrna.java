package urna.gui;

import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.SwingConstants.CENTER;
import static urna.gui.BotoesUrna.BRANCO;
import static urna.gui.BotoesUrna.CONFIRMA;
import static urna.gui.BotoesUrna.CORRIGE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
		setLayout(null);
		setOpaque(false);
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
		
		//TODO dry
		Font font = Font.getFont("ARIAL");
		if(getFont() != null)
			font = getFont();
		
		lblCargo = initLblCargo();
		add(lblCargo);
		
		//TODO talvez diminuir
		lblNumero1 = new JLabel("", SwingConstants.CENTER);
		lblNumero1.setBorder(BorderFactory.createLineBorder(Color.black));
		lblNumero1.setBounds(100, 60, 50, 50);
		lblNumero1.setFont(font.deriveFont((float) 26));
		add(lblNumero1);
		
		lblNumero2 = new JLabel("", SwingConstants.CENTER);
		lblNumero2.setBorder(BorderFactory.createLineBorder(Color.black));
		lblNumero2.setBounds(160, 60, 50, 50);
		lblNumero2.setFont(font.deriveFont((float) 26));
		add(lblNumero2);
		
		lblChapa = new JLabel();
		add(lblChapa);
	}
	
	private void monitorBranco() {
		removeAll();
		
		//TODO dry
		Font font = Font.getFont("ARIAL");
		if(getFont() != null)
			font = getFont();
		
		lblCargo = initLblCargo();
		add(lblCargo);
		
		JLabel lblBranco = new JLabel("VOTO EM BRANCO", SwingConstants.CENTER);
		lblBranco.setBounds(0, 60, 575, 100);
		lblBranco.setFont(font.deriveFont((float) 30));
		add(lblBranco);
	
		initLblInfoVoto();
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
		
		//TODO dry
		Font font = Font.getFont("ARIAL");
		if(getFont() != null)
			font = getFont();
		
		if(chapa == null) {
			JLabel lblNulo = new JLabel("VOTO NULO", SwingConstants.CENTER);
			lblNulo.setBounds(0, 115, 575, 100);
			lblNulo.setFont(font.deriveFont((float) 30));
			add(lblNulo);
			
			initLblInfoVoto();
			return;
		}
		
		String infoChapa = "<HTML><BODY>"
				+ " <b>Número: </b>"+ chapa.getNumeroEleitoral() +"<br/>"
				+ " <b>Presidente: </b> " + chapa.getNomePresidente() + "<br/>"
				+ " <b>Vice: </b> " + chapa.getNomeVice() + "<br/>"
			 + "</BODY></HTML>";

		lblChapa.setText(infoChapa);
		lblChapa.setFont(font.deriveFont((float) 20));
		lblChapa.setBounds(10, 130, 300, 100);
		add(lblChapa);
		
		ImageIcon ftPresidente = new ImageIcon(chapa.getFotoPresidente());
		JLabel lblFotoPresidente = new JLabel(ftPresidente);
		lblFotoPresidente.setBounds(450, 10, 120, 120);
		add(lblFotoPresidente);
		
		JLabel lblTxtPresidente = new JLabel("Presidente", CENTER);
		lblTxtPresidente.setBounds(450, 130, 120, 20);
		lblTxtPresidente.setFont(font.deriveFont((float) 12));
		add(lblTxtPresidente);
		
		ImageIcon ftVice = new ImageIcon(chapa.getFotoVice());
		JLabel lblFotoVice = new JLabel(ftVice);
		lblFotoVice.setBounds(450, 160, 120, 120);
		add(lblFotoVice);
		
		JLabel lblTxtVice = new JLabel("Vice Presidente", CENTER);
		lblTxtVice.setBounds(450, 290, 120, 20);
		lblTxtVice.setFont(font.deriveFont((float)12));
		add(lblTxtVice);
		
		initLblInfoVoto();
	}
	
	private JLabel initLblCargo() {
		//TODO dry
		Font font = Font.getFont("ARIAL");
		if(getFont() != null)
			font = getFont();
		
		lblCargo = new JLabel("Presidente", SwingConstants.CENTER);
		lblCargo.setSize(new Dimension(575, 50));
		lblCargo.setFont(font.deriveFont((float) 20));
		add(lblCargo);
		
		return lblCargo;
	}
	
	private void initLblInfoVoto() {
		//TODO dry
		Font font = Font.getFont("ARIAL");
		if(getFont() != null)
			font = getFont();
		
		String infoVoto = "<HTML><BODY>"
				+ "<hr/>"
				+ "Aperte a tecla:<br/>"
				+ "CONFIRMA para CONFIRMAR este voto<br/>"
				+ "CORRIGE para REINICIAR este voto"
	 			+ "</BODY></HTML>";

		JLabel lblInfoVoto = new JLabel(infoVoto);
		lblInfoVoto.setBounds(5, 220, 500, 120);
		lblInfoVoto.setFont(font.deriveFont((float) 14));
		add(lblInfoVoto);
	}
}
