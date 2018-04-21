/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatos;

import lexico.Buffer;
import lexico.TipoToken;
import lexico.Token;

/**
 *
 * @author wstro
 */
public class AutomatoNumero extends Automato {

	public AutomatoNumero(Buffer buffer) {
		super(buffer);
	}

	@SuppressWarnings("static-access")
	@Override
	public Token executar() {

		//System.out.println("Automato Numero");
		lexema = "";
		int estado = 0;
		int contador = 0;
		int contadorIncremento = 0;
		String soma = "";

		while (!buffer.fimCodigo()) {
			char c = buffer.lookAhead();
			switch (estado) {
			case 0:
				// System.out.println("estado 0: " + c);
				if (c == '-') {
					consumirCaractere();
					soma += c;
					if (buffer.fimCodigo() == true) {
						return getToken(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO);
					}
					if (buffer.getTamanhoCodigo() == 1) {
						return getToken(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO);
					}

					estado = 1;

				} else if (this.isDigito(c)) {
					consumirCaractere();
					soma += c;
					if (buffer.fimCodigo() == true) {
						return getToken(TipoToken.NUMERO);
					}
					if (buffer.getTamanhoCodigo() == 1) {
						return getToken(TipoToken.NUMERO);

					}
					estado = 3;
				} else {
					estado = -1;
				}
				break;
			case 1:
				consumirCaractere();
				// System.out.println("asdas " + c);
				if (c == ' ') {
					contador++;
					estado = 2;
				} else if (c == '-') {
					return getToken(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
				} else if (this.isDigito(c)) {
					soma += c;
					estado = 3;

				} else {
					buffer.setPosicaoAtual(buffer.getPosicaoAtual() - 1);
					return new Token(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO, "-", buffer.getLinhaAtual(),
							buffer.getPosicaoAtual());
				}
				break;

			case 2:
				consumirCaractere();
				if (buffer.fimCodigo() == true) {
					soma += c;
					return new Token(TipoToken.NUMERO, soma, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
				} else if (this.isDigito(c)) {
					soma += c;
					// System.out.println("numero " + c);
					estado = 3;
				} else if (c == ' ') {
					contador++;
					estado = 2;
				} else {
					buffer.setPosicaoAtual(buffer.getPosicaoAtual() - contador);
					return new Token(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO, "-", buffer.getLinhaAtual(),
							buffer.getPosicaoAtual());
				}
				break;

			case 3:

				consumirCaractere();

				// System.out.println("3");
				if (buffer.fimCodigo() == true) {
					soma += c;
					if (!this.isDigito(c)) {
						buffer.setPosicaoAtual(buffer.getPosicaoAtual() - 1);
						return new Token(TipoToken.NUMERO, soma, buffer.getLinhaAtual(), buffer.getPosicaoAtual());

					}

					return new Token(TipoToken.NUMERO, soma, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
				} else if (this.isDigito(c)) {
					// consumirCaractere();
					soma += c;
					// System.out.println("digito " + c);
					estado = 3;
				} else if (c == '.') {
					soma += c;
					// System.out.println("digito " + c);
					estado = 4;

				} else {

					buffer.setPosicaoAtual(buffer.getPosicaoAtual() - 1);
					return new Token(TipoToken.NUMERO, soma, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
				}
				break;

			case 4:
				consumirCaractere();

				// System.out.println("asdasdasd "+c);
				if (buffer.fimCodigo() == true) {

					if (!this.isDigito(c)) {
						buffer.setPosicaoAtual(buffer.getPosicaoAtual() - 1);
						return new Token(TipoToken.NUMERO, soma, buffer.getLinhaAtual(), buffer.getPosicaoAtual());

					}
					soma += c;

					return new Token(TipoToken.NUMERO, soma, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
				} else if (c == '.') {
					// System.out.println("tt "+c);
					buffer.setPosicaoAtual(buffer.getPosicaoAtual() - 1);
					return new Token(TipoToken.NUMERO, soma, buffer.getLinhaAtual(), buffer.getPosicaoAtual());

				} else if (this.isDigito(c)) {
					// consumirCaractere();
					soma += c;
					// System.out.println("digito " + c);
					estado = 4;
				} else {
					// System.out.println("x "+c);
					buffer.setPosicaoAtual(buffer.getPosicaoAtual() - 1);
					return new Token(TipoToken.NUMERO, soma, buffer.getLinhaAtual(), buffer.getPosicaoAtual());
				}
				break;

			default:
				// System.out.println("estado default: " + c);
				return getToken(TipoToken.NUMERO);
			}

		}

		return getToken(TipoToken.INDEFINIDO);

	}

}
