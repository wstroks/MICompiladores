package lexico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	
	public static void main(String[] args) {
	      
	      try {
		      
	    	BufferedReader r = new BufferedReader(new FileReader(new File("arquivos/entrada.txt")));
			String line = null;
			
			while((line = r.readLine()) != null) {
				line = line.trim();
				
				System.out.println(line);
				Pattern p = Pattern.compile("^[\"][a-zA-Z0-9_\\s[\\x20-\\x7E][\"]&&[^\\x22]]*[\"]$");
				Matcher m = p.matcher(line);
				 
				if(m.matches()){
					System.out.println("É uma cadeia de caracteres");
				}
				else{
					System.out.println("Não é uma cadeia de caracteres");
				}
			}
			
			r.close();
			

	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }
		 

	}

}
