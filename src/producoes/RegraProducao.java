package producoes;

import java.util.ArrayList;

import lexico.TipoToken;
import sintatico.GerenciadorToken;

/**
*
* @author Tayane
*
*/
public abstract class RegraProducao {
	
    protected ArrayList<TipoToken> first  = new ArrayList<TipoToken>();
    protected ArrayList<TipoToken> follow = new ArrayList<TipoToken>();
    
    protected RegraProducao() {
        this.gerarFirst();
        this.gerarFollow();
    }
    
    /**
     * Realiza a analise sintática para a regra
     * 
     * @return true se a sequência foi reconhecida, falso caso contrário
     */
    public abstract boolean analisar(GerenciadorToken gerenciadorToken);


    /**
     * Gera o conjunto primeiro da regra.
     *
     */
    protected abstract void gerarFirst();

    /**
     * Gera o conjunto seguinte da regra.
     *
     */
    protected abstract void gerarFollow();

    /**
     * Informa se o token esta no conjunto primeiro da regra
     * @param tipoToken um TokenClass
     * @return true se sim, falso caso contrario
     */
    public boolean isFirst(TipoToken tipoToken) {
        return first.contains(tipoToken);
    }

    /**
     * Informa se o token esta no conjunto seguinte da regra
     * @param tipoToken um TokenClass
     * @return true se sim, falso caso contrário
     */
    public boolean isFollow(TipoToken tipoToken) {
        return follow.contains(tipoToken);
    }

}
