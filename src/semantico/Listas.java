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

}
