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
                int verificar=0;

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
                               //System.out.println("Estado 1: "+c);
			        if(this.isBarraInvertida(c)){
                                   // System.out.println("Estado 2: "+c);
                                    verificar=1;
                                    consumirCaractere();
                                    
                                                               
                                }
                                else if (c == '"') {
					consumirCaractere();
                                        if(buffer.fimCodigo()==true && verificar==1){
                                            return getToken(TipoToken.CADEIA_CARACTERES_MAL_FORMADA);
                                        }
                                       else if(verificar==0) {                                   
					return getToken(TipoToken.CADEIA_CARACTERES);
                                        }else{
                                            verificar=0;
                                            estado=1;
                                        }
				} 
                                else if (this.isLetra(c) || this.isDigito(c) || this.isSimbolo(c)) {
					consumirCaractere();
                                        if(buffer.fimCodigo()==true){
                                            return getToken(TipoToken.CADEIA_CARACTERES_MAL_FORMADA);
                                        }
                                        verificar=0;
				} else if (this.isBarraInvertida(c)) {
					estado = 2;
                                        verificar=0;
					consumirCaractere();
				} else {
					if(c != '\n'){
						consumirCaractere();
                                                if(buffer.fimCodigo()==true && verificar==1){
                                                    return getToken(TipoToken.CADEIA_CARACTERES_MAL_FORMADA);
                                        }
                                                verificar=0;
					}
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
