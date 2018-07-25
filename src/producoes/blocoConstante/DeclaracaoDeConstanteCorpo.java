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
public class DeclaracaoDeConstanteCorpo extends RegraProducao {

	/**
	 * @param ts
	 */
	public DeclaracaoDeConstanteCorpo(TabelaSimbolos ts) {
		super(ts);
	}

	public static RegraProducao getInstancia(TabelaSimbolos ts) {
		return new DeclaracaoDeConstanteCorpo(ts);
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if (DeclaracaoDeConstanteLinha.getInstancia().analisar(gerenciadorToken)) {
				if (DeclaracaoDeConstanteCorpoAux.getInstancia().analisar(gerenciadorToken)) {
					return true;
				}
			}

		}
		
		return false;

	}

	@Override
	protected void gerarFirst() {
		// bool, float, identificador, int, string, struct
		first.add(TipoToken.PALAVRA_RESERVADA_BOOL);
		first.add(TipoToken.PALAVRA_RESERVADA_FLOAT);
		first.add(TipoToken.IDENTIFICADOR);
		first.add(TipoToken.PALAVRA_RESERVADA_INT);
		first.add(TipoToken.PALAVRA_RESERVADA_STRING);
		first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);

	}

	@Override
	protected void gerarFollow() {
		follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);
	}

}
