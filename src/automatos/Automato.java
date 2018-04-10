package automatos;

import lexico.Buffer;
import lexico.TipoToken;
import lexico.Token;

public abstract class Automato {
	
	protected Buffer buffer;
	
	public Automato(Buffer buffer){
		this.buffer = buffer;
	}

	/**
	 * Executa o automato e retorna o token encontrado
	 * @return Token
	 */
	public abstract Token executar();
	
}
