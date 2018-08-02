/**
 *
 */
package producoes.precedenciaOperadores;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class OperacaoDeAtribuicao extends RegraProducao {

	public static RegraProducao getInstancia() {
		return new OperacaoDeAtribuicao();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {

		if (gerenciadorToken.getTipoTokenAtual() == TipoToken.IDENTIFICADOR
				&& (gerenciadorToken.getProximoToken().getTipoToken() == TipoToken.OPERADOR_RELACIONAL_ATRIBUICAO
						|| gerenciadorToken.getProximoToken().getTipoToken() == TipoToken.DELIMITADOR_ABRE_COLCHETE)) {

			if (gerenciadorToken.getProximoToken().getTipoToken() == TipoToken.OPERADOR_RELACIONAL_ATRIBUICAO) {
				if (verificarToken(gerenciadorToken, TipoToken.IDENTIFICADOR, false)) {
					gerenciadorToken.ts.atribuicaoConst(gerenciadorToken.getAnteriorToken());                    
					gerenciadorToken.ts.addAtriicaoExpressao(gerenciadorToken.getAnteriorToken());                  
                    gerenciadorToken.ts.variavelNaoDeclaradoErro(gerenciadorToken.getAnteriorToken());
                                        
					//System.out.println(gerenciadorToken.getAnteriorToken().getLexema());
					if (verificarToken(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_ATRIBUICAO, true)) {

						if (Expressao.getInstancia().analisar(gerenciadorToken)) {		 
							return true;
						}
					}
				}
			} else if (Final.getInstancia().analisar(gerenciadorToken)) {
				if (verificarToken(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_ATRIBUICAO, true)) {
					if (Expressao.getInstancia().analisar(gerenciadorToken)) {
                                            gerenciadorToken.ts.addAtriicaoExpressao(gerenciadorToken.getTokenAtual());
						return true;
					}
				}
			}

		} else if (Expressao.getInstancia().analisar(gerenciadorToken)) {
                    gerenciadorToken.ts.addAtriicaoExpressao(gerenciadorToken.getTokenAtual());
			return true;
		}

		return false;

	}

	@Override
	protected void gerarFirst() {
		// {!, ++, --, Identificador, (, Digitos, CadeiraDeCaracteres, true,
		// false}
		first.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
		first.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
		first.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
		first.add(TipoToken.IDENTIFICADOR);
		first.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
		first.add(TipoToken.NUMERO);
		first.add(TipoToken.CADEIA_CARACTERES);
		first.add(TipoToken.PALAVRA_RESERVADA_TRUE);
		first.add(TipoToken.PALAVRA_RESERVADA_FALSE);
	}

	@Override
	protected void gerarFollow() {
		// {;}
		follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
	}

}
