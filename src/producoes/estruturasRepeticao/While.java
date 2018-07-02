/**
 *
 */
package producoes.estruturasRepeticao;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.declaracaoFuncaoProcedimento.Bloco;
import producoes.precedenciaOperadores.Expressao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class While extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new While();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {
            if (verificarToken(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_WHILE, false)) {
                if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_PARENTESES, true)) {
                    if (Expressao.getInstancia().analisar(gerenciadorToken)) {
                        if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_PARENTESES, true)) {
                            if (Bloco.getInstancia().analisar(gerenciadorToken)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;

    }

    @Override
    protected void gerarFirst() {
        // while
        first.add(TipoToken.PALAVRA_RESERVADA_WHILE);
    }

    @Override
    protected void gerarFollow() {
        // --, !, ( , ++, CadeiaDeCaracter, Digitos, false, identificador, print, return
        // , scan, struct,true, typdef,
        // var,while, }
        follow.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
        follow.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        follow.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
        follow.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);
        follow.add(TipoToken.CADEIA_CARACTERES);
        follow.add(TipoToken.NUMERO);
        follow.add(TipoToken.IDENTIFICADOR);
        follow.add(TipoToken.PALAVRA_RESERVADA_FALSE);
        follow.add(TipoToken.PALAVRA_RESERVADA_PRINT);
        follow.add(TipoToken.PALAVRA_RESERVADA_RETURN);
        follow.add(TipoToken.PALAVRA_RESERVADA_SCAN);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        follow.add(TipoToken.PALAVRA_RESERVADA_TRUE);
        follow.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        follow.add(TipoToken.PALAVRA_RESERVADA_VAR);
        follow.add(TipoToken.PALAVRA_RESERVADA_WHILE);
    }

}
