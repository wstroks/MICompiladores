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

        lexema = "";
        int estado = 0;
        int contador = 0;
        boolean afirma = false;
        boolean primeiroPonto = false;
        
        while (!buffer.fimCodigo()) {
            char c = buffer.lookAhead();
            switch (estado) {
                case 0:
                    if (c == '-') {
                        consumirCaractere();
                        if (buffer.fimCodigo() == true) {
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO);
                        }
                        if (buffer.getTamanhoCodigo() == 1) {
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO);
                        }
                        estado = 1;
                    } else if (Automato.isDigito(c)) {
                        consumirCaractere();
                        if (buffer.fimCodigo() == true) {
                            return getToken(TipoToken.NUMERO);
                        }
                        if (buffer.getTamanhoCodigo() == 1) {
                            return getToken(TipoToken.NUMERO);

                        }
                        estado = 3;
                    } else {
                        estado = -1;
                    }
                    break;
                case 1:
                    consumirCaractere();
                    if (c == ' ') {
                        contador++;
                        estado = 2;
                    } else if (c == '-') {
                        return getToken(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
                    } else if (Automato.isDigito(c)) {
                        estado = 3;
                    } else {
                        buffer.goBack();
                        goBackLexema();
                        return new Token(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO, "-", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                    }
                    break;

                case 2:
                    consumirCaractere();
                    if (buffer.fimCodigo()) {
                        return getToken(TipoToken.NUMERO);
                    } else if (Automato.isDigito(c)) {
                        estado = 3;
                    } else if (c == ' ') {
                        contador++;
                        estado = 2;
                    } else {
                        buffer.goBack();
                        goBackLexema();
                        return new Token(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO, "-", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                    }
                    break;

                case 3:
                    consumirCaractere();
                    if (buffer.fimCodigo()) {
                        if (!Automato.isDigito(c)) {
                            buffer.goBack();
                            goBackLexema();
                            return getToken(TipoToken.NUMERO);
                        }
                        return getToken(TipoToken.NUMERO);
                    } else if (Automato.isDigito(c)) {
                        estado = 3;
                    } else if (c == '.') {
                        primeiroPonto = true;
                        estado = 4;
                    } else {
                        buffer.goBack();
                        goBackLexema();
                        return getToken(TipoToken.NUMERO);
                    }
                    break;

                case 4:
                    consumirCaractere();
                    if (buffer.fimCodigo() == true) {
                       System.out.println("estado 56: " + c);
                       if(c=='.' && primeiroPonto==true && afirma==false){
                            buffer.goBack();
                            goBackLexema();
                            buffer.goBack();
                            goBackLexema();
                            return getToken(TipoToken.NUMERO);
                           
                       }else if(c=='.' && primeiroPonto==true && afirma==true){
                            buffer.goBack();
                            goBackLexema();
                            
                            return getToken(TipoToken.NUMERO);
                           
                       }else if (!Automato.isDigito(c)) {
                            buffer.goBack();
                            goBackLexema();
                            return getToken(TipoToken.NUMERO);
                        }
                        return getToken(TipoToken.NUMERO);
                    } else if (c == '.') {
                        if (primeiroPonto == true && afirma==false) {
                            buffer.goBack();
                            goBackLexema();
                            buffer.goBack();
                            goBackLexema();
                            return getToken(TipoToken.NUMERO);
                        }
                        buffer.goBack();
                        goBackLexema();
                        return getToken(TipoToken.NUMERO);
                    } else if (Automato.isDigito(c)) {
                        estado = 4;
                        afirma = true;
                    } else {
                        if (afirma == true) {
                            buffer.goBack();
                            goBackLexema();
                            return getToken(TipoToken.NUMERO);
                        }
                        buffer.goBack();
                        goBackLexema();
                        buffer.goBack();
                        goBackLexema();
                        return getToken(TipoToken.NUMERO);
                    }
                    break;

                default:
                    return getToken(TipoToken.NUMERO);
            }

        }

        return getToken(TipoToken.INDEFINIDO);

    }

}
