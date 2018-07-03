package producoes.core;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.blocoConstante.DeclaracaoDeConst;
import producoes.blocoStruct.DeclaracaoDeStruct;
import producoes.blocoVariaveis.DeclaracaoDeVar;
import producoes.declaracaoFuncaoProcedimento.DeclaracaoDeFuncao;
import producoes.declaracaoFuncaoProcedimento.DeclaracaoDeInicio;
import producoes.declaracaoFuncaoProcedimento.DeclaracaoDeProcedimento;
import producoes.declaracaoTipo.DeclaracaoDeTypedef;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class Declaracao extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new Declaracao();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (DeclaracaoDeFuncao.getInstancia().analisar(gerenciadorToken)) {
                return true;
            } else if (DeclaracaoDeProcedimento.getInstancia().analisar(gerenciadorToken)) {
                return true;
            } else if (DeclaracaoDeInicio.getInstancia().analisar(gerenciadorToken)) {
                return true;
            } else if (DeclaracaoDeVar.getInstancia().analisar(gerenciadorToken)) {
                return true;
            } else if (DeclaracaoDeConst.getInstancia().analisar(gerenciadorToken)) {
                return true;
            } else if (DeclaracaoDeStruct.getInstancia().analisar(gerenciadorToken)) {
                return true;
            } else if (DeclaracaoDeTypedef.getInstancia().analisar(gerenciadorToken)) {
                return true;
            }

        }
        
        return false;

    }

    @Override
    protected void gerarFirst() {
        // const, function, procedure, start, typedef, var
        first.add(TipoToken.PALAVRA_RESERVADA_CONST);
        first.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
        first.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
        first.add(TipoToken.PALAVRA_RESERVADA_START);
        first.add(TipoToken.PALAVRA_RESERVADA_VAR);
        first.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);

    }

    @Override
    protected void gerarFollow() {
        // const, function, procedure, start, struct, typedef, var, $
        follow.add(TipoToken.EOF);
        follow.add(TipoToken.PALAVRA_RESERVADA_CONST);
        follow.add(TipoToken.PALAVRA_RESERVADA_FUNCTION);
        follow.add(TipoToken.PALAVRA_RESERVADA_PROCEDURE);
        follow.add(TipoToken.PALAVRA_RESERVADA_START);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        follow.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        follow.add(TipoToken.PALAVRA_RESERVADA_VAR);
    }

}
