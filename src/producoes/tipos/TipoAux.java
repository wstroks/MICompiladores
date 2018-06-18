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
public class TipoAux extends RegraProducao {
	
	public static RegraProducao getInstancia() {
		return new TipoAux();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		
		if(isFirst(gerenciadorToken.getTokenAtual().getTipoToken())){
			
			if(TipoVetorDeclarando.getInstancia().analisar(gerenciadorToken)){
				return true;
			}
                        //elemento vazio
			else if(gerenciadorToken.eof()){
				return true;
			}
			
                        return false;
		}
		
		return false;
		
	}

	@Override
	protected void gerarFirst() {
		// TODO Auto-generated method stub
                
                first.add(TipoToken.EOF);//vazio
                first.add(TipoToken.DELIMITADOR_ABRE_COLCHETE);
	}

	@Override
	protected void gerarFollow() {
		// TODO Auto-generated method stub
                
                follow.add(TipoToken.IDENTIFICADOR);

	}

}
