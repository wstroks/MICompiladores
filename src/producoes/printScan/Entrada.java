/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.printScan;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.precedenciaOperadores.Final;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class Entrada extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new Entrada();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {
            if (gerenciadorToken.getProximoToken().getTipoToken() == TipoToken.DELIMITADOR_PONTO
                    || gerenciadorToken.getProximoToken().getTipoToken() == TipoToken.DELIMITADOR_ABRE_COLCHETE) {
                if (Final.getInstancia().analisar(gerenciadorToken)) {
                    return true;
                }
            } else if (verificarToken(gerenciadorToken, TipoToken.IDENTIFICADOR, false)) {
                return true;
            }

        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        // { Identificador }
        first.add(TipoToken.IDENTIFICADOR);
    }

    @Override
    protected void gerarFollow() {
        // { ‘,’, ) }
        follow.add(TipoToken.DELIMITADOR_VIRGULA);
        follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
    }

}
