/**
 * 
 */
package producoes.core;

import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class Programa extends RegraProducao{
	
	private static Programa instancia = new Programa();
	
	public static RegraProducao getInstancia() {
		return instancia;
	}

	/* (non-Javadoc)
	 * @see producoes.RegraProducao#analisar()
	 */
	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see producoes.RegraProducao#gerarFirst()
	 */
	@Override
	protected void gerarFirst() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see producoes.RegraProducao#gerarFollow()
	 */
	@Override
	protected void gerarFollow() {
		// TODO Auto-generated method stub
		
	}

}
