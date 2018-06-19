/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.blocoStruct;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class DeclaracaoDeStructAux extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new DeclaracaoDeStructAux();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if(consumir(gerenciadorToken, TipoToken.IDENTIFICADOR)) {
            	
                if (!Extends.getInstancia().analisar(gerenciadorToken)) {
                    return false;
                }
               
                if(!consumir(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_CHAVE)) {
                    return false;
                }
                
                if(!DeclaracaoDeStructCorpo.getInstancia().analisar(gerenciadorToken)) {
                    return false;
                }
                
                if(!consumir(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_CHAVE)) {
                    return false;
                }
                
                return true;
                
            }
            else if(Extends.getInstancia().analisar(gerenciadorToken)){
            	
                if(!consumir(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_CHAVE)) {
                    return false;
                }
                
                if(!DeclaracaoDeStructCorpo.getInstancia().analisar(gerenciadorToken)) {
                    return false;
                }
                
                if(!consumir(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_CHAVE)) {
                    return false;
                }
                
                return true;
            	
            }

        }
        
        return false;
        
    }

    @Override
    protected void gerarFirst() {
        // {, extends,identificador
        first.add(TipoToken.IDENTIFICADOR);
        first.add(TipoToken.PALAVRA_RESERVADA_EXTENDS);
        first.add(TipoToken.DELIMITADOR_ABRE_CHAVE);

    }

    @Override
    protected void gerarFollow() {
        //const, function, procedure, start, struct, typedef, var, $ ,; , [, Identificador
        follow.add(TipoToken.IDENTIFICADOR);
        follow.add(TipoToken.PALAVRA_RESERVADA_CONST);
        follow.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
        follow.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
        follow.add(TipoToken.PALAVRA_RESERVADA_START);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        follow.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        follow.add(TipoToken.PALAVRA_RESERVADA_VAR);
        follow.add(TipoToken.EOF);
        follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
        follow.add(TipoToken.DELIMITADOR_ABRE_COLCHETE);
        follow.add(TipoToken.IDENTIFICADOR);
    }

}
