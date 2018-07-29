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
	
    Token tipo;
    String tipoDoErro;
    
    public String getMensagem(){
    	String mensagem = tipoDoErro;
    	if(tipo != null){
    		mensagem += " " + tipo.getLexema();
    	}
    	return mensagem;
    }
    
}
