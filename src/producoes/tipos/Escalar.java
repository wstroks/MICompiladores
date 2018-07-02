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
public class Escalar extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new Escalar();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        //System.out.println("Analisando <Escalar>");

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {
            if (verificarToken(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_INT, false)) {
                return true;
            } else if (verificarToken(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_FLOAT, false)) {
                return true;
            } else if (verificarToken(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_BOOL, false)) {
                return true;
            } else if (verificarToken(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_STRING, false)) {
                return true;
            }

        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        // TODO Auto-generated method stub
        // bool, float, int, string
        first.add(TipoToken.PALAVRA_RESERVADA_BOOL);
        first.add(TipoToken.PALAVRA_RESERVADA_FLOAT);
        first.add(TipoToken.PALAVRA_RESERVADA_INT);
        first.add(TipoToken.PALAVRA_RESERVADA_STRING);

    }

    @Override
    protected void gerarFollow() {
        // TODO Auto-generated method stub
        // [, Identificador
        follow.add(TipoToken.DELIMITADOR_ABRE_COLCHETE);
        follow.add(TipoToken.IDENTIFICADOR);

    }

}
