/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.declaracaoFuncaoProcedimento;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class DeclaracaoDeProcedimento extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new DeclaracaoDeProcedimento();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        System.out.println("Analisando <DeclaracaoDeProcedimento>");

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (!consumir(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_PROCEDURE)) {
                return false;
            }

            
            else if (!consumir(gerenciadorToken, TipoToken.IDENTIFICADOR)) {
                return false;
            }

            else if (!consumir(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_PARENTESES)) {
                return false;
            }

            //TODO: <FuncaoProcedimentoFim>
             else if (!FuncaoProcedimentoFim.getInstancia().analisar(gerenciadorToken)) {
                return false;
            }
            return true;

        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        //{ PROCEDURE }
        first.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
    }

    @Override
    protected void gerarFollow() {
        //{ const, function, procedure, start, struct, typedef, var, $ }
        follow.add(TipoToken.PALAVRA_RESERVADA_CONST);
        follow.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
        follow.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
        follow.add(TipoToken.PALAVRA_RESERVADA_START);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        follow.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        follow.add(TipoToken.PALAVRA_RESERVADA_VAR);
        follow.add(TipoToken.EOF);
    }

}
