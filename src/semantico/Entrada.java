/**
 * 
 */
package semantico;

import lexico.Token;

/**
 * @author Tayane
 *
 * A ideia é que essa classe represente uma entrada na tabela de símbolos. Outras classes poderão extender ela a depender das necessidades do que se deseja armazenar. 
 * Na prática, é como se a a quantidade de colunas da tabela fosse variável. A ideia é que a gente não tenha problemas para armazenar as informações necessárias.
 */
public class Entrada {
	
	protected Token token;

	public Entrada(Token token) {
		this.token = token;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}
	
}
