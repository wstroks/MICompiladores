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
public class Extends extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new Extends();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (verificarToken(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_EXTENDS, false)) {
            if (verificarToken(gerenciadorToken, TipoToken.IDENTIFICADOR, true)) {
                return true;
            }
        } 
        else{
			return verificarSimboloVazio(gerenciadorToken, true);
		}
        
        return false;

    }

    @Override
    protected void gerarFirst() {
        // struct
        first.add(TipoToken.PALAVRA_RESERVADA_EXTENDS);
        first.add(TipoToken.EOF);
    }

    @Override
    protected void gerarFollow() {
        // const, function, procedure, start, struct, typedef, var, $ ,; , [,
        // Identificador
        follow.add(TipoToken.DELIMITADOR_ABRE_CHAVE);

    }

}
