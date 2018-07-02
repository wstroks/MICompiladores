/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.blocoStruct;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class ExpressaoIdentificadoresStructAux extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new ExpressaoIdentificadoresStructAux();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_VIRGULA)) {

                if (ExpressaoIdentificadoresStruct.getInstancia().analisar(gerenciadorToken)) {
                    return true;
                }

            } else if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_PONTO_VIRGULA)) {
                return true;
            }

        }

        return false;

    }

    @Override
    protected void gerarFirst() {
        // ‘,’ , ;
        first.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
        first.add(TipoToken.DELIMITADOR_VIRGULA);
    }

    @Override
    protected void gerarFollow() {
        // bool, float, identificador, int, string, struct }
        follow.add(TipoToken.IDENTIFICADOR);
        follow.add(TipoToken.PALAVRA_RESERVADA_BOOL);
        follow.add(TipoToken.PALAVRA_RESERVADA_FLOAT);
        follow.add(TipoToken.PALAVRA_RESERVADA_INT);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRING);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);
    }

}
