package ar.edu.unq.po2;

import java.util.HashSet;
import java.util.Set;

public class ZonaDeCobertura implements ISubject{
	
	private String nombre;
	private double radioKm;
	private Set<IZonaDeCoberturaListener> listeners;
	private Ubicacion epicentro;

	public ZonaDeCobertura(String nombre, double radioKm, Ubicacion epicentro) {
		super();
		this.setNombre(nombre);
		this.setRadioKm(radioKm);
		this.setListeners(new HashSet<IZonaDeCoberturaListener>());
		this.setEpicentro(epicentro);
	}

	public String getNombre() {
		return nombre;
	}

	public double getRadioKm() {
		return radioKm;
	}

	public Set<IZonaDeCoberturaListener> getListeners() {
		return listeners;
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
	
	private void setListeners(Set<IZonaDeCoberturaListener> listeners) {
		this.listeners = listeners;
	}
	
	private void setEpicentro(Ubicacion epicentro) {
		this.epicentro = epicentro;
	}

	@Override
	public void notificarMuestraSubida(Muestra muestra) {
		this.getListeners()
			.stream()
			.forEach(l -> l.muestraSubida(this, muestra));
	}

	@Override
	public void addObserver(IZonaDeCoberturaListener listener) {
		this.getListeners().add(listener);
	}

	@Override
	public void removeObserver(IZonaDeCoberturaListener listener) {
		this.getListeners().remove(listener);
	}

	@Override
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
