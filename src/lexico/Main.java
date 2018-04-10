package lexico;


import automatos.AutomatoCadeiaCaracteres;

public class Main {

	public static void main(String[] args) {
		      
		try {

			Buffer buffer = new Buffer("arquivos/entrada.txt");
			buffer.printCodigo();
			//AutomatoCadeiaCaracteres automato = new AutomatoCadeiaCaracteres(buffer);
			//automato.executar();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	      
	}

}
