/**
 *
 */
package producoes.blocoConstante;

import lexico.TipoToken;
import producoes.RegraProducao;
import semantico.TabelaSimbolos;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class DeclaracaoDeConst extends RegraProducao {

    /**
	 * @param ts
	 */
	public DeclaracaoDeConst(TabelaSimbolos ts) {
		super(ts);
	}

	public static RegraProducao getInstancia(TabelaSimbolos ts) {
        return new DeclaracaoDeConst(ts);
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {
            if (verificarToken(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_CONST, false)) {
                if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_ABRE_CHAVE, true)) {
                    if (DeclaracaoDeConstanteCorpo.getInstancia().analisar(gerenciadorToken)) {
                        if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_FECHA_CHAVE, true)) {
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
        // const
        first.add(TipoToken.PALAVRA_RESERVADA_CONST);
    }

    @Override
    protected void gerarFollow() {
        // const, function, procedure, start, struct, typedef, var, $
        follow.add(TipoToken.EOF);
        follow.add(TipoToken.PALAVRA_RESERVADA_CONST);
        follow.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
        follow.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
        follow.add(TipoToken.PALAVRA_RESERVADA_START);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        follow.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        follow.add(TipoToken.PALAVRA_RESERVADA_VAR);

    }

}
