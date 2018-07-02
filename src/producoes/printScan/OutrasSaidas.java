/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.printScan;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class OutrasSaidas extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new OutrasSaidas();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {
            
		if(verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_VIRGULA)){
			if (Saida.getInstancia().analisar(gerenciadorToken)) {
				if (OutrasSaidas.getInstancia().analisar(gerenciadorToken)) {
					return true;
				}
			}
		}
		else if (isFollow(gerenciadorToken.getTipoTokenAtual())) {
			return true;
		}

		return false;
	}

	@Override
	protected void gerarFirst() {
		// { E, ‘,’ }
		first.add(TipoToken.DELIMITADOR_VIRGULA);
		first.add(TipoToken.EOF);

	}

    @Override
    protected void gerarFollow() {
        // { )}
        follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
    }

}
