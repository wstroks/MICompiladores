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
public class DeclaracaoDeStructCorpo extends RegraProducao {

	public static RegraProducao getInstancia() {
		return new DeclaracaoDeStructCorpo();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if (DeclaracaoDeStructLinha.getInstancia().analisar(gerenciadorToken)) {
				if (DeclaracaoDeStructCorpoAux.getInstancia().analisar(gerenciadorToken)) {
					return true;
				}
			} 

		}
		return false;
	}

	@Override
	protected void gerarFirst() {
		// bool, float, identificador, int, string, struct
		first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
		first.add(TipoToken.PALAVRA_RESERVADA_BOOL);
		first.add(TipoToken.PALAVRA_RESERVADA_FLOAT);
		first.add(TipoToken.PALAVRA_RESERVADA_INT);
		first.add(TipoToken.PALAVRA_RESERVADA_STRING);
		first.add(TipoToken.IDENTIFICADOR);
	}

	@Override
	protected void gerarFollow() {
		// }
		follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);
	}

}
