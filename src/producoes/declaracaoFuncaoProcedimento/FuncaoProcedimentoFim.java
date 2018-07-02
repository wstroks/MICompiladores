/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.declaracaoFuncaoProcedimento;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class FuncaoProcedimentoFim extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new FuncaoProcedimentoFim();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (Parametros.getInstancia().analisar(gerenciadorToken)) {
                if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_PARENTESES)) {
                    if (Bloco.getInstancia().analisar(gerenciadorToken)) {
                        return true;
                    }
                }
            } else if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_PARENTESES)) {
                if (Bloco.getInstancia().analisar(gerenciadorToken)) {
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        // { ), bool, float, identificador,int, string, struct }
        first.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
        first.add(TipoToken.PALAVRA_RESERVADA_BOOL);
        first.add(TipoToken.PALAVRA_RESERVADA_FLOAT);
        first.add(TipoToken.PALAVRA_RESERVADA_INT);
        first.add(TipoToken.PALAVRA_RESERVADA_STRING);
        first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        first.add(TipoToken.IDENTIFICADOR);

    }

    @Override
    protected void gerarFollow() {
        // { const, function, procedure, start, struct, typedef, var, $ }
        follow.add(TipoToken.PALAVRA_RESERVADA_CONST);
        follow.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
        follow.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
        follow.add(TipoToken.PALAVRA_RESERVADA_START);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        follow.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        follow.add(TipoToken.PALAVRA_RESERVADA_VAR);
        follow.add(TipoToken.EOF);
    }

}
