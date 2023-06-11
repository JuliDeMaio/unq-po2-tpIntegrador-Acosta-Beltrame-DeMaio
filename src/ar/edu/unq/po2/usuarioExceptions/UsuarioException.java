package ar.edu.unq.po2.usuarioExceptions;

	/**
	 * 
	 * @author De Maio, Julian
	 * @note Esta clase tiene como objetivo modelar la superclase de las Excepciones sobre Usuario.
	 * @see Usuario
	 */

@SuppressWarnings("serial")
public class UsuarioException extends Exception {

	public UsuarioException(String string){
		super(string);
	}
}
