package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Eleicao {

	private HashMap<Integer, Chapa> chapas;
	
	public Eleicao(ArrayList<Chapa> chapas) {
		this.chapas = new HashMap<>();
		
		for (Chapa chapa : chapas) {
			this.chapas.put(chapa.getNumeroEleitoral(), chapa);
		}
	}
	
	public void computarVoto(int numeroEleitoral) {
		Chapa chapa = chapas.get(numeroEleitoral);
		chapa.adicionarVoto();
		//TODO armazenar em memoria nao volatil
	}
}
