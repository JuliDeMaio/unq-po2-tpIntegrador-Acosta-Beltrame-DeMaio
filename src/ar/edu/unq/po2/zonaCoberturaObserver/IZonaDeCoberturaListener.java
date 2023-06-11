package ar.edu.unq.po2.zonaCoberturaObserver;

import ar.edu.unq.po2.Muestra;

	/**
	 * 
	 * @author Acosta, Federico
	 * 		   Beltrame, Franco
	 *
	 * @note Esta Interfaz tiene como objetivo tipar a los Listeners de ZonaDeCobertura, indicando asi los mensajes
	 * 			que deberian responder.
	 * @see ZonaDeCobertura, Organizacion
	 * @DesignPattern Observer <<Listener>>
	 */
public interface IZonaDeCoberturaListener {
	
	public void muestraSubida(ZonaDeCobertura zonaDeCobertura, Muestra muestra);
	public void muestraValidada(ZonaDeCobertura zonaDeCobertura, Muestra muestra);
}
