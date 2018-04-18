package lexico;

import automatos.Automato;
import automatos.AutomatoCadeiaCaracteres;
import automatos.AutomatoIdentificador;
import automatos.AutomatoNumero;
import automatos.AutomatoOperadorAritmetico;
import automatos.AutomatoOperadorLogico;
import automatos.AutomatoOperadorRelacional;

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
	private Automato automatoOpAritimetico;
	private Automato automatoOpLogico;
	private Automato automatoOpRelacional;

	public Lexico(Buffer buffer){
		this.buffer = buffer;
		instanciarAutomatos();
	}
	
	private void instanciarAutomatos(){
		automatoIdentificador = new AutomatoIdentificador(buffer);
		automatoCadeiaCaracteres = new AutomatoCadeiaCaracteres(buffer);
		automatoNumero = new AutomatoNumero(buffer);
		
		//Operadores
		automatoOpAritimetico = new AutomatoOperadorAritmetico(buffer);
		automatoOpLogico = new AutomatoOperadorLogico(buffer);
		automatoOpRelacional = new AutomatoOperadorRelacional(buffer);
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
			else if(c == '"'){
				token = automatoCadeiaCaracteres.executar();
				if(verficarToken(token)){
					continue;
				}
			}
			else if(Automato.isDigito(c) || c == '-'){
				token = automatoNumero.executar();
				if(verficarToken(token)){
					continue;
				}
			}
			else{
				//existe um bug nesse automato
//				token = automatoOpAritimetico.executar();
//				if(verficarToken(token)){
//					continue;
//				}
				
				token = automatoOpLogico.executar();
				if(verficarToken(token)){
					continue;
				}
				
				token = automatoOpRelacional.executar();
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
