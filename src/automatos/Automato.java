package automatos;

import lexico.Buffer;
import lexico.TipoToken;
import lexico.Token;

/**
 * 
 * @author Tayane
 *
 */
public abstract class Automato {
	
	protected Buffer buffer;
	protected String lexema = "";
	
	public Automato(Buffer buffer){
		this.buffer = buffer;
	}
	
	/**
	 * Executa o automato e retorna o token encontrado
	 * @return Token
	 */
	public abstract Token executar();
	
    public static boolean isLetra(char c){
        c = Character.toLowerCase(c);
        return (c == 'a' ||
                c == 'b' ||
                c == 'c' ||
                c == 'd' ||
                c == 'e' ||
                c == 'f' ||
                c == 'g' ||
                c == 'h' ||
                c == 'i' ||
                c == 'j' ||
                c == 'k' ||
                c == 'l' ||
                c == 'm' ||
                c == 'n' ||
                c == 'o' ||
                c == 'p' ||
                c == 'q' ||
                c == 'r' ||
                c == 's' ||
                c == 't' ||
                c == 'u' ||
                c == 'v' ||
                c == 'w' ||
                c == 'x' ||
                c == 'y' ||
                c == 'z' );
    }
    
    public static boolean isDigito(char c){
        return (c == '0' ||
                c == '1' ||
                c == '2' ||
                c == '3' ||
                c == '4' ||
                c == '5' ||
                c == '6' ||
                c == '7' ||
                c == '8' ||
                c == '9' );
    }
    
    public static boolean isSimbolo(Character c){
    	int ascii = c;
    	return (ascii >= 32 && ascii <= 126 && ascii != 34);
    }
    
    public static boolean isBarraInvertida(Character c){
    	return (int) c == 92;
    }
    
    protected void consumirCaractere(){
    	char c = buffer.proximoCaractere();
    	lexema += c;
    }
    
    /**
     * Remove o último caractere do lexema
     */
    protected void goBackLexema(){
    	lexema = (lexema != null && lexema.length() != 0) ? lexema.substring(0, lexema.length() - 1): lexema;
    }
    
    protected Token getToken(TipoToken tipo){
    	if(tipo == TipoToken.NUMERO){
    		lexema = lexema.replace("\n", "");
    		lexema = lexema.replace("\r", "");
    		lexema = lexema.replace("\t", "");
    	}
    	return new Token(tipo, lexema, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
    }
	
}
