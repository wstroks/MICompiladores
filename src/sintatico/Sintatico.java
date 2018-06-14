/**
 * 
 */
package sintatico;

import java.util.List;

import lexico.Token;

/**
 * @author Tayane
 *
 */
public class Sintatico {
	
	protected GerenciadorToken gerenciadorToken;
	
	public void run(){
		OperacaoDeAtribuicao.getInstance().analisar(gerenciadorToken);
	}
	


}
