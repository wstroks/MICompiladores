/**
 * 
 */
package sintatico;

import java.util.List;

import lexico.Token;

/**
 * @author Tayane
 *
 */
public class GerenciadorToken {
	
	private List<Token> listaTokens;
	protected int contTokenAtual = 0;
	
	private GerenciadorToken(List<Token> listaTokens){
		this.listaTokens = listaTokens;
	}
	
    /**
     * Imprime a lista de tokens
     */
    public void printTokens(){
		System.out.println("Tokens: ");
		for (Token token : listaTokens) System.out.println(token);
    }
    
    /**
     * Obtendo o token atual
     * @return o token atual
     */
    public Token getTokenAtual(){
        return listaTokens.get(contTokenAtual);
    }

    /**
     * Consome o token atual
     * @return o token atual
     */
    public Token consumirTokenAtual(){
        return listaTokens.get(contTokenAtual++);
    }

}
