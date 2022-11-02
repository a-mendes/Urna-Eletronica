package model;

import java.util.HashMap;

public class Eleicao {

	private HashMap<Integer, Chapa> chapas;
	
	public Eleicao(HashMap<Integer, Chapa> chapas) {
		this.chapas = chapas;
	}
	
	public void computarVoto(int numeroEleitoral) {
		Chapa chapa = chapas.get(numeroEleitoral);
		chapa.adicionarVoto();
		//TODO armazenar em memoria nao volatil
	}
}
