package lexico;

import automatos.Automato;
import automatos.AutomatoCadeiaCaracteres;
import automatos.AutomatoDelimitador;
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
        private Automato automatoDelimiAutomato;
        public Token token;

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
                
                //Delimitador . { e etc
                automatoDelimiAutomato= new AutomatoDelimitador(buffer);
	}
	
	public void run(){
		
		char c = ' ';
		token = null;
		
		while(!buffer.fimCodigo()){
			c = buffer.lookAhead();
			//System.out.println("caractere a ser analisado: " + c);
			//System.out.println("double look ahead: " + buffer.doubleLookAhead());
			if((Character) c == null){
				break;
			}
			
			if(Buffer.isEspaco(c)){
				buffer.proximoCaractere();
				continue;
			}
			
			if(c == '/' && (buffer.doubleLookAhead() == '/' || buffer.doubleLookAhead() == '*')){
				buffer.pularComentario();
			}
			
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
			}//|| c == '-'){
			else if(Automato.isDigito(c) || c=='-' ){
				token = automatoNumero.executar();
				if(verficarToken(token)){
					continue;
				}
			}
			else{
				//os automatos abaixo estão bugados
				
				token = automatoOpAritimetico.executar();
				if(verficarToken(token)){
					continue;
				}
				
				token = automatoOpLogico.executar();
				if(verficarToken(token)){
					continue;
				}

				token = automatoOpRelacional.executar();
				if(verficarToken(token)){
					continue;
				
                                }
                                
                                token = automatoDelimiAutomato.executar();
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
			System.out.println("indefinido");
		}
		else{
			token.print();
		}
		return tokenReconhecido;
	}

}
