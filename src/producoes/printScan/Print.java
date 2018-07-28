/**
 *
 */
package producoes.printScan;

import lexico.TipoToken;
import producoes.RegraProducao;
import semantico.TabelaSimbolos;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class Print extends RegraProducao {

    private TabelaSimbolos ts;

    

  
    public static RegraProducao getInstancia() {
        return new Print();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {
            if (verificarToken(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_PRINT, false)) {
                if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_PARENTESES, true)) {
                    if (Saida.getInstancia().analisar(gerenciadorToken)) {
                        if (OutrasSaidas.getInstancia().analisar(gerenciadorToken)) {
                            if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_PARENTESES, true)) {
                                return true;
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
        // { print }
        first.add(TipoToken.PALAVRA_RESERVADA_PRINT);
    }

    @Override
    protected void gerarFollow() {
        // { ; }
        follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
    }

}
