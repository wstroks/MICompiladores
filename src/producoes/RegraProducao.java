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
    	//System.out.println("Analisando <" + getNomeClasse() + ">");
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

    public ArrayList<TipoToken> getFirst() {
		return first;
	}

	public ArrayList<TipoToken> getFollow() {
		return follow;
	}

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
    
    public boolean verificarSimboloVazio(GerenciadorToken gt, boolean addErro){
    	if (isFollow(gt.getTipoTokenAtual())) {
			return true;
		}
    	else{
    		if(addErro){
    			gt.addErro(getNomeClasse(), getFollow());
    		}
    		return false;
    	}
    }
    
    /**
     * Verifica se o token atual será consumido pela produção 
     * @param gerenciadorToken
     * @param tipoToken
     * @return boolean
     */
    protected boolean verificarToken(GerenciadorToken gerenciadorToken, TipoToken tipoToken, boolean addErro){
    	
    	if(tipoToken == gerenciadorToken.getTipoTokenAtual()){
    		consumir(gerenciadorToken, tipoToken);
    		return true;
        }
    	else{
    		if(addErro){
    			gerenciadorToken.addErro(getNomeClasse(), convertTokenToArrayList(tipoToken));
    		}
    		return false;
    	}
    	
    }
    
    /**
     * Verifica se o token atual é igual ao token esperado. Se sim, consome o token atual, se não adiciona o token encontrato na lista de erros
     * @param gerenciadorToken
     * @param tokenEsperado
     * @return
     */
    protected boolean consumir(GerenciadorToken gt, TipoToken tokenEsperado){
    	
		//System.out.println("\n**** Consumindo token "+ gt.getTipoTokenAtual() + " ("  + gt.getTokenAtual().getLexema() + ") na classe " + getNomeClasse() + ". Próximo token: " + gt.getProximoToken().getTipoToken() + "\n");
		gt.consumirTokenAtual();
		return true;
    	
    }
    
    /**
     * Converte um tipo token em um array list. Esse método é último ao adicionar um erro passando um tipo de token específico
     * @param token
     * @return ArrayList<TipoToken> array list contendo 1 único elemento, que é o token passado para o método
     */
    protected ArrayList<TipoToken> convertTokenToArrayList(TipoToken token){
    	ArrayList<TipoToken> lista = new ArrayList<TipoToken>();
    	lista.add(token);
    	return lista;
    }

}
