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
		lexema = "";
		int estado = 0;
		
		while(!buffer.fimCodigo()){
			char c = buffer.lookAhead();
			switch (estado) {
				case 0:
					//System.out.println("	estado 0: " + c);
					if(c == '"'){
						estado = 1;
						consumirCaractere();
					}
					else{
						estado = -1;
					}
					break;
					
				case 1:
					//System.out.println("	estado 1: " + c);
					if(c == '"'){
						consumirCaractere();
						return getToken(TipoToken.CADEIA_CARACTERES);
					}
					else if(this.isLetra(c) || this.isDigito(c) || this.isSimbolo(c)){
						consumirCaractere();
					}
					else if(this.isBarraInvertida(c)){
						estado = 2;
						consumirCaractere();
					}
					else{
						estado = -1;
					}
					break;
				case 2:
					//System.out.println("	estado 2: " + c);
					if(this.isBarraInvertida(c)){
						lexema += buffer.proximoCaractere();
					}
					else if(this.isLetra(c) || this.isDigito(c) || this.isSimbolo(c) || c == '"'){
						estado = 1;
						consumirCaractere();
					}
					else{
						estado = -1;
					}
					break;
	
				default:
					//System.out.println("	estado default: " + c);
					return getToken(TipoToken.CADEIA_CARACTERES);
			}
			
		}
		
		return getToken(TipoToken.INDEFINIDO);
		
	}

}
