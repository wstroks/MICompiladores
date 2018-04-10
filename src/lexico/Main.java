package lexico;


import automatos.AutomatoCadeiaCaracteres;

public class Main {

	public static void main(String[] args) {
		      
		try {

			Buffer buffer = new Buffer("arquivos/entrada.txt");
			//buffer.printCodigo();
			AutomatoCadeiaCaracteres automato = new AutomatoCadeiaCaracteres(buffer);
			Token token = automato.executar();
			System.out.println(token.getTipoToken());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	      
	}

}
