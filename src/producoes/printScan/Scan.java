/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.printScan;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class Scan extends RegraProducao {

	public static RegraProducao getInstancia() {
		return new Scan();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if (!consumir(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_SCAN)) {
				return false;
			}

			if (!consumir(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_PARENTESES)) {
				return false;
			}

			if (!Entrada.getInstancia().analisar(gerenciadorToken)) {
				return false;
			}

			if (!OutrasEntradas.getInstancia().analisar(gerenciadorToken)) {
				return false;
			}

			if (!consumir(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_PARENTESES)) {
				return false;
			}

			return true;

		}

		return false;
	}

	@Override
	protected void gerarFirst() {
		// { scan}
		first.add(TipoToken.PALAVRA_RESERVADA_SCAN);
	}

	@Override
	protected void gerarFollow() {
		// { ; }
		follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
	}

}
