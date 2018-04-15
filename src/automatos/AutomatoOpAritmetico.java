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
public class AutomatoOpAritmetico extends Automato {

    public AutomatoOpAritmetico(Buffer buffer) {
        super(buffer);
    }

    @SuppressWarnings("static-access")
    @Override
    public Token executar() {

        int estado = 0;
        int contador=0;
        int ponto=0;
        
        char c = buffer.proximoCaractere();
        while (!buffer.fimCodigo()) {
            switch (estado) {
                case 0:
                    System.out.println("estado 0: " + c);
                    if (c == '+') {

                        if (buffer.getTamanhoCodigo() == 1) {
                            return new Token(TipoToken.OPERADOR_ARITIMETICO_ADICAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());

                        } else {
                            c = buffer.proximoCaractere();
                            System.out.println(c);
                            if (c == '+') {
                                return new Token(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                            } else {
                                return new Token(TipoToken.OPERADOR_ARITIMETICO_ADICAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                            }
                        }

                    } else if (c == '-') {
                        if (buffer.getTamanhoCodigo() == 1) {
                            return new Token(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                        } else {
                            estado =1;
                            //subtracao=1;
                        }

                    } else if (c == '/') {

                        if (buffer.getTamanhoCodigo() == 1) {
                            return new Token(TipoToken.OPERADOR_ARITIMETICO_DIVISAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());

                        } else {
                            c = buffer.proximoCaractere();
                            System.out.println(c);
                            if (c == '/') {
                                return new Token(TipoToken.DELIMITADOR_COMENTARIO_LINHA, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                            } else if(c=='*'){
                                return new Token(TipoToken.DELIMITADOR_COMENTARIO_BLOCO_INICIO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                            }else {
                                return new Token(TipoToken.OPERADOR_ARITIMETICO_DIVISAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                            }
                        }

                    } else if (c == '*') {
                         c = buffer.proximoCaractere();
                         System.out.println(c);
                        if(buffer.getTamanhoCodigo()==1){
                             return new Token(TipoToken.OPERADOR_ARITIMETICO_MULTIPLICACAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                        }else if(c=='/'){
                            return new Token(TipoToken.DELIMITADOR_COMENTARIO_BLOCO_FIM, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                        }else{
                        return new Token(TipoToken.OPERADOR_ARITIMETICO_MULTIPLICACAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                        }
                    } else {
                        estado = -1;
                    }
                    break;
                    
                case 1:
                    c = buffer.proximoCaractere();
                    System.out.println("estado 1: " + c);
                    contador++;
                    //System.out.println(c);
                    if (c == '-'){
                                return new Token(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                    }else if(c==' '){
                        estado=1;
                    }else if(this.isDigito(c)){
                        estado =2;
                    }else {
                         return new Token(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                    }
                    break;
                case 2:
                    c = buffer.proximoCaractere();
                    System.out.println("estado 2: " + c);
                    contador++;
                    if(contador==buffer.getTamanhoCodigo()){
                        return new Token(TipoToken.NUMERO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual()); 
                    }else if(this.isDigito(c)){
                        estado = 2;
                    }else if(c=='.'){
                        
                        estado =3;
                        
                    }else{
                        return new Token(TipoToken.NUMERO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual()); 
                    }
                    break;
                case 3:
                     c = buffer.proximoCaractere();
                     contador++;
                    System.out.println("estado 3: " + c);
                    if(this.isDigito(c)) {
                        if (contador == buffer.getTamanhoCodigo()) {
                            return new Token(TipoToken.NUMERO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());

                        } else{
                                              
                           
                            
                            estado=3;
                        }
                    }else {
                        return new Token(TipoToken.NUMERO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
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
