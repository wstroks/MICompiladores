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
public class AutomatoDelimitador extends Automato{
	
	public AutomatoDelimitador(Buffer buffer) {
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
					if(c == ';'){
                                            
                                            return new Token(TipoToken.DELIMITADOR_PONTO_VIRGULA, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                            
					}else if(c=='.'){
                                            return new Token(TipoToken.DELIMITADOR_PONTO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                        }
                                        else if(c=='('){
                                            return new Token(TipoToken.DELIMITADOR_ABRE_PARENTESES, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                        }
                                        else if(c==')'){
                                            return new Token(TipoToken.DELIMITADOR_FECHA_PARENTESES, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                        }else if(c=='{'){
                                            return new Token(TipoToken.DELIMITADOR_ABRE_CHAVE, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                        }
                                        else if(c=='}'){
                                            return new Token(TipoToken.DELIMITADOR_FECHA_CHAVE, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                        }else if(c==','){
                                            return new Token(TipoToken.DELIMITADOR_VIRGULA, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                        }
                                        else if(c==']'){
                                            return new Token(TipoToken.DELIMITADOR_FECHA_COLCHETE, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                        }
                                        else if(c=='['){
                                            return new Token(TipoToken.DELIMITADOR_ABRE_COLCHETE, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
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