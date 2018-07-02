/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.instrucoes;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class InstrucaoDeRetorno extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new InstrucaoDeRetorno();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {
            if (verificarToken(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_RETURN)) {
                if (InstrucaoDeRetornoAux.getInstancia().analisar(gerenciadorToken)) {
                    return true;
                }
            }
        }

        return false;

    }

    @Override
    protected void gerarFirst() {
        // RETURN
        first.add(TipoToken.PALAVRA_RESERVADA_RETURN);
    }

    @Override
    protected void gerarFollow() {
        // ;
        follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
    }

}
