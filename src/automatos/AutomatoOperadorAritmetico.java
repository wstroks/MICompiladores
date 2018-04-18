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
public class AutomatoOperadorAritmetico extends Automato {

    public AutomatoOperadorAritmetico(Buffer buffer) {
        super(buffer);
    }

    @SuppressWarnings("static-access")
    @Override
    public Token executar() {

        int estado = 0;
        int contador=0;
        int ponto=0;
        
        //char c = buffer.proximoCaractere();
        while (!buffer.fimCodigo()) {
        	char c = buffer.lookAhead();
            switch (estado) {
                case 0:
                    System.out.println("estado 0: " + c);
                    if (c == '+') {
                        if (buffer.getTamanhoCodigo() == 1) {
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_ADICAO);
                        } else {
                            //c = buffer.proximoCaractere();
                        	consumirCaractere();
                            System.out.println(c);
                            if (c == '+') {
                                return getToken(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
                            } else {
                                return getToken(TipoToken.OPERADOR_ARITIMETICO_ADICAO);
                            }
                        }

                    } else if (c == '-') {
                        if (buffer.getTamanhoCodigo() == 1) {
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO);
                        } else {
                            estado =1;
                            //subtracao=1;
                        }

                    } else if (c == '/') {

                        if (buffer.getTamanhoCodigo() == 1) {
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_DIVISAO);

                        } else {
                            //c = buffer.proximoCaractere();
                        	consumirCaractere();
                            System.out.println(c);
                            if (c == '/') {
                                return getToken(TipoToken.DELIMITADOR_COMENTARIO_LINHA);
                            } else if(c=='*'){
                                return getToken(TipoToken.DELIMITADOR_COMENTARIO_BLOCO_INICIO);
                            }else {
                                return getToken(TipoToken.OPERADOR_ARITIMETICO_DIVISAO);
                            }
                        }

                    } else if (c == '*') {
                         //c = buffer.proximoCaractere();
                    	consumirCaractere();
                         System.out.println(c);
                        if(buffer.getTamanhoCodigo()==1){
                             return getToken(TipoToken.OPERADOR_ARITIMETICO_MULTIPLICACAO);
                        }else if(c=='/'){
                            return getToken(TipoToken.DELIMITADOR_COMENTARIO_BLOCO_FIM);
                        }else{
                        return getToken(TipoToken.OPERADOR_ARITIMETICO_MULTIPLICACAO);
                        }
                    } else {
                        estado = -1;
                    }
                    break;
                    
                case 1:
                    //c = buffer.proximoCaractere();
                	consumirCaractere();
                    System.out.println("estado 1: " + c);
                    contador++;
                    //System.out.println(c);
                    if (c == '-'){
                    	return getToken(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
                    }else if(c==' '){
                        estado=1;
                    }else if(this.isDigito(c)){
                        estado =2;
                    }else {
                         return getToken(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO);
                    }
                    break;
                case 2:
                    //c = buffer.proximoCaractere();
                	consumirCaractere();
                    System.out.println("estado 2: " + c);
                    contador++;
                    if(contador==buffer.getTamanhoCodigo()){
                        return getToken(TipoToken.NUMERO); 
                    }else if(this.isDigito(c)){
                        estado = 2;
                    }else if(c=='.'){  
                        estado =3;      
                    }else{
                        return getToken(TipoToken.NUMERO); 
                    }
                    break;
                case 3:
                     //c = buffer.proximoCaractere();
                	consumirCaractere();
                    contador++;
                    System.out.println("estado 3: " + c);
                    if(this.isDigito(c)) {
                        if (contador == buffer.getTamanhoCodigo()) {
                            return getToken(TipoToken.NUMERO);
                        } else{                       
                            estado=3;
                        }
                    }else {
                        return getToken(TipoToken.NUMERO);
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
