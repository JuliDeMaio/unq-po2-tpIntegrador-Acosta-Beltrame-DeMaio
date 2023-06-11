package ar.edu.unq.po2.muestraExceptions;

	/**
	 * 
	 * @author Acosta, Federico
	 * @note Esta clase tiene como objetivo modelar la superclase de las Excepciones de Muestra.
	 * @see Muestra
	 */

@SuppressWarnings("serial")
public class MuestraException extends Exception{
	
	public MuestraException(String string){
		super(string);
	}
}
