package producoes.blocoVariaveis;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.blocoStruct.DeclaracaoDeStructCorpo;
import producoes.precedenciaOperadores.Expressao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class ExpressaoIdentificadorVarAux extends RegraProducao {

	public static RegraProducao getInstancia() {
		return new ExpressaoIdentificadorVarAux();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

			if (consumir(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_ATRIBUICAO)) {
				if (Expressao.getInstancia().analisar(gerenciadorToken)) {
					return true;
				}
			}
			else if(gerenciadorToken.eof()){
				return true;
			}

		}
		
		return false;
		
	}

	@Override
	protected void gerarFirst() {
		// =, E
		first.add(TipoToken.OPERADOR_RELACIONAL_ATRIBUICAO);
		first.add(TipoToken.EOF);
	}

	@Override
	protected void gerarFollow() {
		// , ;
		follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
		follow.add(TipoToken.DELIMITADOR_VIRGULA);
	}

}
