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
public class OpUnary extends RegraProducao {

	public static RegraProducao getInstancia() {
		return new OpUnary();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if(verificarToken(gerenciadorToken, TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO, false)){
				if (OpUnary.getInstancia().analisar(gerenciadorToken)) {
					return true;
				}
			}
			else if(verificarToken(gerenciadorToken, TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO, false)){
				if (OpUnary.getInstancia().analisar(gerenciadorToken)) {
					return true;
				}
			}
			else if(verificarToken(gerenciadorToken, TipoToken.OPERADOR_ARITIMETICO_DECREMENTO, false)){
				if (OpUnary.getInstancia().analisar(gerenciadorToken)) {
					return true;
				}
			}	
			else if(gerenciadorToken.getTokenAtual().getTipoToken() == TipoToken.IDENTIFICADOR || gerenciadorToken.getTokenAtual().getTipoToken() == TipoToken.CADEIA_CARACTERES || gerenciadorToken.getTokenAtual().getTipoToken() == TipoToken.NUMERO||gerenciadorToken.getTokenAtual().getTipoToken() == TipoToken.DELIMITADOR_ABRE_PARENTESES  || gerenciadorToken.getTokenAtual().getTipoToken() == TipoToken.PALAVRA_RESERVADA_FALSE || gerenciadorToken.getTokenAtual().getTipoToken() == TipoToken.PALAVRA_RESERVADA_TRUE ){
				if(gerenciadorToken.getProximoToken().getTipoToken() == TipoToken.DELIMITADOR_PONTO || gerenciadorToken.getProximoToken().getTipoToken() == TipoToken.DELIMITADOR_ABRE_COLCHETE){
					if (Final.getInstancia().analisar(gerenciadorToken)) {
						if (SimboloUnario.getInstancia().analisar(gerenciadorToken)) {
							return true;
						}
					}
				}
				else{
					if (Valor.getInstancia().analisar(gerenciadorToken)) {
						if (SimboloUnario.getInstancia().analisar(gerenciadorToken)) {
							return true;
						}
					}
				}
			}

		}

		return false;

	}

	@Override
	protected void gerarFirst() {
		// { --, ! , (, ++ , CadeiadeCaracter, digitos, false, identificador,
		// true}
		first.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
		first.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
		first.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
		first.add(TipoToken.CADEIA_CARACTERES);
		first.add(TipoToken.IDENTIFICADOR);
		first.add(TipoToken.PALAVRA_RESERVADA_FALSE);
		first.add(TipoToken.PALAVRA_RESERVADA_TRUE);
		first.add(TipoToken.NUMERO);
                first.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
	}

	@Override
	protected void gerarFollow() {
		// { *,/- , + != , < , <=, == , > , >= , &&, || , ), ‘,’, ;, ]}
		follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
		follow.add(TipoToken.DELIMITADOR_VIRGULA);
		follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
		follow.add(TipoToken.DELIMITADOR_FECHA_COLCHETE);
		follow.add(TipoToken.OPERADOR_LOGICO_OU);
		follow.add(TipoToken.OPERADOR_LOGICO_E);
		follow.add(TipoToken.OPERADOR_RELACIONAL_DIFERENTE);
		follow.add(TipoToken.OPERADOR_RELACIONAL_IGUAL);
		follow.add(TipoToken.OPERADOR_RELACIONAL_MAIOR_IGUAL_QUE);
		follow.add(TipoToken.OPERADOR_RELACIONAL_MAIOR_QUE);
		follow.add(TipoToken.OPERADOR_RELACIONAL_MENOR_IGUAL_QUE);
		follow.add(TipoToken.OPERADOR_RELACIONAL_MENOR_QUE);
		follow.add(TipoToken.OPERADOR_ARITIMETICO_ADICAO);
		follow.add(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO);
		follow.add(TipoToken.OPERADOR_ARITIMETICO_DIVISAO);
		follow.add(TipoToken.OPERADOR_ARITIMETICO_MULTIPLICACAO);
	}

}
