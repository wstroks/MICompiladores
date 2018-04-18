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
public class AutomatoDelimitadorComentario extends Automato{
    public AutomatoDelimitadorComentario(Buffer buffer) {
		super(buffer);
	}

	@SuppressWarnings("static-access")
	@Override
	public Token executar() {
		
		int estado = 0;
		//char c = buffer.proximoCaractere();
        int comentario=0;
        int contador=0;
		while(!buffer.fimCodigo()){
			char c = buffer.lookAhead();
			switch (estado) {
				case 0:
					System.out.println("estado 0: " + c);
					if(c == '/'){
						consumirCaractere();
						if(buffer.getTamanhoCodigo()==1){
						    return getToken(TipoToken.OPERADOR_ARITIMETICO_DIVISAO);    
						}
                        //c=buffer.proximoCaractere();
                        contador++;
                        if(c=='*'){
                        	estado=1;
                        	return getToken(TipoToken.DELIMITADOR_COMENTARIO_BLOCO_INICIO); 
                        }
                        else if(c=='/'){
                            return getToken(TipoToken.DELIMITADOR_COMENTARIO_LINHA);
                            
                        }else{
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_DIVISAO); 
                        }
               
                    }
					else if(c=='*'){
						consumirCaractere();
						if(buffer.getTamanhoCodigo()==1){
							return getToken(TipoToken.OPERADOR_ARITIMETICO_MULTIPLICACAO);
						}
	                    //c=buffer.proximoCaractere();
	                    contador++;
	                    
	                    if(c=='/'){
	                        return getToken(TipoToken.DELIMITADOR_COMENTARIO_BLOCO_FIM); 
	                    }else{
	                        return getToken(TipoToken.OPERADOR_ARITIMETICO_MULTIPLICACAO); 
	                    }
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
