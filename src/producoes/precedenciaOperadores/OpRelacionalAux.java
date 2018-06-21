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
public class OpRelacionalAux extends RegraProducao {

	public static RegraProducao getInstancia() {
		return new OpRelacionalAux();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if(EscalarRelacional.getInstancia().analisar(gerenciadorToken)){
				if(OpRelacional.getInstancia().analisar(gerenciadorToken)){
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
		// { E, != , < , <=, == , > , >=}
		first.add(TipoToken.EOF);
		first.add(TipoToken.OPERADOR_RELACIONAL_DIFERENTE);
		first.add(TipoToken.OPERADOR_RELACIONAL_MAIOR_QUE);
		first.add(TipoToken.OPERADOR_RELACIONAL_MENOR_QUE);
		first.add(TipoToken.OPERADOR_RELACIONAL_MAIOR_IGUAL_QUE);
		first.add(TipoToken.OPERADOR_RELACIONAL_MENOR_IGUAL_QUE);
		first.add(TipoToken.OPERADOR_RELACIONAL_IGUAL);
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
