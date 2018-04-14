package lexico;


import automatos.AutomatoCadeiaCaracteres;
import automatos.AutomatoLogico;

/**
 * 
 * @author Tayane
 *
 */
public class Main {

	public static void main(String[] args) {
		      
		try {

			Buffer buffer = new Buffer("arquivos/entrada.txt");
			//buffer.printCodigo();
			                 AutomatoLogico automato = new AutomatoLogico(buffer);
			Token token = automato.executar();
			System.out.println(token.getTipoToken());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	      
	}

}
