/**
 *
 */
package producoes.declaracaoFuncaoProcedimento;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.blocoVariaveis.ExpressaoIdentificadoresVar;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class DeclaracaoDeFuncao extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new DeclaracaoDeFuncao();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {
            if (verificarToken(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_FUNCTION, false)) {
            	gerenciadorToken.ts.clearBufferFuncao();
                if (FuncID.getInstancia().analisar(gerenciadorToken)) {
                	gerenciadorToken.ts.bufferFuncao.setNome(gerenciadorToken.getAnteriorToken().getLexema());
                    if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_PARENTESES, true)) {
                        if (FuncaoProcedimentoFim.getInstancia().analisar(gerenciadorToken)) {
                        	gerenciadorToken.ts.addFuncao();
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        // { function }
        first.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
    }

    @Override
    protected void gerarFollow() {
        // { const, function, procedure, start, struct, typedef, var, $ }
        follow.add(TipoToken.PALAVRA_RESERVADA_CONST);
        follow.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
        follow.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
        follow.add(TipoToken.PALAVRA_RESERVADA_START);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        follow.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        follow.add(TipoToken.PALAVRA_RESERVADA_VAR);
        follow.add(TipoToken.EOF);
    }

}
