/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.declaracaoFuncaoProcedimento;

import lexico.TipoToken;
import lexico.Token;
import producoes.RegraProducao;
import semantico.Listas;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class Parametros extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new Parametros();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (Parametro.getInstancia().analisar(gerenciadorToken)) {
                String tipoVariavel = gerenciadorToken.getAnteriorDeterminaToken(2).getLexema();
                Token token = gerenciadorToken.getAnteriorToken();
                gerenciadorToken.ts.bufferFuncao.addParametro(new Listas(token, tipoVariavel));
                if (ParametrosAux.getInstancia().analisar(gerenciadorToken)) {
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
        //{ ) }
        follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
    }

}
