package lexico;

import automatos.Automato;
import automatos.AutomatoNumero;

/**
 * 
 * @author Tayane
 *
 */
public class Main {

	public static void main(String[] args) {
		      
		try {

			Buffer buffer = new Buffer("arquivos/entrada.txt");
			Lexico analisador = new Lexico(buffer);
			analisador.run();
			
//			Automato numero = new AutomatoNumero(buffer);
//			Token token = numero.executar();
//			System.out.println("lexema: " + token.getLexema());
//			System.out.println("posicao caractere: " + buffer.getCaractereAtual());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	      
	}

}
