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

            if (!Tipo.getInstancia().analisar(gerenciadorToken)) {
                return false;
            }
            
            if (!consumir(gerenciadorToken, TipoToken.IDENTIFICADOR)) {
                return false;
            }

            return true;

        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        //{ ( }
        first.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
    }

    @Override
    protected void gerarFollow() {
        //{ bool, float, identificador,int, string, struct }
        follow.add(TipoToken.PALAVRA_RESERVADA_BOOL);
        follow.add(TipoToken.PALAVRA_RESERVADA_FLOAT);
        follow.add(TipoToken.IDENTIFICADOR);
        follow.add(TipoToken.PALAVRA_RESERVADA_INT);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRING);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
    }

}
