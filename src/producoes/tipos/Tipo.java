/**
 * 
 */
package producoes.tipos;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class Tipo extends RegraProducao{
	
	public static RegraProducao getInstancia() {
		return new Tipo();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		
		if(isFirst(gerenciadorToken.getTokenAtual().getTipoToken())){
			
			//TODO: <TipoBase>
			
			//TODO: <TipoAux>
			
			return true;
			
		}
		
		return false;
	}

	@Override
	protected void gerarFirst() {
		//{ Identificador }
		first.add(TipoToken.IDENTIFICADOR);
	}

	@Override
	protected void gerarFollow() {
		//{ bool, float, identificador,int, string, struct }
		follow.add(TipoToken.PALAVRA_RESERVADA_BOOL);
		follow.add(TipoToken.IDENTIFICADOR);
		follow.add(TipoToken.PALAVRA_RESERVADA_INT);
		follow.add(TipoToken.PALAVRA_RESERVADA_STRING);
		follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
	}

}
