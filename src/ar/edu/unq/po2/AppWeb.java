package ar.edu.unq.po2;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.po2.filtros.Filtro;

public class AppWeb {
	
	private Set<Muestra> muestras;

	public AppWeb() {
	    this.setMuestras(new HashSet<Muestra>());
	}
	
	public Set<Muestra> getMuestras() {
		return this.muestras;
	}
	
	private void setMuestras(Set<Muestra> muestras) {
		this.muestras = muestras;
	}

	public void guardarMuestra(Muestra muestra) {
		this.getMuestras().add(muestra);
	}
	
	public void eliminarMuestra(Muestra muestra) {
		this.getMuestras().remove(muestra);
	}

	public Set<Muestra> filtrarMuestras(Set<Muestra> muestras, Filtro filtro) {
		return (filtro.filtrarMuestras(muestras));
	}

	public Set<Muestra> muestrasDeZonaDeCobertura(Set<Muestra> muestras, ZonaDeCobertura zonaDeCobertura) {
		return Set.copyOf(muestras.stream()
								  .filter(m -> m.seTomoDentroDeZonaDeCobertura(zonaDeCobertura))
								  .toList());
	}
}