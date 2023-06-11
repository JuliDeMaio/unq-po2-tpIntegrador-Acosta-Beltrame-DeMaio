package ar.edu.unq.po2.zonaCoberturaObserver;

import java.util.HashSet;
import java.util.Set;

	/**
	 * 
	 * @author Acosta, Federico
	 * @note Esta clase tiene como objetivo tipar y asignar los mensajes default
	 * 		para el observer implementado.
	 * @see ZonaDeCobertura
	 * @DesignPattern Observer <<Observable>>
	 */
public abstract class Subject {
	
	private Set<IZonaDeCoberturaListener> listeners;
	
	public Subject() {
		super();
		this.setListeners(new HashSet<IZonaDeCoberturaListener>());
	}
	
	public Set<IZonaDeCoberturaListener> getListeners() {
		return listeners;
	}
	
	private void setListeners(Set<IZonaDeCoberturaListener> listeners) {
		this.listeners = listeners;
	}
	
	public void addObserver(IZonaDeCoberturaListener listener) {		this.getListeners().add(listener);
	}
	
	public void removeObserver(IZonaDeCoberturaListener listener) {
		this.getListeners().remove(listener);
	}
}