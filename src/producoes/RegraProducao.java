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
    	System.out.println("Analisando <" + getNomeClasse() + ">");
        this.gerarFirst();
        this.gerarFollow();
    }
    
    protected String getNomeClasse(){
    	return this.getClass().getSimpleName();
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
    	//System.out.println("\n\n" + tipoToken + " isFirst de " + this.getClass().getSimpleName() + "? " + first.contains(tipoToken));
    	if(first.contains(tipoToken)){
    		System.out.println("\n\n" + tipoToken + " isFirst de " + this.getClass().getSimpleName() + "? " + first.contains(tipoToken));
    	}
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
    
    /**
     * Verifica se o token atual será consumido pela produção 
     * @param gerenciadorToken
     * @param tipoToken
     * @return boolean
     */
    protected boolean verificarToken(GerenciadorToken gerenciadorToken, TipoToken tipoToken){
    	
        if (!gerenciadorToken.eof() && tipoToken == gerenciadorToken.getTokenAtual().getTipoToken()) {
            return true;
        }
        return false;
    	
    }
    
    /**
     * Verifica se o token atual é igual ao token esperado. Se sim, consome o token atual, se não adiciona o token encontrato na lista de erros
     * @param gerenciadorToken
     * @param tokenEsperado
     * @return
     */
    protected boolean consumir(GerenciadorToken gerenciadorToken, TipoToken tokenEsperado){
    	
    	if(verificarToken(gerenciadorToken, tokenEsperado)){
    		System.out.println("**** Consumindo token "+ gerenciadorToken.getTipoTokenAtual() +" na classe " + getNomeClasse() + ". Próximo token: " + gerenciadorToken.getProximoToken().getTipoToken());
    		gerenciadorToken.consumirTokenAtual();
    		return true;
    	}
    	else{
    		System.out.println("--> Erro na classe " + getNomeClasse() + ". Token recebido: " + gerenciadorToken.getTipoTokenAtual() + ". Token esperado: " + tokenEsperado);
    		gerenciadorToken.addErro(tokenEsperado);
    		return false;
    	}
    	
    }

}
