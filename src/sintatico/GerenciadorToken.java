/**
 *
 */
package sintatico;

import java.util.ArrayList;
import java.util.List;

import lexico.TipoToken;
import lexico.Token;

/**
 * @author Tayane
 *
 */
public class GerenciadorToken {

    private List<Token> listaTokens;
    private ArrayList<Erro> listaErros;
    protected int contTokenAtual = 0;

    GerenciadorToken(List<Token> listaTokens) {
        this.listaTokens = listaTokens;
        //Adiciona o token EOF no final da lista
        this.listaTokens.add(getEofToken());
        this.listaErros = new ArrayList<Erro>();
    }

    /**
     * Imprime a lista de tokens
     */
    public void printTokens() {
        System.out.println("Tokens: ");
        for (Token token : listaTokens) {
            System.out.println(token);
        }
    }

    /**
     * Obtendo o token atual
     *
     * @return o token atual
     */
    public Token getTokenAtual() {
        //System.out.println("\n asda " +listaTokens.get(contTokenAtual).getTipoToken());
        return listaTokens.get(contTokenAtual);
    }
    
    public TipoToken getTipoTokenAtual(){
    	return getTokenAtual().getTipoToken();
    }

    /**
     * Obtendo o próximo token
     *
     * @return o próximo token
     */
    public Token getProximoToken() {
        //System.out.println(listaTokens.get(contTokenAtual).getTipoToken());
        return listaTokens.get(contTokenAtual + 1);
    }

    /**
     * Consome o token atual
     *
     * @return o token atual
     */
    public Token consumirTokenAtual() {
        //System.out.println("Token consumido: " + listaTokens.get(contTokenAtual).getTipoToken());
    	contTokenAtual++;
        System.out.println("Quantidade de tokens restantes: " + getQtdTokensRestantes());
        return listaTokens.get(contTokenAtual);
    }

    /**
     * Volta um token
     */
    public void goBack(String nomeClasse) {
        //contTokenAtual--;
        //System.out.println("-> "+nomeClasse+": Go back. Token atual: " + getTipoTokenAtual() + " | Quantidade de tokens restantes: " + getQtdTokensRestantes());
    }
    
    public void stepBack(boolean removerUltimoErro){
    	contTokenAtual--;
    	if(removerUltimoErro){
    		removerUltimoErro();
    	}
    }
    
    protected void removerUltimoErro(){
    	if(!listaErros.isEmpty()){
    		listaErros.remove(listaErros.size() - 1);
    	}
    }

    public void addErro(String nomeProducao, ArrayList<TipoToken> tokensEsperados) {

        Token tokenAnterior;
        if (contTokenAtual == 0) {
            tokenAnterior = getEofToken();
        } else {
            tokenAnterior = listaTokens.get(contTokenAtual - 1);
        }
        
        Erro erro = new Erro(tokensEsperados, listaTokens.get(contTokenAtual), tokenAnterior);
        listaErros.add(erro);
        System.out.println("--> Erro na classe " + nomeProducao + ". Token recebido: " + getTipoTokenAtual() + ". Tokens esperados: " + erro.getStringTokensEsperados());
    }

    public boolean eof() {
    	return getTipoTokenAtual() == TipoToken.EOF;
    }

    private Token getEofToken() {
        return new Token(TipoToken.EOF, "$", 0, 0);
    }

    /*
    
    não sei ao certo o erro, mas as vezes ele adiciona na lista de erro .. tokens que ja foram consumidos
    ae armenguei aque kkkk 
     */
    public void printErros() {

        System.out.print("\n");
        if (listaErros.isEmpty()) {
            System.out.println("Nenhum erro sintático foi encontrado");
        }

       /* if (contTokenAtual == listaTokens.size()) {
            System.out.println("Nenhum erro sintático foi encontrado");
        }*/ else {
            for (Erro erro : listaErros) {
                System.out.println("Token inválido: " + erro.getTokenEncontrado().getLexema() + ". Era esperado: " + erro.getStringTokensEsperados());
            }
        }

    }
    
    public int getQtdTokensRestantes(){
    	return listaTokens.size() - contTokenAtual;
    }
}
