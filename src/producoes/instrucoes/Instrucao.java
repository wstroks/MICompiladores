/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import producoes.estruturasCondicionais.EstruturaCondicional;
import producoes.estruturasRepeticao.While;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class Instrucao extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new Instrucao();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {
        // TODO Auto-generated method stub

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (!InstrucaoNormal.getInstancia().analisar(gerenciadorToken)) {
                return false;
            } else if (!EstruturaCondicional.getInstancia().analisar(gerenciadorToken)) {
                return false;
            } else if (!While.getInstancia().analisar(gerenciadorToken)) {
                return false;
            } else if (!DeclaracaoDeVar.getInstancia().analisar(gerenciadorToken)) {
                return false;
            } else if (!DeclaracaoDeTypedef.getInstancia().analisar(gerenciadorToken)) {
                return false;
            } 

            return true;

        }
        return false;

    }

    @Override
    protected void gerarFirst() {
        // TODO Auto-generated method stub
        //  --, !, (, ++, CadeiadeCaracter, Digitos, false, identificador, if, print, return,scan, struct, true, typedef,
        //var, while
        first.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
        first.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        first.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        first.add(TipoToken.CADEIA_CARACTERES);
        first.add(TipoToken.NUMERO);
        first.add(TipoToken.IDENTIFICADOR);
        first.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
        first.add(TipoToken.PALAVRA_RESERVADA_FALSE);
        first.add(TipoToken.PALAVRA_RESERVADA_TRUE);
        first.add(TipoToken.PALAVRA_RESERVADA_PRINT);
        first.add(TipoToken.PALAVRA_RESERVADA_RETURN);
        first.add(TipoToken.PALAVRA_RESERVADA_SCAN);
        first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        first.add(TipoToken.PALAVRA_RESERVADA_VAR);
        first.add(TipoToken.PALAVRA_RESERVADA_WHILE);
        
        first.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        
    }

    @Override
    protected void gerarFollow() {
        // TODO Auto-generated method stub
        //--, !, ( , ++, CadeiaDeCaracteres, Digitos, false, identificador, print, return , scan, struct, true, typdef,
        //var, while, }

        follow.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
        follow.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        follow.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        follow.add(TipoToken.CADEIA_CARACTERES);
        follow.add(TipoToken.NUMERO);
        follow.add(TipoToken.IDENTIFICADOR);
        follow.add(TipoToken.PALAVRA_RESERVADA_FALSE);
        follow.add(TipoToken.PALAVRA_RESERVADA_TRUE);
        follow.add(TipoToken.PALAVRA_RESERVADA_PRINT);
        follow.add(TipoToken.PALAVRA_RESERVADA_RETURN);
        follow.add(TipoToken.PALAVRA_RESERVADA_SCAN);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        follow.add(TipoToken.PALAVRA_RESERVADA_VAR);
        follow.add(TipoToken.PALAVRA_RESERVADA_WHILE);
        follow.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
        follow.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);

    }

}

