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
		
		if(Programa.getInstancia().isFirst(gerenciadorToken.getTipoTokenAtual())){
			Programa.getInstancia().analisar(gerenciadorToken);
		}
        else{
        	gerenciadorToken.addErro("<Programa>", Programa.getInstancia().getFirst());
        }
		
		gerenciadorToken.printErros();
		
	}
	


}
