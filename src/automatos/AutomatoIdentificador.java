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
public class AutomatoIdentificador extends Automato {

    public AutomatoIdentificador(Buffer buffer) {
        super(buffer);
    }

    @SuppressWarnings("static-access")
    @Override
    public Token executar() {

        lexema = "";
        int estado = 0;
        String soma = "";

        while (!buffer.fimCodigo()) {
            char c = buffer.lookAhead();
            switch (estado) {
                case 0:
                    if (Automato.isLetra(c)) {
                        estado = 1;
                        consumirCaractere();
                        if (buffer.fimCodigo() == true) {
                            return getToken(getTipoToken());
                        }
                        soma += c;
                    } else {
                        estado = -1;
                    }
                    break;
                case 1:
                    if (buffer.isUltimoCaractere()) {
                        consumirCaractere();
                        soma += c;
                        if (c != '_' && !Automato.isDigito(c) && !Automato.isLetra(c)) {
                            buffer.setPosicaoAtual(buffer.getPosicaoAtual() - 1);
                            soma = soma.substring(0, soma.length() - 1);
                            lexema = soma;
                            return new Token(getTipoToken(), soma, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
                        } else {

                            return getToken(getTipoToken());
                        }

                    } else {
                        if (Automato.isLetra(c)) {
                            consumirCaractere();
                            soma += c;
                            estado = 1;
                        } else if (Automato.isDigito(c)) {
                            consumirCaractere();
                            soma += c;
                            estado = 1;
                        } else if (c == '_') {
                            consumirCaractere();
                            soma += c;
                            estado = 1;
                        } else {
                            return getToken(getTipoToken());
                        }
                    }
                    break;

                default:
                    consumirCaractere();
                    return getToken(getTipoToken());
            }

        }

        return getToken(TipoToken.INDEFINIDO);

    }

    private TipoToken getTipoToken() {
        switch (lexema) {
            case "const":
                return TipoToken.PALAVRA_RESERVADA_CONST;
            case "var":
                return TipoToken.PALAVRA_RESERVADA_VAR;
            case "struct":
                return TipoToken.PALAVRA_RESERVADA_STRUCT;
            case "procedure":
                return TipoToken.PALAVRA_RESERVADA_PROCEDURE;
            case "function":
                return TipoToken.PALAVRA_RESERVADA_FUNCTION;
            case "return":
                return TipoToken.PALAVRA_RESERVADA_RETURN;
            case "start":
                return TipoToken.PALAVRA_RESERVADA_START;
            case "if":
                return TipoToken.PALAVRA_RESERVADA_IF;
            case "then":
                return TipoToken.PALAVRA_RESERVADA_THEN;
            case "else":
                return TipoToken.PALAVRA_RESERVADA_ELSE;
            case "scan":
                return TipoToken.PALAVRA_RESERVADA_SCAN;
            case "print":
                return TipoToken.PALAVRA_RESERVADA_PRINT;
            case "int":
                return TipoToken.PALAVRA_RESERVADA_INT;
            case "float":
                return TipoToken.PALAVRA_RESERVADA_FLOAT;
            case "bool":
                return TipoToken.PALAVRA_RESERVADA_BOOL;
            case "string":
                return TipoToken.PALAVRA_RESERVADA_STRING;
            case "true":
                return TipoToken.PALAVRA_RESERVADA_TRUE;
            case "false":
                return TipoToken.PALAVRA_RESERVADA_FALSE;
            case "extends":
                return TipoToken.PALAVRA_RESERVADA_EXTENDS;
            case "while":
                return TipoToken.PALAVRA_RESERVADA_WHILE;
            case "typedef":
                return TipoToken.PALAVRA_RESERVADA_TYPEDEF;
            default:
                return TipoToken.IDENTIFICADOR;
        }
    }

}
