package ar.edu.unq.po2.usuarioExceptions;

	/**
	 * 
	 * @author De Maio, Julian
	 * @note Esta clase tiene como objetivo modelar la Excepcion de cuando un Usuario quiere emitir una Opinion en una Muestra
	 * 			en la cual ya han votado Usuarios expertos.
	 * @see Usuario, UsuarioException
	 */

@SuppressWarnings("serial")
public class UsuarioYaVotoUnExpertoException extends UsuarioException{

	public UsuarioYaVotoUnExpertoException() {
		super("La muestra cuenta con la opinion de un experto, usted no cuenta con la categoria necesaria para opinar sobre ella.");
	}

}
