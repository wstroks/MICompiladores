/**
 * 
 */
package semantico;

import java.util.ArrayList;

import lexico.TipoToken;

/**
 * @author Tayane
 *
 */
public class Funcao {
	
	private String nome;
	private ArrayList<TipoToken> parametros;
	private TipoToken tipoRetorno;
	
	public Funcao(){
		parametros = new ArrayList<TipoToken>();
	}
	
	public void addParametro(TipoToken tipo){
		parametros.add(tipo);
	}

	public TipoToken getTipoRetorno() {
		return tipoRetorno;
	}

	public void setTipoRetorno(TipoToken tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public ArrayList<TipoToken> getParametros() {
		return parametros;
	}	
	
	public int getQtdParametros(){
		return parametros.size();
	}
	
	public String converterParametrosToString(){
		String string = "";
		for (int i = 0; i < parametros.size(); i++) {
			if(i == 0){
				string = parametros.get(i).toString();
			}
			else{
				string += ", " + parametros.get(i).toString();
			}
		}
		return string;
	}
	
	/**
	 * Verifica se uma função é igual a outra (sobrecarga não configura funções iguais, sobrescrita não é permitido)
	 * @param funcao
	 * @return
	 */
	public boolean isEquals(Funcao funcao){
		
		if(nome.equals(funcao.getNome())){
			if(getQtdParametros() == funcao.getQtdParametros()){ 
				return true;
			}
		}
		
		return false;
	}

}
