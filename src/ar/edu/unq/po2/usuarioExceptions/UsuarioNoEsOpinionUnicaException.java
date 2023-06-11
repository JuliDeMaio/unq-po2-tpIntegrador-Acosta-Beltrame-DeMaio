package ar.edu.unq.po2.usuarioExceptions;

	/**
	 * 
	 * @author De Maio, Julian
	 * @note Esta clase tiene como objetivo modelar la Excepcion de cuando un Usuario quiere emitir una Opinion por segunda
	 * 			vez sobre una misma Muestra.
	 * @see Usuario, UsuarioException
	 */

@SuppressWarnings("serial")
public class UsuarioNoEsOpinionUnicaException extends UsuarioException{

	public UsuarioNoEsOpinionUnicaException() {
		super("Usted ya ha emitido una opinión sobre esta muestra.");
	}
}
