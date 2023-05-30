package ar.edu.unq.po2.filtros;

import java.util.Set;

import ar.edu.unq.po2.Muestra;

	/**
	 * 
	 * @author Acosta, Federico
	 * 
	 * @see 
	 * 
	 * Rol: <<Leaf>>
	 */
public abstract class Filtro implements IBusqueda {

	@Override	
	public Set<Muestra> filtrarMuestras(Set<Muestra> muestras) {
		return Set.copyOf(muestras.stream()
						.filter(m -> pasaElFiltro(m))
						.toList());	
	}
	
	abstract public boolean pasaElFiltro(Muestra muestra);
}
