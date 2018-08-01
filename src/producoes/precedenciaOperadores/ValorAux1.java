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
public class ValorAux1 extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new ValorAux1();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_PARENTESES, false)) {
            // System.out.println(gerenciadorToken.getAnteriorToken().getLexema()+gerenciadorToken.getTokenAtual().getLexema()+ gerenciadorToken.getProximoDeterminaToken(1).getLexema());
            gerenciadorToken.ts.clearBufferChamadaFuncao();
            gerenciadorToken.ts.bufferChamadaFuncao.setNome(gerenciadorToken.getAnteriorDeterminaToken(2).getLexema());
            if (ValorAux2.getInstancia().analisar(gerenciadorToken)) {
                return true;
            }
        } else {
            return verificarSimboloVazio(gerenciadorToken, true);
        }

        return false;

    }

    @Override
    protected void gerarFirst() {
        // { (, E }
        first.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        first.add(TipoToken.EOF);
    }

    @Override
    protected void gerarFollow() {
        // { =,--,++,*,/- , + != , < , <=, == , > , >= , &&, || , ), ‘,’, ;, ]}
        follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
        follow.add(TipoToken.DELIMITADOR_VIRGULA);
        follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
        follow.add(TipoToken.DELIMITADOR_FECHA_COLCHETE);
        follow.add(TipoToken.OPERADOR_LOGICO_OU);
        follow.add(TipoToken.OPERADOR_LOGICO_E);
        follow.add(TipoToken.OPERADOR_RELACIONAL_DIFERENTE);
        follow.add(TipoToken.OPERADOR_RELACIONAL_IGUAL);
        follow.add(TipoToken.OPERADOR_RELACIONAL_MAIOR_IGUAL_QUE);
        follow.add(TipoToken.OPERADOR_RELACIONAL_MAIOR_QUE);
        follow.add(TipoToken.OPERADOR_RELACIONAL_MENOR_IGUAL_QUE);
        follow.add(TipoToken.OPERADOR_RELACIONAL_MENOR_QUE);
        follow.add(TipoToken.OPERADOR_ARITIMETICO_ADICAO);
        follow.add(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO);
        follow.add(TipoToken.OPERADOR_ARITIMETICO_DIVISAO);
        follow.add(TipoToken.OPERADOR_ARITIMETICO_MULTIPLICACAO);
        follow.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        follow.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
        follow.add(TipoToken.OPERADOR_RELACIONAL_ATRIBUICAO);
    }

}
