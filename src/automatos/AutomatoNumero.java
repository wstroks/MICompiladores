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


public class AutomatoNumero extends Automato {

    public AutomatoNumero(Buffer buffer) {
        super(buffer);
    }

    @SuppressWarnings("static-access")
    @Override
    public Token executar() {
    	
    	System.out.println("Automato Numero");
        int estado = 0;
        int contador = 1;
        int contadorIncremento = 0;
        
        while (!buffer.fimCodigo()) {
        	char c = buffer.lookAhead();
            switch (estado) {
                case 0:
                    System.out.println("estado 0: " + c);
                    if (c == '-'){ 
                    	consumirCaractere(false);
                        if(buffer.getTamanhoCodigo() == 1){
                            return new Token(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                        }
                        else if(contadorIncremento == 1){
                            return new Token(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                        }
	                    contador++;
	                    contadorIncremento++;
	                    estado = 0;

                    }
                    else if (c == ' ') {
                    	consumirCaractere(false);
                        contador++;
                        estado = 0;
                    }
                    else if (this.isDigito(c)) {
                    	consumirCaractere(false);
                        if (buffer.getTamanhoCodigo() == 1) {
                            return new Token(TipoToken.NUMERO, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                        } 
                        else if (contador == buffer.getTamanhoCodigo()) {
                            return new Token(TipoToken.NUMERO, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                        } 
                        else if (contador < buffer.getTamanhoCodigo()) {
                            contador++;
                            if (c == '.') {
                                estado = 1;
                            } else {
                                estado = 0;
                            }
                        }
                    } 
                    else {
                        if(contadorIncremento == 1){
                        	consumirCaractere(false);
                            return new Token(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                        }
                        estado = -1;
                    }
                    break;
                case 1:
                    System.out.println("estado 1: " + c);
                    if (c == '.') {
                    	consumirCaractere(false);
                        estado = 2;
                        contador++;                   
                    }
                    break;
                case 2:
                    //System.out.println("estado 2: " + c);
                    if (this.isDigito(c)) {
                    	consumirCaractere(false);
                        if (contador == buffer.getTamanhoCodigo()) {
                            return new Token(TipoToken.NUMERO, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                        }
                        else{
                            contador++;
                            estado=2;
                        }
                    }
                    else {
                        return new Token(TipoToken.NUMERO, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                    }
                    break;
                default:
                    System.out.println("estado default: " + c);
                    consumirCaractere(true);
                    return new Token(TipoToken.NUMERO, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
            }

        }

        return new Token(TipoToken.INDEFINIDO, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());

    }

}
