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
public class AutomatoOperadorLogico extends Automato {

	private Token token = null;

	public AutomatoOperadorLogico(Buffer buffer) {
		super(buffer);
	}

	@SuppressWarnings("static-access")
	@Override
	public Token executar() {

		lexema = "";
		int estado = 0;
		int verifica = 0;

		while (!buffer.fimCodigo()) {
			char c = buffer.lookAhead();
			switch (estado) {
			case 0:
				if (c == '!') {
					consumirCaractere();
					if (buffer.fimCodigo() == true) {
						return getToken(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
					}

					if (buffer.getTamanhoCodigo() == 1) {
						return getToken(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
					}
					estado = 3;
				} else if (c == '&') {
					consumirCaractere();
					if (buffer.fimCodigo() == true) {
						return getToken(TipoToken.OPERADOR_LOGICO_MAL_FORMADO);
					}

					else if (buffer.getTamanhoCodigo() == 1) {
						return getToken(TipoToken.OPERADOR_LOGICO_MAL_FORMADO);
					}
					estado = 2;

				} else if (c == '|') {
					consumirCaractere();
					if (buffer.fimCodigo() == true) {
						return getToken(TipoToken.OPERADOR_LOGICO_MAL_FORMADO);
					} else if (buffer.getTamanhoCodigo() == 1) {
						return getToken(TipoToken.OPERADOR_LOGICO_MAL_FORMADO);
					}
					verifica = 2;
					estado = 1;
				} else {
					estado = -1;
				}
				break;
			case 1:
				consumirCaractere();
				if (c == '|') {
					return getToken(TipoToken.OPERADOR_LOGICO_OU);
				} else {
					buffer.setPosicaoAtual(buffer.getPosicaoAtual() - 1);
					return new Token(TipoToken.OPERADOR_LOGICO_MAL_FORMADO, "|", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
				}
			case 2:
				consumirCaractere();
				if (c == '&') {
					return getToken(TipoToken.OPERADOR_LOGICO_E);
				} else {
					buffer.setPosicaoAtual(buffer.getPosicaoAtual() - 1);
					return new Token(TipoToken.OPERADOR_LOGICO_MAL_FORMADO, "&", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
				}
			case 3:
				consumirCaractere();
				if (c == '=') {
					return getToken(TipoToken.OPERADOR_RELACIONAL_DIFERENTE);
				} else {
					int x = buffer.getPosicaoAtual();
					buffer.setPosicaoAtual(x - 1);
					return new Token(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO, "!", buffer.getLinhaAtual(), buffer.getPosicaoAtual());
				}
			default:
				return getToken(TipoToken.INDEFINIDO);
			}
		}

		return getToken(TipoToken.INDEFINIDO);

	}

}
