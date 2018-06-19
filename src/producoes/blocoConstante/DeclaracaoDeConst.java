/**
 * 
 */
package producoes.blocoConstante;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class DeclaracaoDeConst extends RegraProducao {

	public static RegraProducao getInstancia() {
		return new DeclaracaoDeConst();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if (!consumir(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_CONST)) {
				return false;
			}

			if (!consumir(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_CHAVE)) {
				return false;
			}

			if (!DeclaracaoDeConstanteCorpo.getInstancia().analisar(gerenciadorToken)) {
				return false;
			}

			if (!consumir(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_CHAVE)) {
				return false;
			}

			return true;

		}

		return false;

	}

	@Override
	protected void gerarFirst() {
		// const
		first.add(TipoToken.PALAVRA_RESERVADA_CONST);
	}

	@Override
	protected void gerarFollow() {
		// const, function, procedure, start, struct, typedef, var, $
		follow.add(TipoToken.EOF);
		follow.add(TipoToken.PALAVRA_RESERVADA_CONST);
		follow.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
		follow.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
		follow.add(TipoToken.PALAVRA_RESERVADA_START);
		follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
		follow.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
		follow.add(TipoToken.PALAVRA_RESERVADA_VAR);

	}

}
