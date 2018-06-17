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
    	System.out.println(listaTokens.get(contTokenAtual).getTipoToken());
    	if(eof()){
    		return getEofToken();
    	}
        return listaTokens.get(contTokenAtual);
    }

    /**
     * Consome o token atual
     * @return o token atual
     */
    public Token consumirTokenAtual(){
        return listaTokens.get(contTokenAtual++);
    }
    
    public void addErro(ArrayList<TipoToken> tokensEsperados){
    	
    	Token tokenAnterior;
    	if(contTokenAtual == 0){
    		tokenAnterior = getEofToken();
    	}
    	else{
    		tokenAnterior = listaTokens.get(contTokenAtual-1);
    	}
    	listaErros.add(new Erro(tokensEsperados, listaTokens.get(contTokenAtual), tokenAnterior));
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
			System.out.println("Token inválido: " + erro.getTokenEncontrado().getLexema());
		}
    	
    }

}
