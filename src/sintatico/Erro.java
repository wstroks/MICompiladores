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
	
	protected ArrayList<TipoToken> tipoTokensEsperados;
	protected Token tokenEncontrado;
	protected Token tokenAnterior;
	
	public Erro(ArrayList<TipoToken> tipoTokensEsperados, Token tokenEncontrado, Token tokenAnterior) {
		this.tipoTokensEsperados = tipoTokensEsperados;
		this.tokenEncontrado = tokenEncontrado;
		this.tokenAnterior = tokenAnterior;
	}

	public ArrayList<TipoToken> getTokensEsperados() {
		return tipoTokensEsperados;
	}

	public Token getTokenEncontrado() {
		return tokenEncontrado;
	}

	public Token getTokenAnterior() {
		return tokenAnterior;
	}

	
}
