/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.printScan;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class OutrasSaidas extends RegraProducao{
	
	public static RegraProducao getInstancia() {
		return new OutrasSaidas();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		
		if(isFirst(gerenciadorToken.getTokenAtual().getTipoToken())){
			
			if(!consumir(gerenciadorToken, TipoToken.DELIMITADOR_VIRGULA)){
				return false;
			}
			
                       
			
			//TODO: <Saida>
                        
                        else if(!Saida.getInstancia().analisar(gerenciadorToken)){
                            return false;
                        }
			
			//TODO: <OutrasSaidas>
                        else if(!OutrasSaidas.getInstancia().analisar(gerenciadorToken)){
                            return false;
                        }
                        
                        else if(gerenciadorToken.eof()){
				return true;
			}
			
			
                        
			return true;
		}
		
		return false;
	}

	@Override
	protected void gerarFirst() {
		//{  E, ‘,’ }
		first.add(TipoToken.EOF);
                first.add(TipoToken.DELIMITADOR_VIRGULA);
	}

	@Override
	protected void gerarFollow() {
		//{ )}
		follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
	}

}
