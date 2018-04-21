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
	
	public char proximoCaractere(){
		if(fimCodigo()){
			return (Character) null;
		}
		
		if(codigo.charAt(posicaoAtual) == '\n'){
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
		return codigo.charAt(posicaoAtual+1);
	}
	
	public void goBack(){
		posicaoAtual--;
	}
	
    public static boolean isEspaco(Character c){
    	int ascii = (int) c;
    	return (ascii == 9 || ascii == 10 || ascii == 13 || ascii == 32);
    }
	
	public boolean isUltimoCaractere(){
		return ((codigo.length() - 1) == posicaoAtual);
	}
	
	/**
	 * Metodo utilizado para pular os comentarios contidos no codigo que esta sendo analisado
	 */
	public void pularComentario(){
		
		int estado = 0;
		boolean continua = true; 
		String comentario = "";
		TipoToken tipoToken = TipoToken.INDEFINIDO;
		
		int posicao = posicaoAtual;
		int linha = linhaAtual;
		
		while(!fimCodigo() && continua){
			char c = lookAhead();
			switch (estado) {
				case 0:
					//System.out.println("estado 0: " + c);
					if(c == '/'){
						estado = 1;
						comentario += c;
						proximoCaractere();
					}
					else{
						estado = -1;
					}
					break;
				case 1:
					//System.out.println("estado 1: " + c);
					if(c == '/'){
						estado = 2;
						comentario += c;
						proximoCaractere();
					}
					else if(c == '*'){
						estado = 3;
						comentario += c;
						proximoCaractere();
					}
					else{
						estado = -1;
					}
					break;
				case 2:
					//System.out.println("estado 2: " + c);
					if(codigo.charAt(posicaoAtual++) == '\n'){ //fim do comentario de linha
						tipoToken = TipoToken.COMENTARIO_LINHA;
						continua = false;
					}
					else{
						comentario += c;
					}
					break;
				case 3:
					//System.out.println("estado 3: " + c);
					comentario += c;
					if(c == '*'){
						estado = 4;
						proximoCaractere();
					}
					else{		
						proximoCaractere();
					}
					break;
				case 4:
					//System.out.println("estado 4: " + c);
					comentario += c;
					if(c == '/'){ //fim do comentario de bloco
						proximoCaractere();
						tipoToken = TipoToken.COMENTARIO_BLOCO;
						continua = false;
					}
					else{
						estado = 3;
						proximoCaractere();
					}
					break;
	
				default:
					//System.out.println("default: " + c);
					proximoCaractere();
					continua = false;
			}
		}
		
		//Ver depois onde e se será adicionando o token de comentario
		Token token = new Token(tipoToken, comentario, posicao, linha);
		token.print();
		
	}

}
