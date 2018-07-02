/**
 *
 */
package producoes.declaracaoFuncaoProcedimento;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.instrucoes.ListaDeIntrucoes;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class BlocoAux extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new BlocoAux();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (ListaDeIntrucoes.getInstancia().analisar(gerenciadorToken)) {
                if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_CHAVE, true)) {
                    return true;
                }

            } else if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_CHAVE, false)) {
                return true;
            }

        }

        return false;

    }

    @Override
    protected void gerarFirst() {
        // { --, ++, !, (, }, ++, CadeiaDeCaracteres, Digito, false,
        // identificador, if, print, return, scan, struct, true, typedef, var,
        // while }
        first.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
        first.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
        first.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        first.add(TipoToken.DELIMITADOR_FECHA_CHAVE);
        first.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        first.add(TipoToken.CADEIA_CARACTERES);
        first.add(TipoToken.NUMERO);
        first.add(TipoToken.PALAVRA_RESERVADA_FALSE);
        first.add(TipoToken.IDENTIFICADOR);
        first.add(TipoToken.PALAVRA_RESERVADA_PRINT);
        first.add(TipoToken.PALAVRA_RESERVADA_RETURN);
        first.add(TipoToken.PALAVRA_RESERVADA_SCAN);
        first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        first.add(TipoToken.PALAVRA_RESERVADA_TRUE);
        first.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        first.add(TipoToken.PALAVRA_RESERVADA_VAR);
        first.add(TipoToken.PALAVRA_RESERVADA_WHILE);
        first.add(TipoToken.PALAVRA_RESERVADA_IF);

    }

    @Override
    protected void gerarFollow() {
        // {--, !, ( , ++, CadeiaDeCaracteres, Digitos, false, Identificador,
        // print, return , scan, struct, true, typdef, var, while, else, } ,
        // const, function, procedure, start, $ }
        follow.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
        follow.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
        follow.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        follow.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        follow.add(TipoToken.CADEIA_CARACTERES);
        follow.add(TipoToken.NUMERO);
        follow.add(TipoToken.PALAVRA_RESERVADA_FALSE);
        follow.add(TipoToken.IDENTIFICADOR);
        follow.add(TipoToken.PALAVRA_RESERVADA_PRINT);
        follow.add(TipoToken.PALAVRA_RESERVADA_RETURN);
        follow.add(TipoToken.PALAVRA_RESERVADA_SCAN);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        follow.add(TipoToken.PALAVRA_RESERVADA_TRUE);
        follow.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        follow.add(TipoToken.PALAVRA_RESERVADA_VAR);
        follow.add(TipoToken.PALAVRA_RESERVADA_WHILE);
        follow.add(TipoToken.PALAVRA_RESERVADA_ELSE);
        follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);
        follow.add(TipoToken.PALAVRA_RESERVADA_CONST);
        follow.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
        follow.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
        follow.add(TipoToken.PALAVRA_RESERVADA_START);
        follow.add(TipoToken.EOF);
    }

}
