/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.precedenciaOperadores;

import lexico.TipoToken;
import lexico.Token;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class Acesso extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new Acesso();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {
            if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_PONTO, false)) {
                if (verificarToken(gerenciadorToken, TipoToken.IDENTIFICADOR, true)) {
                    return true;
                }
            } 
            else if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_COLCHETE, false)) {
                gerenciadorToken.ts.ajudaVetor=true;
                if (Expressao.getInstancia().analisar(gerenciadorToken)) {
                    
                    gerenciadorToken.ts.ajudaVetores();
                    if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_COLCHETE, true)) {
                       gerenciadorToken.ts.ajudaVetor=false;
                       
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        // { . ,[}
        first.add(TipoToken.DELIMITADOR_ABRE_COLCHETE);
        first.add(TipoToken.DELIMITADOR_PONTO);
    }

    @Override
    protected void gerarFollow() {
        // { =,--,++,*,/- , + != , < , <=, == , > , >= , &&, || , ), ‘,’, ;, ]}
        follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
        follow.add(TipoToken.DELIMITADOR_VIRGULA);
        follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
        follow.add(TipoToken.DELIMITADOR_PONTO);
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
