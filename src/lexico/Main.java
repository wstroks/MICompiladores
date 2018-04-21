package lexico;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Tayane
 *
 */
public class Main {

    

    public static void main(String[] args) {

        try {
           

            File arquivos[];
            File diretorio = new File("entrada");
            arquivos = diretorio.listFiles();
            int i=0;
            
            while(i < arquivos.length){
            	
	            String arquivo="entrada/"+(arquivos[i].getName());
	            System.out.println("Arquivo : "+arquivos[i].getName());
	            System.out.println("-------------------\n");
	            Buffer buffer = new Buffer(arquivo);
	            Lexico analisador = new Lexico(buffer);
	            analisador.run();
	            i++;
	            
	        }      

//			Automato numero = new AutomatoNumero(buffer);
//			Token token = numero.executar();
//			System.out.println("lexema: " + token.getLexema());
//			System.out.println("posicao caractere: " + buffer.getCaractereAtual());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
