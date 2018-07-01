/**
 * 
 */
package sintatico;

import java.util.ArrayList;

import lexico.TipoToken;
import lexico.Token;

/**
 * @author Tayane
 *
 */
public class Erro {
	
	protected ArrayList<TipoToken> tokensEsperados;
	protected Token tokenEncontrado;
	protected Token tokenAnterior;
	
	public Erro(ArrayList<TipoToken> tokensEsperados, Token tokenEncontrado, Token tokenAnterior) {
		this.tokensEsperados = tokensEsperados;
		this.tokenEncontrado = tokenEncontrado;
		this.tokenAnterior = tokenAnterior;
	}

	public ArrayList<TipoToken> getTokenEsperado() {
		return tokensEsperados;
	}

	public Token getTokenEncontrado() {
		return tokenEncontrado;
	}

	public Token getTokenAnterior() {
		return tokenAnterior;
	}
	
	public String getStringTokensEsperados(){
		String tokens = new String();
		for (int i = 0; i < tokensEsperados.size(); i++) {
			tokens += tokensEsperados.get(i);
			if(i < (tokensEsperados.size() - 1)){
				tokens += ", ";
			}
		}
		return tokens;
	}
	
}
