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
public class TipoBase extends RegraProducao {
	
	public static RegraProducao getInstancia() {
		return new TipoBase();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		
		if(isFirst(gerenciadorToken.getTokenAtual().getTipoToken())){
			
			//TODO: <Escalar>
			
			//TODO: <DeclaracaoDeStruct>
			
			if(consumir(gerenciadorToken, TipoToken.IDENTIFICADOR)){
				return true;
			}
			else if(consumir(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_STRUCT)){
				return true;
			}		
			
		}
		
		return false;
	}

	@Override
	protected void gerarFirst() {
		//{ bool, float, identificador, int, string, struct }
		first.add(TipoToken.PALAVRA_RESERVADA_BOOL);
		first.add(TipoToken.PALAVRA_RESERVADA_FLOAT);
		first.add(TipoToken.IDENTIFICADOR);
		first.add(TipoToken.PALAVRA_RESERVADA_INT);
		first.add(TipoToken.PALAVRA_RESERVADA_STRING);
		first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
	}

	@Override
	protected void gerarFollow() {
		//{ [, Identificador }
		follow.add(TipoToken.DELIMITADOR_ABRE_COLCHETE);
		follow.add(TipoToken.IDENTIFICADOR);
	}

}
