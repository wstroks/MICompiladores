package automatos;

import lexico.Buffer;
import lexico.TipoToken;
import lexico.Token;

/**
 * 
 * @author Tayane
 *
 */
public class AutomatoCadeiaCaracteres extends Automato {

	public AutomatoCadeiaCaracteres(Buffer buffer) {
		super(buffer);
	}

	@SuppressWarnings("static-access")
	@Override
	public Token executar() {

		lexema = "";
		int estado = 0;

		while (!buffer.fimCodigo()) {
			char c = buffer.lookAhead();
			switch (estado) {
			case 0:
				if (c == '"') {
					estado = 1;
					consumirCaractere();
				} else {
					estado = -1;
				}
				break;

			case 1:
				if (c == '"') {
					consumirCaractere();
					return getToken(TipoToken.CADEIA_CARACTERES);
				} else if (this.isLetra(c) || this.isDigito(c) || this.isSimbolo(c)) {
					consumirCaractere();
				} else if (this.isBarraInvertida(c)) {
					estado = 2;
					consumirCaractere();
				} else {
					return getToken(TipoToken.CADEIA_CARACTERES_MAL_FORMADA);
				}
				break;
			case 2:
				if (this.isBarraInvertida(c)) {
					lexema += buffer.proximoCaractere();
				} else if (this.isLetra(c) || this.isDigito(c) || this.isSimbolo(c) || c == '"') {
					estado = 1;
					consumirCaractere();
				} else {
					estado = -1;
				}
				break;

			default:
				return getToken(TipoToken.CADEIA_CARACTERES);
			}

		}

		return getToken(TipoToken.INDEFINIDO);

	}

}
