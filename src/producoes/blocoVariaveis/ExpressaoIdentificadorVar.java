package producoes.blocoVariaveis;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class ExpressaoIdentificadorVar extends RegraProducao {

	public static RegraProducao getInstancia() {
		return new ExpressaoIdentificadorVar();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if (!consumir(gerenciadorToken, TipoToken.IDENTIFICADOR)) {
				return false;
			}

			if (!ExpressaoIdentificadorVarAux.getInstancia().analisar(gerenciadorToken)) {
				return false;
			}
			
			return true;

		}
		
		return false;
		
	}

	@Override
	protected void gerarFirst() {
		// Identificador
		first.add(TipoToken.IDENTIFICADOR);
	}

	@Override
	protected void gerarFollow() {
		// ‘,’ , ;
		follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
		follow.add(TipoToken.DELIMITADOR_VIRGULA);
	}

}
