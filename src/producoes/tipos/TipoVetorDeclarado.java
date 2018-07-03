/**
 *
 */
package producoes.tipos;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class TipoVetorDeclarado extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new TipoVetorDeclarado();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {
            if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_COLCHETE, false)) {
                if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_COLCHETE, true)) {
                    return true;
                }
            }
        }

        return false;

    }

    @Override
    protected void gerarFirst

    () {
        // [
        first.add(TipoToken.DELIMITADOR_ABRE_COLCHETE);
    }

    @Override
    protected void gerarFollow

    () {
        // [, Identificador
        follow.add(TipoToken.DELIMITADOR_ABRE_COLCHETE);
        follow.add(TipoToken.IDENTIFICADOR);
    }

}
