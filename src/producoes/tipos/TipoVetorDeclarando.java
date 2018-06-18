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
			
                        else if(!TipoVetorDeclarandoAux.getInstancia().analisar(gerenciadorToken)){
				return false;
			}
			
			return true;
			
		}
		
		return false;
		
	}

	@Override
	protected void gerarFirst() {
		// TODO Auto-generated method stub
                // {
                first.add(TipoToken.DELIMITADOR_ABRE_CHAVE);
	}

	@Override
	protected void gerarFollow() {
		// TODO Auto-generated method stub
                // identificador
                
                follow.add(TipoToken.IDENTIFICADOR);

	}

}
