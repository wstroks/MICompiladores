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
public class Tipo extends RegraProducao {

	public static RegraProducao getInstancia() {
		return new Tipo();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if (!TipoBase.getInstancia().analisar(gerenciadorToken)) {
				return false;
			}

			if (!TipoAux.getInstancia().analisar(gerenciadorToken)) {
				return false;
			}

			return true;

		}

		return false;
	}

	@Override
	protected void gerarFirst() {
		// bool, float, identificador,int, string, struct
		first.add(TipoToken.PALAVRA_RESERVADA_BOOL);
		first.add(TipoToken.IDENTIFICADOR);
		first.add(TipoToken.PALAVRA_RESERVADA_INT);
		first.add(TipoToken.PALAVRA_RESERVADA_STRING);
		first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
	}

	@Override
	protected void gerarFollow() {
		// identificador
		follow.add(TipoToken.IDENTIFICADOR);
	}

}
