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
    	
        lexema = "";
        int estado = 0;
        int contador = 0;
        int ponto = 0;

        while (!buffer.fimCodigo()) {
            char c = buffer.lookAhead();
            switch (estado) {
                case 0:

                    if (c == '+') {
                        consumirCaractere();
                        if (buffer.fimCodigo() == true) {
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_ADICAO);
                        } else if (buffer.getTamanhoCodigo() == 1) {
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_ADICAO);
                        } else {
                            estado = 1;
                        }

                    } else if (c == '-') {
                        consumirCaractere();
                        if (buffer.fimCodigo() == true) {
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO);
                        } else if (buffer.getTamanhoCodigo() == 1) {
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO);
                        } else {
                            estado = 2;
                        }

                    } else if (c == '*') {
                        consumirCaractere();
                        if (buffer.fimCodigo() == true) {
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_MULTIPLICACAO);
                        } else if (buffer.getTamanhoCodigo() == 1) {
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_MULTIPLICACAO);
                        } else {

                            return getToken(TipoToken.OPERADOR_ARITIMETICO_MULTIPLICACAO);
                        }

                    } else if (c == '/') {
                        consumirCaractere();
                        if (buffer.fimCodigo() == true) {
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_DIVISAO);
                        } else if (buffer.getTamanhoCodigo() == 1) {
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_DIVISAO);
                        } else {

                            return getToken(TipoToken.OPERADOR_ARITIMETICO_DIVISAO);
                        }
                    } else {
                        estado = -1;
                    }
                    break;

                case 1:
                    consumirCaractere();
                    if (c == '+') {
                        return getToken(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
                    } else {
                        buffer.setPosicaoAtual(buffer.getPosicaoAtual() - 1);
                        return new Token(TipoToken.OPERADOR_ARITIMETICO_ADICAO, "+", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                    }

                case 2:
                    consumirCaractere();
                    if (c == '-') {
                        return getToken(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
                    } else {
                        buffer.setPosicaoAtual(buffer.getPosicaoAtual() - 1);
                        return new Token(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO, "-", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                    }

                default:
                    return getToken(TipoToken.INDEFINIDO);
            }

        }

        return getToken(TipoToken.INDEFINIDO);

    }

}
