/**
 * 
 */
package producoes.core;

import lexico.TipoToken;
import producoes.RegraProducao;
import semantico.TabelaSimbolos;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class Programa extends RegraProducao {

	/**
	 * @param ts
	 */
	public Programa(TabelaSimbolos ts) {
		super(ts);
	}

	public static RegraProducao getInstancia(TabelaSimbolos ts) {
		return new Programa(ts);
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		
		if(isFirst(gerenciadorToken.getTipoTokenAtual())){
			if (Declaracao.getInstancia().analisar(gerenciadorToken)) {
				if (ProgramaAux.getInstancia().analisar(gerenciadorToken)) {
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	protected void gerarFirst() {
		// {const, function, procedure, start, struct, typedef, var }
		first.add(TipoToken.PALAVRA_RESERVADA_CONST);
		first.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
		first.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
		first.add(TipoToken.PALAVRA_RESERVADA_START);
		first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
		first.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
		first.add(TipoToken.PALAVRA_RESERVADA_VAR);
	}

	@Override
	protected void gerarFollow() {
		// { $ }
		follow.add(TipoToken.EOF);
	}

}
