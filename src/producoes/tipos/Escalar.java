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
public class Escalar extends RegraProducao {
	
	public static RegraProducao getInstancia() {
		return new Escalar();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		
		System.out.println("Analisando <Escalar>");
		
		if(isFirst(gerenciadorToken.getTokenAtual().getTipoToken())){
			
			if(consumir(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_INT)){
				return true;
			}
			else if(consumir(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_FLOAT)){
				return true;
			}
			else if(consumir(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_BOOL)){
				return true;
			}
			else if(consumir(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_STRING)){
				return true;
			}
			
		}
		
		return false;
	}

	@Override
	protected void gerarFirst() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void gerarFollow() {
		// TODO Auto-generated method stub

	}

}
