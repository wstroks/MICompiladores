package lexico;

import automatos.Automato;
import automatos.AutomatoCadeiaCaracteres;
import automatos.AutomatoIdentificador;
import automatos.AutomatoNumero;

/**
 * 
 * @author Tayane
 *
 */
public class Lexico {
	
	private Buffer buffer;
	private Automato automatoIdentificador;
	private Automato automatoCadeiaCaracteres;
	private Automato automatoNumero;

	public Lexico(Buffer buffer){
		this.buffer = buffer;
		instanciarAutomatos();
	}
	
	private void instanciarAutomatos(){
		this.automatoIdentificador = new AutomatoIdentificador(buffer);
		this.automatoCadeiaCaracteres = new AutomatoCadeiaCaracteres(buffer);
		this.automatoNumero = new AutomatoNumero(buffer);
	}
	
	public void run(){
		while(!buffer.fimCodigo()){
			char c = buffer.lookAhead();
			//System.out.println("caractere a ser analisado: " + c);
			if((Character) c == null){
				break;
			}
			
			Token token = null;
			
			if(Automato.isLetra(c)){
				token = automatoIdentificador.executar();
				if(verficarToken(token)){
					continue;
				}
			}
			
			if(c == '"'){
				token = automatoCadeiaCaracteres.executar();
				if(verficarToken(token)){
					continue;
				}
			}
			
			if(Automato.isDigito(c) || c == '-'){
				token = automatoNumero.executar();
				if(verficarToken(token)){
					continue;
				}
			}
			
		}
		
	}
	
	private boolean verficarToken(Token token){
		boolean tokenReconhecido = true;
		if(token.getTipoToken().equals(TipoToken.INDEFINIDO)){
			tokenReconhecido = false;
		}
		else{
			token.print();
		}
		return tokenReconhecido;
	}

}
