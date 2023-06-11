package ar.edu.unq.po2.filtros;

import java.util.Set;

import ar.edu.unq.po2.Muestra;

	/**
	 * 
	 * @author Acosta, Federico
	 * @note Esta clase tiene como objetivo modelar la clase abstracta de la que heredan los Filtros, partes del
	 * 			Patron Composite.
	 * @see IBusqueda, And, Or
	 * @DesignPattern Composite <<Leaf>>
	 */
public abstract class Filtro implements IBusqueda {

	/**
	 * @DesignPattern Template Method - Template
	 */
	@Override	
	public Set<Muestra> filtrarMuestras(Set<Muestra> muestras) {
		return Set.copyOf(muestras.stream()
						.filter(m -> pasaElFiltro(m))
						.toList());	
	}
	
	/**
	 * @DesignPattern Template Method - Primitive Operation
	 */
	abstract public boolean pasaElFiltro(Muestra muestra);
}
