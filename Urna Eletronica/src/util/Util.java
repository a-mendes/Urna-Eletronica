package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import model.Chapa;
import model.Eleicao;

public class Util {
	
	private static File fileChapas = new File("chapas.txt");
	private static File fileVotos = new File("votos.txt");
	
	/**
	 * Retorna as chapas cadastradas ou um hashmap vazio
	 * @return HashMap
	 */
	public static HashMap<Integer, Chapa> lerChapas() {
		try {
			return lerArquivosChapas();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um problema ao ler o arquivo \"chapas.txt\"");
			e.printStackTrace();
			return new HashMap<Integer, Chapa>();
		}
	}
	/**
	 * Lê as chapas cadastradas, caso existam
	 * 
	 * @return HashMap<Chapa>
	 * @throws Exception
	 */
	private static HashMap<Integer, Chapa> lerArquivosChapas() throws Exception {
		HashMap<Integer, Chapa> chapas = new HashMap<Integer, Chapa>();

		if(!fileChapas.exists())
			return new HashMap<Integer, Chapa>();
	 
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileChapas));
	
        String strNumeroEleitoral;
        while ((strNumeroEleitoral = bufferedReader.readLine()) != null) {
        	int numeroEleitoral = Integer.parseInt(strNumeroEleitoral);
        	String nomePresidente = bufferedReader.readLine();
        	String nomeVice = bufferedReader.readLine();
        	
        	//TODO refact
        	Chapa chapa = null;
        	if(numeroEleitoral == 11)
    			chapa = new Chapa(numeroEleitoral, nomePresidente, "res/candidatos/jordan.jpg", 
    												nomeVice, "res/candidatos/nicolas.jpg");
    		
    		else if(numeroEleitoral == 69)
    			chapa = new Chapa(numeroEleitoral, nomePresidente, "res/candidatos/manoa.jpg", 
    												nomeVice, "res/candidatos/pedro.jpg");        	
        	chapas.put(chapa.getNumeroEleitoral(), chapa);
        }
        
        bufferedReader.close();
        
        return chapas;
	}
	
	public static void armazenarChapa(Chapa chapa) {
		try {
			armazenarChapaNoArquivo(chapa);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um problema ao abrir o arquivo \"chapas.txt\"");
			e.printStackTrace();
		}
	}
	
	/**
	 * Armazena uma chapa no arquivo de base de dados
	 * @param Chapa chapa
	 * @throws Exception
	 */
	private static void armazenarChapaNoArquivo(Chapa chapa) throws Exception {

		if(!fileChapas.exists()) 
			fileChapas.createNewFile();
		
		BufferedWriter out = new BufferedWriter(new FileWriter(fileChapas, true));
		
		String strChapa = chapa.getNumeroEleitoral() + "\n" 
						+ chapa.getNomePresidente() + "\n" 
						+ chapa.getNomeVice() + "\n";
		
		out.write(strChapa);
		out.close();
	}
	
	public static void atualizarChapas(HashMap<Integer, Chapa> chapas) {
		if(fileChapas.exists()) {
			fileChapas.delete();
		}
		
		ArrayList<Chapa> listChapas = HashToArray(chapas);
		
		for (Chapa chapa : listChapas) {
			armazenarChapa(chapa);
		}
	}
	
	/**
	 * Verifica se há eleição em andamento
	 * @return boolean
	 */
	public static boolean hasEleicaoEmAndamento() {
		return fileVotos.exists();
	}
	
	/**
	 * Verifica se há chapas cadastradas
	 * @return boolean
	 */
	public static boolean hasChapas() {
		try {
			HashMap<Integer, Chapa> chapas = lerChapas();
			
			if(chapas == null || chapas.isEmpty()) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Converte um HashMap de Chapas para um ArrayList
	 * @param HashMap<Integer, Chapa>
	 * @return ArrayList
	 */
	public static ArrayList<Chapa> HashToArray(HashMap<Integer, Chapa> hashChapas){
		ArrayList<Chapa> listChapas = new ArrayList<>();
		for (Map.Entry<Integer, Chapa> chapa : hashChapas.entrySet()) {
		    listChapas.add(chapa.getValue());
		}
		
		return listChapas;
	}
	
	public static Eleicao lerEleicao() {
		try {
			return lerArquivoEleicao();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Eleicao(lerChapas(), 0, 0);
	}
	private static Eleicao lerArquivoEleicao() throws Exception {
		if(!fileVotos.exists())
			return new Eleicao(lerChapas(), 0, 0);
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileVotos))) {
			String strVotosNulos = bufferedReader.readLine();
			int votosNulos = Integer.parseInt(strVotosNulos);
			
			String strVotosBrancos = bufferedReader.readLine();
			int votosBrancos = Integer.parseInt(strVotosBrancos);
			
			HashMap<Integer, Chapa> chapas = lerChapas();
			
			String strNumeroEleitoral;
			while ((strNumeroEleitoral = bufferedReader.readLine()) != null) {
				int numeroEleitoral = Integer.parseInt(strNumeroEleitoral);
				String strVotos = bufferedReader.readLine();
				int votos = Integer.parseInt(strVotos);
				
				chapas.get(numeroEleitoral).setQtdVotos(votos);
			}
			
			return new Eleicao(chapas, votosBrancos, votosNulos);
		}
	}
	
	public static void atualizarEleicao(Eleicao eleicao) throws Exception {
		if(!fileVotos.exists())
			fileVotos.createNewFile();
		else {
			fileVotos.delete();
		}
			
		BufferedWriter out = new BufferedWriter(new FileWriter(fileVotos, true));
		
		String strEleicao = eleicao.getVotosNulos() + "\n" 
						+ eleicao.getVotosBrancos() + "\n";
		
		ArrayList<Chapa> chapas = HashToArray(eleicao.getChapas());
		
		for (Chapa chapa : chapas) {
			strEleicao += chapa.getNumeroEleitoral() + "\n";
			strEleicao += chapa.getQtdVotos() + "\n";
		}
		
		out.write(strEleicao);
		out.close();
	}
	
	public static void finalizarEleicao() {
		Eleicao eleicao = lerEleicao();
	}

}
