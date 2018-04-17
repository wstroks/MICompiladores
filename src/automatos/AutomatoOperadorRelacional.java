/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatos;

import lexico.Buffer;
import lexico.TipoToken;
import lexico.Token;


public class AutomatoOperadorRelacional extends Automato{
	
	public AutomatoOperadorRelacional(Buffer buffer) {
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
                                        if(buffer.getTamanhoCodigo()==1){    
                                         return new Token(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                        }
                                        c=buffer.proximoCaractere();
                                        estado=1;
                                   }else if(c=='>'){
                                       if(buffer.getTamanhoCodigo()==1){ 
                                       return new Token(TipoToken.OPERADOR_RELACIONAL_MAIOR_QUE, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                       }
                                       c=buffer.proximoCaractere();
                                        estado=2;
                                       
                                   }
                                   else if(c=='<'){
                                       if(buffer.getTamanhoCodigo()==1){ 
                                       return new Token(TipoToken.OPERADOR_RELACIONAL_MENOR_QUE, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                       }
                                        c=buffer.proximoCaractere();
                                        estado=3;
                                   }else if(c=='='){
                                       if(buffer.getTamanhoCodigo()==1){ 
                                       return new Token(TipoToken.OPERADOR_RELACIONAL_ATRIBUICAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                       }
                                       c=buffer.proximoCaractere();
                                       estado=4;
                                   }else{
                                      estado=-1;
                                       
                                   }
                                        
					
					break;
                                case 1:
                                   System.out.println("estado 1: " + c);
                                    if(c=='='){
                                       
                                       return new Token(TipoToken.OPERADOR_RELACIONAL_DIFERENTE, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                       
                                   }else{
                                      
                                       return new Token(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                   }
                                    
                                case 2:
                                    System.out.println("estado 2: " + c);
                                    if(c=='='){
                                       
                                       return new Token(TipoToken.OPERADOR_RELACIONAL_MAIOR_IGUAL_QUE, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                       
                                   }else{
                                      
                                       return new Token(TipoToken.OPERADOR_RELACIONAL_MAIOR_QUE, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                   }
                                    
                                case 3:
                                    System.out.println("estado 3: " + c);
                                    if(c=='='){
                                       
                                       return new Token(TipoToken.OPERADOR_RELACIONAL_MENOR_IGUAL_QUE, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                       
                                   }else{
                                      
                                       return new Token(TipoToken.OPERADOR_RELACIONAL_MENOR_QUE, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                    }
                                case 4:
                                     System.out.println("estado 3: " + c);
                                    if(c=='='){
                                       
                                       return new Token(TipoToken.OPERADOR_RELACIONAL_IGUAL, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                       
                                   }else{
                                      
                                       return new Token(TipoToken.OPERADOR_RELACIONAL_ATRIBUICAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                                    }
                                
	
				default:
					System.out.println("estado default: " + c);
					return new Token(TipoToken.INDEFINIDO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
			}
			
		}
		
		return new Token(TipoToken.INDEFINIDO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
		
	}

}