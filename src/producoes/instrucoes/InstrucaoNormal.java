/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.instrucoes;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.blocoStruct.DeclaracaoDeStruct;
import producoes.precedenciaOperadores.OperacaoDeAtribuicao;
import producoes.printScan.Print;
import producoes.printScan.Scan;
import semantico.TabelaSimbolos;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class InstrucaoNormal extends RegraProducao {

    
   
    public static RegraProducao getInstancia() {
        return new InstrucaoNormal();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (OperacaoDeAtribuicao.getInstancia().analisar(gerenciadorToken)) {
                if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_PONTO_VIRGULA, true)) {
                    gerenciadorToken.ts.addAtriicaoExpressao(gerenciadorToken.getAnteriorToken());
                    gerenciadorToken.ts.atribuicaoExpressao();
                    System.out.println(gerenciadorToken.getAnteriorToken().getLexema());
                    return true;
                }
            } else if (DeclaracaoDeStruct.getInstancia().analisar(gerenciadorToken)) {
                //System.out.println("\n\n asuhauhsua");
                if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_PONTO_VIRGULA, true)) {
                    return true;
                }
            } else if (InstrucaoDeRetorno.getInstancia().analisar(gerenciadorToken)) {
                if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_PONTO_VIRGULA, true)) {
                    return true;
                }
            } else if (Print.getInstancia().analisar(gerenciadorToken)) {
                if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_PONTO_VIRGULA, true)) {
                    return true;
                }
            } else if (Scan.getInstancia().analisar(gerenciadorToken)) {
                if (verificarToken(gerenciadorToken, TipoToken.DELIMITADOR_PONTO_VIRGULA, true)) {
                    return true;
                }
            }
        }

        return false;

    }

    @Override
    protected void gerarFirst() {
        //  --, !, ( , ++ , CadeiadeCaracter, Digitos, false, identificador, print, return, scan, struct, true 
        first.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
        first.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        first.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        first.add(TipoToken.CADEIA_CARACTERES);
        first.add(TipoToken.NUMERO);
        first.add(TipoToken.IDENTIFICADOR);
        first.add(TipoToken.PALAVRA_RESERVADA_FALSE);
        first.add(TipoToken.PALAVRA_RESERVADA_TRUE);
        first.add(TipoToken.PALAVRA_RESERVADA_PRINT);
        first.add(TipoToken.PALAVRA_RESERVADA_SCAN);
        first.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
        first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        first.add(TipoToken.PALAVRA_RESERVADA_RETURN);
    }

    @Override
    protected void gerarFollow() {
        //--, !, ( , ++, CadeiaDeCaracteres, Digitos, false, identificador, print, return , scan, struct, true, typdef,
        //var, while, }
        follow.add(TipoToken.OPERADOR_ARITIMETICO_DECREMENTO);
        follow.add(TipoToken.OPERADOR_ARITIMETICO_INCREMENTO);
        follow.add(TipoToken.DELIMITADOR_ABRE_PARENTESES);
        follow.add(TipoToken.CADEIA_CARACTERES);
        follow.add(TipoToken.NUMERO);
        follow.add(TipoToken.IDENTIFICADOR);
        follow.add(TipoToken.PALAVRA_RESERVADA_FALSE);
        follow.add(TipoToken.PALAVRA_RESERVADA_TRUE);
        follow.add(TipoToken.PALAVRA_RESERVADA_PRINT);
        follow.add(TipoToken.PALAVRA_RESERVADA_RETURN);
        follow.add(TipoToken.PALAVRA_RESERVADA_SCAN);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        follow.add(TipoToken.PALAVRA_RESERVADA_VAR);
        follow.add(TipoToken.PALAVRA_RESERVADA_WHILE);
        follow.add(TipoToken.OPERADOR_LOGICO_EXCLAMACAO_NEGADO);
        follow.add(TipoToken.PALAVRA_RESERVADA_TYPEDEF);
        follow.add(TipoToken.PALAVRA_RESERVADA_IF);
        follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);
    }

}
