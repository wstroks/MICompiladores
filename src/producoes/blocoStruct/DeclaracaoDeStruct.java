/**
 *
 */
package producoes.blocoStruct;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class DeclaracaoDeStruct extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new DeclaracaoDeStruct();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (!consumir(gerenciadorToken, TipoToken.PALAVRA_RESERVADA_STRUCT)) {
                return false;
            }
            
            if (!DeclaracaoDeStructAux.getInstancia().analisar(gerenciadorToken)) {
                return false;
            }

            return true;

        }
        
        return false;
        
    }

    @Override
    protected void gerarFirst() {
        // struct
        first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);

    }

    @Override
    protected void gerarFollow() {
        //const, function, procedure, start, struct, typedef, var, $ ,; , [, Identificador
        follow.add(TipoToken.IDENTIFICADOR);
        follow.add(TipoToken.PALAVRA_RESERVADA_CONST);
        follow.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
        follow.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
        follow.add(TipoToken.PALAVRA_RESERVADA_START);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        follow.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        follow.add(TipoToken.PALAVRA_RESERVADA_VAR);
        follow.add(TipoToken.EOF);
        follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
        follow.add(TipoToken.DELIMITADOR_ABRE_COLCHETE);
        follow.add(TipoToken.IDENTIFICADOR);
    }

}
