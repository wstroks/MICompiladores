/**
 * 
 */
package producoes.declaracaoFuncaoProcedimento;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class DeclaracaoDeInicio extends RegraProducao{
	
	public static RegraProducao getInstancia() {
		return new DeclaracaoDeInicio();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		
		if(!consumir(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_START)){
			return false;
		}
		
                else if(!consumir(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_PARENTESES)){
			return false;
		}
		
                else if(!consumir(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_PARENTESES)){
			return false;
		}
		
                else if(!Bloco.getInstancia().analisar(gerenciadorToken)){
			return false;
		}
		
		return true;
	}

	@Override
	protected void gerarFirst() {
		//{ start }
        first.add(TipoToken.PALAVRA_RESERVADA_START);
	}

	@Override
	protected void gerarFollow() {
		//{ const, function, procedure, start, struct, typedef, var, $ }
		follow.add(TipoToken.PALAVRA_RESERVADA_CONST);
		follow.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
		follow.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
		follow.add(TipoToken.PALAVRA_RESERVADA_START);
		follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
		follow.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
		follow.add(TipoToken.PALAVRA_RESERVADA_VAR);
		follow.add(TipoToken.EOF);
	}

}