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
           
        	//testarArquivosDiretorio("entrada");
        	testarArquivo("entrada/testes-tay.txt");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
        
    private static void testarArquivosDiretorio(String nomeDiretorio) throws FileNotFoundException{
    	
        File arquivos[];
        File diretorio = new File(nomeDiretorio);
        arquivos = diretorio.listFiles();
        int i=0;
        
        while(i < arquivos.length){
        	testarArquivo(nomeDiretorio+"/"+(arquivos[i].getName()));
        	i++;
        } 
    	
    }
    
    private static void testarArquivo(String nomeArquivo) throws FileNotFoundException{
    	
        //System.out.println("Testando Arquivo : "+ nomeArquivo);
        //System.out.println("-----------------------------------------------\n");
        Buffer buffer = new Buffer(nomeArquivo);
        Lexico analisador = new Lexico(buffer);
        analisador.run();
        
    }

}
