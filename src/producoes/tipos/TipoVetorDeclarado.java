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
public class TipoVetorDeclarado extends RegraProducao {
	
	public static RegraProducao getInstancia() {
		return new TipoVetorDeclarado();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if(isFirst(gerenciadorToken.getTokenAtual().getTipoToken())){
			
			if(!consumir(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_COLCHETE)){
				return false;
			}
			
                        else if(!consumir(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_COLCHETE)){
				return false;
			}
			
			return true;
			
		}
		
		return false;
		
	}


	@Override
	protected void gerarFirst() {
		// TODO Auto-generated method stub
                // [ 
                first.add(TipoToken.DELIMITADOR_ABRE_COLCHETE);

	}

	@Override
	protected void gerarFollow() {
		// TODO Auto-generated method stub
                // [, Identificador
                follow.add(TipoToken.DELIMITADOR_ABRE_COLCHETE);
                follow.add(TipoToken.IDENTIFICADOR);
                
	}

}
