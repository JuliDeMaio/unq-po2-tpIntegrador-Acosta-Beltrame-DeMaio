package ar.edu.unq.po2.usuarioExceptions;

/**
 * 
 * @author De Maio, Julian
 *
 */

public class UsuarioEsMuestraVerificadaException extends UsuarioException{

	public UsuarioEsMuestraVerificadaException() {
		super("La muestra ya está verificada, nadie puede opinar sobre la misma.");
	}

}
