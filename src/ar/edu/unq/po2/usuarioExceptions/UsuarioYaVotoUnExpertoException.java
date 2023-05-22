package ar.edu.unq.po2.usuarioExceptions;

/**
 * 
 * @author De Maio, Julian
 *
 */

public class UsuarioYaVotoUnExpertoException extends UsuarioException{

	public UsuarioYaVotoUnExpertoException() {
		super("La muestra cuenta con la opinion de un experto, usted no cuenta con la categoria necesaria para opinar sobre ella.");
	}

}
