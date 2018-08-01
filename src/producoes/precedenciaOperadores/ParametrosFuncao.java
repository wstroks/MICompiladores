/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.precedenciaOperadores;

import lexico.TipoToken;
import lexico.Token;
import producoes.RegraProducao;
import semantico.Listas;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class ParametrosFuncao extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new ParametrosFuncao();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (Expressao.getInstancia().analisar(gerenciadorToken)) {
            Token token = gerenciadorToken.getAnteriorDeterminaToken(1);
            
            if(gerenciadorToken.ts.bufferFuncaoProcedimento != null && !gerenciadorToken.ts.bufferFuncaoProcedimento.getNome().isEmpty()){//declaracao
                gerenciadorToken.ts.bufferFuncaoProcedimento.addParametro(new Listas(token, gerenciadorToken.ts.getTipoVariavel(token.getLexema())));
            }
            else{//chamada
                gerenciadorToken.ts.bufferChamadaFuncaoProcedimento.addParametro(new Listas(token, gerenciadorToken.ts.getTipoVariavel(token.getLexema())));
            }
            if (ParametrosFuncaoAux.getInstancia().analisar(gerenciadorToken)) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        //{!, ++, --, Identificador, (, Digitos, CadeiraDeCaracteres, true, false}
        first.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
        first.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        first.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
        first.add(TipoToken.IDENTIFICADOR);
        first.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        first.add(TipoToken.NUMERO);
        first.add(TipoToken.CADEIA_CARACTERES);
        first.add(TipoToken.PALAVRA_RESERVADA_TRUE);
        first.add(TipoToken.PALAVRA_RESERVADA_FALSE);
    }

    @Override
    protected void gerarFollow() {
        //{ )}
        follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
    }

}
