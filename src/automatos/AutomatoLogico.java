/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aque;

/**
 *
 * @author wstro
 */
import aque.Buffer;
import aque.TipoToken;
import aque.Token;

public class AutomatoLogico extends Automato{
	
	public AutomatoLogico(Buffer buffer) {
		super(buffer);
	}

	@SuppressWarnings("static-access")
	@Override
	public Token executar() {
		
		int estado = 0;
		char c = buffer.proximoCaractere();
		while(!buffer.fimCodigo()){
			switch (estado) {
				case 0:
					System.out.println("estado 0: " + c);
					if(c == '!'){
                                            
                                            return new Token(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                            
					}else if(c=='&'){
                                            if(buffer.getTamanhoCodigo()==1){
                                            return new Token(TipoToken.INDEFINIDO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                            }
                                         c=buffer.proximoCaractere();
                                         estado=1;
                                        }else if(c=='|'){
                                            if(buffer.getTamanhoCodigo()==1){
                                            return new Token(TipoToken.INDEFINIDO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                            }
                                         c=buffer.proximoCaractere();
                                         estado=1;
                                        }
                                            
					else{
						estado = -1;
					}
					break;
                                case 1:
                                   System.out.println("estado 0: " + c);
                                    if(c=='&'){
                                        return new Token(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                    
                                    }
                                    else if(c=='|'){
                                        return new Token(TipoToken.OPERADOR_LOGICO_OU, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                    }
                                    
                                    else{
                                      estado=-1;
					
					}
                                
	
				default:
					System.out.println("estado default: " + c);
					return new Token(TipoToken.INDEFINIDO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
			}
			
		}
		
		return new Token(TipoToken.INDEFINIDO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
		
	}

}