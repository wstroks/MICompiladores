/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatos;

import lexico.Buffer;
import lexico.TipoToken;
import lexico.Token;

/**
 *
 * @author wstro
 */
public class AutomatoIdentificador extends Automato{
    public AutomatoIdentificador(Buffer buffer) {
		super(buffer);
	}

	@SuppressWarnings("static-access")
	@Override
	public Token executar() {
		System.out.println("Automato Identificador");
		int estado = 0;
        int contador = 0;
		
		while(!buffer.fimCodigo()){
			char c = buffer.lookAhead();
			switch (estado) {
				case 0:
					System.out.println("estado 0: " + c);
					if(this.isLetra(c)){
                        contador++;
                        estado = 1;
                        consumirCaractere(false);
                    }
					else{
						estado = -1;
					}
					break;
                case 1:
                    contador++;
                     System.out.println("estado 1: " + c);
                     if(contador == buffer.getTamanhoCodigo()){
                    	 consumirCaractere(false);
                         return new Token(TipoToken.IDENTIFICADOR, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                     }else{
	                     if(this.isLetra(c)){
	                    	 consumirCaractere(false);
	                         estado = 1;
	                     }else if(this.isDigito(c)){
	                    	 consumirCaractere(false);
	                         estado = 1;
	                     }else if(c=='_'){  
	                    	 consumirCaractere(false);
	                         estado = 1;
	                     }else{
	                         return new Token(TipoToken.IDENTIFICADOR, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
	                     }
                     }
                     break;
	
				default:
					System.out.println("estado default: " + c);
					//lexema += buffer.proximoCaractere();
					consumirCaractere(true);
					return new Token(TipoToken.IDENTIFICADOR, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
			}
			
		}
		
		return new Token(TipoToken.INDEFINIDO, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
		
	}

}
