package automatos;

import lexico.TipoToken;
import lexico.Token;

public abstract class Automato {
	

	/**
	 * Executa o automato e retorna o token encontrado
	 * @return Token
	 */
	public abstract Token executar();
	
	/**
	 * Retorna o tipo do lexama. Caso não seja identificado pelo automato é retornado TipoToken.INDEFINIDO
	 * @param lexema
	 * @return TipoToken
	 */
	public abstract TipoToken setTipoToken(String lexema);

}
