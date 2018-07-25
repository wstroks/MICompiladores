/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.blocoConstante;

import lexico.TipoToken;
import producoes.RegraProducao;
import semantico.TabelaSimbolos;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class DeclaracaoDeConstanteCorpoAux extends RegraProducao {

    /**
	 * @param ts
	 */
	public DeclaracaoDeConstanteCorpoAux(TabelaSimbolos ts) {
		super(ts);
	}

	public static RegraProducao getInstancia(TabelaSimbolos ts) {
        return new DeclaracaoDeConstanteCorpoAux(ts);
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (DeclaracaoDeConstanteCorpo.getInstancia().analisar(gerenciadorToken)) {
            return true;
        }
        else{
			return verificarSimboloVazio(gerenciadorToken, true);
		} 

    }

    @Override
    protected void gerarFirst() {
        // bool, float, identificador, int, string, struct, E
        first.add(TipoToken.PALAVRA_RESERVADA_BOOL);
        first.add(TipoToken.PALAVRA_RESERVADA_FLOAT);
        first.add(TipoToken.PALAVRA_RESERVADA_INT);
        first.add(TipoToken.PALAVRA_RESERVADA_STRING);
        first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        first.add(TipoToken.IDENTIFICADOR);
        first.add(TipoToken.EOF);

    }

    @Override
    protected void gerarFollow() {
        // }
        follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);

    }

}
