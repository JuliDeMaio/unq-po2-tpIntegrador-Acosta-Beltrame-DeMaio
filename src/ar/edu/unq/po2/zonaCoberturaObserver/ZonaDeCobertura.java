package ar.edu.unq.po2.zonaCoberturaObserver;

import java.util.Set;

import ar.edu.unq.po2.Muestra;
import ar.edu.unq.po2.Ubicacion;

	/**
	 * 
	 * @author Acosta, Federico
	 * 		   De Maio, Julian
	 * @note Esta clase tiene como objetivo modelar una ZonaDeCobertura, como parte del Patron Observer.
	 * @see IZonaDeCoberturaListener, Subject
	 * @DesignPattern Observer <<ConcreteSubject/ConcreteObservable>>
	 */
public class ZonaDeCobertura extends Subject {
	
	private String nombre;
	private double radioKm;
	
	private Ubicacion epicentro;

	public ZonaDeCobertura(String nombre, double radioKm, Ubicacion epicentro) {
		super();
		this.setNombre(nombre);
		this.setRadioKm(radioKm);
		this.setEpicentro(epicentro);
	}

	public String getNombre() {
		return nombre;
	}

	public double getRadioKm() {
		return radioKm;
	}	

	public Ubicacion getEpicentro() {
		return epicentro;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	private void setRadioKm(double radioKm) {
		this.radioKm = radioKm;
	}
	
	private void setEpicentro(Ubicacion epicentro) {
		this.epicentro = epicentro;
	}
	
	public void notificarMuestraSubida(Muestra muestra) {
		this.getListeners()
			.stream()
			.forEach(l -> l.muestraSubida(this, muestra));
	}
	
	public void notificarMuestraValidada(Muestra muestra) {
		this.getListeners()
			.stream()
			.forEach(l -> l.muestraValidada(this, muestra));
	}

	public Set<ZonaDeCobertura> zonasQueIntersecan(Set<ZonaDeCobertura> zonasAFiltrar) {
		return Set.copyOf(zonasAFiltrar.stream()
			      .filter(z -> this.intersecaCon(z))
			      .toList());
	}

	private boolean intersecaCon(ZonaDeCobertura zonaDeCobertura) {
		return (this.getEpicentro().distanciaEntre(zonaDeCobertura.getEpicentro()))
				<
			   (this.getRadioKm() + zonaDeCobertura.getRadioKm());
	}
}