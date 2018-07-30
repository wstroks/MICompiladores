/**
 *
 */
package producoes.declaracaoFuncaoProcedimento;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.tipos.Tipo;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class FuncID extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new FuncID();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (Tipo.getInstancia().analisar(gerenciadorToken)) {
            	gerenciadorToken.ts.bufferFuncao.setTipoRetorno(gerenciadorToken.getAnteriorToken().getLexema());
                if (verificarToken(gerenciadorToken, TipoToken.IDENTIFICADOR, true)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        // { bool, float, identificador,int, string, struct }
        first.add(TipoToken.PALAVRA_RESERVADA_BOOL);
        first.add(TipoToken.PALAVRA_RESERVADA_FLOAT);
        first.add(TipoToken.IDENTIFICADOR);
        first.add(TipoToken.PALAVRA_RESERVADA_INT);
        first.add(TipoToken.PALAVRA_RESERVADA_STRING);
        first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);

    }

    @Override
    protected void gerarFollow() {

        follow.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
    }

}
