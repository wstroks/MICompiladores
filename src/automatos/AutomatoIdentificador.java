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
					//System.out.println("estado 0: " + c);
					if(this.isLetra(c)){
                        contador++;
                        estado = 1;
                        consumirCaractere();
                    }
					else{
						estado = -1;
					}
					break;
                case 1:
                     contador++;
                     //System.out.println("estado 1: " + c);
                     if(contador == buffer.getTamanhoCodigo()){
                    	 consumirCaractere();
                         return getToken(TipoToken.IDENTIFICADOR);
                     }else{
	                     if(this.isLetra(c)){
	                    	 consumirCaractere();
	                         estado = 1;
	                     }else if(this.isDigito(c)){
	                    	 consumirCaractere();
	                         estado = 1;
	                     }else if(c=='_'){  
	                    	 consumirCaractere();
	                         estado = 1;
	                     }else{
	                         return getToken(TipoToken.IDENTIFICADOR);
	                     }
                     }
                     break;
	
				default:
					//System.out.println("estado default: " + c);
					consumirCaractere();
					return getToken(TipoToken.IDENTIFICADOR);
			}
			
		}
		
		return getToken(TipoToken.INDEFINIDO);
		
	}

}
