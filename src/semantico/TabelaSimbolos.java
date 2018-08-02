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
    private List<StructTabela> tabelaSimbolosStruct;
    private ArrayList<ErroSemantico> erro;
    private List<Token> defineOTipoDeclarado;
    private List<Listas> tabelaSimbolosConstAux;
    private List<Listas> tabelaSimbolosVarAux;
    public List<Token> ajudaExpressaoAtribuicao;
    public List<Token> ajudaVetores;
    public boolean ajuda;
    public String ajudaStructExtende = "";
    public String ajudaStructNome = "";
    public boolean ajudaStruct;
    public boolean jaTemStructNome;
    public boolean jaTemStructExtends;
    public boolean ajudaFunction;
    private ArrayList<FuncaoProcedimento> tabelaSimbolosFuncao;
    public FuncaoProcedimento bufferFuncaoProcedimento;
    public FuncaoProcedimento bufferChamadaFuncaoProcedimento;
    public boolean ajudaVetor = false;

    private int qtdStart = 0; //quantidade de vezes que o método start foi chamado

    public TabelaSimbolos() {
        //functionsProcedures = new ArrayList<Entrada>();
        //variaveis = new ArrayList<Entrada>();

        tabelaSimbolosVariavel = new ArrayList<Listas>();
        tabelaSimbolosConst = new ArrayList<Listas>();
        tabelaSimbolosStruct = new ArrayList<StructTabela>();
        tabelaSimbolosFuncao = new ArrayList<FuncaoProcedimento>();
        tabelaSimbolosConstAux = new ArrayList<Listas>();
        tabelaSimbolosVarAux = new ArrayList<Listas>();
        erro = new ArrayList<ErroSemantico>();
        defineOTipoDeclarado = new ArrayList<Token>();
        ajudaExpressaoAtribuicao = new ArrayList<Token>();
        ajudaVetores = new ArrayList<Token>();
        this.ajuda = ajuda;
        
        //Inicializando só pra evitar exceção
        clearBufferFuncaoProcedimento(FuncaoProcedimento.getTipoFuncao());

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

    /**
     * Retorna o tipo de uma variáve. Caso a variável não seja encontrada na
     * tabela de símbolos, uma string vazia é retornada. obs: não está sendo
     * considerado o escopo
     *
     * @param nomeVariavel
     * @return String tipo da variável
     */
    public String getTipoVariavel(String nomeVariavel) {
        for (Listas variavel : tabelaSimbolosVariavel) {
            if (variavel.nome.equals(nomeVariavel)) {
                return variavel.foiDeclaradocomo;
            }
        }
        return "";
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
            atualizaVar();
            Listas etds = new Listas();
            //System.out.println("  " + nome);
            etds.nome = nome;
            etds.tipo = tipo;
            etds.foiDeclaradocomo = encontraTipo(tipo);
            etds.setEscopo(bufferFuncaoProcedimento.getNome());
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
            atualizaConst();
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

    public void atualizaConst() {
        if (tabelaSimbolosConst.size() != 0 && tabelaSimbolosConstAux.size() != 0) {
            for (int i = 0; i < tabelaSimbolosConst.size(); i++) {
                Listas a = tabelaSimbolosConst.get(i);
                for (int x = 0; x < tabelaSimbolosConstAux.size(); x++) {
                    Listas b = tabelaSimbolosConstAux.get(x);
                    if (a.equals(b)) {
                        tabelaSimbolosConst.remove(i);
                    }
                }
            }
        }
    }

    public void atualizaVar() {
        if (tabelaSimbolosVariavel.size() != 0 && tabelaSimbolosVarAux.size() != 0) {
            for (int i = 0; i < tabelaSimbolosVariavel.size(); i++) {
                Listas a = tabelaSimbolosVariavel.get(i);
                for (int x = 0; x < tabelaSimbolosVarAux.size(); x++) {
                    Listas b = tabelaSimbolosVarAux.get(x);
                    if (a.equals(b)) {
                        tabelaSimbolosVariavel.remove(i);
                    }
                }
            }
        }
    }

    public void erroStructNome(Token tipo) {

        if (jaExisteStructNome(ajudaStructNome, tabelaSimbolosStruct)) {
            //throw new RuntimeException("Erro semantico: Variavel "+ nome+ " foi declarada duas vezes");
            ErroSemantico er = new ErroSemantico();
            er.tipo = tipo;
            er.tipoDoErro = "Bloco de Struct criado com mesmo nome " + (ajudaStructNome);
            erro.add(er);
            jaTemStructNome = true;
        }
    }
    
     public void erroEx(Token tipo) {

        if (jaExisteStructNomeExtends(ajudaStructExtende, tabelaSimbolosStruct)) {
            //throw new RuntimeException("Erro semantico: Variavel "+ nome+ " foi declarada duas vezes");
            
        
            ErroSemantico er = new ErroSemantico();
            er.tipo = tipo;
            er.tipoDoErro = "Struct extendida não foi declarada " + (ajudaStructExtende);
            erro.add(er);
            jaTemStructNome = true;
        }
        
    }

    public void addTabelaStruct(String nome, Token tipo) {
        if (jaExisteStructVariavel(nome, tabelaSimbolosStruct) && !jaTemStructNome) {

            ErroSemantico er = new ErroSemantico();
            er.tipo = tipo;
            er.tipoDoErro = "Variável dentro da struct " + (ajudaStructNome) + " já existe";
            erro.add(er);

        } else {
            if (!jaTemStructNome ) {
                StructTabela etds = new StructTabela();
                //System.out.println("  " + nome);
                etds.nomeVariavel = nome;
                etds.extende = ajudaStructExtende;
                etds.nomeStruct = ajudaStructNome;
                etds.tipo = tipo;
                etds.foiDeclaradocomo = encontraTipo(tipo);
                tabelaSimbolosStruct.add(etds);
            }
        }
        //ImpressaoVariavel();

    }

    public boolean JafoiDeclarado(String nome, List<Listas> a) {
    	String escopoAtual = bufferFuncaoProcedimento.getNome();
        for (Listas etds : a) {
            if (etds.nome.equals(nome)) {
            	if(etds.isEscopoGlobal() || (etds.getEscopo().equals(escopoAtual))){
            		return true;
            	}  
            }
        }
        return false;
    }

    public boolean jaExisteStructNome(String nome, List<StructTabela> a) {
        for (StructTabela etds : a) {
            String naruto = etds.nomeStruct;
            if (naruto.equals(nome)) {
                return true;
            }
        }
        return false;
    }
    
    
    
    public boolean jaExisteStructNomeExtends(String nome, List<StructTabela> a) {
        for (StructTabela etds : a) {
            String naruto = etds.nomeStruct;
            //System.out.println(naruto +"  yy");
            if (naruto.equals(nome)) {
                return false;
            }
        }
        return true;
    }
    
    

    public boolean jaExisteStructVariavel(String nome, List<StructTabela> a) {
        for (StructTabela etds : a) {
            String naruto = etds.nomeVariavel;
            String sasuke = etds.nomeStruct;
            if (sasuke.equals(ajudaStructNome)) {
                if (naruto.equals(nome)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void ImpressaoVariavel() {
        //int a = 0;
        System.out.println("TABELA DE SIMBOLOS VARIAVEL");
        for (Listas etds : tabelaSimbolosVariavel) {
            //a++;
            //System.out.println("Variavel " + etds.nome.toUpperCase() + " Linha: " + etds.tipo.getLinha() + " ele é um " + etds.foiDeclaradocomo.toUpperCase());
            System.out.println("VARIAVEL=> " + "Nome: " + etds.nome + " | " + "Tipo: " + etds.foiDeclaradocomo + " | " + "linha: " + etds.tipo.getLinha());
        }

        System.out.println("\nTABELA DE SIMBOLOS CONSTANTE");
        for (Listas n : tabelaSimbolosConst) {
            //a++;
            //System.out.println("Const " + n.nome.toUpperCase() + " Linha: " + n.tipo.getLinha() + " ele é um " + n.foiDeclaradocomo.toUpperCase());
            System.out.println("CONST=> " + "Nome: " + n.nome + " | " + "Tipo: " + n.foiDeclaradocomo + " | " + "linha: " + n.tipo.getLinha());
        }
        System.out.println("\nTABELA DE SIMBOLOS STRUCT");
        for (StructTabela n : tabelaSimbolosStruct) {
            //a++;
            //System.out.println("Struct " + n.nome.toUpperCase() + "Linha : " + n.tipo.getLinha() + " ele é um " + n.foiDeclaradocomo.toUpperCase());
            System.out.println("STRUCT=> " + "Nome da Struct: " + n.nomeStruct +" | "+ " extends: " + n.extende + " | "+ "Nome da Variavel: " + n.nomeVariavel + " | " + "VariavelTipo: " + n.foiDeclaradocomo + " | " + "linha: " + n.tipo.getLinha());

        }
        System.out.println("\nTABELA DE SIMBOLOS FUNCAO E PROCEDIMENTO");
        for (FuncaoProcedimento n : tabelaSimbolosFuncao) {
            //a++;
            n.print();
        }
    }

    public void ImpressaoVariavelErro() {
        //int a = 0;
        System.out.println("Erros semânticos (" + erro.size() + "): ");
        for (ErroSemantico e : erro) {
            // a++;
            //System.out.println("ERRO=> " + "Descricao do erro: " + (e.getMensagem()) + " | " + "Linha: " + e.tipo.getLinha());
        	System.out.println("ERRO=> " + "Descricao do erro: " + (e.getMensagem()));
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
    public void atribuicaoCorretaPeloTipoConst(Token t, Token a) {

        for (Listas lista : tabelaSimbolosConst) {
            if (lista.tipo.getLexema().equals(a.getLexema())) {
                //System.out.println("asas");
                if (lista.foiDeclaradocomo.equals("int")) {
                    // System.out.println("asas 1");
                    if (t.getLexema().contains(".") || t.getLexema().equals("true") || t.getLexema().equals("false") || t.getTipoToken().toString().equals("CADEIA_CARACTERES")) {
                        //Listas b =new Listas();
                        tabelaSimbolosConstAux.add(lista);
                        ErroSemantico er = new ErroSemantico();
                        er.tipo = t;
                        er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de Const";
                        erro.add(er);

                    }

                } else if (lista.foiDeclaradocomo.equals("float")) {
                    if (!t.getLexema().contains(".") || t.getLexema().equals("true") || t.getLexema().equals("false") || t.getTipoToken().toString().equals("CADEIA_CARACTERES")) {
                        //tabelaSimbolosConst.remove(lista);
                        tabelaSimbolosConstAux.add(lista);
                        ErroSemantico er = new ErroSemantico();
                        er.tipo = t;
                        er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de Const";
                        erro.add(er);

                    }

                } else if (lista.foiDeclaradocomo.equals("string")) {
                    if (!t.getTipoToken().toString().equals("CADEIA_CARACTERES") || t.getLexema().equals("true") || t.getLexema().equals("false")) {
                        //tabelaSimbolosConst.remove(lista);
                        tabelaSimbolosConstAux.add(lista);
                        ErroSemantico er = new ErroSemantico();
                        er.tipo = t;
                        er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de Const";
                        erro.add(er);
                        //tabelaSimbolosConst.remove(lista);

                    }

                }
            }

        }
    }

    public void atribuicaoCorretaPeloTipoVar(Token t, Token a) {
        for (Listas lista : tabelaSimbolosVariavel) {
            if (lista.tipo.getLexema().equals(a.getLexema())) {
                //System.out.println("asas");
                if (lista.foiDeclaradocomo.equals("int")) {
                    // System.out.println("asas 1");
                    if (t.getLexema().contains(".") || t.getLexema().equals("true") || t.getLexema().equals("false") || t.getTipoToken().toString().equals("CADEIA_CARACTERES")) {
                        tabelaSimbolosVarAux.add(lista);
                        ErroSemantico er = new ErroSemantico();
                        er.tipo = t;
                        er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de var";
                        erro.add(er);
                        // tabelaSimbolosVariavel.remove(lista);
                    }
                } else if (lista.foiDeclaradocomo.equals("float")) {
                    if (!t.getLexema().contains(".") || t.getLexema().equals("true") || t.getLexema().equals("false") || t.getTipoToken().toString().equals("CADEIA_CARACTERES")) {
                        tabelaSimbolosVarAux.add(lista);
                        ErroSemantico er = new ErroSemantico();
                        er.tipo = t;
                        er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de var";
                        erro.add(er);
                        //tabelaSimbolosVariavel.remove(lista);
                    }
                } else if (lista.foiDeclaradocomo.equals("string")) {
                    if (!t.getTipoToken().toString().equals("CADEIA_CARACTERES") || t.getLexema().equals("true") || t.getLexema().equals("false")) {
                        tabelaSimbolosVarAux.add(lista);
                        ErroSemantico er = new ErroSemantico();
                        er.tipo = t;
                        er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de var";
                        erro.add(er);
                        // tabelaSimbolosVariavel.remove(lista);
                    }
                }
            }

        }

    }

    /**
     * Adiciona a função que está no buffer na tabela de simbolos de function
     */
    public void addFuncaoProcedimento() {
        //Verificar se já existe uma função com o mesmo nome mesmos parametros (mesma quantidade e mesmos tipos)
        if (!funcaoProcedimentoBufferJaExiste()) {
            tabelaSimbolosFuncao.add(bufferFuncaoProcedimento);
        } else {
            ErroSemantico er = new ErroSemantico();
            er.tipoDoErro = bufferFuncaoProcedimento.getTipo() + " " + bufferFuncaoProcedimento.getNome() + " já declarada como function ou procedure";
            erro.add(er);
        }
        clearBufferFuncaoProcedimento(bufferFuncaoProcedimento.getTipo());
    }

    /**
     * Valida a chamada de uma função. Em casa de erro, é adicionado na lista de
     * erros semânticos A função a ser avaliada é sempre a que está no buffer de
     * função
     */
    public void validaChamadaFuncao() {
        //Verifica se a função foi declarada
        if (getFuncao(bufferChamadaFuncaoProcedimento) != null) {
            //Verifica se a quantidade de parâmetros e os tipos estão corretos
            FuncaoProcedimento funcaoDeclarada = getFuncao(bufferChamadaFuncaoProcedimento);
            if (funcaoDeclarada.getQtdParametros() == bufferChamadaFuncaoProcedimento.getQtdParametros()) {
                for (int i = 0; i < funcaoDeclarada.getQtdParametros(); i++) {
                    if (!funcaoDeclarada.getParametros().get(i).foiDeclaradocomo.equals(bufferChamadaFuncaoProcedimento.getParametros().get(i).foiDeclaradocomo)) {
                        //erro: chama de função com tipos diferentes
                        ErroSemantico er = new ErroSemantico();
                        er.tipoDoErro = "A chamada da " + bufferChamadaFuncaoProcedimento.getTipo() + " " + funcaoDeclarada.getNomeComArgumentos() + " não é aplicável para os argumentos " + bufferChamadaFuncaoProcedimento.getStringArgumentos();
                        erro.add(er);
                        break;
                    }
                }
            } else {
                //erro: chamada de função errada - quantidade de parametros diferentes
                ErroSemantico er = new ErroSemantico();
                er.tipoDoErro = "A chamada da " + bufferChamadaFuncaoProcedimento.getTipo() + " " + funcaoDeclarada.getNomeComArgumentos() + " não é aplicável para os argumentos " + bufferChamadaFuncaoProcedimento.getStringArgumentos() + ". Quantidade de argumentos diferente.";
                erro.add(er);
            }
        } else {
            //erro: chamando função não declarada
            if (existeFuncaoMesmoNome(bufferChamadaFuncaoProcedimento)) {
                //Verifica se os parametros foram declarados
                if (bufferChamadaFuncaoProcedimento.possuiParametrosNaoDeclarados()) {
                    for (Listas parametro : bufferChamadaFuncaoProcedimento.getParametros()) {
                        //System.out.println(parametro.isDeclarado());
                        if (!parametro.isDeclarado()) {
                            //erro: parâmetro de função não declarado
                            ErroSemantico er = new ErroSemantico();
                            er.tipoDoErro = "A variável " + parametro.nome + " não foi declarada";
                            erro.add(er);
                        }
                    }
                } 
                else {
                    ErroSemantico er = new ErroSemantico();
                    er.tipoDoErro = "A chamada da rotina " + bufferChamadaFuncaoProcedimento.getNome() + " não é aplicável para os argumentos " + bufferChamadaFuncaoProcedimento.getStringArgumentos();
                    erro.add(er);
                }
            } else {
                ErroSemantico er = new ErroSemantico();
                er.tipoDoErro = "Chamada de rotina não declarada " + bufferChamadaFuncaoProcedimento.getNome();
                erro.add(er);
            }
        }

    }

    /**
     * Verifica se existe uma função com declarada com o mesmo nome e tipo (não
     * verifica se os parâmetros são iguais)
     *
     * @param FuncaoProcedimento funcao
     * @return boolean
     */
    public boolean existeFuncaoMesmoNome(FuncaoProcedimento funcaoProcedimento) {
        for (FuncaoProcedimento f : tabelaSimbolosFuncao) {
            if (f.getNome().equals(funcaoProcedimento.getNome())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna uma função declarada a partir de uma função chamada. Caso a
     * função chamada não tenha sido declarada, é retornado null
     *
     * @param FuncaoProcedimento funcao
     * @return FuncaoProcedimento
     */
    public FuncaoProcedimento getFuncao(FuncaoProcedimento funcaoProcedimento) {
        for (FuncaoProcedimento f : tabelaSimbolosFuncao) {
            if (f.isEquals(funcaoProcedimento)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Limpa o buffer de declaração de função/procedimento
     */
    public void clearBufferFuncaoProcedimento(String tipo) {
        bufferFuncaoProcedimento = new FuncaoProcedimento(tipo, "declaracao");
    }

    /**
     * Limpa o buffer de chamada de função/procedimento
     */
    public void clearBufferChamadaFuncaoProcedimento(String tipo) {
        bufferChamadaFuncaoProcedimento = new FuncaoProcedimento(tipo, "chamada");
    }

    /**
     * Verifica se a função que está no buffer de declaração de função já existe
     *
     * @return boolean
     */
    public boolean funcaoProcedimentoBufferJaExiste() {

        for (FuncaoProcedimento funcaoProcedimento : tabelaSimbolosFuncao) {
            if (funcaoProcedimento.isEquals(bufferFuncaoProcedimento)) {
                return true;
            }
        }

        return false;

    }

    public void expressaoIf(Token anterior, Token atual, Token proximo) {
        int cont = 0;
        Listas primeiroTipo = retornaTokenDeclarado(anterior.getLexema(), tabelaSimbolosVariavel);
        if (primeiroTipo == null) {
            primeiroTipo = retornaTokenDeclarado(anterior.getLexema(), tabelaSimbolosConst);
        }
        Listas segundoTipo = retornaTokenDeclarado(proximo.getLexema(), tabelaSimbolosVariavel);
        if (segundoTipo == null) {
            segundoTipo = retornaTokenDeclarado(proximo.getLexema(), tabelaSimbolosConst);
        }

        if (!JafoiDeclarado(anterior.getLexema(), tabelaSimbolosVariavel) && !JafoiDeclarado(proximo.getLexema(), tabelaSimbolosVariavel)) {
            //System.out.println("1");
            if (!anterior.getTipoToken().toString().equals(proximo.getTipoToken().toString())) {

                ErroSemantico er = new ErroSemantico();
                er.tipo = anterior;
                er.tipoDoErro = "Expressao condicional errada";
                erro.add(er);

                ErroSemantico e = new ErroSemantico();
                e.tipo = proximo;
                e.tipoDoErro = "Expressao condicional errada";
                erro.add(e);
                cont++;
            } else {
                if (anterior.getTipoToken().toString().equals("NUMERO")) {
                    //System.out.println("2");
                    if ((!anterior.getLexema().contains(".") && proximo.getLexema().contains(".")) || (anterior.getLexema().contains(".") && !proximo.getLexema().contains("."))) {
                        ErroSemantico er = new ErroSemantico();
                        er.tipo = anterior;
                        er.tipoDoErro = "Expressao condicional errada";
                        erro.add(er);

                        ErroSemantico e = new ErroSemantico();
                        e.tipo = proximo;
                        e.tipoDoErro = "Expressao condicional errada";
                        erro.add(e);
                        cont++;
                    }
                } else if (anterior.getTipoToken().toString().equals("PALAVRA_RESERVADA_TRUE") || proximo.getTipoToken().toString().equals("PALAVRA_RESERVADA_FALSE") || anterior.getTipoToken().toString().equals("PALAVRA_RESERVADA_FALSE") || proximo.getTipoToken().toString().equals("PALAVRA_RESERVADA_TRUE")) {
                    ErroSemantico er = new ErroSemantico();
                    er.tipo = anterior;
                    er.tipoDoErro = "Expressao condicional errada";
                    erro.add(er);

                    ErroSemantico e = new ErroSemantico();
                    e.tipo = proximo;
                    e.tipoDoErro = "Expressao condicional errada";
                    erro.add(e);
                    cont++;
                } else {
                    //  System.out.println("asdasdv");
                    ErroSemantico er = new ErroSemantico();
                    er.tipo = anterior;
                    er.tipoDoErro = "Expressao em IF Não foi declarado em Var";
                    erro.add(er);

                    ErroSemantico e = new ErroSemantico();
                    e.tipo = proximo;
                    e.tipoDoErro = "Expressao em IF Não foi declarado em Var";
                    erro.add(e);
                    cont++;
                }
            }
        } else if (primeiroTipo == null && cont != 0) {
            //System.out.println("3");
            ErroSemantico er = new ErroSemantico();
            er.tipo = anterior;
            er.tipoDoErro = "Expressao em IF Não foi declarado em Var ";
            erro.add(er);
        } else if (segundoTipo == null && cont != 0) {
            //System.out.println("3");
            ErroSemantico er = new ErroSemantico();
            er.tipo = proximo;
            er.tipoDoErro = "Expressao em IF Não foi declarado em Var";
            erro.add(er);
        }
        /*else {
           
            if (!primeiroTipo.foiDeclaradocomo.equals(segundoTipo.foiDeclaradocomo)) {
                ErroSemantico er = new ErroSemantico();
                er.tipo = proximo;
                er.tipoDoErro = "Expressao em IF Não são do msm tipo";
                erro.add(er);

            }
        }*/

    }

    public Listas retornaTokenDeclarado(String nome, List<Listas> a) {
        for (Listas etds : a) {
            String naruto = etds.nome;
            if (naruto.equals(nome)) {
                return etds;
            }
        }
        return null;
    }

    public void incrementadorDescremeta(Token anterior, Token proximo) {
        Listas primeiroTipo = retornaTokenDeclarado(anterior.getLexema(), tabelaSimbolosVariavel);
        Listas segundoTipo = retornaTokenDeclarado(anterior.getLexema(), tabelaSimbolosConst);

        if (primeiroTipo == null && segundoTipo == null) {
            ErroSemantico er = new ErroSemantico();
            er.tipo = anterior;
            er.tipoDoErro = "Variavel não declarada";
            erro.add(er);

        } else {
            if (primeiroTipo != null) {
                if (primeiroTipo.foiDeclaradocomo.equals("string") || primeiroTipo.foiDeclaradocomo.equals("bool")) {
                    ErroSemantico er = new ErroSemantico();
                    er.tipo = anterior;
                    er.tipoDoErro = "Tipo de incremento/decremento não é float/int";
                    erro.add(er);
                }

            } else if (segundoTipo != null) {
                if (segundoTipo.foiDeclaradocomo.equals("string") || segundoTipo.foiDeclaradocomo.equals("bool")) {
                    ErroSemantico er = new ErroSemantico();
                    er.tipo = anterior;
                    er.tipoDoErro = "Tipo de incremento/decremento não é float/int";
                    erro.add(er);
                }
            }
        }

    }

    public void atribuicaoExpressao() {
        int inteiros = 0;
        int Ninteiros = 0;
        int strin = 0;
        int bo = 0;
        int errado = 0;

        int nulo = 0, nulo2 = 0;

        for (Token a : ajudaExpressaoAtribuicao) {
            Listas primeiroTipo = retornaTokenDeclarado(a.getLexema(), tabelaSimbolosVariavel);
            Listas segundoTipo = retornaTokenDeclarado(a.getLexema(), tabelaSimbolosConst);
            Token recebe = a;
            System.out.println(a.getLexema() + " " + a.getLinha() + "\n");
            if (primeiroTipo != null) {
                nulo++;
                if (primeiroTipo.foiDeclaradocomo.equals("int")) {
                    inteiros++;
                } else if (primeiroTipo.foiDeclaradocomo.equals("float")) {
                    Ninteiros++;
                } else if (primeiroTipo.foiDeclaradocomo.equals("string")) {
                    strin++;
                } else if (primeiroTipo.foiDeclaradocomo.equals("bool")) {
                    bo++;
                }
            } else if (segundoTipo != null) {
                nulo2++;
                if (segundoTipo.foiDeclaradocomo.equals("int")) {
                    inteiros++;
                } else if (segundoTipo.foiDeclaradocomo.equals("float")) {
                    Ninteiros++;
                } else if (segundoTipo.foiDeclaradocomo.equals("string")) {
                    strin++;
                } else if (segundoTipo.foiDeclaradocomo.equals("bool")) {
                    bo++;
                }
            } else if (recebe != null) {
                if (recebe.getTipoToken().toString().toString().equals("CADEIA_CARACTERES")) {
                    strin++;

                } else if (recebe.getTipoToken().toString().equals("PALAVRA_RESERVADA_TRUE")) {
                    bo++;
                } else if (recebe.getTipoToken().toString().equals("PALAVRA_RESERVADA_FALSE")) {
                    bo++;
                } else if (recebe.getTipoToken().toString().equals("NUMERO")) {
                    if (recebe.getLexema().contains(".")) {
                        Ninteiros++;
                    } else {
                        inteiros++;
                    }
                }
            }
        }
        //System.out.println("inteiro "+ inteiros);
        //System.out.println(" não inteiro "+ Ninteiros);
        if ((inteiros != 0) && ((Ninteiros > 0) || strin > 0 && bo > 0)) {
            ErroSemantico er = new ErroSemantico();
            er.tipo = ajudaExpressaoAtribuicao.get(0);
            er.tipoDoErro = "Os tipos não são iguais ";
            erro.add(er);
        } else if (Ninteiros != 0 && (inteiros > 0 || strin > 0 || bo > 0)) {
            ErroSemantico er = new ErroSemantico();
            er.tipo = ajudaExpressaoAtribuicao.get(0);
            er.tipoDoErro = "Os tipos não são iguais ";
            erro.add(er);
        } else if (strin != 0 && (bo > 0 || inteiros > 0 || Ninteiros > 0)) {

            ErroSemantico er = new ErroSemantico();
            er.tipo = ajudaExpressaoAtribuicao.get(0);
            er.tipoDoErro = "Os tipos não são iguais ";
            erro.add(er);

        } else if (bo != 0 && (strin > 0 || inteiros > 0 || Ninteiros > 0)) {
            ErroSemantico er = new ErroSemantico();
            er.tipo = ajudaExpressaoAtribuicao.get(0);
            er.tipoDoErro = "Os tipos não  são iguais  ";
            erro.add(er);
        }
        ajudaExpressaoAtribuicao.clear();
    }

    public void addAtriicaoExpressao(Token x) {
        if (x.getTipoToken().toString().equals("DELIMITADOR_PONTO_VIRGULA")) {
            ajudaExpressaoAtribuicao.clear();
        } else {
            ajudaExpressaoAtribuicao.add(x);
        }

    }

    public void removeAtriicaoExpressao(Token x) {

        ajudaExpressaoAtribuicao.remove(ajudaExpressaoAtribuicao.size() - 1);

    }

    public void incrementaStart() {
        qtdStart++;
    }

    public void verificaStart() {
        ErroSemantico er = new ErroSemantico();
        if (qtdStart == 0) {
            er.tipoDoErro = "Método start não declarado";
            erro.add(er);
        } else if (qtdStart > 1) {
            er.tipoDoErro = "Múltiplas ocorrências do método start foram encontradas: " + qtdStart + " ocorrências";
            erro.add(er);
        }
    }

    public void variavelNaoDeclaradoErro(Token o) {
        boolean x = JafoiDeclarado(o.getLexema(), tabelaSimbolosConst);
        boolean y = JafoiDeclarado(o.getLexema(), tabelaSimbolosVariavel);

        if (x != true && y != true) {
            ErroSemantico er = new ErroSemantico();
            er.tipo = o;
            er.tipoDoErro = "Variavel não declarada ";
            erro.add(er);
        }

    }

    public void ajudaVetores() {
        int inteiros = 0;
        int Ninteiros = 0;
        int strin = 0;
        int bo = 0;
        int errado = 0;

        int nulo = 0, nulo2 = 0;

        for (Token a : ajudaVetores) {
            Listas primeiroTipo = retornaTokenDeclarado(a.getLexema(), tabelaSimbolosVariavel);
            Listas segundoTipo = retornaTokenDeclarado(a.getLexema(), tabelaSimbolosConst);
            Token recebe = a;
            //System.out.println(a.getLexema()+" "+a.getLinha()+ "\n");
            if (primeiroTipo != null) {
                nulo++;
                if (primeiroTipo.foiDeclaradocomo.equals("int")) {
                    inteiros++;
                } else if (primeiroTipo.foiDeclaradocomo.equals("float")) {
                    Ninteiros++;
                } else if (primeiroTipo.foiDeclaradocomo.equals("string")) {
                    strin++;
                } else if (primeiroTipo.foiDeclaradocomo.equals("bool")) {
                    bo++;
                }
            } else if (segundoTipo != null) {
                nulo2++;
                if (segundoTipo.foiDeclaradocomo.equals("int")) {
                    inteiros++;
                } else if (segundoTipo.foiDeclaradocomo.equals("float")) {
                    Ninteiros++;
                } else if (segundoTipo.foiDeclaradocomo.equals("string")) {
                    strin++;
                } else if (segundoTipo.foiDeclaradocomo.equals("bool")) {
                    bo++;
                }
            } else if (recebe != null) {
                if (recebe.getTipoToken().toString().toString().equals("CADEIA_CARACTERES")) {
                    strin++;

                } else if (recebe.getTipoToken().toString().equals("PALAVRA_RESERVADA_TRUE")) {
                    bo++;
                } else if (recebe.getTipoToken().toString().equals("PALAVRA_RESERVADA_FALSE")) {
                    bo++;
                } else if (recebe.getTipoToken().toString().equals("NUMERO")) {
                    if (recebe.getLexema().contains(".")) {
                        Ninteiros++;
                    } else {
                        inteiros++;
                    }
                }
            }
        }
        // System.out.println("inteiro "+ inteiros);
        //  System.out.println("não inteiro "+ Ninteiros);
        if (Ninteiros > 0 || strin > 0 || bo > 0) {

            ErroSemantico er = new ErroSemantico();
            er.tipo = ajudaVetores.get(0);
            er.tipoDoErro = "Em vetor só aceita acesso por um int ";
            erro.add(er);
        }
        ajudaVetores.clear();
    }

    public void addVetores(Token x) {
        ajudaVetores.add(x);

    }

}
