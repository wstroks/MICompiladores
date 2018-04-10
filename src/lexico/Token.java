package lexico;

public class Token {
	
	private TipoToken tipoToken;
	private String lexema;
	private int linha;
	private int posicao;
	
	public Token(TipoToken tipoToken, String lexema, int linha, int posicao) {
		this.tipoToken = tipoToken;
		this.lexema = lexema;
		this.linha = linha;
		this.posicao = posicao;
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

}
