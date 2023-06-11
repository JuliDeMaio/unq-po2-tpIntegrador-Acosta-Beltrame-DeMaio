package ar.edu.unq.po2.filtros;

import java.util.Set;

import ar.edu.unq.po2.Muestra;

	/**	
	 * 
	 * @author De Maio, Julian
	 * @note Esta interfaz tiene como objetivo indicar el mensaje estandar para todos los Filtros miembros del Patron
	 * 			Composite.
	 * @see Filtro, IBusqueda, And, Or
	 * @DesignPattern Composite <<Component>>
	 */
public interface IBusqueda {

	public Set<Muestra> filtrarMuestras(Set<Muestra> muestras);
}
