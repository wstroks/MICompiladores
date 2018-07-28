/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.printScan;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.precedenciaOperadores.Expressao;
import semantico.TabelaSimbolos;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class Saida extends RegraProducao {


	public static RegraProducao getInstancia() {
		return new Saida();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {
			
			if (Expressao.getInstancia().analisar(gerenciadorToken)) {
				return true;
			}

		}

		return false;
	}

	@Override
	protected void gerarFirst() {
		// { -- , !, ( , ++ , CadeiaDeCaracter, Digitos, false, identificador,
		// true}
		first.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
		first.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
		first.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
		first.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
		first.add(TipoToken.CADEIA_CARACTERES);
		first.add(TipoToken.NUMERO);
		first.add(TipoToken.PALAVRA_RESERVADA_FALSE);
		first.add(TipoToken.IDENTIFICADOR);
		first.add(TipoToken.PALAVRA_RESERVADA_TRUE);
	}

	@Override
	protected void gerarFollow() {
		// { ‘,’ , ) }
		follow.add(TipoToken.DELIMITADOR_VIRGULA);
		follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
	}

}
