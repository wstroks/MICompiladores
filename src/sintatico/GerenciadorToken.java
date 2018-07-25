/**
 *
 */
package sintatico;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import lexico.TipoToken;
import lexico.Token;
import semantico.TabelaSimbolos;

/**
 * @author Tayane
 *
 */
public class GerenciadorToken {

    private List<Token> listaTokens;
    private ArrayList<Erro> listaErros;
    protected int contTokenAtual = 0;
    private TabelaSimbolos ts;

    GerenciadorToken(List<Token> listaTokens, TabelaSimbolos ts) {
        this.listaTokens = listaTokens;
        //Adiciona o token EOF no final da lista
        this.listaTokens.add(getEofToken());
        this.listaErros = new ArrayList<Erro>();
        this.ts = ts;
    }

    /**
     * Imprime a lista de tokens
     */
    public void printTokens() {
        //System.out.println("TOKENS QUE SERÃO ANALISADOS PELO LÉXICO: ");
        for (Token token : listaTokens) {
            System.out.println(token.getImpressaoToken());
        }
    }

    /**
     * Obtendo o token atual
     *
     * @return o token atual
     */
    public Token getTokenAtual() {
        //System.out.println("\n " +listaTokens.get(contTokenAtual).getTipoToken());
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
    	contTokenAtual++;
        //System.out.println("Quantidade de tokens restantes: " + getQtdTokensRestantes());
        //System.out.println();
        return listaTokens.get(contTokenAtual);
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
        //System.out.println("\n!!!!! Erro na regra <" + nomeProducao + ">. Token recebido: " + getTipoTokenAtual() + ". Tokens esperados: " + erro.getStringTokensEsperados() + "\n");
    }

    public boolean eof() {
    	return getTipoTokenAtual() == TipoToken.EOF;
    }

    private Token getEofToken() {
        return new Token(TipoToken.EOF, "$", 0, 0);
    }

    public void printErros() {

        System.out.print("\n");
        if (listaErros.isEmpty()) {
            System.out.println("Nenhum erro sintático foi encontrado");
        }
        else {
        	System.out.println("Foram encontrados os seguintes erros sintáticos: ");
            for (Erro erro : listaErros) {
            	int linha = erro.getTokenEncontrado().getLinha() + 1;
            	int posicao = erro.getTokenEncontrado().getPosicao();
            	String lexema = erro.getTokenEncontrado().getLexema();
                System.out.println("Token inválido \"" + lexema + "\" na linha " + linha + " posição " + posicao + ". Eram esperados os seguintes tokens: " + erro.getStringTokensEsperados());
            }
        }

    }
    public void printErroToFile(String output) throws IOException {

        FileWriter arquivo = new FileWriter(output + ".txt");
        PrintWriter writer = new PrintWriter(arquivo);

        //writer.println("\n");
        if (listaErros.isEmpty()) {
            writer.println("Nenhum erro foi encontrado");
        } else {
            writer.println("Foram encontrados os seguintes erros sintáticos: ");
            for (Erro erro : listaErros) {
                int linha = erro.getTokenEncontrado().getLinha() + 1;
                int posicao = erro.getTokenEncontrado().getPosicao();
                String lexema = erro.getTokenEncontrado().getLexema();
                writer.println("Token inválido \"" + lexema + "\" na linha " + linha + ". Eram esperados os seguintes tokens: " + erro.getStringTokensEsperados()+"\n");
                }
        }

        arquivo.close();

    }
    
    public int getQtdTokensRestantes(){
    	return listaTokens.size() - contTokenAtual;
    }
}
