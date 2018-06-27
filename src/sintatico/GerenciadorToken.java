/**
 * 
 */
package sintatico;

import java.util.ArrayList;
import java.util.List;

import lexico.TipoToken;
import lexico.Token;

/**
 * @author Tayane
 *
 */
public class GerenciadorToken {
	
	private List<Token> listaTokens;
	private ArrayList<Erro> listaErros;
	protected int contTokenAtual = 0;
	
	GerenciadorToken(List<Token> listaTokens){
		this.listaTokens = listaTokens;
		this.listaErros = new ArrayList<Erro>();
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
    	//System.out.println("\n asda " +listaTokens.get(contTokenAtual).getTipoToken());
    	if(eof()){
    		return getEofToken();
    	}
        return listaTokens.get(contTokenAtual);
    }
    
    /**
     * Obtendo o próximo token
     * @return o próximo token
     */
    public Token getProximoToken(){
    	//System.out.println(listaTokens.get(contTokenAtual).getTipoToken());
    	if(eof()){
    		return getEofToken();
    	}
        return listaTokens.get(contTokenAtual+1);
    }

    /**
     * Consome o token atual
     * @return o token atual
     */
    public Token consumirTokenAtual(){
    	System.out.println("Token consumido: " + listaTokens.get(contTokenAtual).getTipoToken());
        return listaTokens.get(contTokenAtual++);
    }
    
    /**
     * Volta um token
     */
    public void goBack(){
    	contTokenAtual--;
    }
    
    public void addErro(TipoToken tokenEsperado){
    	
    	Token tokenAnterior;
    	if(contTokenAtual == 0){
    		tokenAnterior = getEofToken();
    	}
    	else{
    		tokenAnterior = listaTokens.get(contTokenAtual-1);
    	}
    	listaErros.add(new Erro(tokenEsperado, listaTokens.get(contTokenAtual), tokenAnterior));
    }
    
    public boolean eof(){
    	return contTokenAtual == listaTokens.size();
    }
    
    private Token getEofToken(){
    	return new Token(TipoToken.EOF, "$", 0, 0);
    }
    
    public void printErros(){
    	
		System.out.print("\n");
		if(listaErros.isEmpty()){
			System.out.println("Nenhum erro sintático foi encontrado");
		}
    	
    	for (Erro erro : listaErros) {
			System.out.println("Token inválido: " + erro.getTokenEncontrado().getLexema() + ". Era esperado: " + erro.tokenEsperado);
		}
    	
    }

}
