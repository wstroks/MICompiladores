package producoes.blocoVariaveis;

import lexico.TipoToken;
import lexico.Token;
import producoes.RegraProducao;
import producoes.printScan.Entrada;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class ExpressaoIdentificadorVar extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new ExpressaoIdentificadorVar();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (verificarToken(gerenciadorToken, TipoToken.IDENTIFICADOR, false)) {
                //System.out.println("Token: "+gerenciadorToken.getAnteriorToken().getLexema().toString()+"\Token              Entrada envia=gerenciadorToken.getAnteriorToken();
                
                gerenciadorToken.ts.addTabelaVariaveis(gerenciadorToken.getAnteriorToken().getLexema(), gerenciadorToken.getAnteriorToken());

                
                if (ExpressaoIdentificadorVarAux.getInstancia().analisar(gerenciadorToken)) {
                    return true;
                }
            }
        }

        return false;

    }

    @Override
    protected void gerarFirst() {
        // Identificador
        first.add(TipoToken.IDENTIFICADOR);
    }

    @Override
    protected void gerarFollow() {
        // ‘,’ , ;
        follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
        follow.add(TipoToken.DELIMITADOR_VIRGULA);
    }

}
