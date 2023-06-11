package ar.edu.unq.po2.muestraExceptions;

	/**
	 * 
	 * @author Acosta, Federico
	 * @note Esta clase tiene como objetivo modelar la Excepcion de cuando una Muestra verificada quiere recalcular el 
	 * 			ResultadoMuestra.
	 * @see Muestra, MuestraException
	 */

@SuppressWarnings("serial")
public class MuestraEstaVerificadaException extends MuestraException {

	public MuestraEstaVerificadaException() {
		super("Esta muestra ya esta verificada, no puede ser modificada.");
	}
}
