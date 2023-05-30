package ar.edu.unq.po2.filtros;

import java.util.Set;

import ar.edu.unq.po2.Muestra;

	/**
	 * 
	 * @author Acosta, Federico
	 * 
	 * @see 
	 * Rol: <<Component>>
	 */
public interface IBusqueda {

	public Set<Muestra> filtrarMuestras(Set<Muestra> muestras);
}
