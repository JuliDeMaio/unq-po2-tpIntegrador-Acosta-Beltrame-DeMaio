package ar.edu.unq.po2.usuarioExceptions;

	/**
	 * 
	 * @author De Maio, Julian
	 * @note Esta clase tiene como objetivo modelar la Excepcion de cuando un Usuario quiere emitir una opinion que el
	 * 			mismo subio.
	 * @see Usuario, UsuarioException
	 */

@SuppressWarnings("serial")
public class UsuarioEsDueñoDeLaMuestraException extends UsuarioException{

	public UsuarioEsDueñoDeLaMuestraException() {
		super("Usted es el creador de la muestra, no puede opinar sobre ella.");
	}

}
