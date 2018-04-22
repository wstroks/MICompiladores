package lexico;

/**
 * 
 * @author Tayane
 *
 */
public class Token {
	
	private TipoToken tipoToken;
	private String lexema;
	private int linha;
	private int posicao;
	private boolean isErroLexico;
	
	public Token(TipoToken tipoToken, String lexema, int linha, int posicao) {
		this.tipoToken = tipoToken;
		this.lexema = lexema;
		this.linha = linha;
		this.posicao = posicao;
	}
	
	public Token(TipoToken tipoToken, String lexema, int linha, int posicao, boolean isErroLexico) {
		this.tipoToken = tipoToken;
		this.lexema = lexema;
		this.linha = linha;
		this.posicao = posicao;
		this.isErroLexico = isErroLexico;
	}
	
	public TipoToken getTipoToken() {
		return tipoToken;
	}
	public void setTipoToken(TipoToken tipoToken) {
		this.tipoToken = tipoToken;
	}
	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
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
	public boolean isErroLexico() {
		return isErroLexico;
	}

	public void setErroLexico(boolean isErroLexico) {
		this.isErroLexico = isErroLexico;
	}

	public String getImpressaoToken(){
		if(isErroLexico){
			String tipo = tipoToken == TipoToken.INDEFINIDO ? "CARACTERE_NAO_RECONHECIDO" : tipoToken.toString();
			return ((linha + 1) + " " + lexema + " " + tipo);
		}
		else{
			return ((linha + 1) + " " + tipoToken + " " + lexema);
		}
	}

	public void print(){
		System.out.println(getImpressaoToken());
	}

}
