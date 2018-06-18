/**
 * 
 */
package producoes.printScan;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class Print extends RegraProducao{
	
	private static Print instancia = new Print();
	
	public static RegraProducao getInstancia() {
		return instancia;
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		
		if(isFirst(gerenciadorToken.getTokenAtual().getTipoToken())){
			
			if(!consumir(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_PRINT)){
				gerenciadorToken.addErro(TipoToken.PALAVRA_RESERVADA_PRINT);
				return false;
			}
			
			if(!consumir(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_PARENTESES)){
				gerenciadorToken.addErro(TipoToken.DELIMITADOR_ABRE_PARENTESES);
				return false;
			}
			
			//TODO: <Saida>
			
			//TODO: <OutrasSaidas>
			
			if(!consumir(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_PARENTESES)){
				gerenciadorToken.addErro(TipoToken.DELIMITADOR_ABRE_PARENTESES);
				return false;
			}
			
		}
		
		return false;
	}

	@Override
	protected void gerarFirst() {
		//{ print }
		first.add(TipoToken.PALAVRA_RESERVADA_PRINT);
	}

	@Override
	protected void gerarFollow() {
		//{ ; }
		follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
	}

}
