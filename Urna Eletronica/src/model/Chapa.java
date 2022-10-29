package model;

public class Chapa {

	private int numeroEleitoral;
	private String nomePresidente;
	private String nomeVice;
	private int qtdVotos;
	
	public Chapa(int numeroEleitoral, String nomePresidente, String nomeVice) {
		this.numeroEleitoral = numeroEleitoral;
		this.nomePresidente = nomePresidente;
		this.nomeVice = nomeVice;
		this.qtdVotos = 0;
	}

	public String getNomePresidente() {
		return nomePresidente;
	}
	
	public void setNomePresidente(String nomePresidente) {
		this.nomePresidente = nomePresidente;
	}
	
	public String getNomeVice() {
		return nomeVice;
	}
	
	public void setNomeVice(String nomeVice) {
		this.nomeVice = nomeVice;
	}
	
	public int getNumeroEleitoral() {
		return numeroEleitoral;
	}
	
	public void setNumeroEleitoral(int numeroEleitoral) {
		this.numeroEleitoral = numeroEleitoral;
	}
	
	public int getQtdVotos() {
		return qtdVotos;
	}
	
	public void adicionarVoto() {
		this.qtdVotos++;
	}
}