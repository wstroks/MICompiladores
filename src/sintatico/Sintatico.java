/**
 * 
 */
package sintatico;

import java.io.IOException;
import java.util.List;

import lexico.Token;
import producoes.core.Programa;
import semantico.TabelaSimbolos;

/**
 * @author Tayane
 *
 */
public class Sintatico {

	private List<Token> listaTokens;
	private TabelaSimbolos ts;
        

	public Sintatico(List<Token> listaTokens) {
		this.listaTokens = listaTokens;
		this.ts = new TabelaSimbolos();
                //this.addConst=addConst;
	}

	public void run(String b) throws IOException {

		System.out.println("\n===================== Análise sintática ===================");

		GerenciadorToken gerenciadorToken = new GerenciadorToken(listaTokens, ts);

		if (Programa.getInstancia().isFirst(gerenciadorToken.getTipoTokenAtual())) {
			Programa.getInstancia().analisar(gerenciadorToken);
		} else {
			gerenciadorToken.addErro("<Programa>", Programa.getInstancia().getFirst());
		}

		gerenciadorToken.printErros();

		System.out.println("\n===================== Análise Semantica ===================");

		gerenciadorToken.ts.ImpressaoVariavelErro();
		System.out.println("\n");
		gerenciadorToken.ts.ImpressaoVariavel();
		// gerenciadorToken.printErroToFile(b);

	}

}
