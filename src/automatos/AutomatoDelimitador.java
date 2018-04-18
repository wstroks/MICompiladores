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
public class AutomatoDelimitador extends Automato{
	
	public AutomatoDelimitador(Buffer buffer) {
		super(buffer);
	}

	@SuppressWarnings("static-access")
	@Override
	public Token executar() {
		
		int estado = 0;
		//char c = buffer.proximoCaractere();
		while(!buffer.fimCodigo()){
			char c = buffer.lookAhead();
			switch (estado) {
				case 0:
					System.out.println("estado 0: " + c);
					if(c == ';'){
						consumirCaractere();
						return getToken(TipoToken.DELIMITADOR_PONTO_VIRGULA);                   
					}else if(c=='.'){
                        return getToken(TipoToken.DELIMITADOR_PONTO);
                    }
                    else if(c=='('){
                        return getToken(TipoToken.DELIMITADOR_ABRE_PARENTESES);
                    }
                    else if(c==')'){
                        return getToken(TipoToken.DELIMITADOR_FECHA_PARENTESES);
                    }else if(c=='{'){
                        return getToken(TipoToken.DELIMITADOR_ABRE_CHAVE);
                    }
                    else if(c=='}'){
                        return getToken(TipoToken.DELIMITADOR_FECHA_CHAVE);
                    }else if(c==','){
                        return getToken(TipoToken.DELIMITADOR_VIRGULA);
                    }
                    else if(c==']'){
                        return getToken(TipoToken.DELIMITADOR_FECHA_COLCHETE);
                    }
                    else if(c=='['){
                        return getToken(TipoToken.DELIMITADOR_ABRE_COLCHETE);
                    }
					else{
						estado = -1;
					}
					break;
                                                         	
				default:
					System.out.println("estado default: " + c);
					return getToken(TipoToken.INDEFINIDO);
			}
			
		}
		
		return getToken(TipoToken.INDEFINIDO);
		
	}

}