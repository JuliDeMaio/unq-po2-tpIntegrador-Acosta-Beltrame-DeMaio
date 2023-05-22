package ar.edu.unq.po2.usuarioExceptions;

/**
 * 
 * @author De Maio, Julian
 *
 */

public class UsuarioEsDueñoDeLaMuestraException extends UsuarioException{

	public UsuarioEsDueñoDeLaMuestraException() {
		super("Usted es el creador de la muestra, no puede opinar sobre ella.");
	}

}
