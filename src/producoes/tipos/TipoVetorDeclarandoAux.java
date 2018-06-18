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
public class TipoVetorDeclarandoAux extends RegraProducao {
	
	public static RegraProducao getInstancia() {
		return new TipoVetorDeclarandoAux();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if(isFirst(gerenciadorToken.getTokenAtual().getTipoToken())){
			
			if(TipoVetorDeclarando.getInstancia().analisar(gerenciadorToken)){
				return true;
			}
                        //vazio
			else if(gerenciadorToken.eof()){
				return true;
			}
			
		}
		
		return false;
		
	}

	@Override
	protected void gerarFirst() {
		// TODO Auto-generated method stub
                //[, E
                first.add(TipoToken.E);
                first.add(TipoToken.DELIMITADOR_ABRE_COLCHETE);

	}

	@Override
	protected void gerarFollow() {
		// TODO Auto-generated method stub
                // Identificador
                follow.add(TipoToken.IDENTIFICADOR);

	}

}
