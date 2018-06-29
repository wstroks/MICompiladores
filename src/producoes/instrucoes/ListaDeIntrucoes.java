/**
 * 
 */
package producoes.instrucoes;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class ListaDeIntrucoes extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new ListaDeIntrucoes();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (Instrucao.getInstancia().analisar(gerenciadorToken)) {
                if (ListaDeIntrucoesAux.getInstancia().analisar(gerenciadorToken)) {
                    return true;
                }
            }

        }
        
        return false;

    }

    @Override
    protected void gerarFirst() {
        // --, !, ( , ++, CadeiaDeCaracter, Digitos, false, identificador, print, return , scan, struct,true,
        //typdef, var,while
        first.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
        first.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        first.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        first.add(TipoToken.CADEIA_CARACTERES);
        first.add(TipoToken.NUMERO);
        first.add(TipoToken.IDENTIFICADOR);
        first.add(TipoToken.PALAVRA_RESERVADA_FALSE);
        first.add(TipoToken.PALAVRA_RESERVADA_TRUE);
        first.add(TipoToken.PALAVRA_RESERVADA_PRINT);
        first.add(TipoToken.PALAVRA_RESERVADA_RETURN);
        first.add(TipoToken.PALAVRA_RESERVADA_SCAN);
        first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        first.add(TipoToken.PALAVRA_RESERVADA_VAR);
        first.add(TipoToken.PALAVRA_RESERVADA_WHILE);
        first.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
        first.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        first.add(TipoToken.PALAVRA_RESERVADA_IF);

    }

    @Override
    protected void gerarFollow() {
        // }
        follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE); 
    }

}
