/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.ecomp.uefs;

import java.util.regex.Pattern;



/**
 *
 * @author wstro
 */
public class AutomatoPalavra {


    
    int cont;
    boolean aceito;
    static char [] car;
    
    public static void main(String[] args){
        AutomatoPalavra aut = new AutomatoPalavra();
        String cadeia = "as_123@";
        /*
        
        teste 
        as_123 aceita
        as_  aceita
        @as rejeita
        as@ rejeita
        a+b rejeita 
        a-b rejeita
        
        resumindo ainda não fiz pra detectar quando nao tem espaço
        */
        car= cadeia.toCharArray();
        
       //boolean a;
       //a="11Z".matches("[^0-9][a-zA-Z]*");
       //System.out.println(a);
        
        aut.inicio();
        if(aut.aceito){
            System.out.println("Aceita");
        }else{
            System.out.println("Rejeita");
        }
    }
    
    private void inicio() {
        cont =0;
        aceito= false;
        q0();
        
    }
    
    private void q0(){
        //System.out.println("Em q0");
        if(cont<car.length){
            String aux=""+car[cont];
             if(aux.matches("[^@$%&+-][^0-9]*[a-zA-Z]*")){
                 cont++;
                 //System.out.println(aux);
                 q1();
                 //System.out.println(aux);
                 
             }
             else{
                qError();
             }
            
        }
    }
    private void q1(){
        
        //System.out.println("Em q1");
        if(cont<car.length){
            aceito= true;
            String aux=""+car[cont];
             if(aux.matches("[^$%@#&+-][\\w]*")){
                 cont++;
                 //System.out.println(aux);
                 q1();
                 
             }else{
                 qError();
                 
             }
            
        }
        
    }
    
    private void qError(){
       aceito= false;
    }
    
}
