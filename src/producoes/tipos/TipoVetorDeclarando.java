/**
 * 
 */
package producoes.tipos;

import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class TipoVetorDeclarando extends RegraProducao {
	
	public static RegraProducao getInstancia() {
		return new TipoVetorDeclarando();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
			
		if(isFirst(gerenciadorToken.getTokenAtual().getTipoToken())){
					
			if(!TipoVetorDeclarado.getInstancia().analisar(gerenciadorToken)){
				return false;
			}
			
			if(!TipoVetorDeclarandoAux.getInstancia().analisar(gerenciadorToken)){
				return false;
			}
			
			return true;
			
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
