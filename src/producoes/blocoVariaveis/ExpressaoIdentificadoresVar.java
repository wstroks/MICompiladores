/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.blocoVariaveis;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class ExpressaoIdentificadoresVar extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new ExpressaoIdentificadoresVar();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (ExpressaoIdentificadorVar.getInstancia().analisar(gerenciadorToken)) {
                return true;
            }

            if(ExpressaoIdentificadoresVarAux.getInstancia().analisar(gerenciadorToken)) {
                return true;
            }
            
            

        }
        
        return false;
        
    }

    @Override
    protected void gerarFirst() {
        // Identificador
        first.add(TipoToken.IDENTIFICADOR);
    }

    @Override
    protected void gerarFollow() {
        //bool, float, identificador, int, string, struct }
        follow.add(TipoToken.PALAVRA_RESERVADA_BOOL);
        follow.add(TipoToken.PALAVRA_RESERVADA_FLOAT);
        follow.add(TipoToken.PALAVRA_RESERVADA_INT);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRING);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        follow.add(TipoToken.IDENTIFICADOR);
        follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);
    }

}
