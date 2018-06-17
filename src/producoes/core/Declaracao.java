/**
 * 
 */
package producoes.core;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class Declaracao extends RegraProducao{
	
	private static Declaracao instancia = new Declaracao();
	
	public static RegraProducao getInstancia() {
		return instancia;
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void gerarFirst() {
		//{ const, function, procedure, start, typedef, var }
		first.add(TipoToken.PALAVRA_RESERVADA_CONST);
		first.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
		first.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
		first.add(TipoToken.PALAVRA_RESERVADA_START);
		first.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
		first.add(TipoToken.PALAVRA_RESERVADA_VAR);
	}

	@Override
	protected void gerarFollow() {
		//{ const, function, procedure, start, struct, typedef, var, $ }
		follow.add(TipoToken.PALAVRA_RESERVADA_CONST);
		follow.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
		follow.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
		follow.add(TipoToken.PALAVRA_RESERVADA_START);
		follow.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
		follow.add(TipoToken.PALAVRA_RESERVADA_VAR);
	}

}
