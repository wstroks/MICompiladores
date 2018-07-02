/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.instrucoes;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.precedenciaOperadores.Expressao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class InstrucaoDeRetornoAux extends RegraProducao {

	public static RegraProducao getInstancia() {
		return new InstrucaoDeRetornoAux();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		
		if(Expressao.getInstancia().analisar(gerenciadorToken)){
			return true;
		}
		else if(isFollow(gerenciadorToken.getTipoTokenAtual())){
			return true;
		}

		return false;

	}

	@Override
	protected void gerarFirst() {
		// --, !, ( , ++ , CadeiadeCaracter, Digitos, false, identificador,
		// print, return, scan, struct, true ,e
		first.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
		first.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
		first.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
		first.add(TipoToken.CADEIA_CARACTERES);
		first.add(TipoToken.NUMERO);
		first.add(TipoToken.IDENTIFICADOR);
		first.add(TipoToken.PALAVRA_RESERVADA_FALSE);
		first.add(TipoToken.PALAVRA_RESERVADA_TRUE);
		first.add(TipoToken.PALAVRA_RESERVADA_PRINT);
		first.add(TipoToken.PALAVRA_RESERVADA_SCAN);
		first.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
		first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        first.add(TipoToken.PALAVRA_RESERVADA_RETURN);
		first.add(TipoToken.EOF);
	}

	@Override
	protected void gerarFollow() {
		// ;
		follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
	}

}
