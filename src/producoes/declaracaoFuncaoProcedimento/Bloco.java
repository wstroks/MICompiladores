/**
 *
 */
package producoes.declaracaoFuncaoProcedimento;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class Bloco extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new Bloco();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (consumir(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_CHAVE)) {
                if (BlocoAux.getInstancia().analisar(gerenciadorToken)) {
                    return true;
                }
                //gerenciadorToken.goBack(getNomeClasse());
            }

        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        // { { }
        first.add(TipoToken.DELIMITADOR_ABRE_CHAVE);
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
