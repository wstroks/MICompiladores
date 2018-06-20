/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.declaracaoTipo;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.tipos.Tipo;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class DeclaracaoDeTypedefAux extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new DeclaracaoDeTypedefAux();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {
        // TODO Auto-generated method stub
        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (!Tipo.getInstancia().analisar(gerenciadorToken)) {
                return false;
            } else if (!consumir(gerenciadorToken, TipoToken.IDENTIFICADOR)) {
                return false;
            } else if (!consumir(gerenciadorToken, TipoToken.DELIMITADOR_PONTO_VIRGULA)) {
                return false;
            }

            return true;

        }
        return false;
    }

    @Override
    protected void gerarFirst() {
        // TODO Auto-generated method stub
        // TYPEDEF
        first.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
    }

    @Override
    protected void gerarFollow() {
        // TODO Auto-generated method stub
        /*
        --, !, ( , ++, CadeiaDeCaracteres, Digitos, false, identificador, print, return , scan, struct,
        true, typdef, var, while, }, const, function, procedure, start, $
         */
        follow.add(TipoToken.EOF);
        follow.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
        follow.add(TipoToken.PALAVRA_RESERVADA_START);
        follow.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
        follow.add(TipoToken.PALAVRA_RESERVADA_CONST);
        follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);
        follow.add(TipoToken.PALAVRA_RESERVADA_WHILE);
        follow.add(TipoToken.PALAVRA_RESERVADA_VAR);
        follow.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        follow.add(TipoToken.PALAVRA_RESERVADA_TRUE);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        follow.add(TipoToken.PALAVRA_RESERVADA_SCAN);
        follow.add(TipoToken.PALAVRA_RESERVADA_RETURN);
        follow.add(TipoToken.PALAVRA_RESERVADA_PRINT);
        follow.add(TipoToken.IDENTIFICADOR);
        follow.add(TipoToken.NUMERO);
        follow.add(TipoToken.CADEIA_CARACTERES);
        follow.add(TipoToken.PALAVRA_RESERVADA_FALSE);
        follow.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
        follow.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        follow.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        follow.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);

    }

}