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
public class Programa extends RegraProducao{
	
	private static Programa instancia = new Programa();
	
	public static RegraProducao getInstancia() {
		return instancia;
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		
		if(!first.contains(gerenciadorToken.getTokenAtual())){
			gerenciadorToken.addErro(first);
			return false;
		}
		
		if(!Declaracao.getInstancia().analisar(gerenciadorToken)){
			return false;
		}
		
		return gerenciadorToken.eof();
		
	}

	@Override
	protected void gerarFirst() {
		//{const, function, procedure, start, struct, typedef, var }
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
		//{ $ }
		follow.add(TipoToken.EOF);
	}

}
