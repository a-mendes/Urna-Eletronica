package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.Chapa;

public class Util {
	
	/**
	 * Lê as chapas cadastradas, caso existam
	 * 
	 * @return ArrayList<Chapa>
	 * @throws Exception
	 */
	public static ArrayList<Chapa> lerChapas() throws Exception {
		ArrayList<Chapa> chapas = new ArrayList<Chapa>();
		
		File file = new File("chapas.txt");
	 
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
	
        String strNumeroEleitoral;
        while ((strNumeroEleitoral = bufferedReader.readLine()) != null) {
        	int numeroEleitoral = Integer.parseInt(strNumeroEleitoral);
        	String nomePresidente = bufferedReader.readLine();
        	String nomeVice = bufferedReader.readLine();
        	
        	Chapa chapa = new Chapa(numeroEleitoral, nomeVice, nomePresidente);
        	
        	chapas.add(chapa);
        }
        
        bufferedReader.close();
        
        return chapas;
	}
	
	/**
	 * Armazena uma chapa no arquivo de base de dados
	 * @param Chapa chapa
	 * @throws Exception
	 */
	public static void armazenarChapa(Chapa chapa) throws Exception {
		File file = new File("chapas.txt");
	 
		if(!file.exists())
			file.createNewFile();
		
	    PrintWriter printWriter = new PrintWriter(new FileWriter(file));
	    printWriter.printf("%d", chapa.getNumeroEleitoral());
	    printWriter.printf("%s", chapa.getNomePresidente());
	    printWriter.printf("%s", chapa.getNomeVice());
	    printWriter.close();
	}
	
	/**
	 * Verifica se há eleição em andamento
	 * @return boolean
	 */
	public static boolean hasEleicaoEmAndamento() {
		File file = new File("votos.txt");
		return file.exists();
	}
}
