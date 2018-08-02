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
public class Valor extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new Valor();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {
            if (verificarToken(gerenciadorToken, TipoToken.IDENTIFICADOR, false)) {
                
               gerenciadorToken.ts.addAtriicaoExpressao(gerenciadorToken.getAnteriorToken());
               if(gerenciadorToken.ts.ajudaVetor){
                    gerenciadorToken.ts.removeAtriicaoExpressao(gerenciadorToken.getAnteriorToken());
                    gerenciadorToken.ts.ajudaVetores.add(gerenciadorToken.getAnteriorToken());
                }
               if(!gerenciadorToken.ts.ajudaFunction){
               gerenciadorToken.ts.variavelNaoDeclaradoErro(gerenciadorToken.getAnteriorToken());
               }
                //System.out.println(gerenciadorToken.getAnteriorToken().getLexema());
                if (ValorAux1.getInstancia().analisar(gerenciadorToken)) {
                    
                    return true;
                }
            } else if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_PARENTESES, false)) {
                if (Expressao.getInstancia().analisar(gerenciadorToken)) {
                    if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_PARENTESES, true)) {
                        return true;
                    }
                }
            } else if (verificarToken(gerenciadorToken, TipoToken.NUMERO, false)) {
                
                gerenciadorToken.ts.addAtriicaoExpressao(gerenciadorToken.getAnteriorToken());
                if(gerenciadorToken.ts.ajudaVetor){
                    gerenciadorToken.ts.removeAtriicaoExpressao(gerenciadorToken.getAnteriorToken());
                    gerenciadorToken.ts.ajudaVetores.add(gerenciadorToken.getAnteriorToken());
                }
                return true;
            } else if (verificarToken(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_FALSE, false)) {
                gerenciadorToken.ts.addAtriicaoExpressao(gerenciadorToken.getAnteriorToken());
                 if(gerenciadorToken.ts.ajudaVetor){
                    gerenciadorToken.ts.removeAtriicaoExpressao(gerenciadorToken.getAnteriorToken());
                    gerenciadorToken.ts.ajudaVetores.add(gerenciadorToken.getAnteriorToken());
                }
                return true;
            } else if (verificarToken(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_TRUE, false)) {
                gerenciadorToken.ts.addAtriicaoExpressao(gerenciadorToken.getAnteriorToken());
                 if(gerenciadorToken.ts.ajudaVetor){
                    gerenciadorToken.ts.removeAtriicaoExpressao(gerenciadorToken.getAnteriorToken());
                    gerenciadorToken.ts.ajudaVetores.add(gerenciadorToken.getAnteriorToken());
                }
                return true;
            } else if (verificarToken(gerenciadorToken, TipoToken.CADEIA_CARACTERES, false)) {
                gerenciadorToken.ts.addAtriicaoExpressao(gerenciadorToken.getAnteriorToken());
                 if(gerenciadorToken.ts.ajudaVetor){
                    gerenciadorToken.ts.removeAtriicaoExpressao(gerenciadorToken.getAnteriorToken());
                    gerenciadorToken.ts.ajudaVetores.add(gerenciadorToken.getAnteriorToken());
                }
                return true;
            }

        }
        
        return false;
    }

    @Override
    protected void gerarFirst() {
        // { ( , cadeiadeCaracter, Digitos, false, identificador, true}
        first.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        first.add(TipoToken.CADEIA_CARACTERES);
        first.add(TipoToken.NUMERO);
        first.add(TipoToken.PALAVRA_RESERVADA_FALSE);
        first.add(TipoToken.PALAVRA_RESERVADA_TRUE);
        first.add(TipoToken.IDENTIFICADOR);
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
