/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.blocoConstante;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.tipos.Tipo;
import semantico.TabelaSimbolos;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class DeclaracaoDeConstanteLinha extends RegraProducao {

	/**
	 * @param ts
	 */
	public DeclaracaoDeConstanteLinha(TabelaSimbolos ts) {
		super(ts);
	}

	public static RegraProducao getInstancia(TabelaSimbolos ts) {
		return new DeclaracaoDeConstanteLinha(ts);
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		
		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if (Tipo.getInstancia().analisar(gerenciadorToken)) {
				if (ExpressaoIdentificadoresConst.getInstancia().analisar(gerenciadorToken)) {
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
		first.add(TipoToken.PALAVRA_RESERVADA_INT);
		first.add(TipoToken.PALAVRA_RESERVADA_STRING);
		first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
		first.add(TipoToken.IDENTIFICADOR);
	}

	@Override
	protected void gerarFollow() {
		// bool, float, identificador, int, string, struct }
		follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);
		follow.add(TipoToken.PALAVRA_RESERVADA_BOOL);
		follow.add(TipoToken.PALAVRA_RESERVADA_FLOAT);
		follow.add(TipoToken.PALAVRA_RESERVADA_INT);
		follow.add(TipoToken.PALAVRA_RESERVADA_STRING);
		follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
		follow.add(TipoToken.IDENTIFICADOR);
	}

}
