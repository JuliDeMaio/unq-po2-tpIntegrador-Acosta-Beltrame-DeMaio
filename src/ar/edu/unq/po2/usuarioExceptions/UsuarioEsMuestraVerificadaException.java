package ar.edu.unq.po2.usuarioExceptions;

	/**
	 * 
	 * @author De Maio, Julian
	 * 		   Beltrame, Franco
	 * @note Esta clase tiene como objetivo modelar la Excepcion de cuando un Usuario quiere emitir una opinion sobre
	 * 			una Muestra verificada.
	 * @see Usuario, UsuarioException
	 */

@SuppressWarnings("serial")
public class UsuarioEsMuestraVerificadaException extends UsuarioException{

	public UsuarioEsMuestraVerificadaException() {
		super("La muestra ya está verificada, nadie puede opinar sobre la misma.");
	}

}
