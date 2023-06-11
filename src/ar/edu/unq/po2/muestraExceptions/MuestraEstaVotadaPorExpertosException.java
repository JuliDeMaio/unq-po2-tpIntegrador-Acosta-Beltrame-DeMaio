package ar.edu.unq.po2.muestraExceptions;

	/**
	 * 
	 * @author Acosta, Federico
	 * @note Esta clase tiene como objetivo modelar la Excepcion de cuando una Muestra votada por expertos quiere realizar el 
	 * 			double-dispatch a partir del voto de un Usuario basico.
	 * @see Muestra, MuestraException
	 */

@SuppressWarnings("serial")
public class MuestraEstaVotadaPorExpertosException extends MuestraException {
	
	public MuestraEstaVotadaPorExpertosException() {
		super("Esta muestra ya fue votada por expertos.");
	}
}
