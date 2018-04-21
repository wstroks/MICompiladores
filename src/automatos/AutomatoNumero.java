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

        //System.out.println("Automato Numero");
        lexema = "";
        int estado = 0;
        int contador = 0;
        boolean afirma = false;
        boolean primeiroPonto = false;
        while (!buffer.fimCodigo()) {
            char c = buffer.lookAhead();
            switch (estado) {
                case 0:
                    //System.out.println("estado 0: " + c);
                    if (c == '-') {
                        consumirCaractere();
                        //soma += c;
                        if (buffer.fimCodigo() == true) {
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO);
                        }
                        if (buffer.getTamanhoCodigo() == 1) {
                            return getToken(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO);
                        }
                        estado = 1;
                    } else if (this.isDigito(c)) {
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
                    //System.out.println("estado 1: " + c);
                    consumirCaractere();
                    if (c == ' ') {
                        contador++;
                        estado = 2;
                    } else if (c == '-') {
                        return getToken(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
                    } else if (this.isDigito(c)) {
                        estado = 3;
                    } else {
                        buffer.goBack();
                        goBackLexema();
                        return new Token(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO, "-", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                    }
                    break;

                case 2:
                    //System.out.println("estado 2: " + c);
                    consumirCaractere();
                    if (buffer.fimCodigo()) {
                        return getToken(TipoToken.NUMERO);
                    } else if (this.isDigito(c)) {
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
                    //System.out.println("estado 3: " + c);
                    //System.out.println("estado 3: " + c);
                    consumirCaractere();
                    if (buffer.fimCodigo()) {
                        if (!this.isDigito(c)) {
                            buffer.goBack();
                            goBackLexema();
                            return getToken(TipoToken.NUMERO);
                        }
                        return getToken(TipoToken.NUMERO);
                    } else if (this.isDigito(c)) {
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
                    //System.out.println("estado 4: " + c);
                    consumirCaractere();
                    if (buffer.fimCodigo() == true) {
                       // System.out.println("estado 56: " + c);
                        if (!this.isDigito(c)) {
                            buffer.goBack();
                            goBackLexema();
                            return getToken(TipoToken.NUMERO);
                        }
                        return getToken(TipoToken.NUMERO);
                    } else if (c == '.') {
                        // System.out.println("tt "+c);
                        //System.out.println("estado 4ss: " + c);

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
                    } else if (this.isDigito(c)) {
                        estado = 4;
                        afirma = true;
                    } else {
                        //System.out.println("estado 4ds: " + c);
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
                    // System.out.println("estado default: " + c);
                    return getToken(TipoToken.NUMERO);
            }

        }

        return getToken(TipoToken.INDEFINIDO);

    }

}
