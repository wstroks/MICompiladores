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
public class EscalarRelacional extends RegraProducao {

	public static RegraProducao getInstancia() {
		return new EscalarRelacional();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		// TODO Auto-generated method stub
		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if (!consumir(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_DIFERENTE)) {
				return false;
			} else if (!consumir(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_IGUAL)) {
				return false;
			} else if (!consumir(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_MENOR_QUE)) {
				return false;
			} else if (!consumir(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_MENOR_IGUAL_QUE)) {
				return false;
			} else if (!consumir(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_MAIOR_IGUAL_QUE)) {
				return false;
			} else if (!consumir(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_MAIOR_QUE)) {
				return false;
			}
			
			return true;

		}

		return false;
	}

	@Override
	protected void gerarFirst() {

		// { != , < , <= , == , >=, >}
		first.add(TipoToken.OPERADOR_RELACIONAL_DIFERENTE);
		first.add(TipoToken.OPERADOR_RELACIONAL_MAIOR_IGUAL_QUE);
		first.add(TipoToken.OPERADOR_RELACIONAL_MAIOR_QUE);
		first.add(TipoToken.OPERADOR_RELACIONAL_MENOR_IGUAL_QUE);
		first.add(TipoToken.OPERADOR_RELACIONAL_MENOR_QUE);
		first.add(TipoToken.OPERADOR_RELACIONAL_IGUAL);

	}

	@Override
	protected void gerarFollow() {

		// { --, ! , (, ++ , CadeiaDeCaracteres, Digitos, false, identificador,
		// true}
		follow.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
		follow.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
		follow.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
		follow.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
		follow.add(TipoToken.CADEIA_CARACTERES);
		follow.add(TipoToken.NUMERO);
		follow.add(TipoToken.PALAVRA_RESERVADA_FALSE);
		follow.add(TipoToken.PALAVRA_RESERVADA_TRUE);
		follow.add(TipoToken.IDENTIFICADOR);

	}

}
