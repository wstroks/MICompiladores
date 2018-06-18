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
public class ParametrosAux extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new ParametrosAux();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        System.out.println("Analisando <FuncID>");

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if(!consumir(gerenciadorToken, TipoToken.DELIMITADOR_VIRGULA)){
				return false;
	    }
			
            else if (!Parametros.getInstancia().analisar(gerenciadorToken)) {
                return false;
            }
            else if (gerenciadorToken.eof()) {
                return true;
            }

            return true;

        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        //{ E, ‘,’}
        first.add(TipoToken.DELIMITADOR_VIRGULA);
        
        first.add(TipoToken.EOF);
    }

    @Override
    protected void gerarFollow() {
        //{ ) }
       follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
    }

}