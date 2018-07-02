/**
 *
 */
package producoes.core;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class ProgramaAux extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new ProgramaAux();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if(Programa.getInstancia().analisar(gerenciadorToken)){
            return true;
        }
        else{
			return verificarSimboloVazio(gerenciadorToken, true);
		}

    }

    @Override
    protected void gerarFirst() {
        //{ const, function, procedure, start, struct, typedef, var, E }
        first.add(TipoToken.PALAVRA_RESERVADA_CONST);
        first.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
        first.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
        first.add(TipoToken.PALAVRA_RESERVADA_START);
        first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        first.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        first.add(TipoToken.PALAVRA_RESERVADA_VAR);
        first.add(TipoToken.EOF);
    }

    @Override
    protected void gerarFollow() {
        //{ $ }
        follow.add(TipoToken.EOF);
    }

}
