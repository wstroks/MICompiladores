package lexico;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Tayane
 *
 */
public class Buffer {
	
	protected String codigo = "";
	private int linhaAtual = 0;
	private int posicaoAtual = 0;
	
	public Buffer(String nomeArquivo) throws FileNotFoundException{
		Scanner s = new Scanner(new File(nomeArquivo));
		this.codigo = s.useDelimiter("\\Z").next();
		s.close();
	}
	
	public void printCodigo(){
		while(!fimCodigo()){
			char c = proximoCaractere();
			System.out.println("caractere: " + c + " | " + "linha: " + linhaAtual + " | " + "posição atual: " + posicaoAtual);
		}
	}
	
	@SuppressWarnings("null")
	public char proximoCaractere(){
		
		if(this.fimCodigo()){
			return (Character) null;
		}
		
		if(this.codigo.charAt(posicaoAtual) == '\n'){
			linhaAtual++;
		}
		
		return this.codigo.charAt(posicaoAtual++);
	}
	
	public int getLinhaAtual() {
		return linhaAtual;
	}
	public void setLinhaAtual(int linhaAtual) {
		this.linhaAtual = linhaAtual;
	}
	public int getPosicaoAtual() {
		return posicaoAtual;
	}
	public void setPosicaoAtual(int posicaoAtual) {
		this.posicaoAtual = posicaoAtual;
	}
	
	public int getTamanhoCodigo(){
		return this.codigo.length();
	}
	
	public boolean fimCodigo(){
		return this.codigo.length() == (this.posicaoAtual-1);
	}
	

}
