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
public class ExpressaoAux extends RegraProducao {

	public static RegraProducao getInstancia() {
		return new ExpressaoAux();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if(verificarToken(gerenciadorToken, TipoToken.OPERADOR_LOGICO_OU, false)){
			if (Expressao.getInstancia().analisar(gerenciadorToken)) {
				return true;
			}
		}
        else{
			return verificarSimboloVazio(gerenciadorToken, true);
		}

		return false;
	}

	@Override
	protected void gerarFirst() {
		// { E, ||}
		first.add(TipoToken.OPERADOR_LOGICO_OU);
		first.add(TipoToken.EOF);
	}

	@Override
	protected void gerarFollow() {
		// { ), ‘,’, ;, ]}
		follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
		follow.add(TipoToken.DELIMITADOR_VIRGULA);
		follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
		follow.add(TipoToken.DELIMITADOR_FECHA_COLCHETE);
	}

}
