package ar.edu.unq.po2.muestraExceptions;

public class MuestraEstaVotadaPorExpertosException extends MuestraException {
	
	public MuestraEstaVotadaPorExpertosException() {
		super("Esta muestra ya fue votada por expertos.");
	}
}
