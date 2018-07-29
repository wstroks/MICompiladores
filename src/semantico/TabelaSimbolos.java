/**
 *
 */
package semantico;

import java.util.ArrayList;
import java.util.List;
import lexico.Token;

/**
 * @author Tayane
 *
 */
public class TabelaSimbolos {

    //private ArrayList<Entrada> functionsProcedures;
    //public ArrayList<Entrada> variaveis;
    //private ArrayList<String> test;
    private List<Listas> tabelaSimbolosVariavel;
    private List<Listas> tabelaSimbolosConst;
    private List<Listas> tabelaSimbolosStruct;
    private ArrayList<ErroSemantico> erro;
    private List<Token> defineOTipoDeclarado;
    
    private ArrayList<Funcao> tabelaSimbolosFuncao;
    public Funcao bufferFuncao;

    public TabelaSimbolos() {
        //functionsProcedures = new ArrayList<Entrada>();
        //variaveis = new ArrayList<Entrada>();

        tabelaSimbolosVariavel = new ArrayList<Listas>();
        tabelaSimbolosConst = new ArrayList<Listas>();
        tabelaSimbolosStruct = new ArrayList<Listas>();
        tabelaSimbolosFuncao= new ArrayList<Funcao>();
        erro = new ArrayList<ErroSemantico>();
        defineOTipoDeclarado = new ArrayList<Token>();
        
    }

    public void addTipo(Token tipo) {

        defineOTipoDeclarado.add(tipo);

        //ImpressaoVariavel();
    }

    public String encontraTipo(Token tipo) {
        for (Token t : defineOTipoDeclarado) {
            if (t.getLinha() == tipo.getLinha()) {
                return t.getLexema().toString();
            }
        }
        return null;
    }

    public void addTabelaVariaveis(String nome, Token tipo) {
        if (JafoiDeclarado(nome, tabelaSimbolosConst)) {
            //throw new RuntimeException("Erro semantico: Variavel "+ nome+ " foi declarada duas vezes");
            ErroSemantico er = new ErroSemantico();
            er.tipo = tipo;
            er.tipoDoErro = "Variável " + nome + " já foi declarada anteriomente com uma constante";
            erro.add(er);
        } else if (JafoiDeclarado(nome, tabelaSimbolosVariavel)) {

            //throw new RuntimeException("Erro semantico: Variavel "+ nome+ " foi declarada duas vezes");
            ErroSemantico er = new ErroSemantico();
            er.tipo = tipo;
            er.tipoDoErro = "Variável local duplicada";
            erro.add(er);
        } else {

            Listas etds = new Listas();
            //System.out.println("  " + nome);
            etds.nome = nome;
            etds.tipo = tipo;
            etds.foiDeclaradocomo = encontraTipo(tipo);

            tabelaSimbolosVariavel.add(etds);
        }
        //ImpressaoVariavel();

    }

    public void addTabelaConst(String nome, Token tipo) {
        if (JafoiDeclarado(nome, tabelaSimbolosConst)) {
            //throw new RuntimeException("Erro semantico: Variavel "+ nome+ " foi declarada duas vezes");
            ErroSemantico er = new ErroSemantico();
            er.tipo = tipo;
            er.tipoDoErro = "Const";
            erro.add(er);
        } else {

            Listas etds = new Listas();
            //System.out.println("  " + nome);
            etds.nome = nome;
            etds.tipo = tipo;
            etds.foiDeclaradocomo = encontraTipo(tipo);
            //antes dele adiciona verifica se existe o tipo atribuido tá correto
            //if (atribuicaoCorretaPeloTipo(tipo, nome)) {
            tabelaSimbolosConst.add(etds);
            //} else {

            //}
        }
        //ImpressaoVariavel();

    }

    public void addTabelaStruct(String nome, Token tipo) {
        if (JafoiDeclarado(nome, tabelaSimbolosStruct)) {
            //throw new RuntimeException("Erro semantico: Variavel "+ nome+ " foi declarada duas vezes");
            ErroSemantico er = new ErroSemantico();
            er.tipo = tipo;
            er.tipoDoErro = "Struct";
            erro.add(er);
        } else {

            Listas etds = new Listas();
            //System.out.println("  " + nome);
            etds.nome = nome;
            etds.tipo = tipo;
            etds.foiDeclaradocomo = encontraTipo(tipo);
            tabelaSimbolosStruct.add(etds);
        }
        //ImpressaoVariavel();

    }

    public boolean JafoiDeclarado(String nome, List<Listas> a) {
        for (Listas etds : a) {
            String naruto = etds.nome;
            if (naruto.equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public void ImpressaoVariavel() {
        //int a = 0;
        System.out.println("TABELA DE SIMBOLOS VARIAVEL");
        for (Listas etds : tabelaSimbolosVariavel) {
            //a++;
            System.out.println("Variavel " + etds.nome.toUpperCase() + " Linha: " + etds.tipo.getLinha() + " ele é um " + etds.foiDeclaradocomo.toUpperCase());

        }

        System.out.println("\nTABELA DE SIMBOLOS CONSTANTE");
        for (Listas n : tabelaSimbolosConst) {
            //a++;
            System.out.println("Const " + n.nome.toUpperCase() + " Linha: " + n.tipo.getLinha() + " ele é um " + n.foiDeclaradocomo.toUpperCase());

        }
        System.out.println("\nTABELA DE SIMBOLOS STRUCT");
        for (Listas n : tabelaSimbolosStruct) {
            //a++;
            System.out.println("Struct " + n.nome.toUpperCase() + "Linha : " + n.tipo.getLinha() + " ele é um " + n.foiDeclaradocomo.toUpperCase());

        }   
        System.out.println("\nTABELA DE SIMBOLOS FUNCAO");
        for (Funcao n : tabelaSimbolosFuncao) {
            //a++;
            System.out.println("Nome: " + n.getNome() + " | Tipo de retorno: " + n.getTipoRetorno().toString() + " | Parâmetros: " + n.converterParametrosToString());

        }
    }

    public void ImpressaoVariavelErro() {
        //int a = 0;
        System.out.println("Erros semânticos (" + erro.size() + "): ");
        for (ErroSemantico e : erro) {
            // a++;
            System.out.println(e.getMensagem());
        }
    }

    /*
    esse metodo não deixa fazer atribuição em const
    exemplo a=10; a é um tipo const e não pode ser alterado
     */
    public void atribuicaoConst(Token recebe) {
        for (Listas a : tabelaSimbolosConst) {
            if (a.tipo.getLexema().equals(recebe.getLexema())) {
                ErroSemantico er = new ErroSemantico();
                er.tipo = recebe;
                er.tipoDoErro = "Alterar valor de uma const";
                erro.add(er);

            }
        }
    }

    /*
    
    depois tem que colocar a atribuicao na tabela nao sei se precisa
     */
    public void atribuicaoCorretaPeloTipoConst(Token recebe) {
/*
        for (Listas a : tabelaSimbolosConst) {
          //  System.out.println(a.tipo.getPosicao() + " " + recebe.getPosicao();
            if (a.tipo.getLinha() == recebe.getLinha() && !(a.foiDeclaradocomo.equals(recebe.getTipoToken())) && (a.tipo.getPosicao() + 3) == recebe.getPosicao()) {
                ErroSemantico er = new ErroSemantico();
                er.tipo = a.tipo;
                er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de Const";
                erro.add(er);
            } else if (a.foiDeclaradocomo.equals("int")) {
                //System.out.println("entrou  ");
                
                if (recebe.getLexema().contains(".")) {
                    ErroSemantico er = new ErroSemantico();
                    er.tipo = a.tipo;
                    er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de Const";
                    erro.add(er);
                }
            }else if (a.foiDeclaradocomo.equals("float")) {
                //System.out.println("entrou  ");
                
                if (!recebe.getLexema().contains(".")) {
                    ErroSemantico er = new ErroSemantico();
                    er.tipo = a.tipo;
                    er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de Const";
                    erro.add(er);
                }
            }
        }*/

    }

    public void atribuicaoCorretaPeloTipoVar(Token recebe) {

        /*for (Listas a : tabelaSimbolosVariavel) {
            //System.out.println(a.tipo.getTipoToken() + " " + recebe.getTipoToken());
            if (a.foiDeclaradocomo.equals("bool")) {
                if(!recebe.getLexema().equals("true") && !recebe.getLexema().equals("false")){
                ErroSemantico er = new ErroSemantico();
                er.tipo = a.tipo;
                er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de Var";
                erro.add(er);
                }
            }if (a.foiDeclaradocomo.equals("int")) {
                //System.out.println("entrou  1");
                
                if(!recebe.getTipoToken().toString().toLowerCase().equals("palavra_reservada_int")) {
                    ErroSemantico er = new ErroSemantico();
                    er.tipo = a.tipo;
                    er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de Var";
                    erro.add(er);
                }
            }
        }*/

    }
    
    /**
     * Adiciona a função que está no buffer na tabela de simbolos de function
     */
    public void addFuncao(){
    	//Verificar se já existe uma função com o mesmo nome mesmos parametros (mesma quantidade e mesmos tipos)
    	if(!funcaoBufferJaExiste()){
    		tabelaSimbolosFuncao.add(bufferFuncao);
    	}
    	else{
            ErroSemantico er = new ErroSemantico();
            er.tipoDoErro = "Função já declarada " + bufferFuncao.getNome();
            erro.add(er);  
    	}
    }
    
    /**
     * Limpa o buffer de função
     */
    public void clearBufferFuncao(){
    	bufferFuncao = new Funcao();
    }
    
    /**
     * Verifica se a função que está no buffer já existe
     * @return boolean
     */
    public boolean funcaoBufferJaExiste(){
    	
    	for (Funcao funcao : tabelaSimbolosFuncao) {
    		if(funcao.isEquals(bufferFuncao)){
    			return true;
    		}
		}
    	
    	return false;
    	
    }
   
}
