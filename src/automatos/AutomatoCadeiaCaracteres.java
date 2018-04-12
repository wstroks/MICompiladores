package automatos;

import lexico.Buffer;
import lexico.TipoToken;
import lexico.Token;

/**
 * 
 * @author Tayane
 *
 */
public class AutomatoCadeiaCaracteres extends Automato{
	
	public AutomatoCadeiaCaracteres(Buffer buffer) {
		super(buffer);
	}

	@SuppressWarnings("static-access")
	@Override
	public Token executar() {
		
		int estado = 0;
		char c = buffer.proximoCaractere();
		while(!buffer.fimCodigo()){
			System.out.println("Linha: " + buffer.getLinhaAtual());
			switch (estado) {
				case 0:
					System.out.println("	estado 0: " + c);
					if(c == '"'){
						estado = 1;
						c = buffer.proximoCaractere();
					}
					else{
						estado = -1;
					}
					break;
					
				case 1:
					System.out.println("	estado 1: " + c);
					if(c == '"'){
						return new Token(TipoToken.CADEIA_CARACTERES, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
					}
					else if(this.isLetra(c) || this.isDigito(c) || this.isSimbolo(c)){
						c = buffer.proximoCaractere();
					}
					else if(this.isBarraInvertida(c)){
						estado = 2;
						c = buffer.proximoCaractere();
					}
					else{
						estado = -1;
					}
					break;
				case 2:
					System.out.println("	estado 2: " + c);
					if(this.isBarraInvertida(c)){
						c = buffer.proximoCaractere();
					}
					else if(this.isLetra(c) || this.isDigito(c) || this.isSimbolo(c) || c == '"'){
						estado = 1;
						c = buffer.proximoCaractere();
					}
					else{
						estado = -1;
					}
					break;
	
				default:
					System.out.println("	estado default: " + c);
					return new Token(TipoToken.INDEFINIDO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
			}
			
		}
		
		return new Token(TipoToken.INDEFINIDO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
		
	}

}
