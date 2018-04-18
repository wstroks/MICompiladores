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
                    //System.out.println("estado 0: " + c);
                    if (c == '-'){ 
                    	consumirCaractere();
                        if(buffer.getTamanhoCodigo() == 1){
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO);
                        }
                        else if(contadorIncremento == 1){
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
                        }
	                    contador++;
	                    contadorIncremento++;
	                    estado = 0;

                    }
                    else if (c == ' ') {
                    	consumirCaractere();
                        contador++;
                        estado = 0;
                    }
                    else if (this.isDigito(c)) {
                    	consumirCaractere();
                        if (buffer.getTamanhoCodigo() == 1) {
                            return getToken(TipoToken.NUMERO);
                        } 
                        else if (contador == buffer.getTamanhoCodigo()) {
                            return getToken(TipoToken.NUMERO);
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
                        	consumirCaractere();
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO);
                        }
                        estado = -1;
                    }
                    break;
                case 1:
                    System.out.println("estado 1: " + c);
                    if (c == '.') {
                    	consumirCaractere();
                        estado = 2;
                        contador++;                   
                    }
                    break;
                case 2:
                    //System.out.println("estado 2: " + c);
                    if (this.isDigito(c)) {
                    	consumirCaractere();
                        if (contador == buffer.getTamanhoCodigo()) {
                            return getToken(TipoToken.NUMERO);
                        }
                        else{
                            contador++;
                            estado=2;
                        }
                    }
                    else {
                        return getToken(TipoToken.NUMERO);
                    }
                    break;
                default:
                    //System.out.println("estado default: " + c);
                    return getToken(TipoToken.NUMERO);
            }

        }

        return getToken(TipoToken.INDEFINIDO);

    }

}
