package lexico;

import java.util.ArrayList;
import java.util.List;
import automatos.Automato;
import automatos.AutomatoCadeiaCaracteres;
import automatos.AutomatoDelimitador;
import automatos.AutomatoIdentificador;
import automatos.AutomatoNumero;
import automatos.AutomatoOperadorAritmetico;
import automatos.AutomatoOperadorLogico;
import automatos.AutomatoOperadorRelacional;

/**
 * 
 * @author Tayane
 *
 */
public class Lexico {

	private Buffer buffer;
	private Automato automatoIdentificador;
	private Automato automatoCadeiaCaracteres;
	private Automato automatoNumero;
	private Automato automatoOpAritimetico;
	private Automato automatoOpLogico;
	private Automato automatoOpRelacional;
	private Automato automatoDelimiAutomato;
	
	private List<Token> listaTokens;
	private List<Token> listaErros;

	public Lexico(Buffer buffer) {
		this.buffer = buffer;
		listaTokens = new ArrayList<Token>();
		listaErros = new ArrayList<Token>();
		instanciarAutomatos();
	}

	private void instanciarAutomatos() {
		automatoIdentificador = new AutomatoIdentificador(buffer);
		automatoCadeiaCaracteres = new AutomatoCadeiaCaracteres(buffer);
		automatoNumero = new AutomatoNumero(buffer);

		// Operadores
		automatoOpAritimetico = new AutomatoOperadorAritmetico(buffer);
		automatoOpLogico = new AutomatoOperadorLogico(buffer);
		automatoOpRelacional = new AutomatoOperadorRelacional(buffer);

		// Delimitador . { e etc
		automatoDelimiAutomato = new AutomatoDelimitador(buffer);
	}

	public void run() {

		char c = ' ';
		Token token = null;

		while (!buffer.fimCodigo()) {
			c = buffer.lookAhead();
			//System.out.println("caractere a ser analisado: " + c);
			if ((Character) c == null) {
				break;
			}

			if (Buffer.isEspaco(c)) {
				buffer.proximoCaractere();
				continue;
			}

			if (c == '/' && (buffer.doubleLookAhead() == '/' || buffer.doubleLookAhead() == '*')) {	
				if(verficarToken(pularComentario())){
					continue;
				}
			}

			if (Automato.isLetra(c)) {
				token = automatoIdentificador.executar();
				if (verficarToken(token)) {
					continue;
				}
			} else if (c == '"') {
				token = automatoCadeiaCaracteres.executar();
				if (verficarToken(token)) {
					continue;
				}
			} // || c == '-'){
			else if (Automato.isDigito(c) || c == '-') {
				token = automatoNumero.executar();
				if (verficarToken(token)) {
					continue;
				}
			} 
			else {
				// os automatos abaixo estão bugados

				token = automatoOpAritimetico.executar();
				if (verficarToken(token)) {
					continue;
				}

				token = automatoOpLogico.executar();
				if (verficarToken(token)) {
					continue;
				}

				token = automatoOpRelacional.executar();
				if (verficarToken(token)) {
					continue;

				}

				token = automatoDelimiAutomato.executar();
				if (verficarToken(token)) {
					continue;
				}
				
				if(!buffer.fimCodigo()){
					listaErros.add(getTokenErro(Character.toString(c)));
					buffer.proximoCaractere();
				}
				
			}

		}
		
		listaErros.addAll(buffer.getListaErrosComentario());
		printTokens();

	}
	
	private Token pularComentario(){
		
		int estado = 0;
		String comentario = "";
		
		while(!buffer.fimCodigo()){
			char c = buffer.lookAhead();
			switch (estado) {
				case 0:
					//System.out.println("estado 0: " + c);
					if(c == '/'){
						estado = 1;
						comentario += c;
						buffer.proximoCaractere();
					}
					else{
						estado = -1;
					}
					break;
				case 1:
					//System.out.println("estado 1: " + c);
					if(c == '/'){
						estado = 2;
						comentario += c;
						buffer.proximoCaractere();
					}
					else if(c == '*'){
						estado = 3;
						comentario += c;
						buffer.proximoCaractere();
					}
					else{
						estado = -1;
					}
					break;
				case 2:
					//System.out.println("estado 2: " + c);
					if(buffer.lookAhead() == '\n'){ //fim do comentario de linha
						return new Token(TipoToken.COMENTARIO_LINHA, comentario, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
					}
					else if(buffer.isUltimoCaractere()){
						comentario += c;
						buffer.proximoCaractere();
						return new Token(TipoToken.COMENTARIO_LINHA, comentario, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
					}
					else{
						comentario += c;
						buffer.proximoCaractere();
					}
					break;
				case 3:
					//System.out.println("estado 3: " + c);
					comentario += c;
					if(c == '*'){
						estado = 4;
						buffer.proximoCaractere();
					}
					else if(buffer.isUltimoCaractere()){
						buffer.proximoCaractere();
						return new Token(TipoToken.COMENTARIO_MAL_FORMADO, comentario, buffer.getLinhaAtual(), buffer.getPosicaoAtual(), true);
					}
					else{		
						buffer.proximoCaractere();
					}
					break;
				case 4:
					//System.out.println("estado 4: " + c);
					comentario += c;
					if(c == '/'){ //fim do comentario de bloco
						return new Token(TipoToken.COMENTARIO_BLOCO, comentario, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
					}
					else{
						estado = 3;
						buffer.proximoCaractere();
					}
					break;
	
				default:
					//System.out.println("default: " + c);
					buffer.proximoCaractere();
					return new Token(TipoToken.INDEFINIDO, comentario, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
			}
		}
		
		return new Token(TipoToken.INDEFINIDO, comentario, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
	}

	private boolean verficarToken(Token token) {
		boolean tokenReconhecido = true;
		TipoToken tipoToken = token.getTipoToken();
		if (tipoToken.equals(TipoToken.INDEFINIDO)) {
			tokenReconhecido = false;
			//System.out.println("indefinido");
		} else {
			if(tipoToken.equals(TipoToken.CADEIA_CARACTERES_MAL_FORMADA) || tipoToken.equals(TipoToken.COMENTARIO_MAL_FORMADO)){
				listaErros.add(token);
			}
			else{
				listaTokens.add(token);
			}
			//token.print();
		}
		return tokenReconhecido;
	}
	
	private Token getTokenErro(String c){
		return new Token(TipoToken.INDEFINIDO, c, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
	}
	
	private void printTokens(){
		for (Token token : listaTokens) {
			token.print();
		}
		System.out.println("\n ------- ERROS LÉXICOS -----------");
		for (Token token : listaErros) {
			token.print();
		}
	}

}
