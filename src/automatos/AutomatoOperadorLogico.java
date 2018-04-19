/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatos;

import lexico.Buffer;
import lexico.Lexico;
import lexico.TipoToken;
import lexico.Token;

/**
 *
 * @author wstro
 */
public class AutomatoOperadorLogico extends Automato {

    private Token token = null;

    public AutomatoOperadorLogico(Buffer buffer) {
        super(buffer);
    }

    @SuppressWarnings("static-access")
    @Override
    public Token executar(){
          System.out.println("Automato OperadorLogico");

        lexema = "";
        int estado = 0;
        int verifica = 0;

        while (!buffer.fimCodigo()) {
            char c = buffer.lookAhead();
            switch (estado) {
                case 0:
                   //System.out.println("estado 0: " + c);
                    if (c == '!') {
                        consumirCaractere();
                        if(buffer.fimCodigo()==true){
                            return getToken(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
                        }
                        
                        
                        
                        if (buffer.getTamanhoCodigo() == 1) {
                            return getToken(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
                        }
                        
                        
                        //c = buffer.proximoCaractere();
                        estado = 3;
                    } else if (c == '&') {
                        consumirCaractere();
                        if(buffer.fimCodigo()==true){
                            return getToken(TipoToken.INDEFINIDO_E);
                        }
                        
                        else if(buffer.getTamanhoCodigo() == 1) {
                            return getToken(TipoToken.INDEFINIDO_E);
                        }
                        estado = 2;

                    } else if (c == '|') {
                        consumirCaractere();
                        if(buffer.fimCodigo()==true){
                            return getToken(TipoToken.INDEFINIDO_OU);
                        }
                       else if(buffer.getTamanhoCodigo() == 1) {
                            return getToken(TipoToken.INDEFINIDO_OU);
                        }
                        //c = buffer.proximoCaractere();
                        verifica = 2;
                        estado = 1;
                    } else {
                        estado = -1;
                    }
                    break;
                case 1:
                    consumirCaractere();
                    //System.out.println("estado 1: " + c);
                    if (c == '|') {
                        return getToken(TipoToken.OPERADOR_LOGICO_OU);
                    } else {
                        buffer.setPosicaoAtual(buffer.getPosicaoAtual() - 1);
                        return new Token(TipoToken.INDEFINIDO_OU, "|", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                    }
                case 2:
                    
                    consumirCaractere();
                    //System.out.println("estado 1: " + c);
                    if (c == '&') {
                        return getToken(TipoToken.OPERADOR_LOGICO_E);
                    } else {
                        buffer.setPosicaoAtual(buffer.getPosicaoAtual() - 1);
                        return new Token(TipoToken.INDEFINIDO_E, "&", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                    }
                case 3:
                    //System.out.println("estado 3: " + c);
                    consumirCaractere();
                    //System.out.println("asd "+c);
                    if (c == '=') {
                        return getToken(TipoToken.OPERADOR_RELACIONAL_DIFERENTE);
                    } else {
                        int x = buffer.getPosicaoAtual();
                        //System.out.println("naurto "+x);
                        buffer.setPosicaoAtual(x - 1);
                        // return Token.class.
                        return new Token(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO, "!", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                        //return getToken(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
                    }
                default:
                    //System.out.println("estado default: " + c);
                    //System.out.println("asd "+c);
                    return getToken(TipoToken.INDEFINIDO);
            }
        }

        return getToken(TipoToken.INDEFINIDO);

    }

}
