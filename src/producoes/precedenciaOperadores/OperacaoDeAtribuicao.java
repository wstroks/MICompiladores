/**
 *
 */
package producoes.precedenciaOperadores;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.tipos.Tipo;
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
        // TODO Auto-generated method stub
        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (!Final.getInstancia().analisar(gerenciadorToken)) {
                return false;
            } else if (!consumir(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_ATRIBUICAO)) {
                return false;
            }else if(!Expressao.getInstancia().analisar(gerenciadorToken)) {
                return false;
            } else if (!consumir(gerenciadorToken, TipoToken.IDENTIFICADOR)) {
                return false;
            }

            return true;

        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        //{!, ++, --, Identificador, (, Digitos, CadeiraDeCaracteres, true, false}
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
        //{;}
        follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);

    }

}