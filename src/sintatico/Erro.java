/**
 * 
 */
package sintatico;

import lexico.TipoToken;

/**
 * @author Tayane
 *
 */
public class Erro {
	
	protected TipoToken tokenEsperado;
	protected TipoToken tokenEncontrado;
	protected TipoToken tokenAnterior;
	protected int linha = 0;
	protected int posicao = 0 ;
	
	public Erro(TipoToken tokenEsperado, TipoToken tokenEncontrado, TipoToken tokenAnterior, int linha, int posicao) {
		this.tokenEsperado = tokenEsperado;
		this.tokenEncontrado = tokenEncontrado;
		this.tokenAnterior = tokenAnterior;
		this.linha = linha;
		this.posicao = posicao;
	}

	public TipoToken getTokenEsperado() {
		return tokenEsperado;
	}

	public void setTokenEsperado(TipoToken tokenEsperado) {
		this.tokenEsperado = tokenEsperado;
	}

	public TipoToken getTokenEncontrado() {
		return tokenEncontrado;
	}

	public void setTokenEncontrado(TipoToken tokenEncontrado) {
		this.tokenEncontrado = tokenEncontrado;
	}

	public TipoToken getTokenAnterior() {
		return tokenAnterior;
	}

	public void setTokenAnterior(TipoToken tokenAnterior) {
		this.tokenAnterior = tokenAnterior;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	
}
