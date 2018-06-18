/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.precedenciaOperadores;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class Expressao extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new Expressao();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {
        // TODO Auto-generated method stub
        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

             if (!OpE.getInstancia().analisar(gerenciadorToken)) {
                return false;
            }else if (!ExpressaoAux.getInstancia().analisar(gerenciadorToken)) {
                return false;
            }
            
            

            return true;

        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        //{  -- , ! , ( , ++ , CadeiaDeCaracteres, Digitos, false, identificador, true}
        first.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
        first.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        first.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
        first.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        first.add(TipoToken.CADEIA_CARACTERES);
        first.add(TipoToken.NUMERO);
        first.add(TipoToken.PALAVRA_RESERVADA_FALSE);
        first.add(TipoToken.PALAVRA_RESERVADA_TRUE);
        first.add(TipoToken.IDENTIFICADOR);
        
        
        
      
        
    }

    @Override
    protected void gerarFollow() {
        //{ ), ‘,’, ;, ]}
        follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
        follow.add(TipoToken.DELIMITADOR_VIRGULA);
        follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
        follow.add(TipoToken.DELIMITADOR_FECHA_COLCHETE);
        
        

    }

}
