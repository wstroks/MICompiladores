/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatos;

import lexico.Buffer;
import lexico.TipoToken;
import lexico.Token;

public class AutomatoOperadorRelacional extends Automato {

	public AutomatoOperadorRelacional(Buffer buffer) {
		super(buffer);
	}

	@SuppressWarnings("static-access")
	@Override
	public Token executar() {

		int estado = 0;
		//char c = buffer.proximoCaractere();
		while (!buffer.fimCodigo()) {
			char c = buffer.lookAhead();
			switch (estado) {
			case 0:
				System.out.println("estado 0: " + c);
				if (c == '!') {
					if (buffer.getTamanhoCodigo() == 1) {
						return getToken(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
					}
					//c = buffer.proximoCaractere();
					consumirCaractere();
					estado = 1;
				} else if (c == '>') {
					if (buffer.getTamanhoCodigo() == 1) {
						return getToken(TipoToken.OPERADOR_RELACIONAL_MAIOR_QUE);
					}
					//c = buffer.proximoCaractere();
					consumirCaractere();
					estado = 2;

				} else if (c == '<') {
					if (buffer.getTamanhoCodigo() == 1) {
						return getToken(TipoToken.OPERADOR_RELACIONAL_MENOR_QUE);
					}
					//c = buffer.proximoCaractere();
					consumirCaractere();
					estado = 3;
				} else if (c == '=') {
					if (buffer.getTamanhoCodigo() == 1) {
						return getToken(TipoToken.OPERADOR_RELACIONAL_ATRIBUICAO);
					}
					//c = buffer.proximoCaractere();
					consumirCaractere();
					estado = 4;
				} else {
					estado = -1;

				}

				break;
			case 1:
				System.out.println("estado 1: " + c);
				if (c == '=') {
					return getToken(TipoToken.OPERADOR_RELACIONAL_DIFERENTE);
				} else {
					return getToken(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
				}

			case 2:
				System.out.println("estado 2: " + c);
				if (c == '=') {
					return getToken(TipoToken.OPERADOR_RELACIONAL_MAIOR_IGUAL_QUE);
				} else {
					return getToken(TipoToken.OPERADOR_RELACIONAL_MAIOR_QUE);
				}

			case 3:
				System.out.println("estado 3: " + c);
				if (c == '=') {
					return getToken(TipoToken.OPERADOR_RELACIONAL_MENOR_IGUAL_QUE);
				} else {
					return getToken(TipoToken.OPERADOR_RELACIONAL_MENOR_QUE);
				}
			case 4:
				System.out.println("estado 3: " + c);
				if (c == '=') {
					return getToken(TipoToken.OPERADOR_RELACIONAL_IGUAL);
				} else {
					return getToken(TipoToken.OPERADOR_RELACIONAL_ATRIBUICAO);
				}

			default:
				System.out.println("estado default: " + c);
				return getToken(TipoToken.INDEFINIDO);
			}

		}

		return getToken(TipoToken.INDEFINIDO);

	}

}