/**
 * 
 */
package sintatico;

import java.util.List;

import lexico.Token;
import producoes.core.Programa;

/**
 * @author Tayane
 *
 */
public class Sintatico {
	
	private List<Token> listaTokens;
	
	public Sintatico(List<Token> listaTokens){
		this.listaTokens = listaTokens;
	}
	
	public void run(){
		
		System.out.println("\n===================== Análise sintática ===================");
		
		GerenciadorToken gerenciadorToken = new GerenciadorToken(listaTokens);
		
		//while(!gerenciadorToken.eof()){
			Programa.getInstancia().analisar(gerenciadorToken);
			//System.out.print("Qtd de tokens restantes: " + gerenciadorToken.getQtdTokensRestantes());
		//}
		
		gerenciadorToken.printErros();
		
	}
	


}
