/**
 *
 */
package producoes.printScan;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class Print extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new Print();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (consumir(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_PRINT)) {
                if (consumir(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_PARENTESES)) {
                    if (Saida.getInstancia().analisar(gerenciadorToken)) {
                        if (OutrasSaidas.getInstancia().analisar(gerenciadorToken)) {

                            if (consumir(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_PARENTESES)) {
                                return true;
                            }
                        }

                    }
                }
            }
        } if (isFollow(gerenciadorToken.getTokenAtual().getTipoToken())) {
            return true;
        }
        //System.out.println("naruto \n\n\n\n");
        return false;
    }

    @Override
    protected void gerarFirst() {
        // { print }
        first.add(TipoToken.PALAVRA_RESERVADA_PRINT);
    }

    @Override
    protected void gerarFollow() {
        // { ; }
        follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
    }

}
