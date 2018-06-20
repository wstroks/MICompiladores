/**
 * 
 */
package producoes.instrucoes;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.blocoConstante.DeclaracaoDeConst;
import producoes.blocoStruct.DeclaracaoDeStruct;
import producoes.blocoVariaveis.DeclaracaoDeVar;
import producoes.declaracaoFuncaoProcedimento.DeclaracaoDeFuncao;
import producoes.declaracaoFuncaoProcedimento.DeclaracaoDeInicio;
import producoes.declaracaoFuncaoProcedimento.DeclaracaoDeProcedimento;
import producoes.declaracaoTipo.DeclaracaoDeTypedef;
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
        // TODO Auto-generated method stub

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (!Instrucao.getInstancia().analisar(gerenciadorToken)) {
                return false;
            } else if (!ListaDeIntrucoesAux.getInstancia().analisar(gerenciadorToken)) {
                return false;
            } 

            return true;

        }
        return false;

    }

    @Override
    protected void gerarFirst() {
        // TODO Auto-generated method stub
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

    }

    @Override
    protected void gerarFollow() {
        // TODO Auto-generated method stub
        // }

        follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);
       

    }

}