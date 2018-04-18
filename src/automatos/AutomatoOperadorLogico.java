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

	public AutomatoOperadorLogico(Buffer buffer) {
		super(buffer);
	}

	@SuppressWarnings("static-access")
	@Override
	public Token executar() {

		int estado = 0;
		int verifica = 0;
		//char c = buffer.proximoCaractere();
		while (!buffer.fimCodigo()) {
			char c = buffer.lookAhead();
			switch (estado) {
			case 0:
				System.out.println("estado 0: " + c);
				if (c == '!') {
					consumirCaractere();
					if (buffer.getTamanhoCodigo() == 1) {
						return getToken(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
					}
					//c = buffer.proximoCaractere();
					consumirCaractere();
					if (c == '=') {
						return getToken(TipoToken.OPERADOR_RELACIONAL_DIFERENTE);
					} else {
						return getToken(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
					}
				} else if (c == '&') {
					consumirCaractere();
					if (buffer.getTamanhoCodigo() == 1) {
						return getToken(TipoToken.INDEFINIDO);
					}
					//c = buffer.proximoCaractere();
					verifica = 1;
					estado = 1;
				} else if (c == '|') {
					consumirCaractere();
					if (buffer.getTamanhoCodigo() == 1) {
						return getToken(TipoToken.INDEFINIDO);
					}
					//c = buffer.proximoCaractere();
					verifica = 2;
					estado = 1;
				}

				else {
					estado = -1;
				}
				break;
			case 1:
				consumirCaractere();
				System.out.println("estado 1: " + c);
				if (c == '&') {
					return getToken(TipoToken.OPERADOR_LOGICO_E);

				} else if (c == '|') {
					return getToken(TipoToken.OPERADOR_LOGICO_OU);
				}

				else {
					if (verifica == 1) {
						System.out.println("Verifiquei o proximo para saber se é erro quando recomeçar tem que começar pelo anterior (Erro &)");
						return getToken(TipoToken.INDEFINIDO);

					} else if (verifica == 2) {
						System.out.println("Verifiquei o proximo para saber se é erro quando recomeçar tem que começar pelo anterior (Erro |)");
						return getToken(TipoToken.INDEFINIDO);

					}
				}

			default:
				System.out.println("estado default: " + c);
				return getToken(TipoToken.INDEFINIDO);
			}
		}

		return getToken(TipoToken.INDEFINIDO);

	}

}