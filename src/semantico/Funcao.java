/**
 * 
 */
package semantico;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import semantico.Listas;

/**
 * @author Tayane
 *
 */
public class Funcao {
	
	private String nome;
	private ArrayList<Listas> parametros;
	private String tipoRetorno;
	
	public Funcao(){
		parametros = new ArrayList<Listas>();
	}
	
	public void addParametro(Listas tipo){
		parametros.add(tipo);
	}

	public String getTipoRetorno() {
		return tipoRetorno;
	}

	public void setTipoRetorno(String tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public ArrayList<Listas> getParametros() {
		return parametros;
	}	
	
	public int getQtdParametros(){
		return parametros.size();
	}
	
	public String converterParametrosToString(){
		String string = "";
		for (int i = 0; i < parametros.size(); i++) {
			if(i == 0){
				string = parametros.get(i).getString();
			}
			else{
				string += ", " + parametros.get(i).getString();
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
				//Quando a quantidade de parâmetros é igual, verificar se os tipos são iguais
				for (int i = 0; i < parametros.size(); i++) {
					if(parametros.get(i).foiDeclaradocomo != funcao.getParametros().get(i).foiDeclaradocomo){
						return false;
					}
				}
				return true;
			}
		}
		
		return false;
	}
	
	public void print(){
		String string = "";
		if(nome != null){
			string += "Nome: " + nome;
		}
		if(tipoRetorno != null){
			string += " | Tipo de retorno: " + tipoRetorno;
		}
		if(parametros != null){
			string += " | Parâmetros: " + converterParametrosToString();
		}
		System.out.println(string);
	}
	
	public String getStringArgumentos(){
		String argumentos = "(";
		for (int i = 0; i < parametros.size(); i++) {
			if(i == 0){
				argumentos += parametros.get(i).foiDeclaradocomo;
			}
			else{
				argumentos += ", " + parametros.get(i).foiDeclaradocomo;
			}
		}
		argumentos += ")";
		return argumentos;
	}
	
	public String getNomeComArgumentos(){
		return nome + getStringArgumentos();
	}
	
	public boolean possuiParametrosNaoDeclarados(){
		for (Listas parametro : parametros) {
			if(!parametro.isDeclarado()){
				return true;
			}
		}
		return false;
	}

}
