package ar.edu.unq.po2.usuarioExceptions;

/**
 * 
 * @author De Maio, Julian
 *
 */

public class UsuarioNoEsOpinionUnicaException extends UsuarioException{

	public UsuarioNoEsOpinionUnicaException() {
		super("Usted ya ha emitido una opinión sobre esta muestra.");
	}

}
