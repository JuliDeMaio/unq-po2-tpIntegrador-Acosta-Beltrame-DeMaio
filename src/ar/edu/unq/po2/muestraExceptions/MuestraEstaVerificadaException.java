package ar.edu.unq.po2.muestraExceptions;

public class MuestraEstaVerificadaException extends MuestraException {

	public MuestraEstaVerificadaException() {
		super("Esta muestra ya esta verificada, no puede ser modificada.");
	}
}
