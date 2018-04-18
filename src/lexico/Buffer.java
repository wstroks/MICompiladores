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
			//System.out.println("caractere: " + c + " | " + "linha: " + linhaAtual + " | " + "posição atual: " + posicaoAtual);
		}
	}
	
	@SuppressWarnings("null")
	public char proximoCaractere(){
		
		pularComentario();
		
		if(this.fimCodigo()){
			return (Character) null;
		}
		
		if(this.codigo.charAt(posicaoAtual) == '\n'){
			linhaAtual++;
		}
		
		return codigo.charAt(posicaoAtual++);
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
		return codigo.length();
	}
	
	public boolean fimCodigo(){
		return codigo.length() == posicaoAtual;
	}
	
	public char getCaractereAtual(){
		if(this.fimCodigo()){
			return (Character) null;
		}
		return codigo.charAt(posicaoAtual);
	}
	
	public char lookAhead(){
		//System.out.println("\nlook ahead: tamanho do codigo: " + getTamanhoCodigo() + " | posicao atual: " + getPosicaoAtual() + " | caractere atual: " + getCaractereAtual()+"\n");
		if(this.fimCodigo()){
			return (Character) null;
		}
		return codigo.charAt(posicaoAtual);
	}
	
	public char doubleLookAhead(){
		if(this.fimCodigo()){
			return (Character) null;
		}
		return codigo.charAt(posicaoAtual++);
	}
	
	private void pularComentario(){
		//vou implementar ainda
	}

}
