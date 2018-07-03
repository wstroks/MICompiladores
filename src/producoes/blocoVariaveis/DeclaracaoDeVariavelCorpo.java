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
public class DeclaracaoDeVariavelCorpo extends RegraProducao {

	public static RegraProducao getInstancia() {
		return new DeclaracaoDeVariavelCorpo();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if (DeclaracaoDeVariavelLinha.getInstancia().analisar(gerenciadorToken)) {
				if (DeclaracaoDeVariavelCorpoAux.getInstancia().analisar(gerenciadorToken)) {
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
		// }
		follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);
	}

}
