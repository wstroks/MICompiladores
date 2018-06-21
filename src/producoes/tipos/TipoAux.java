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

		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if (TipoVetorDeclarando.getInstancia().analisar(gerenciadorToken)) {
				return true;
			}
			else if (gerenciadorToken.eof()) {
				return true;
			}

		}

		return false;

	}

	@Override
	protected void gerarFirst() {
		first.add(TipoToken.EOF);// vazio
		first.add(TipoToken.DELIMITADOR_ABRE_COLCHETE);
	}

	@Override
	protected void gerarFollow() {
		follow.add(TipoToken.IDENTIFICADOR);
	}

}
