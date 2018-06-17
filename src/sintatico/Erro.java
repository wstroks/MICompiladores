/**
 * 
 */
package sintatico;

import lexico.TipoToken;
import lexico.Token;

/**
 * @author Tayane
 *
 */
public class Erro {
	
	protected TipoToken tokenEsperado;
	protected Token tokenEncontrado;
	protected Token tokenAnterior;
	
	public Erro(TipoToken tokenEsperado, Token tokenEncontrado, Token tokenAnterior) {
		this.tokenEsperado = tokenEsperado;
		this.tokenEncontrado = tokenEncontrado;
		this.tokenAnterior = tokenAnterior;
	}

	public TipoToken getTokenEsperado() {
		return tokenEsperado;
	}

	public Token getTokenEncontrado() {
		return tokenEncontrado;
	}

	public Token getTokenAnterior() {
		return tokenAnterior;
	}

	
}
