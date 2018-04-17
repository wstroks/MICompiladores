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
		System.out.println("Automato Cadeia de Caracteres");
		int estado = 0;
		
		while(!buffer.fimCodigo()){
			char c = buffer.lookAhead();
			switch (estado) {
				case 0:
					System.out.println("	estado 0: " + c);
					if(c == '"'){
						estado = 1;
						consumirCaractere(false);
					}
					else{
						estado = -1;
					}
					break;
					
				case 1:
					System.out.println("	estado 1: " + c);
					if(c == '"'){
						consumirCaractere(false);
						return new Token(TipoToken.CADEIA_CARACTERES, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
					}
					else if(this.isLetra(c) || this.isDigito(c) || this.isSimbolo(c)){
						consumirCaractere(false);
						//c = buffer.proximoCaractere();
					}
					else if(this.isBarraInvertida(c)){
						estado = 2;
						consumirCaractere(false);
						//c = buffer.proximoCaractere();
					}
					else{
						estado = -1;
					}
					break;
				case 2:
					System.out.println("	estado 2: " + c);
					if(this.isBarraInvertida(c)){
						lexema += buffer.proximoCaractere();
					}
					else if(this.isLetra(c) || this.isDigito(c) || this.isSimbolo(c) || c == '"'){
						estado = 1;
						consumirCaractere(false);
					}
					else{
						estado = -1;
					}
					break;
	
				default:
					System.out.println("	estado default: " + c);
					consumirCaractere(true);
					return new Token(TipoToken.CADEIA_CARACTERES, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
					//return new Token(TipoToken.INDEFINIDO, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
			}
			
		}
		
		return new Token(TipoToken.INDEFINIDO, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
		
	}

}
