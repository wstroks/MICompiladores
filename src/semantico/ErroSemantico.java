/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantico;

import lexico.Token;

/**
 *
 * @author wstro
 */
public class ErroSemantico {
	
    public Token tipo;
    public String tipoDoErro;
    
    public ErroSemantico(Token token, String mensagem){
    	tipo = token;
    	tipoDoErro = mensagem;
    }
    
    public String getMensagem(){
    	return "Linha " + (tipo.getLinha() + 1) + ": " + tipoDoErro + " \"" + tipo.getLexema() + "\"";
    }
    
}
