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

		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if (TipoVetorDeclarado.getInstancia().analisar(gerenciadorToken)) {
				return true;
			}
                        if (TipoVetorDeclarandoAux.getInstancia().analisar(gerenciadorToken)) {
					return true;
				}

		}

		return false;

	}

	@Override
	protected void gerarFirst() {
		// {
		first.add(TipoToken.DELIMITADOR_ABRE_CHAVE);
	}

	@Override
	protected void gerarFollow() {
		// identificador
		follow.add(TipoToken.IDENTIFICADOR);
	}

}
