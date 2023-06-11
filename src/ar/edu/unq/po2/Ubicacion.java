package ar.edu.unq.po2;

import java.util.List;
import java.util.Set;

import ar.edu.unq.po2.zonaCoberturaObserver.ZonaDeCobertura;

/**
	 * @author Beltrame, Franco
	 * 		   Acosta, Federico
	 * 		   De Maio, Julian
	 * @see ZonaDeCobertura, Muestra
	 * @note Esta clase tiene como objetivo modelar una Ubicacion del sistema.
	 */
public class Ubicacion {

	private double latitud;
	private double longitud;
	
	public Ubicacion(double latitud, double longitud) {
		super();
		this.setLatitud(latitud); 
		this.setLongitud(longitud);
	}

	public double getLatitud() {
		return latitud;
	}
	
	public double getLongitud() {
		return longitud;
	}
	
	private void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
	private void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double distanciaEntre(Ubicacion otraUbicacion) {
		double distanciaLatitud = Math.abs(this.getLatitud() - otraUbicacion.getLatitud());
		double distanciaLongitud = Math.abs(this.getLongitud() - otraUbicacion.getLongitud());
		return(distanciaLatitud + distanciaLongitud);
	}
	
	public boolean estaDentroDeLaZonaDeCobertura(ZonaDeCobertura zona, Muestra muestra) {
		return(zona.getRadioKm() >= muestra.getUbicacion().distanciaEntre(zona.getEpicentro()));
	}

	public List<Ubicacion> ubicacionesAMenosDeKilometros(List<Ubicacion> ubicaciones, double distancia) {
		return (ubicaciones.stream()
				   		   .filter(u -> u.estaAMenosDe(distancia, this))
				   		   .toList());
	}

	private boolean estaAMenosDe(double distancia, Ubicacion ubicacion) {
		return distanciaEntre(ubicacion) < distancia;
	}

	public Set<Muestra> muestrasAMenosDeKilometros(Set<Muestra> muestras, double distancia) {
		return (Set.copyOf(muestras.stream()
						.filter(m -> m.getUbicacion().estaAMenosDe(distancia, this))
						.toList()));
	}
}
