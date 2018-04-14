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

        int estado = 0;
        int contador = 1;
        int contadorIncremento=0;
        
        char c = buffer.proximoCaractere();
        while (!buffer.fimCodigo()) {
            switch (estado) {
                case 0:
                    System.out.println("estado 0: " + c);
                    if (c == '-'){ 
                            if(buffer.getTamanhoCodigo()==1){
                                 return new Token(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                            }else if(contadorIncremento==1){
                                 return new Token(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                            }
                        contador++;
                        contadorIncremento++;
                        c = buffer.proximoCaractere();
                       
                        estado = 0;
                        
                        
                    

                    }else  if (c == ' ') {
                         contador++;
                        c = buffer.proximoCaractere();
                        estado = 0;
                        
                    }else if (this.isDigito(c)) {
                        if (buffer.getTamanhoCodigo() == 1) {
                            return new Token(TipoToken.NUMERO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                        } else if (contador == buffer.getTamanhoCodigo()) {
                            return new Token(TipoToken.NUMERO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());

                        } else if (contador < buffer.getTamanhoCodigo()) {
                            //System.out.println("aa:" + contador);
                            contador++;
                            c = buffer.proximoCaractere();
                            if (c == '.') {
                                //System.out.println("estado 0: " + c);

                                estado = 1;
                            } else {
                                estado = 0;
                            }
                        }

                    } else {
                        if(contadorIncremento==1){
                                 return new Token(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                            }
                        estado = -1;
                    }
                    break;
                case 1:
                    System.out.println("estado 1: " + c);
                    if (c == '.') {

                        c = buffer.proximoCaractere();
                        estado = 2;
                        contador++;

                    
                    }break;
                case 2:
                    System.out.println("estado 2: " + c);
                    if (this.isDigito(c)) {
                        if (contador == buffer.getTamanhoCodigo()) {
                            return new Token(TipoToken.NUMERO, "", buffer.getLinhaAtual(), buffer.getPosicaoAtual());

                        } else{
                            
                            
                            c = buffer.proximoCaractere();
                            contador++;
                            
                            estado=2;
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
