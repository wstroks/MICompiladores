/**
 * 
 */
package semantico;

import java.util.ArrayList;

import lexico.Token;
import semantico.Listas;

/**
 * @author Tayane
 *
 */
public class FuncaoProcedimento {
	
	private String nome = "";
	private ArrayList<Listas> parametros;
	private String tipoRetorno;
	private String tipo = "function"; //"function" ou "procedure"
	private String cenario = "declaracao"; //"declaracao" ou "chamada"
	private Token tokenErro;
	
	public FuncaoProcedimento(String tipo, String cenario){
		this.tipo = tipo;
		this.cenario = cenario;
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
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getCenario() {
		return cenario;
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
	 * Verifica se uma função/procedimento é igual a outra (sobrecarga não configura funções iguais, sobrescrita não é permitido)
	 * @param funcaoProcedimento
	 * @return
	 */
	public boolean isEquals(FuncaoProcedimento funcaoProcedimento){
		
		if(nome.equals(funcaoProcedimento.getNome())){
			if(getQtdParametros() == funcaoProcedimento.getQtdParametros()){ 
				//Quando a quantidade de parâmetros é igual, verificar se os tipos são iguais
				for (int i = 0; i < parametros.size(); i++) {
					if(!parametros.get(i).foiDeclaradocomo.equals(funcaoProcedimento.getParametros().get(i).foiDeclaradocomo)){
						return false;
					}
				}
				return true;
			}
		}
		
		return false;
	}
	
	public void print(){
		String string = this.tipo.toUpperCase() + " => ";
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

	public boolean isDeclaracao(){
		return cenario.equals("declaracao");
	}

	public boolean isChamada(){
		return cenario.equals("chamada");
	}
	
	public static String getTipoFuncao(){
		return "function";
	}
	
	public static String getTipoProcedimento(){
		return "procedure";
	}
	
	public boolean isFuncao(){
		return tipo.equals(getTipoFuncao());
	}

	public boolean isProcedimento(){
		return tipo.equals(getTipoProcedimento());
	}

	public Token getTokenErro() {
		return tokenErro;
	}

	public void setTokenErro(Token tokenErro) {
		this.tokenErro = tokenErro;
	}

}
