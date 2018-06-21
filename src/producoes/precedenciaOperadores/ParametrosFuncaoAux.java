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
public class ParametrosFuncaoAux extends RegraProducao {

	public static RegraProducao getInstancia() {
		return new ParametrosFuncaoAux();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if(consumir(gerenciadorToken, TipoToken.DELIMITADOR_VIRGULA)) {
				if(ParametrosFuncao.getInstancia().analisar(gerenciadorToken)) {
					return true;
				}
			} 
			else if (gerenciadorToken.eof()) {
				return true;
			}

		}

		return false;
	}

	@Override
	protected void gerarFirst() {
		// { E, ‘,'}
		first.add(TipoToken.EOF);
		first.add(TipoToken.DELIMITADOR_VIRGULA);
	}

	@Override
	protected void gerarFollow() {
		// { )}
		follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
	}

}
