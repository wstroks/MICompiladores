/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.declaracaoFuncaoProcedimento;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.tipos.Tipo;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class Parametro extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new Parametro();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {
            if (Tipo.getInstancia().analisar(gerenciadorToken)) {
                if (verificarToken(gerenciadorToken, TipoToken.IDENTIFICADOR, true)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        //{ bool, float, identificador, int, string,struct}
        first.add(TipoToken.PALAVRA_RESERVADA_BOOL);
        first.add(TipoToken.PALAVRA_RESERVADA_FLOAT);
        first.add(TipoToken.PALAVRA_RESERVADA_INT);
        first.add(TipoToken.PALAVRA_RESERVADA_STRING);
        first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        first.add(TipoToken.IDENTIFICADOR);
    }

    @Override
    protected void gerarFollow() {
        //{ ‘,’, ) }
        follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
        follow.add(TipoToken.DELIMITADOR_VIRGULA);
    }

}
