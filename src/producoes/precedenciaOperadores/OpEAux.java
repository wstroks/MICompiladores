/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.precedenciaOperadores;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class OpEAux extends RegraProducao {

	public static RegraProducao getInstancia() {
		return new OpEAux();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if (gerenciadorToken.eof()) {
				return true;
			}
			else if(consumir(gerenciadorToken, TipoToken.OPERADOR_LOGICO_E)){
				if(OpE.getInstancia().analisar(gerenciadorToken)) {
					return true;
				}
			}
			
		}

		return false;
	}

	@Override
	protected void gerarFirst() {
		// { &&, E}
		first.add(TipoToken.OPERADOR_LOGICO_E);
		first.add(TipoToken.EOF);
	}

	@Override
	protected void gerarFollow() {
		// { ), ‘,’, ;, ], ||}
		follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
		follow.add(TipoToken.DELIMITADOR_VIRGULA);
		follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
		follow.add(TipoToken.DELIMITADOR_FECHA_COLCHETE);
		follow.add(TipoToken.OPERADOR_LOGICO_OU);
	}

}
