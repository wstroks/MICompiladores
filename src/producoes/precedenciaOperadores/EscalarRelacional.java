/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.precedenciaOperadores;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class EscalarRelacional extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new EscalarRelacional();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {
            if (verificarToken(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_DIFERENTE, false)) {
               // System.out.println(gerenciadorToken.getAnteriorToken().getLexema()+gerenciadorToken.getTokenAtual().getLexema()+ gerenciadorToken.getProximoDeterminaToken(1).getLexema());
               gerenciadorToken.ts.atribuicaoExpressao();
               gerenciadorToken.ts.ajudaExpressaoAtribuicao.clear();
               gerenciadorToken.ts.expressaoIf(gerenciadorToken.getAnteriorDeterminaToken(2),gerenciadorToken.getTokenAtual(), gerenciadorToken.getTokenAtual());

                return true;
            } else if (verificarToken(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_IGUAL, false)) {
                //System.out.println(gerenciadorToken.getAnteriorToken().getLexema()+gerenciadorToken.getTokenAtual().getLexema()+ gerenciadorToken.getProximoDeterminaToken(1).getLexema());
                gerenciadorToken.ts.expressaoIf(gerenciadorToken.getAnteriorDeterminaToken(2),gerenciadorToken.getTokenAtual(), gerenciadorToken.getTokenAtual());

                return true;
            } else if (verificarToken(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_MENOR_QUE, false)) {
                 gerenciadorToken.ts.expressaoIf(gerenciadorToken.getAnteriorDeterminaToken(2),gerenciadorToken.getAnteriorToken(), gerenciadorToken.getTokenAtual());
                
                return true;
            } else if (verificarToken(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_MENOR_IGUAL_QUE, false)) {
               // System.out.println(gerenciadorToken.getAnteriorToken().getLexema()+gerenciadorToken.getTokenAtual().getLexema()+ gerenciadorToken.getProximoDeterminaToken(1).getLexema());
                gerenciadorToken.ts.expressaoIf(gerenciadorToken.getAnteriorDeterminaToken(2),gerenciadorToken.getAnteriorToken(), gerenciadorToken.getTokenAtual());

                return true;
            } else if (verificarToken(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_MAIOR_IGUAL_QUE, false)) {
                System.out.println(gerenciadorToken.getAnteriorDeterminaToken(2).getLexema()+gerenciadorToken.getAnteriorToken().getLexema()+ gerenciadorToken.getTokenAtual().getLexema());
                 gerenciadorToken.ts.expressaoIf(gerenciadorToken.getAnteriorDeterminaToken(2),gerenciadorToken.getAnteriorToken(), gerenciadorToken.getTokenAtual());
                return true;
            } else if (verificarToken(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_MAIOR_QUE, false)) {
                gerenciadorToken.ts.atribuicaoExpressao();
               gerenciadorToken.ts.ajudaExpressaoAtribuicao.clear();
               //System.out.println(gerenciadorToken.getAnteriorDeterminaToken(2).getLexema() + gerenciadorToken.getAnteriorToken().getLexema()+ gerenciadorToken.getTokenAtual().getLexema());
                        gerenciadorToken.ts.expressaoIf(gerenciadorToken.getAnteriorDeterminaToken(2),gerenciadorToken.getAnteriorToken(), gerenciadorToken.getTokenAtual());

                return true;
            }

        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        // { != , < , <= , == , >=, >}
        first.add(TipoToken.OPERADOR_RELACIONAL_DIFERENTE);
        first.add(TipoToken.OPERADOR_RELACIONAL_MAIOR_IGUAL_QUE);
        first.add(TipoToken.OPERADOR_RELACIONAL_MAIOR_QUE);
        first.add(TipoToken.OPERADOR_RELACIONAL_MENOR_IGUAL_QUE);
        first.add(TipoToken.OPERADOR_RELACIONAL_MENOR_QUE);
        first.add(TipoToken.OPERADOR_RELACIONAL_IGUAL);
    }

    @Override
    protected void gerarFollow() {
        // { --, ! , (, ++ , CadeiaDeCaracteres, Digitos, false, identificador,
        // true}
        follow.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
        follow.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        follow.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
        follow.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        follow.add(TipoToken.CADEIA_CARACTERES);
        follow.add(TipoToken.NUMERO);
        follow.add(TipoToken.PALAVRA_RESERVADA_FALSE);
        follow.add(TipoToken.PALAVRA_RESERVADA_TRUE);
        follow.add(TipoToken.IDENTIFICADOR);
    }

}
