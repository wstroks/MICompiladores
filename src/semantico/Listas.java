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
public class Listas {
    
    
    public String nome;
    public Token tipo;
    public String foiDeclaradocomo;
    private String escopo = "";
    
    public Listas(){
    	
    }
    
    public Listas(Token token, String tipoVariavel){
    	this.nome = token.getLexema();
    	tipo = token;
    	foiDeclaradocomo = tipoVariavel;
    }
    
    public String getString(){
    	String string = "";
    	if(nome != null){
    		string += nome;
    	}
    	
    	if(foiDeclaradocomo != null && !foiDeclaradocomo.isEmpty()){
    		string += " (" + foiDeclaradocomo + ")";
    	}
    	
    	return string;
    }
    
    public boolean isDeclarado(){
    	return !foiDeclaradocomo.isEmpty();
    }

	public String getEscopo() {
		return escopo;
	}

	public void setEscopo(String escopo) {
		this.escopo = escopo;
	}
	
	public boolean isEscopoGlobal(){
		return escopo.isEmpty();
	}

}
