package ar.edu.unq.po2;

import java.util.HashSet;
import java.util.Set;

public final class AppWeb {
	
	private static AppWeb instance;
	private Set<Muestra> muestras;

	private AppWeb() {
	    this.setMuestras(new HashSet<Muestra>());
	}
	
	public Set<Muestra> getMuestras() {
		return this.muestras;
	}
	
	private void setMuestras(Set<Muestra> muestras) {
		this.muestras = muestras;
	}
	
	public static AppWeb getInstance() {
	    if (instance == null) {
	        instance = new AppWeb();
	    }
	    return instance;
	}

	public void guardarMuestra(Muestra muestra) {
		this.getMuestras().add(muestra);
	}

	public void reiniciarApp() {
		this.getMuestras().clear();
	}
}