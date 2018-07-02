/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.estruturasCondicionais;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.declaracaoFuncaoProcedimento.Bloco;
import producoes.precedenciaOperadores.Expressao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class IfThen extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new IfThen();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {
            if (verificarToken(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_IF)) {
                if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_PARENTESES)) {
                    if (Expressao.getInstancia().analisar(gerenciadorToken)) {
                        // System.out.println("fada se\n\n\n");
                        if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_PARENTESES)) {
                            if (verificarToken(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_THEN)) {
                                if (Bloco.getInstancia().analisar(gerenciadorToken)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;

    }

    @Override
    protected void gerarFirst() {
        // IF
        first.add(TipoToken.PALAVRA_RESERVADA_IF);
    }

    @Override
    protected void gerarFollow() {
        // --, !, ( , ++, CadeiaDeCaracter, Digitos, false, identificador,
        // print, return , scan, struct,true, typdef,
        // var,while, }
        follow.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
        follow.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        follow.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
        follow.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);
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
        follow.add(TipoToken.PALAVRA_RESERVADA_ELSE);
    }

}
