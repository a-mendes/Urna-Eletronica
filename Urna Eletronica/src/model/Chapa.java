package model;

public class Chapa {

	private int numeroEleitoral;
	private String nomePresidente;
	private String fotoPresidente;
	private String nomeVice;
	private String fotoVice;
	private int qtdVotos;
	
	public Chapa(int numeroEleitoral, String nomePresidente, String fotoPresidente, 
										String nomeVice, String fotoVice) {
		this.numeroEleitoral = numeroEleitoral;
		this.nomePresidente = nomePresidente;
		this.setFotoPresidente(fotoPresidente);
		this.nomeVice = nomeVice;
		this.setFotoVice(fotoVice);
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

	public String getFotoPresidente() {
		return fotoPresidente;
	}

	public void setFotoPresidente(String fotoPresidente) {
		this.fotoPresidente = fotoPresidente;
	}

	public String getFotoVice() {
		return fotoVice;
	}

	public void setFotoVice(String fotoVice) {
		this.fotoVice = fotoVice;
	}
}