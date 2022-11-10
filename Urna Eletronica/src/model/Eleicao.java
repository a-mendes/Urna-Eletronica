package model;

import java.util.HashMap;

import util.Util;

public class Eleicao {

	private HashMap<Integer, Chapa> chapas;

	private int votosBrancos, votosNulos;
	
	public Eleicao(HashMap<Integer, Chapa> chapas, int votosBrancos, int votosNulos) {
		this.chapas = chapas;
		this.setVotosBrancos(votosBrancos);
		this.setVotosNulos(votosNulos);
	}
	
	public void computarVoto(int numeroEleitoral) {
		if(numeroEleitoral < 0) {
			if(numeroEleitoral == -1)
				setVotosBrancos(getVotosBrancos() + 1);
		}
		
		else {
			Chapa chapa = chapas.get(numeroEleitoral);
			
			if(chapa != null)
				chapa.adicionarVoto();
			else
				setVotosNulos(getVotosNulos() + 1);
		}
		
		try {
			Util.atualizarEleicao(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getVotosNulos() {
		return votosNulos;
	}

	public void setVotosNulos(int votosNulos) {
		this.votosNulos = votosNulos;
	}

	public int getVotosBrancos() {
		return votosBrancos;
	}

	public void setVotosBrancos(int votosBrancos) {
		this.votosBrancos = votosBrancos;
	}

	public HashMap<Integer, Chapa> getChapas() {
		return chapas;
	}
	
	public void setChapas(HashMap<Integer, Chapa> chapas) {
		this.chapas = chapas;
	}
}
