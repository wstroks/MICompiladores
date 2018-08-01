/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.declaracaoFuncaoProcedimento;

import lexico.TipoToken;
import producoes.RegraProducao;
import semantico.FuncaoProcedimento;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class DeclaracaoDeProcedimento extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new DeclaracaoDeProcedimento();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {
            if (verificarToken(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_PROCEDURE, false)) {
            	gerenciadorToken.ts.clearBufferFuncaoProcedimento(FuncaoProcedimento.getTipoProcedimento());
                if (verificarToken(gerenciadorToken, TipoToken.IDENTIFICADOR, true)) {
                	gerenciadorToken.ts.bufferFuncaoProcedimento.setNome(gerenciadorToken.getAnteriorToken().getLexema());
                    if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_PARENTESES, true)) {
                        if (FuncaoProcedimentoFim.getInstancia().analisar(gerenciadorToken)) {
                        	gerenciadorToken.ts.addFuncaoProcedimento();
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        // { PROCEDURE }
        first.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
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
