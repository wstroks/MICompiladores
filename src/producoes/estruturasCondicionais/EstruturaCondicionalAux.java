/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.estruturasCondicionais;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.declaracaoFuncaoProcedimento.Bloco;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class EstruturaCondicionalAux extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new EstruturaCondicionalAux();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {
        // TODO Auto-generated method stub
        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

           if (!consumir(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_ELSE)) {
                return false;}
           else if (!Bloco.getInstancia().analisar(gerenciadorToken)) {
                return false;
            }
            // falta produçãovazia
            return true;

        }
        return false;
    }

    @Override
    protected void gerarFirst() {
        // TODO Auto-generated method stub
        // else e E
        first.add(TipoToken.PALAVRA_RESERVADA_ELSE);
        first.add(TipoToken.E);
    }

    @Override
    protected void gerarFollow() {
        // TODO Auto-generated method stub
        // --, !, ( , ++, CadeiaDeCaracteres, Digitos, false, identificador, print, return , scan, struct,
        //true, typdef, var, while
        
        follow.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
        follow.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        follow.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
        follow.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
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
