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
		char c = buffer.proximoCaractere();
                int comentario=0;
                int contador=0;
		while(!buffer.fimCodigo()){
			switch (estado) {
				case 0:
					System.out.println("estado 0: " + c);
					if(c == '/'){
                                             if(buffer.getTamanhoCodigo()==1){
                                                 return new Token(TipoToken.OPERADOR_ARITIMETICO_DIVISAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                                 
                                                 }
                                            c=buffer.proximoCaractere();
                                           
                                            contador++;
                                            if(c=='*'){
                                                    
                                                    estado=1;
                                                    return new Token(TipoToken.DELIMITADOR_COMENTARIO_BLOCO_INICIO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual()); 
                                                    }
                                            else if(c=='/'){
                                                return new Token(TipoToken.DELIMITADOR_COMENTARIO_LINHA, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                                
                                            }else{
                                                return new Token(TipoToken.OPERADOR_ARITIMETICO_DIVISAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual()); 
                                            }
                                            
                                            
                                            
                                        }else if(c=='*'){
                                             if(buffer.getTamanhoCodigo()==1){
                                                 return new Token(TipoToken.OPERADOR_ARITIMETICO_MULTIPLICACAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                                 
                                                 }
                                        c=buffer.proximoCaractere();
                                        contador++;
                                        
                                        if(c=='/'){
                                            return new Token(TipoToken.DELIMITADOR_COMENTARIO_BLOCO_FIM, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual()); 
                                        }else{
                                            return new Token(TipoToken.OPERADOR_ARITIMETICO_MULTIPLICACAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual()); 
                                        }
                                    }
					else{
						estado = -1;
					}
					break;
                               
                                    
	
				default:
					System.out.println("estado default: " + c);
					return new Token(TipoToken.INDEFINIDO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
			}
			
		}
		
		return new Token(TipoToken.INDEFINIDO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
		
	}

}
