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
public class OperacaoDeAtribuicao extends RegraProducao{
	
	private static OperacaoDeAtribuicao instancia = new OperacaoDeAtribuicao();
	
	public static RegraProducao getInstancia() {
		return instancia;
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void gerarFirst() {
		//{!, ++, --, Identificador, (, Digitos, CadeiraDeCaracteres, true, false}
        first.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
        first.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        first.add(TipoToken.IDENTIFICADOR);
        first.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        first.add(TipoToken.NUMERO);
        first.add(TipoToken.CADEIA_CARACTERES);
        first.add(TipoToken.PALAVRA_RESERVADA_TRUE);
        first.add(TipoToken.PALAVRA_RESERVADA_FALSE);
	}

	@Override
	protected void gerarFollow() {
		//{;}
		follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
		
	}

}
